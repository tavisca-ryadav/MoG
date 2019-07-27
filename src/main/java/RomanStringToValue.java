public class RomanStringToValue {
    public int Convert(String romanString) {
        int romanStringValue=0,currentValue=0,nextvalue = 0,i=0;
        RomanToValueConverter romanToValueConverter = new RomanToValueConverter();
        for(i=0;i<romanString.length()-1;i++){
            currentValue = romanToValueConverter.romanToValue.get(Character.toString(romanString.charAt(i)));
            nextvalue = romanToValueConverter.romanToValue.get(Character.toString(romanString.charAt(i+1)));
            if(nextvalue>currentValue){
                romanStringValue+=(nextvalue-currentValue);
                i++;
            }
            else{
                romanStringValue+=currentValue;
            }
        }

        if(i<romanString.length())
            romanStringValue+=nextvalue;


        return romanStringValue;
    }
}
