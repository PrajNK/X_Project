package Ciphers;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import Mechanisms.*;
import RepeatFeatures.Repeater;
import RepeatFeatures.Ending;
import File_Functions.*;
public class OneTimePad{
    static BufferedReader br;
    public static void start(){
        System.out.println("\fThe One Time Pad");
        System.out.print("\nPress 'E' to encode, 'D' to decode, and 'A' for About (X to return): ");
        System.out.print(">>: ");
        br = new BufferedReader(new InputStreamReader(System.in));
        char curOper = '\0'; //current operation
        
        try{
            curOper = Character.toUpperCase(br.readLine().charAt(0));
            if(!(curOper == 'E' || curOper == 'D' || curOper == 'A') || (curOper == 'K' || curOper == 'F')) //just a precaution
                return; 
                
            if(curOper == 'X') //to quit
                return;
            
            if(curOper == 'A'){
                printAbt();
                start();
                return;
            }
            
        } catch(Exception e){
            Ending.ender(-1);
        }
        String inp = takeInput();
        int[] shift;
        String fin = "";
        if(curOper == 'E'){
            shift = RandomStream.generate(inp.length());
            fin = encode(inp, shift); //final
            System.out.println("Your processed message is: " + fin);
            String key = "";
            for(int i = 0; i < shift.length; i++)
                key += shift[i] + " ";
            System.out.println("Your key is " + key);
            
        }
        else{ //if decoding, 
            String keyStr = "", cur = ""; int valNo = 0;  //key(String), current, value no./no. of values
            try{
                System.out.print("Enter the random key, with each shift value separated by a space: ");
                keyStr = br.readLine().trim();
            }
            catch(Exception e){
                Ending.ender(-1);
            }
            boolean isNewVal = true; //is new value
            for(int i = 0; i < keyStr.length(); i++){ //to find the number of values
                if(isNewVal && Character.isDigit(keyStr.charAt(i))){
                    valNo++;
                    isNewVal = false; 
                }
                else if(Character.isWhitespace(keyStr.charAt(i)) && !isNewVal){
                    isNewVal = true; 
                }
            }
            if(valNo != 0)
                valNo++; //to account for the last digit
            if(valNo <= inp.length()){
                System.out.println("ERROR: KEY TOO SHORT!!"); 
                Ending.ender(-1);
            }
            shift = new int[valNo]; valNo = 0; //after creating shift, flushing valNo for use below
            keyStr += " "; //for the final value to get added
            for(int i = 0; i < keyStr.length(); i++){ //to fill values of shift[] (getting shifts of key)
                if(Character.isDigit(keyStr.charAt(i))){
                    cur += keyStr.charAt(i);
                }
                else{ 
                    shift[valNo] = Integer.parseInt(cur);
                    cur = "";
                    valNo++;
                }
            }
            fin = decode(inp, shift);
            System.out.println("Your processed message is: " + fin);
        }
        if(Repeater.goAgain())
                start();
        else{
            try{
                br.close();
            } catch(java.io.IOException e){
                Ending.ender(-1);
            }
            return;
        }
    }
    public static String takeInput(){
        System.out.print("Enter your text: ");
        String txt = "";
        try{
            txt = StripSpaces.strip(br.readLine().trim().toUpperCase());
        } catch(Exception e){
            Ending.ender(-1);
        }
        return txt;
    }
    public static String decode(String msg, int[] sh){
        String dec = "";
        try{
            for(int i = 0; i < msg.length(); i++)
                dec += ShiftCharacters.backward(msg.charAt(i), sh[i]);
        } catch(java.lang.ArrayIndexOutOfBoundsException foo){
            Ending.ender(-1);
        } 
        return dec;
          // try-catch added because: ideally, length of sh will be same as length() of msg. but if there is a 
          // difference, it takes the msg length(). if the key is shorter than the msg, encr can't go on, and 
          //it will stop. in the program, this is emulated by throwing an error.
          //EDIT: this has now been thwarted by a condition in start()
    }
    public static String encode(String msg, int[] sh){             //no try-catch needed here because number of 
        String enc = "";                                           //values is known ( = msg.length())
        for(int i = 0; i < msg.length(); i++)
            enc += ShiftCharacters.forward(msg.charAt(i), sh[i]); 
        return enc;
    }
    public static void printAbt(){
        System.out.println("This is one of the truly unbreakable ciphers, provided that the key is not repeatedly used, and that the \nmessage is of a substantial length (small messages like single words can be deciphered). The number of \npossible messages increases exponentially with each character of plaintext, \nso it is impossible to cryptanalyse large messages due to the role of randomness in encryption.");
        try{new Thread().sleep(7000);} catch(Exception e){}; //wait for 7 seconds
    }
}
/*OVERALL TODOS:
 * Fix the problem on line 41 (NullPointerException thrown)
 */    
        
