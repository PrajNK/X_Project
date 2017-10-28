package File_Functions;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.LineNumberReader;
import java.io.FileReader;
import RepeatFeatures.Ending;
public class ReadFromFile{
    public static String[] readFile(String path){ //path of file to be read
        String[] fileData = null;
        try{
            InputStream str = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(str);
            BufferedReader br = new BufferedReader(isr);
            int lns = countLines(path); //lines in the file
            fileData = new String[lns];
            for(int i = 0; i < lns; i++)
                fileData[i] = br.readLine();
            str.close();
            isr.close();
            br.close();
        } catch(Exception e){
            Ending.ender(-1);
        }
        return fileData;
    }
    public static int countLines(String filename){
        int cnt = 0;
        try{
            LineNumberReader reader  = new LineNumberReader(new FileReader(filename));
            cnt = 0;
            String lineRead = "";
            while ((lineRead = reader.readLine()) != null) {}
            cnt = reader.getLineNumber(); 
            reader.close();
        } catch(Exception e){
            Ending.ender(-1);
        }
        return cnt;
    }
}