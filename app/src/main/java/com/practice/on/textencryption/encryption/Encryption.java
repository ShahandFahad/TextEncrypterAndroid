package com.practice.on.textencryption.encryption;

public class Encryption {
/** Encryption */
    public String encryptPlainText(String plainText , int key){
        String encryptedText = "";

        for(int i = 0; i < plainText.length(); i++){
            if(Character.isUpperCase(plainText.charAt(i)) || Character.isLowerCase(plainText.charAt(i))){
//                System.out.println(plainText.charAt(i));
                int alphabetValue = letterValue(plainText.charAt(i));
//                System.out.println(alphabetValue);
                int encryptedValue = caesarAlgorithm(alphabetValue, key);
//                System.out.println(encryptedValue);
                char encryptedCharacter = cipherLetter(encryptedValue);
//                System.out.println(encryptedCharacter);

                // Maintain integrity of each character
                if(Character.isUpperCase(plainText.charAt(i))){
                    encryptedCharacter = Character.toUpperCase(encryptedCharacter);
                } else {
                    encryptedCharacter = Character.toLowerCase(encryptedCharacter);
                }

                // Convert character to String and concat it to encrypted text
                encryptedText += Character.toString(encryptedCharacter);
            } else {
                // If Character is other than alphabet then just concat it with string
                encryptedText += Character.toString(plainText.charAt(i));
            }
        }
        return encryptedText;
    }
/** Caesar’s algorithm */
    private int caesarAlgorithm(int valueOfAlphabet, int key){
        return (valueOfAlphabet + key) % 26;
    }

// Assigning value to each alphabet
    private int letterValue(char alphabet) {
    if (alphabet == 'A' || alphabet == 'a')
    {
        return 0;
    }
    else if (alphabet == 'B' || alphabet == 'b')
    {
        return 1;
    }
    else if (alphabet == 'C' || alphabet == 'c')
    {
        return 2;
    }
    else if (alphabet == 'D' || alphabet == 'd')
    {
        return 3;
    }
    else if (alphabet == 'E' || alphabet == 'e')
    {
        return 4;
    }
    else if (alphabet == 'F' || alphabet == 'f')
    {
        return 5;
    }
    else if (alphabet == 'G' || alphabet == 'g')
    {
        return 6;
    }
    else if (alphabet == 'H' || alphabet == 'h')
    {
        return 7;
    }
    else if (alphabet == 'I' || alphabet == 'i')
    {
        return 8;
    }
    else if (alphabet == 'J' || alphabet == 'j')
    {
        return 9;
    }
    else if (alphabet == 'K' || alphabet == 'k')
    {
        return 10;
    }
    else if (alphabet == 'L' || alphabet == 'l')
    {
        return 11;
    }
    else if (alphabet == 'M' || alphabet == 'm')
    {
        return 12;
    }
    else if (alphabet == 'N' || alphabet == 'n')
    {
        return 13;
    }
    else if (alphabet == 'O' || alphabet == 'o')
    {
        return 14;
    }
    else if (alphabet == 'P' || alphabet == 'p')
    {
        return 15;
    }
    else if (alphabet == 'Q' || alphabet == 'q')
    {
        return 16;
    }
    else if (alphabet == 'R' || alphabet == 'r')
    {
        return 17;
    }
    else if (alphabet == 'S' || alphabet == 's')
    {
        return 18;
    }
    else if (alphabet == 'T' || alphabet == 't')
    {
        return 19;
    }
    else if (alphabet == 'U' || alphabet == 'u')
    {
        return 20;
    }
    else if (alphabet == 'V' || alphabet == 'v')
    {
        return 21;
    }
    else if (alphabet == 'W' || alphabet == 'w')
    {
        return 22;
    }
    else if (alphabet == 'X' || alphabet == 'x')
    {
        return 23;
    }
    else if (alphabet == 'Y' || alphabet == 'y')
    {
        return 24;
    }
    else if (alphabet == 'Z' || alphabet == 'z')
    {
        return 25;
    }
    else
    {
        return 0;
    }
}
// Return alphabet based on valued assigned
    private char cipherLetter(int letterValue) {
        if (letterValue == 0)
        {
            return 'A';
        }
        else if (letterValue == 1)
        {
            return 'B';
        }
        else if (letterValue == 2)
        {
            return 'C';
        }
        else if (letterValue == 3)
        {
            return 'D';
        }
        else if (letterValue == 4)
        {
            return 'E';
        }
        else if (letterValue == 5)
        {
            return 'F';
        }
        else if (letterValue == 6)
        {
            return 'G';
        }
        else if (letterValue == 7)
        {
            return 'H';
        }
        else if (letterValue == 8)
        {
            return 'I';
        }
        else if (letterValue == 9)
        {
            return 'J';
        }
        else if (letterValue == 10)
        {
            return 'K';
        }
        else if (letterValue == 11)
        {
            return 'L';
        }
        else if (letterValue == 12)
        {
            return 'M';
        }
        else if (letterValue == 13)
        {
            return 'N';
        }
        else if (letterValue == 14)
        {
            return 'O';
        }
        else if (letterValue == 15)
        {
            return 'P';
        }
        else if (letterValue == 16)
        {
            return 'Q';
        }
        else if (letterValue == 17)
        {
            return 'R';
        }
        else if (letterValue == 18)
        {
            return 'S';
        }
        else if (letterValue == 19)
        {
            return 'T';
        }
        else if (letterValue == 20)
        {
            return 'U';
        }
        else if (letterValue == 21)
        {
            return 'V';
        }
        else if (letterValue == 22)
        {
            return 'W';
        }
        else if (letterValue == 23)
        {
            return 'X';
        }
        else if (letterValue == 24)
        {
            return 'Y';
        }
        else if (letterValue == 25)
        {
            return 'Z';
        }
        else
        {
            return '\0';
        }
    }
}
