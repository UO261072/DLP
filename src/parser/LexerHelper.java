package parser;

public class LexerHelper {
	
	public static int lexemeToInt(String str) {
		try {
			return Integer.parseInt(str);
		}
		catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}
	public static double lexemeToReal(String str){
		try{
			return Double.parseDouble(str);
		}catch(NumberFormatException e) {
			System.out.println(e);
		}
		return -1;
	}
	public static char lexemeToChar(String str){
		try{
			if(str.charAt(1)!='\\'){
				return str.charAt(1);
			}
			else if(str.charAt(2)=='n'){
				return '\n';
			}
			else if(str.charAt(2)=='t'){
				return '\t';
			}
			else{
				return (char) Integer.parseInt(str.substring(2,5));
			}
		}catch(NumberFormatException e) {
			System.out.println(e);
		}
		return ' ';
	}

	// TODO: Implement the lexemeToChar and lexemeToReal methods

}
