package com.example.app1;

import java.util.Random;

public class WordMixer
{
    public static String mixWord(String inputWord)
    {
        String result;
        if (inputWord.length()!=1)
        {
            result = inputWord;
            Random random = new Random();
            char a[] = inputWord.toCharArray();
            do {
                // Scramble the letters using the standard Fisher-Yates shuffle,
                for (int i = 0; i < a.length; i++)
                {
                    int j = random.nextInt(a.length);
                    // Swap letters
                    char temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                result = changeFirstLetter(new String( a ), inputWord);
            } while (result.equals(inputWord));
        }
        else result = inputWord;
        return result;
    }

    private static String changeFirstLetter (String result, String word)
    {
        if (result.charAt(0)==word.charAt(0))
        {
            char[] c = result.toCharArray();
            char temp = c[0];
            c[0] = c[1];
            c[1] = temp;
            result = new String(c);
        }
        return result;
    }

}
