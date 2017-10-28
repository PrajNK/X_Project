package Ciphers;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import Mechanisms.ShiftCharacters;
import Mechanisms.StripSpaces;
import RepeatFeatures.Repeater;
import RepeatFeatures.Ending;
import File_Functions.*;

public class Vigenere{
    static BufferedReader br;
    public static void start(){
        System.out.println("\fThe Vigenere Cipher");
        System.out.print("\nPress 'E' to encode, 'D' to decode, and 'A' for About (X to return): ");
        System.out.print(">>: ");
        br = new BufferedReader(new InputStreamReader(System.in));
        char curOper = '\0'; //current operation
        char fileOrKb = '\0'; //file or keyboard input
        try{
            curOper = Character.toUpperCase(br.readLine().charAt(0));
            if(curOper != 'E' && curOper != 'D' && curOper != 'A') //just a precaution
                return; 
            if(curOper == 'X') //to quit
                return;
            
            if(curOper == 'A')
                printAbt();
            else{
                System.out.print("Choose input type: Press F for File (keyword in first line), or K for Keyboard: ");
                fileOrKb = Character.toUpperCase(br.readLine().charAt(0));
                if(!(fileOrKb == 'F' || fileOrKb == 'K'))
                    Ending.ender(-1); //gives an error if bad input
            }
            
            
        } catch(Exception e){
            Ending.ender(-1);
        }
        String inp = takeInput(fileOrKb);
        if(fileOrKb == 'F'){
            String[] plTxt = ReadFromFile.readFile(inp); //plaintext
            String[] procTxt = new String[plTxt.length - 1]; //processed text - size will be one less than plaintext (keyword is not included)
            if(curOper == 'E')  //above line wasn't working. now it is.
                for(int i = 0, j = 1; i < procTxt.length; i++,j++) // 0th element is keyword
                    procTxt[i] = encode(plTxt[j], plTxt[0]);
            else
                for(int i = 0, j = 1; i < procTxt.length; i++, j++)
                    procTxt[i] = decode(plTxt[j], plTxt[0]);
            System.out.print("Your processed message is: ");    //problem code
            for(int i = 0; i< procTxt.length; i++)              //
                System.out.print(procTxt[i]);                   //
            askStore(plTxt[0], procTxt);                        //
        } 
        else{
            String key = "";
            System.out.print("Enter your keyword: ");
            try{
                key = br.readLine().trim(); //taking keyword from usrin
            } catch(Exception e){
                Ending.ender(-1);
            }  
            String fin = "";
            if(curOper == 'E')
                fin = encode(inp, key); //final
            else
                fin = decode(inp, key);
            System.out.println("Your processed message is: " + fin);
            askStore(key, fin);
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
    public static String takeInput(char inpType){
        /** Returns either the text or the path of the file containing the text. **/
        if(inpType == 'F'){
            System.out.println("Enter the path of your file: ");
            String path = "";
            try{
                path = br.readLine().trim();
            } catch(Exception e){
                Ending.ender(-1);
            }
            return path;
        }
        else{
            System.out.print("Enter your text: ");
            String txt = "";
            try{
                txt = br.readLine().trim();
                
            } catch(Exception e){
                Ending.ender(-1);
            }
            return txt;
        }
    }
    public static String encode(String msg, String keyword){
        msg = StripSpaces.strip(msg);
        String rptKW = ""; //repeated keyword
        
        for(int i = 0; i < msg.length(); i++){ //getting the repeat keyword
            if(i < keyword.length())
                rptKW += keyword.charAt(i);
            else
                rptKW += keyword.charAt(i%keyword.length());
        }
        String enc = "";
        for(int i = 0; i < msg.length(); i++){ //for encoding
            enc += ShiftCharacters.forward(msg.charAt(i), ((int)(rptKW.charAt(i)) - 96));
        }
        return enc;
    }
    public static String decode(String msg, String keyword){
        msg = StripSpaces.strip(msg);
        String rptKW = ""; //repeated keyword
        for(int i = 0; i < msg.length(); i++){ //getting the repeat keyword
            if(i < keyword.length())
                rptKW += keyword.charAt(i);
            else
                rptKW += keyword.charAt(i%keyword.length());
        }
        String dec = "";
        for(int i = 0; i < msg.length(); i++){ //for decoding
            dec += ShiftCharacters.backward(msg.charAt(i), ((int)(rptKW.charAt(i)) - 96));
        }
        return dec;
    }
    public static void askStore(String key, String s){ //asks to store output in a file
        System.out.print("\nDo you want to store the output in a file? (Press Y for Yes, anything else for No): ");
        char decn = '\0'; //decision
        try{
            decn = Character.toUpperCase(br.readLine().charAt(0));
            if(decn == 'Y')
                WriteToFile.writeFile(key, s); //flawed. EDIT: not anymore!
            else
                return;
        } catch(Exception e){
            Ending.ender(-1);
        }
    }
    public static void askStore(String key, String[] s){
        System.out.print("\nDo you want to store the output in a file? (Press Y for Yes, anything else for No): ");
        char decn = '\0'; //decision
        try{
            decn = Character.toUpperCase(br.readLine().charAt(0));
            if(decn == 'Y')
                WriteToFile.writeFile(key, s);
            else
                return;
        } catch(Exception e){
            Ending.ender(-1);
        }
    }
    public static void printAbt(){
        System.out.println("This cipher is historically one of the hardest to crack, and was thought to be perfect until \nrecently. It involves using a keyword and repeating it to match the length of the plaintext, and then shifting each \nletter of the plaintext by the corresponding letter of the keyword. More formally, it involves a 26x26 table, called a \ntabula recta or Vigenére’s Table, filled with letters in increasing shifts. ");
        try{new Thread().sleep(7000);} catch(Exception e){}; //wait for 7 seconds
        start();
    }
}
/*OVERALL TODOs
 * Ask ma'am about line 40
 */

    
