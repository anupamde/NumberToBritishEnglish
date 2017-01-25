package com.convert.towords;

/**
 * Interface to take a numeric value and translate it into words
 * @author ade
 */

public interface NumberToWords{

	/**
	 * Translate number to words
	 * @param number numeric value to convert into words.
	 * @return string representing the number in words.
	 */
	public String toWords(int number);
}