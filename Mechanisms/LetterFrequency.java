package Mechanisms;
public class LetterFrequency{
    public static int[] getLF(String txt){ //checks only capital letters
        int[] freq = new int[27]; //index 0 repesents a, 1 is b and so on. index 26 is for non-letter characters.
        char cur = '\0';
        for(int i = 0; i < txt.length(); i++){
            cur = txt.charAt(i);
            if(!Character.isLetter(cur)){
                freq[26]++;
                continue;
            }
            else if(Character.isUpperCase(cur)){
                freq[cur - 65]++;
                continue;
            }
        }
        return freq;
    }
}
