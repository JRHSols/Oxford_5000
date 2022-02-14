package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class TranslationActivity extends AppCompatActivity {

    //public String rus_word;
    public String engWord;
    public String mixedEngWord;
    //public String tempMixedEngWord;
    public int wordId = 0;

    public String[][] tempKeypad = new String[5][4];
    public ArrayList<Integer> keypadButtonBuffer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);

        final TextView rus_word_view = findViewById(R.id.rus_word);
        final WordList wordList = new WordList();
        rus_word_view.setText(wordList.WORD_LIST[wordId][wordList.RUSSIAN_WORD]);
        engWord = wordList.WORD_LIST[wordId][wordList.ENGLISH_WORD];
        mixedEngWord = mixEngWord(engWord);

        final TextView eng_word_view = findViewById(R.id.eng_word);
        keypadButtonBuffer.clear();
        tempKeypad = (createKeypad (mixedEngWord));
        displayKeypad(tempKeypad);
//-----------------------------------------------------------------------------------------------------------------------------
        Button nextWordButton = (Button) findViewById(R.id.next_word);
        nextWordButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //нажатие не возможно если не использованы все кнопки клавиатуры
                if (engWord.length() == eng_word_view.getText().toString().length() || eng_word_view.getText().toString().length() == 0)
                {
                    if (wordId < wordList.WORD_LIST.length - 1) wordId++;
                    else wordId = 0;
                    rus_word_view.setText("");
                    eng_word_view.setText("");
                    rus_word_view.setText(wordList.WORD_LIST[wordId][wordList.RUSSIAN_WORD]);
                    LinearLayout layout = (LinearLayout) findViewById(R.id.keypad);
                    layout.removeAllViews();
                    keypadButtonBuffer.clear();
                    engWord = wordList.WORD_LIST[wordId][wordList.ENGLISH_WORD];
                    mixedEngWord = mixEngWord(engWord);
                    tempKeypad = createKeypad(mixedEngWord);
                    displayKeypad(tempKeypad);
                    eng_word_view.setTextColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });
//-----------------------------------------------------------------------------------------------------------------------------
        ImageButton backspaceButton = (ImageButton) findViewById(R.id.key_backspace);
        backspaceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (eng_word_view.getText().toString().length()>0)
                {
                    String text = eng_word_view.getText().toString(); //получаю текст из текстбокса
                    eng_word_view.setText(""); //сбрасываю текстбокс
                    String letter = text.substring(text.length()-1); // получаю последний символ в строке текстбокса
                    eng_word_view.setText(text.substring(0, text.length()-1)); //передаю в текстбокс строку с удаленным последним символом
                    eng_word_view.setTextColor(getResources().getColor(R.color.colorWhite));
                    int letterID = keypadButtonBuffer.get(keypadButtonBuffer.size()-1); // получаю АйДи последнего символа в буфере последовательности введенных букв
                    keypadButtonBuffer.remove(keypadButtonBuffer.size()-1); //удаляю последний символ в буффере букв
                    tempKeypad[letterID/10][letterID%10] = letter; //записываю в матрицу букв в нужную позицию удаленную букву
                    displayKeypad(tempKeypad); //вывожу клавиатуру
                }
            }
        });
//-----------------------------------------------------------------------------------------------------------------------------
        ImageButton reloadButton = (ImageButton) findViewById(R.id.key_reload);
        reloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                eng_word_view.setText("");
                LinearLayout layout = (LinearLayout) findViewById(R.id.keypad);
                layout.removeAllViews();
                tempKeypad = createKeypad(mixedEngWord);
                displayKeypad(tempKeypad);
                eng_word_view.setTextColor(getResources().getColor(R.color.colorWhite));
            }
        });
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    public void displayKeypad(String [][] keyPad)
    {
        LinearLayout keyPadLayout = ( LinearLayout) findViewById(R.id.keypad); //инициализируем блок клавиатуры
        keyPadLayout.removeAllViews();
        LayoutInflater ltInflater = getLayoutInflater();

        int rowIndex = 0;
        int columnIndex = 0;
        String[] mask = {"empty", "empty", "empty", "empty"};

        for (String[] row : keyPad)
        {
            if (!Arrays.equals(row, mask))//если строка клавиатуры не пустая
            {
                LinearLayout keypadRow = (LinearLayout) ltInflater.inflate(R.layout.keypad_row_layout, null); //создаем строку клавиатуры
                for (String i : row)
                {
                    if (i != "empty")//если кнопка нужна
                    {
                        LinearLayout keypadButtonLayout = (LinearLayout) ltInflater.inflate(R.layout.keypad_button_layout, null);//создаем ячейку кнопки
                        final Button keypadButton = (Button) keypadButtonLayout.findViewById(R.id.keypad_button); //создаем кнопку
                        keypadButton.setId(rowIndex*10 + columnIndex);
                        keypadButton.setText(i); //записываем в кнопку символ
                        keypadRow.addView(keypadButtonLayout); //загоняем ячейку с кнопкой в строку клавиатуры
                        //-----------------------------------------------------------------------------------
                        keypadButton.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                TextView eng_word_view = findViewById(R.id.eng_word);
                                String temp = (String) eng_word_view.getText();
                                eng_word_view.setText(temp + keypadButton.getText());
                                int ID = keypadButton.getId();
                                keypadButtonBuffer.add(ID);
                                keypadButton.setVisibility(View.GONE);
                                tempKeypad[ID/10][ID%10] = "empty";
                                if (engWord.length()==eng_word_view.getText().toString().length())
                                {
                                    if (engWord.equals(eng_word_view.getText().toString()))
                                        eng_word_view.setTextColor(getResources().getColor(R.color.colorGreen));
                                    else  eng_word_view.setTextColor(getResources().getColor(R.color.colorRed));
                                }
                            }
                        });
                        //-----------------------------------------------------------------------------------
                    }
                    columnIndex++;
                }
                keyPadLayout.addView(keypadRow);//добавляем в клавиатуру строку кнопок
                columnIndex = 0;
                rowIndex++;
            }
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    public String mixEngWord (String word)
    {
        WordMixer Mixer = new WordMixer();//инициализируем миксер
        return Mixer.mixWord(word);
    }
    //-----------------------------------------------------------------------------------------------------------------------------
    public String [][] createKeypad (String mixedWord)
    {
        KeypadPattern mixedWordPattern = new KeypadPattern();//инициализируем создетель паттерна
        return mixedWordPattern.createKeypadPattern(mixedWord);
    }
//-----------------------------------------------------------------------------------------------------------------------------
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

