import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class MerchantGalaxyTests {

    public WordToRomanParser wordToRomanParser;
    public RomanToValueConverter romanToValueConverter;
    public ItemValueCalculator itemValueCalculator;
    public MerchantGalaxyTests(){
        wordToRomanParser = new WordToRomanParser();
        romanToValueConverter = new RomanToValueConverter();
        itemValueCalculator = new ItemValueCalculator();
        wordToRomanParser.Parse("glob is I");
        wordToRomanParser.Parse("prok is V");
        wordToRomanParser.Parse("pish is X");
        wordToRomanParser.Parse("tegj is L");

        itemValueCalculator.Calculate("glob glob Silver is 34 Credits");
        itemValueCalculator.Calculate("glob prok Gold is 57800 Credits");
        itemValueCalculator.Calculate("pish pish Iron is 3910 Credits");
    }
    @Test
    public void IsConvertSingleWordToRomanNumeral() {
        assertEquals("I",wordToRomanParser.wordToRoman.get("glob"));
        assertEquals("V",wordToRomanParser.wordToRoman.get("prok"));
        assertEquals("X",wordToRomanParser.wordToRoman.get("pish"));
    }



    @Test
    public void CreditsOfItemStatement(){
        WordToCreditsParser wordToCreditsParser = new WordToCreditsParser();
        assertArrayEquals(new String[]{"glob", "glob","Silver","34"},
                wordToCreditsParser.Parse("glob glob Silver is 34 Credits"));
    }

    @Test
    public void IsConvertWordStringToRomanString(){
        assertEquals("II",wordToRomanParser.convert("glob glob"));
        assertEquals("IV",wordToRomanParser.convert("glob prok"));
    }

    @Test
    public void InvalidRomanNumeralReturnsFalse(){
        RomanStringValidator romanStringValidator = new RomanStringValidator();
        assertEquals(false,romanStringValidator.Validate("DDLLVV"));
        assertEquals(true,romanStringValidator.Validate("MMVI"));
    }

    @Test
    public void IsConvertSimpleRomanStringToValue(){
        RomanStringToValue romanStringToValue = new RomanStringToValue();
        assertEquals(2006,romanStringToValue.Convert("MMVI"));
    }

    @Test
    public void IsConvertComplexRomanStringToValue(){
        RomanStringToValue romanStringToValue = new RomanStringToValue();
        assertEquals(1944,romanStringToValue.Convert("MCMXLIV"));
        assertEquals(4,romanStringToValue.Convert("IV"));
        assertEquals(42,romanStringToValue.Convert("XLII"));
    }

    @Test
    public void CalculatesItemValueFromRomanString(){
        double silverCredit = itemValueCalculator.itemToCreditsMap.get("Silver");
        assertEquals(17.0,silverCredit,10f);
        double goldCredit = itemValueCalculator.itemToCreditsMap.get("Gold");
        assertEquals(14450.0,goldCredit,10f);
        double ironCredit = itemValueCalculator.itemToCreditsMap.get("Iron");
        assertEquals(195.5,ironCredit,10f);
    }

    @Test
    public void QuestionContainsHowMuch(){
        QuestionParser questionParser = new QuestionParser();
        assertEquals(42,questionParser.Parse("how much is pish tegj glob glob ?"));;
    }

    @Test
    public void QuestionContainsHowMany(){
        QuestionParser questionParser = new QuestionParser();
        assertEquals(68,questionParser.Parse("how many Credits is glob prok Silver ?"));
        assertEquals(57800,questionParser.Parse("how many Credits is glob prok Gold ?"));
        assertEquals(782,questionParser.Parse("how many Credits is glob prok Iron ?"));
    }
}