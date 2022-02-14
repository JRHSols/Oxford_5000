package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button runTranslationActivity = (Button) findViewById(R.id.translation);
        runTranslationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TranslationActivity.class);
                //Intent.FLAG_ACTIVITY_CLEAR_TOP;
                startActivity(intent);
            }
        });

        Button runLearningActivity = (Button) findViewById(R.id.learning);
        runLearningActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LearningActivity.class);
                //Intent.FLAG_ACTIVITY_CLEAR_TOP;
                startActivity(intent);
            }
        });

    }

}

/*
        //----------------------------------------------------------------
                    TextView fuckingBug = (TextView) findViewById(R.id.tempKeypad);
                    fuckingBug.setText("");
                    String bugs_1;
                    String bugs_2;
                    for (int i = 0; i<5; i++)
                    {
                        for (int k = 0; k<4; k++)
                        {
                            if (tempKeypad[i][k]!="empty")
                            fuckingBug.append(tempKeypad[i][k] + "\t");
                            else fuckingBug.append("-" + "\t");
                        }
                        fuckingBug.append("\n");
                    }
                    TextView fuckingBuffer = (TextView) findViewById(R.id.bufferKeypad);
                    fuckingBuffer.setText("");
                    for (int i =0; i<keypadButtonBuffer.size(); i++)
                    {
                        fuckingBuffer.append(keypadButtonBuffer.get(i) + "\t");
                    }
         //--------------------------------------------------------------
 */

//https://dajver.blogspot.com/2014/12/android.html
