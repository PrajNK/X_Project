import Ciphers.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import RepeatFeatures.*;
public class Main{
    public static void main(String[] args){
        System.out.println("\fWelcome to the CiphKit!");
        System.out.println("\n1) The Caesar (Shift) Cipher");
        System.out.println("2) The Vigenere Cipher");
        System.out.println("3) The One-Time Pad");
        System.out.println("4) Brute-Forcer");
        System.out.println("5) The Cryptanalyser");
        System.out.println("6) About");
        System.out.println("7) Exit");
        System.out.print("\n>>: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int chc = 0; //choice
        try{
            chc = Integer.parseInt(br.readLine());
        } catch(Exception e){
            Ending.ender(-1);
        }
        switch(chc){
            case 1: ShiftCipher.start();
                    break;
            case 2: Vigenere.start();
                    break;
            case 3: OneTimePad.start();
                    break;
            case 4: BruteForcer.start();
                    break;
            case 5: Cryptanalyser.start();
                    break;
            case 6: printAbt();
                    main(new String[0]);
            case 7: Ending.ender(0);
            
            default: System.out.print("Bad choice!");
                     try{Thread.sleep(1000);} catch(Exception e){}
                     main(new String[0]);
        }
        main(new String[0]); //will keep on recursing until '7' is pressed
    }
    public static void printAbt(){
        System.out.println("This is a compilation of ciphers of varying strengths by Prajwal Nanda Kishore (PrajNK).\nTwo tools to break simple substitution ciphers have also been included.\nMore information about each cipher can be found in their pages.");
        System.out.println("Author: Prajwal Nanda Kishore (PrajNK)");
        System.out.println("Version: 1.1");
        try{Thread.sleep(7000);} catch(Exception e){} //wait for 7 seconds
    }
}