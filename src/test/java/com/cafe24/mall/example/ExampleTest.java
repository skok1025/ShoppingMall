package com.cafe24.mall.example;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleTest {

	// 테스트 케이스(메소드) 끼리 공유해야할 변수가 있으면
	// static

	private static StringBuilder output = new StringBuilder();

	@BeforeClass
	public static void setUpBefore() {
		System.out.println("BeforeClass");
	}

	@Before
	public void setUp() {
		System.out.println("Before");
	}

	@After
	public void tearDown() {
		System.out.println("After");
	}

	@AfterClass // 테스트 클래스에 생성된 내용들 클리어 deleteAll
	public static void AftertearDown() {
		System.out.println("AfterClass: " + output);

	}

	// 알파벳 순서로 실행한다.
	@Test
	public void myKTest() {
		System.out.println("TEST:K");
		output.append("K");
	}

	@Test
	public void myBTest() {
		System.out.println("TEST:B");
		output.append("B");
	}

	@Test
	public void myCTest() {
		System.out.println("TEST:C");
		output.append("C");
	}

	/**
	 * assertXYZ 테스트
	 */

	// 테스트 무시
	@Ignore
	@Test
	public void ignoreTest() {
		assertTrue(false);

	}

	@Test
	public void testAssert1() {
		Object[] a = {"Java","JUnit","Spring"};
		Object[] b = {"Java","JUnit","Spring"};
		
		assertArrayEquals(a, b);
	}
	
	@Test
	public void testAssert() {
		assertTrue(true);
		assertFalse(false);

		assertNull(null);
		assertNotNull(new Object());
		assertEquals(1 + 2, 3);
		assertEquals(new String("hello"), "hello");

		assertSame("Hello", "Hello");
		assertNotSame(new String("hello"), "hello");
		assertNotSame(new Integer(1), new Integer(1));

		// assertThat : is
		assertThat(1 + 2, is(3));
		assertThat("this is never", is(not("this is ever")));

		// assertThat : allof
		assertThat("Hello World", allOf(startsWith("Hell"), containsString("or")));

		// assertThat : anyof
		assertThat("Hello World", anyOf(startsWith("Heaven"), containsString("or")));
		
		// assertThat : both
		assertThat("ABC", both(containsString("A")).and(containsString("B")));
		
		// assertThat : or
		assertThat("ABC", either(containsString("A")).or(containsString("B")));
		
		// assertThat : everyItem
		assertThat(Arrays.asList("Apple","Application","Apolosize"), everyItem(startsWith("Ap")));
		
		// assertThat : hasItem
		assertThat(Arrays.asList("Apple","Bit","siba"), hasItem(startsWith("Ap")));
		
		// 무조건 실패 시키기
		// fail("All Over!!!");
		

	}

}
