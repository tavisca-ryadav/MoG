import java.util.HashMap;

public class WordToRomanParser {

    public static HashMap<String,String> wordToRoman = new HashMap<>();



    public void Parse(String input) {
        String[] splits = input.split(" ");
        wordToRoman.put(splits[0],splits[2]);
    }

    public String convert(String words) {
        String romans ="";
        String[] splits = words.split(" ");
        for(String v:splits){
            romans+=wordToRoman.get(v);
        }
        return romans;
    }
}
