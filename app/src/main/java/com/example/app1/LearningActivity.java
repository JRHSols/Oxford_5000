package com.example.app1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LearningActivity extends AppCompatActivity {

    public int wordId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        final WordList wordList = new WordList();

        final TextView eng_word_view = findViewById(R.id.eng_word);
        eng_word_view.setText(wordList.WORD_LIST[wordId][wordList.ENGLISH_WORD]);

        final TextView rus_word_view = findViewById(R.id.rus_word);
        rus_word_view.setText(wordList.WORD_LIST[wordId][wordList.RUSSIAN_WORD]);



    }
}