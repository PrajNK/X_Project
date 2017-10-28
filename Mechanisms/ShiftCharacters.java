package Mechanisms;
public class ShiftCharacters{
    public static char forward(char c, int sh){ //char, and shift
        char enc = '\0'; //encoded
        
        if(Character.isUpperCase(c)){
            enc = (char)(c + sh);
            while(enc > 90)
                enc -= 26;
        }
        else if(Character.isLowerCase(c)){
            enc = (char)(c + sh);
            while(enc > 122)
                enc -= 26;
        }
        else
            enc = c;
        return enc;
    }
    public static char backward(char c, int sh){
        char dec = '\0';
        
        if(Character.isUpperCase(c)){
            dec = (char)(c - sh);    
            while(dec < 65)
                    dec += 26;
        }
        else if(Character.isLowerCase(c)){
            dec = (char)(c - sh);
            while(dec < 97)
                dec += 26;
        }
        else{
            dec = c; //the same character is added, no shifting
        }
        return dec;
    }
}
