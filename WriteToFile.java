package File_Functions;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import RepeatFeatures.Ending;
//java.nio is only from jdk7. so, java.io.File function added to make directory
public class WriteToFile{
    public static void writeFile(String txt){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of your new file (include extension): ");
        String fn = ""; //File Name
        String path = "";
        try{
            fn = br.readLine();
            path = "C:\\CiphKit\\Saved\\"+fn;
            br.close();
        } catch(Exception e){
            Ending.ender(-1);
        }
        writeFileNoPrompt(path, txt);
    }
    public static void writeFileNoPrompt(String path, String txt){
        try{
            //Files.createDirectories(Paths.get("C:\\CiphKit\\Saved"));
            new File("C:\\CiphKit\\Saved").mkdirs();
            File file = new File(path);
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(txt);
            bw.newLine();
            
            bw.close();
        } catch(Exception e){
            Ending.ender(-1);
        }
    }
    public static void writeFile(String key, String txt){ //used in vigenere
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of your new file (include extension): ");
        String fn = ""; //File Name
        String path = "";
        try{
            fn = br.readLine();
            path = "C:\\CiphKit\\Saved\\"+fn;
            br.close();
        } catch(Exception e){
            Ending.ender(-1);
        }
        try{
            //Files.createDirectories(Paths.get("C:\\CiphKit\\Saved"));
            new File("C:\\CiphKit\\Saved").mkdirs();
            File file = new File(path);
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(key);
            bw.newLine();
            bw.write(txt);
            bw.newLine();
            bw.close();
        } catch(Exception e){
            Ending.ender(-1);
        }
    }
    public static void writeFile(String key, String[] txt){ //used in vigenere
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the name of your new file (include extension): ");
        String fn = ""; //File Name
        String path = "";
        try{
            fn = br.readLine();
            path = "C:\\CiphKit\\Saved\\"+fn;
            br.close();
        } catch(Exception e){
            Ending.ender(-1);
        }
        try{
            //Files.createDirectories(Paths.get("C:\\CiphKit\\Saved"));
            new File("C:\\CiphKit\\Saved").mkdirs();
            File file = new File(path);
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(key);
            bw.newLine();
            for(int i = 0; i < txt.length; i++){
                bw.write(txt[i]);
                bw.newLine();
            }
            bw.close();
        } catch(Exception e){
            Ending.ender(-1);
        } 
    }
}