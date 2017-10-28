package Ciphers;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import RepeatFeatures.*;
import Mechanisms.StripSpaces;
import Mechanisms.LetterFrequency;
import File_Functions.*;
public class Cryptanalyser{
    static BufferedReader br;
    static char rem = '\0', repl = '\0';
    public static void start(){
        System.out.println("\fThe PrajNK Cryptanalyser");
        System.out.print("Press 'F' to read from file, and 'K' to read from keyboard: ");
        char fileOrKb = '\0';
        br = new BufferedReader(new InputStreamReader(System.in));
        try{
            fileOrKb = Character.toUpperCase(br.readLine().charAt(0));
        } catch(Exception e){
           Ending.ender(-1);
        }
        if(fileOrKb != 'F' && fileOrKb != 'K')
            Ending.ender(-1);
        
        // getting the message
        String msg = "";
        if(fileOrKb == 'F'){ 
            msg = StripSpaces.strip(getInpFromFile().toUpperCase());
        }
        else{
            try{
                System.out.print("Enter your message: ");
                msg = StripSpaces.strip(br.readLine().toUpperCase());
            } catch(Exception e){
                Ending.ender(-1);
            }
        }
        //now, the part that loops
        
        int[] lf = new int[27];
        inf: for(int h = 0;  ; h++){ //infinte loop; broken inside
            for(int i = 0; i < msg.length(); i++){
                System.out.print(msg.charAt(i));
                if((i+1) % 100 == 0) //assuming 70 characters fit onto a screen
                    System.out.print("\n");
            }
            System.out.println("\n\n1) Replace characters");
            System.out.println("2) Undo previous change");
            System.out.println("3) Correct change");
            System.out.println("4) View current frequency distribution");
            System.out.println("5) Exit");
            System.out.print("\n>>: ");
            int chc = 0;
            try{
                chc = Integer.parseInt(br.readLine());
            } catch(Exception e){
                Ending.ender(-1);
            }
            switch (chc){
                case 1: msg = replaceChars(msg);
                        break;
                case 2: msg = msg.replace(Character.toLowerCase(repl), rem);
                        break;
                case 3: msg = correctChange(msg);
                        break;
                case 4: lf = LetterFrequency.getLF(msg);
                        for(int i = 0; i < 27; i++)
                            System.out.println((char)(i + 65) + " = " + lf[i] + "\n");
                        break;
                        
                case 5: break inf; //labelling infinite
                default: System.out.print("Bad choice!");
                         try{Thread.sleep(1000);}catch(InterruptedException ie){}
                         continue;
            }
            
        }
        System.out.println("Your final message is: \n" + msg);
        askStore(msg);
    }
    public static String replaceChars(String msg){
        System.out.print("\n\nEnter the character to be replaced: ");
        
        try{
            rem = Character.toUpperCase(br.readLine().charAt(0));
            System.out.print("Enter the character to replace it with: ");   //TODO: add a correction feature
            repl = Character.toUpperCase(br.readLine().charAt(0));
            msg = msg.replace(rem, Character.toLowerCase(repl)); //the lower case is to prevent equalisation
        } catch(Exception e){
            Ending.ender(-1);
        }
        return msg;
    }
    public static String correctChange(String msg){
        System.out.print("\n\nEnter the character to be changed (in lowercase): ");
        char rem = '\0', repl = '\0';
        try{
            rem = Character.toLowerCase(br.readLine().charAt(0));
            System.out.print("Enter the character to change it to (in lower case): ");   
            repl = Character.toLowerCase(br.readLine().charAt(0));
            msg = msg.replace(rem, repl); 
        } catch(Exception e){
            Ending.ender(-1);
        }
        return msg;
    }
    public static String getInpFromFile(){
        System.out.print("Enter the path of your file (with extension): ");
        String fpath = ""; //filepath
        String[] cont = null; //content
        try{
            fpath = br.readLine().trim();
            File file = new File(fpath);        // this block of code is to check...
            if(!(file.exists()) || !(file.isFile()))  // ... if the file exists
                throw new Exception();
            cont = ReadFromFile.readFile(fpath);
        } catch(Exception e){
            Ending.ender(-1);
        }
        String fin = "";
        for(int i = 0; i < cont.length; i++)
            fin += cont[i];
        return fin;
    }
    public static void askStore(String s){ //asks to store output in a file
        System.out.print("\nDo you want to store the output in a file? (Press Y for Yes, anything else for No): ");
        char decn = '\0'; //decision
        try{
            decn = Character.toUpperCase(br.readLine().trim().charAt(0));
            if(decn == 'Y')
                WriteToFile.writeFile(s);
            else
                return;
        } catch(Exception e){
            Ending.ender(-1);
        }
    }
}
//finished all todos

            
            