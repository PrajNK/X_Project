package RepeatFeatures;
import java.util.Scanner;
import RepeatFeatures.Ending;
public class Repeater{
    public static boolean goAgain(){
        System.out.print("\nQuit? (N to return, anything else to quit): ");
        char quit = '\0';
        try{
            quit = new Scanner(System.in).next().charAt(0);
        }
        catch(Exception e){
            Ending.ender(-1);
        }
        if (quit == 'n' || quit == 'N')
            return true;
        else
            return false;
    }
}