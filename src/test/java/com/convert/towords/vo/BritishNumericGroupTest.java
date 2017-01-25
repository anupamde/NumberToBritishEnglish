package com.convert.towords.vo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.convert.towords.vo.BritishNumericGroup;

/**
 * Positional Number grouping
 * @author Anupam
 *
 */

public class BritishNumericGroupTest {
	private BritishNumericGroup million = BritishNumericGroup.MILLION;
	private BritishNumericGroup thousand = BritishNumericGroup.THOUSAND;
	private BritishNumericGroup hundred = BritishNumericGroup.HUNDRED;
	
	@Test
	public void testMillion() {
		assertEquals(" million ",million.getName());
		assertEquals(0,million.getStartIndex());
		assertEquals(3, million.getEndIndex());
	}

	@Test
	public void testThousand() {
		assertEquals(" thousand ",thousand.getName());
		assertEquals(3,thousand.getStartIndex());
		assertEquals(6, thousand.getEndIndex());
	}

	@Test
	public void testHundred() {
		assertEquals("",hundred.getName());
		assertEquals(6,hundred.getStartIndex());
		assertEquals(9, hundred.getEndIndex());
	}
	
}