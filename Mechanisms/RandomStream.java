//generates a stream of random numbers within a specified range
package Mechanisms;
import java.util.Scanner;
import RepeatFeatures.Ending;
public class RandomStream{
    public static void main(String[] args){ //in case of stand-alone use
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of numbers to be generated: ");
        int n = 0;
        try{
            n = sc.nextInt();
        } catch(Exception e){
            Ending.ender(-1);
        }
        sc.close();
        int[] str = generate(n); //str --> stream
        for(int i = 0; i < str.length; i++){
            System.out.println(str[i]);
        }
    }
    public static int[] generate(int x){
        int[] nums = new int[x];
        for(int i = 0; i < x; i++){
            nums[i] = (int)(Math.random() * 26);
        }
        return nums;
    }
}
