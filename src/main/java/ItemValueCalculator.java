import java.text.DecimalFormat;
import java.util.HashMap;

public class ItemValueCalculator {

    public static HashMap<String,Double> itemToCreditsMap = new HashMap<>();
    public void Calculate(String sentence) {
        var wordToCreditsParser = new WordToCreditsParser();
        String[] parsedSentence = wordToCreditsParser.Parse(sentence);
        String item="",wordString = "";
        double quantity=0,credits=0;
        boolean numeric = true;

        for(int i=0;i<parsedSentence.length;i++){
            numeric = parsedSentence[i+2].matches("-?\\d+(\\.\\d+)?");
            wordString += parsedSentence[i]+" ";
            if(numeric){
                item = parsedSentence[i+1];
                credits = Double.parseDouble(parsedSentence[i+2]);
                break;
            }
        }

        var wordToRomanParser = new WordToRomanParser();
        var romanStringToValue = new RomanStringToValue();

        quantity = romanStringToValue.Convert(wordToRomanParser.convert(wordString));
        credits /= quantity;
        var formatter = new DecimalFormat(".00");
        credits = Double.parseDouble(formatter.format(credits));

        itemToCreditsMap.put(item,credits);
    }
}
