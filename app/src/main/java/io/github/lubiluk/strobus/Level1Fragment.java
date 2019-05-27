package io.github.lubiluk.strobus;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Level1Fragment extends Fragment {
    final int min = 20;
    final int max = 80;
    final int random = new Random().nextInt((max - min) + 1) + min;

    public Level1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_level1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView seedTextView = view.findViewById(R.id.seed);

        Resources res = getResources();
        String text = res.getString(R.string.seed, random);

        seedTextView.setText(text);

        final TextView lockTextView = view.findViewById(R.id.lock);
        final TextView answerTextView = view.findViewById(R.id.answer);

        final Button button = view.findViewById(R.id.unlock);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    int answer = Integer.valueOf(answerTextView.getText().toString());

                    if (checkAnswer(answer)) {
                        lockTextView.setText(getText(R.string.unlocked));
                    } else {
                        lockTextView.setText(getText(R.string.locked));
                    }
                } catch (Exception e) {
                    lockTextView.setText(getText(R.string.locked));
                }
            }
        });
    }

    boolean checkAnswer(int input) {
        return input == random + 10;
    }
}
