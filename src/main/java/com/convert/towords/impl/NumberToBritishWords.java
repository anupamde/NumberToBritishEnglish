package com.convert.towords.impl;

import java.text.DecimalFormat;

import com.convert.towords.NumberToWords;
import com.convert.towords.vo.BritishNumericGroup;
import com.convert.towords.vo.NumberToProcess;

/**
 * Implementation to display a number in British English.
 *
 * @author ade
 */
public final class NumberToBritishWords implements NumberToWords{
	private static final String AND 		= " and";
	private static final String ZERO 		= "zero";
	private static final String HUNDRED 	= " hundred ";

	public static final int ALLOWED_MIN_VALUE 	= 0;
	public static final int ALLOWED_MAX_VALUE 	= 999_999_999;
	
	private static final String[] tensNames = {
			"", // divisible by 10 - 10 entries
			" ten", " twenty", " thirty",
			" forty", " fifty", " sixty",
			" seventy", " eighty", " ninety"
	};

	private static final String[] unitNames = {
			"", // divisible by 10 - 20 entries
			" one", " two", " three", " four",
			" five", " six", " seven", " eight",
			" nine", " ten", " eleven", " twelve",
			" thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen"
	};


	/**
	 * Process a number between 1 and 999
	 * Then add the additional grouping logic of millions and thousands
	 * 
	 * @param number
	 * @param additionalGroup
	 * @return
	 */
	private StringBuffer processLessThanThousand(int number, String additionalGroup) {
		StringBuffer numberToWord = new StringBuffer();
		if (number == 0){
			return numberToWord;
		}
		int tempNumber = number / 100;
		if (tempNumber >  0){
			numberToWord.append(unitNames[tempNumber] + HUNDRED);
		}
		int length = numberToWord.length();
		// there is hundred present and there are numbers present
		if (length>0 && number%100 >0){ 
			numberToWord.append(AND);
		}
		if (number % 100 > 20){
			numberToWord.append(tensNames[(number/10) % 10] + unitNames[number % 10]);
		}
		else {
			numberToWord.append(unitNames[number % 100]);
		}
		return numberToWord.append(additionalGroup);
	}


	@Override
	public String toWords(int number) {
		validate(number);

		if (number == 0) { 
			return ZERO; 
		}
		String tempNumber = formatNumber(number);
		StringBuffer result= new StringBuffer();

		NumberToProcess millions = createGroupNumber(tempNumber,BritishNumericGroup.MILLION);
		result = groupNumber(millions);

		NumberToProcess thousands = createGroupNumber(tempNumber,BritishNumericGroup.THOUSAND);
		result.append(groupNumber(thousands));

		NumberToProcess hundreds = createGroupNumber(tempNumber,BritishNumericGroup.HUNDRED);
		result.append(specialAndGrouping(result.length(),hundreds.getIntToProcess(),groupNumber(hundreds)));

		// remove extra spaces!
		return result.toString().replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}


	/**
	 * Format number - pad with zeros make it 9 digits
	 * @param number
	 * @return
	 */
	private String formatNumber(int number) {
		String mask = "000000000"; // pad out to 100 million
		DecimalFormat decimalFormat = new DecimalFormat(mask);
		return decimalFormat.format(number);
	}

	/**
	 * Extract 3 digit positional number from input number
	 * @param tempNumber
	 * @param group
	 * @return
	 */
	private NumberToProcess createGroupNumber(String tempNumber, BritishNumericGroup group) {
		String toProcess = "";
		int intToProcess=0;
		try{
			toProcess = tempNumber.substring(group.getStartIndex(),group.getEndIndex());
		}catch(IndexOutOfBoundsException e){
			throw new IndexOutOfBoundsException("Number type cannot be processed  "+tempNumber);
		}
		try{
			intToProcess = Integer.parseInt(toProcess);
		}catch(NumberFormatException e){
			throw new NumberFormatException("Value cannot be converted to number  "+toProcess);
		}
		return new NumberToProcess(group.getName(), intToProcess);
	}


	/**
	 * Create wording for 3 digit number and then add grouping
	 * 
	 * @param numberToProcess
	 * @return
	 */
	private StringBuffer groupNumber(NumberToProcess numberToProcess) {
		return processLessThanThousand(numberToProcess.getIntToProcess(), numberToProcess.getNumericGroupName()) ;
	}

	/**
	 * The and wording is used after hundred
	 * but there is a special case where you have the last 2 digits 
	 * but no hundred and have a million/thousands wording
	 * 
	 * @param currentWordLength
	 * @param tempNumber
	 * @param tempWord
	 * @return
	 */
	private StringBuffer specialAndGrouping(int currentWordLength, int tempNumber, StringBuffer tempWord){
		// million or thousand wording present
		if (currentWordLength > 0 ){
			// less than hundred so number has no and wording  
			if (tempNumber > 0 && tempNumber < 100){ 
				return new StringBuffer(AND).append(tempWord);
			}
		}
		return tempWord;
	}

	/**
	 * Validate min and max number
	 * Can not be negative or more than 999,999,999
	 * @param number
	 */
	private void validate(int number) {
		if (number < ALLOWED_MIN_VALUE){
			throw new IllegalArgumentException("Number has to be greater than or equal to "+ALLOWED_MIN_VALUE);
		}
		if(number > ALLOWED_MAX_VALUE){
			throw new IllegalArgumentException("Number has to be less than or equal to "+ALLOWED_MAX_VALUE);
		}
	}

	/**
	 * testing
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(BritishNumericGroup.THOUSAND.getStartIndex());
		System.out.println("*** " + new NumberToBritishWords().toWords(0));
		System.out.println("*** " + new NumberToBritishWords().toWords(1));
		System.out.println("*** " + new NumberToBritishWords().toWords(12));
		System.out.println("*** " + new NumberToBritishWords().toWords(123));
		System.out.println("*** " + new NumberToBritishWords().toWords(1234));
		System.out.println("*** " + new NumberToBritishWords().toWords(12345));
		System.out.println("*** " + new NumberToBritishWords().toWords(123456));
		System.out.println("*** " + new NumberToBritishWords().toWords(1234567));
		System.out.println("*** " + new NumberToBritishWords().toWords(12345678));
		System.out.println("*** " + new NumberToBritishWords().toWords(123456789));
		System.out.println("*** " + new NumberToBritishWords().toWords(100001));
		System.out.println("*** " + new NumberToBritishWords().toWords(99999999));

	}

}
