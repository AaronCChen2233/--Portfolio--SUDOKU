package Bootstrap.Parts;

import java.util.ArrayList;

public class Switchs {
    public static ArrayList<Character> allownSwitch = new ArrayList<Character>();

    static {
        /*If in the future want add allow switch add to this String*/
        String allowString = "-\\";
        for (int i = 0 ;i<allowString.length();i++){
            allownSwitch.add(allowString.charAt(i));
        }
    }
}
