package com.convert.towords.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.convert.towords.impl.NumberToBritishWords;

/**
 * Test conversion of number in British English.
 *
 * @author ade
 */
public class NumberToBritishWordsTest{
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test 
	public void groupNumberTesting(){
	}
	@Test
	public void validateNumberTestMin() {
		assertEquals(0, NumberToBritishWords.ALLOWED_MIN_VALUE );
	}

	@Test(expected=IllegalArgumentException.class)
	public void invalidMinNumberTest() {
		new NumberToBritishWords().toWords(-1);
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Number has to be greater than or equal to 0");
	}

	@Test(expected=IllegalArgumentException.class)
	public void invalidMaxNumberTest() {
		new NumberToBritishWords().toWords(1_000_000_000);
		expectedEx.expect(IllegalArgumentException.class);
		expectedEx.expectMessage("Number has to be less than or equal to 999999999");
	}

	@Test
	public void validateNumberTestMax() {
		assertEquals(999_999_999, NumberToBritishWords.ALLOWED_MAX_VALUE );
	}

	@Test
	public void validateWording() {
		assertEquals("zero",new NumberToBritishWords().toWords(0));
		assertEquals("one",new NumberToBritishWords().toWords(1));
		assertEquals("twelve",new NumberToBritishWords().toWords(12));
		assertEquals("one hundred and twenty three",new NumberToBritishWords().toWords(123));
		assertEquals("one thousand two hundred and thirty four",new NumberToBritishWords().toWords(1234));
		assertEquals("twelve thousand three hundred and forty five",new NumberToBritishWords().toWords(12345));
		assertEquals("one hundred and twenty three thousand four hundred and fifty six",new NumberToBritishWords().toWords(123456));
		assertEquals("one million two hundred and thirty four thousand five hundred and sixty seven",new NumberToBritishWords().toWords(1234567));
		assertEquals("twelve million three hundred and forty five thousand six hundred and seventy eight",new NumberToBritishWords().toWords(12345678));
		assertEquals("one hundred and twenty three million four hundred and fifty six thousand seven hundred and eighty nine",new NumberToBritishWords().toWords(123456789));
		assertEquals("one hundred thousand and one",new NumberToBritishWords().toWords(100001));
		assertEquals("ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine",new NumberToBritishWords().toWords(99999999));
	}

}
