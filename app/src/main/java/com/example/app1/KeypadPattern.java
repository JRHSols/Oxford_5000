package com.example.app1;

public class KeypadPattern
{
    final static int[][] KEYPAD_TEMPLATE =
            {
                    {0,0,0,0,0}, //0
                    {0,0,0,0,0}, //1
                    {2,0,0,0,0}, //2
                    {2,1,0,0,0}, //3
                    {2,2,0,0,0}, //4
                    {3,2,0,0,0}, //5
                    {3,3,0,0,0}, //6
                    {2,3,2,0,0}, //7
                    {3,2,3,0,0}, //8
                    {3,3,3,0,0}, //9
                    {3,4,3,0,0}, //10
                    {4,3,4,0,0}, //11
                    {4,4,4,0,0}, //12
                    {4,4,4,1,0}, //13
                    {4,3,4,3,0}, //14
                    {4,4,4,3,0}, //15
                    {4,4,4,4,0}, //16
                    {4,4,4,4,1}  //17
            };

    public static String[][] createKeypadPattern(String mixedWord)
    {
        String[][] keypad = {{"empty", "empty", "empty", "empty"},
                             {"empty", "empty", "empty", "empty"},
                             {"empty", "empty", "empty", "empty"},
                             {"empty", "empty", "empty", "empty"},
                             {"empty", "empty", "empty", "empty"}};
        int letterPos = 0;

        for (int i = 0; i < 5; i++)
        {
            for (int k = 0; k < KEYPAD_TEMPLATE[mixedWord.length()][i]; k++)
            {
                keypad[i][k] = String.valueOf(mixedWord.charAt(letterPos));
                letterPos++;
            }
        }
        return keypad;
    }
}
