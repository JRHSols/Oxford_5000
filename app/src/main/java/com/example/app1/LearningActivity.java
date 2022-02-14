package com.example.app1;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LearningActivity extends AppCompatActivity {

    public int wordId = 0;

    MediaPlayer soundPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learning);

        final WordList wordList = new WordList();

        final TextView eng_word_view = findViewById(R.id.eng_word);
        eng_word_view.setText(wordList.WORD_LIST[wordId][wordList.ENGLISH_WORD]);

        final TextView rus_word_view = findViewById(R.id.rus_word);
        rus_word_view.setText(wordList.WORD_LIST[wordId][wordList.RUSSIAN_WORD]);

        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.common_animation);


//-------------------------------------------------------------------------------------
        final ImageButton playSound = (ImageButton) findViewById(R.id.sound);
        playSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileName = wordList.WORD_LIST[wordId][wordList.ENGLISH_WORD] + "_sound";
                int fileId = getResources().getIdentifier(fileName, "raw", getPackageName());
                soundPlayer= MediaPlayer.create(LearningActivity.this, fileId);
                soundPlayer.start();

                playSound.startAnimation(animation);
            }
        });
//-------------------------------------------------------------------------------------
        final Button nextWordButton = (Button) findViewById(R.id.next_word);
        nextWordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    if (wordId < wordList.WORD_LIST.length - 1) wordId++;
                    else wordId = 0;
                    rus_word_view.setText(wordList.WORD_LIST[wordId][wordList.RUSSIAN_WORD]);
                    eng_word_view.setText(wordList.WORD_LIST[wordId][wordList.ENGLISH_WORD]);

                    nextWordButton.startAnimation(animation);
            }
        });
 //-----------------------------------------------------------------------------------------

    }
}