import java.util.ArrayList;

public class WordToCreditsParser {
    public String[] Parse(String input) {
        String[] split1 = input.split(" is ");
        String[] split2 = split1[0].split(" ");
        String[] split3 = split1[1].split(" ");
        ArrayList<String> allSplits = new ArrayList<String>();
        for(String v:split2){
           allSplits.add(v);
        }
        allSplits.add(split3[0]);
        String[] splitsToReturn = allSplits.toArray(new String[allSplits.size()]);
        return splitsToReturn;
    }
}
