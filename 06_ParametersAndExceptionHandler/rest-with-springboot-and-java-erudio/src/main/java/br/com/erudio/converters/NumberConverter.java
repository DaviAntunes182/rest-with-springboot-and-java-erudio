package br.com.erudio.converters;

public class NumberConverter {


	public static Double convertToDouble(String strNum) {
		if(strNum == null)
			return 0D;
		
		String number = strNum.replaceAll(",", ".");
		if(isNumeric(number))
			return Double.parseDouble(number);
		
		return 0D;
	}

	public static boolean isNumeric(String strNum) {
		if(strNum == null) return false;
		
		String number = strNum.replaceAll(",", ".");	
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}
}
