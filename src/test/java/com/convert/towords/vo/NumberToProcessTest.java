package com.convert.towords.vo;

import static org.junit.Assert.*;

import org.junit.*;

import com.convert.towords.vo.BritishNumericGroup;
import com.convert.towords.vo.NumberToProcess;

/**
 * Test to process number to group mapping
 * @author Anupam
 *
 */
public class NumberToProcessTest {
	private NumberToProcess million,thousands,hundreds;
	
	@Before
	public void createNumberToProcessObjects() {
		million = new NumberToProcess(BritishNumericGroup.MILLION.getName(), 1);
		thousands = new NumberToProcess(BritishNumericGroup.THOUSAND.getName(), 12);
		hundreds = new NumberToProcess(BritishNumericGroup.HUNDRED.getName(), 123);
	}

	@Test
	public void testNumericGroupName() {
		assertEquals(BritishNumericGroup.MILLION.getName(), million.getNumericGroupName());
		assertEquals(BritishNumericGroup.THOUSAND.getName(), thousands.getNumericGroupName());
		assertEquals(BritishNumericGroup.HUNDRED.getName(), hundreds.getNumericGroupName());
	}
	
	@Test
	public void testIntToProcess() {
		assertEquals(1, million.getIntToProcess());
		assertEquals(12, thousands.getIntToProcess());
		assertEquals(123, hundreds.getIntToProcess());
	}
}
