public class QuestionParser {
    WordToRomanParser wordToRomanParser = new WordToRomanParser();
    RomanStringToValue romanStringToValue = new RomanStringToValue();
    ItemValueCalculator itemValueCalculator = new ItemValueCalculator();
    public int Parse(String query) {
        query = query.replace(" ?","");
        if(query.contains("much")){
            String[] split = query.split(" is ");

            return romanStringToValue.Convert(wordToRomanParser.convert(split[1]));
        }
        else if(query.contains("many")){
            String wordString="",item;
            String[] split = query.split(" ");
            int i,quantity;
            for(i=4;i<split.length-1;i++){
                wordString+=split[i]+" ";
            }
            item = split[i];
            quantity = romanStringToValue.Convert(wordToRomanParser.convert(wordString));
            return (int) (quantity*itemValueCalculator.itemToCreditsMap.get(item));
        }
        return 0;
    }
}