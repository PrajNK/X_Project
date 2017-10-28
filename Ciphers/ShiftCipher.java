package Ciphers;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import Mechanisms.ShiftCharacters;
import RepeatFeatures.Repeater;
import RepeatFeatures.Ending;
import File_Functions.*;
public class ShiftCipher{
    static BufferedReader br;
    public static void start(){
        System.out.println("\fThe Caesar (Shift) Cipher");
        System.out.print("\nPress 'E' to encode, 'D' to decode, and 'A' for About (X to return): ");
        System.out.print(">>: ");
        br = new BufferedReader(new InputStreamReader(System.in));
        char curOper = '\0'; //current operation
        char fileOrKb = '\0'; //file or keyboard input
        try{
            curOper = Character.toUpperCase(br.readLine().charAt(0));
            if(curOper != 'E' && curOper != 'D' && curOper != 'A') //just a precaution
                return;
                
            if(curOper == 'X')
                return;
            
            if(curOper == 'A'){
                printAbt();
                start(); //recursive
                return;
            }
            else{
                System.out.print("Choose input type: Press F for File, or K for Keyboard: ");
                fileOrKb = Character.toUpperCase(br.readLine().charAt(0));
                if(!(fileOrKb == 'F' || fileOrKb == 'K'))
                    Ending.ender(-1); //gives an error if bad input
            }
            
            
        } catch(Exception e){
            Ending.ender(-1);
        }
        String inp = takeInput(fileOrKb);
        int shift = takeInput(9);
        if(fileOrKb == 'F'){
            String[] plTxt = ReadFromFile.readFile(inp); //plaintext
            String[] procTxt = new String[plTxt.length]; //processed texttext
            if(curOper == 'E')
                for(int i = 0; i < plTxt.length; i++)
                    procTxt[i] = encode(plTxt[i], shift);
            else
                for(int i = 0; i < plTxt.length; i++)
                    procTxt[i] = decode(plTxt[i], shift);
            System.out.print("Your processed message is: ");    
            for(int i = 0; i< procTxt.length; i++)
                System.out.print(procTxt[i]);
            askStore(procTxt);
        } 
        else{
            String fin = "";
            if(curOper == 'E')
                fin = encode(inp, shift); //final
            else
                fin = decode(inp, shift);
            System.out.println("Your processed message is: " + fin);
            askStore(fin);
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
        if(inpType == 'F'){
            System.out.println("Enter the path of your file: ");
            String path = "";
            try{
                path = br.readLine();
            } catch(Exception e){
                Ending.ender(-1);
            }
            return path;
        }
        else{
            System.out.print("Enter your text: ");
            String txt = "";
            try{
                txt = br.readLine();
            } catch(Exception e){
                Ending.ender(-1);
            }
            return txt;
        }
    }
    public static int takeInput(int whatever){ //overloaded
        System.out.print("Enter your shift: ");
        int sh = 0; // shift
        try{
            sh = Integer.parseInt(br.readLine());
        } catch(Exception e){
            Ending.ender(-1);
        }
        return sh;
    }
    public static String encode(String pl, int sh){ //plain, shift
        String enc = "";
        for(int i = 0; i < pl.length(); i++)
            enc += ShiftCharacters.forward(pl.charAt(i), sh);
        return enc;
    }
    public static String decode(String ct, int sh){ //ciphertext and shift
        String dec = "";
        for(int i = 0; i < ct.length(); i++)
            dec += ShiftCharacters.backward(ct.charAt(i), sh);
        return dec;
    }
    public static void askStore(String s){ //asks to store output in a file
        System.out.print("\nDo you want to store the output in a file? (Press Y for Yes, anything else for No): ");
        char decn = '\0'; //decision
        try{
            decn = Character.toUpperCase(br.readLine().charAt(0));
            if(decn == 'Y')
                WriteToFile.writeFile(s);
            else
                return;
        } catch(Exception e){
            Ending.ender(-1);
        }
    }
    public static void askStore(String[] s){
        System.out.print("\nDo you want to store the output in a file? (Press Y for Yes, anything else for No): ");
        char decn = '\0'; //decision
        try{
            decn = Character.toUpperCase(br.readLine().charAt(0));
            if(decn == 'Y')
                for(int i = 0; i < s.length; i++)
                    WriteToFile.writeFile(s[i]);
            else
                return;
        } catch(Exception e){
            Ending.ender(-1);
        }
    }
    public static void printAbt(){
        System.out.println("The simplest of all substitution ciphers, this involves a simple shift of the plain text \nalphabets by a fixed key, to yield an unreadable cipher text. \nIt is said to have been used by Julius Caesar himself (hence the name), \nbut, by modern standards, it is weak and quite easy to crack by simply comparing letter frequencies.");
        try{new Thread().sleep(6000);} catch(Exception e){}; //wait for 3 seconds
        
    }
}
