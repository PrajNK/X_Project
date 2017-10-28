package RepeatFeatures;
public class Ending{
    public static void ender(int state){
        if(state == 0){
            System.out.println("\fThank you for using the CiphKit!");
            System.out.println("\n     Have a nice day!");
            System.out.println("\n   Programmed by Prajwal NK");
            System.exit(0);
        }
        else{
            System.out.println("An error has occurred, and the process has been terminated");
            System.out.println("The programmer apologises for the inconvenience");
            System.exit(0);
        }
    }
}
