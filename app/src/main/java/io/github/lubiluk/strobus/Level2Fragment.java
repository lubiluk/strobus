package io.github.lubiluk.strobus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Level2Fragment extends Fragment {
    final String filename = "points.txt";
    int points = 0;

    public Level2Fragment() {
        // Required empty public constructor
        createPointsFile();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level2, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView pointsTextView = getView().findViewById(R.id.points);

        points = readPointsFromFile();

        pointsTextView.setText(getResources().getString(R.string.points, points));
    }

    private void writeToFile(String data) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(getActivity().openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private int readPointsFromFile() {
        try {
            FileInputStream inputStream = getContext().openFileInput(filename);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

            return Integer.parseInt(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private void createPointsFile() {
        try {
            // Check file
            File file = new File(getContext().getFilesDir(), filename);

            if (!file.exists()) {
                String fileContents = "0";
                FileOutputStream outputStream;

                outputStream = getContext().openFileOutput(filename, Context.MODE_PRIVATE);
                outputStream.write(fileContents.getBytes());
                outputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
