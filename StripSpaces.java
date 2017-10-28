// stripping a given String of all spaces
package Mechanisms;
public class StripSpaces{
    public static String strip(String inp){
        String tmp = inp.trim(), str = ""; //str --> stripped / string - pick any one :D
        for(int i = 0; i < tmp.length(); i++){
            if(Character.isWhitespace(tmp.charAt(i)))
                continue;
            else
                str += tmp.charAt(i);
        }
        return str;
    }
}
        
