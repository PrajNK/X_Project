package Ciphers;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import File_Functions.*;
import RepeatFeatures.*;

public class BruteForcer{
    static BufferedReader br;
    public static void start(){
        br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\fThe Brute-Forcer Tool");
        System.out.print("Press 'F' to read from file, and 'K' to read from keyboard: ");
        System.out.print(">>: ");
        char fileOrKb = '\0'; 
        
        try{
            fileOrKb = Character.toUpperCase(br.readLine().charAt(0));
        } catch(Exception e){
            e.printStackTrace();
            Ending.ender(-1);
        }
        if(fileOrKb != 'F' && fileOrKb != 'K')
            Ending.ender(-1);
        if(fileOrKb == 'F'){
            String[] inp = getInpFromFile();
            for(int i = 0; i < inp.length; i++){
                System.out.println("Line " + (i+1) + ": ");
                force(inp[i]);
            }
        }
        else{
            System.out.print("\nEnter your message: ");
            String inp = "";
            try{
                inp = br.readLine();
            } catch(Exception e){
                Ending.ender(-1);
            }
            force(inp);
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
    public static void force(String ct){
        ShiftCipher shci = new ShiftCipher();
        for(int i = 1; i < 26; i++){
            System.out.println("Shift: " + i);
            System.out.println(shci.decode(ct, i) + "\n");
        }
    }
    public static String[] getInpFromFile(){
        System.out.print("Enter the path of your file (with extension): ");
        String fpath = ""; //filepath
        String[] cont = null; //content
        try{
            fpath = br.readLine().trim();
            cont = ReadFromFile.readFile(fpath);
        } catch(Exception e){
            Ending.ender(-1);
        }
        return cont;
    }
}
