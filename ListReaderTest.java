import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListReaderTest {

	@Test
	void makeListTest() {
		ListReader lr = new ListReader();
		//invalid list with one four letter word
		String[] a = lr.makeList("input/list-a.txt");
		
		//short valid list
		String[] b = lr.makeList("input/list-b.txt");
		
		//invalid list with duplicate
		String[] c = lr.makeList("input/list-c.txt");
		
		//long valid list
		String[] d = lr.makeList("input/list-d.txt");
		
		assertEquals(478, a.length);
		assertEquals(2, b.length);
		assertEquals(478, c.length);
		assertEquals(478, d.length);
		
		//check for order
		assertEquals("cramp", b[0]);
		assertEquals("these", b[1]);
		
	}
	
	@Test
	void checkListTest() {
		ListReader lr = new ListReader();
		//invalid list with one four letter word
		String[] a = lr.makeList("input/list-a.txt");
		assertFalse(lr.checkList(a));
		
		//short valid list
		String[] b = lr.makeList("input/list-b.txt");
		assertTrue(lr.checkList(b));
		
		//invalid list with duplicate
		String[] c = lr.makeList("input/list-c.txt");
		assertFalse(lr.checkList(c));
		
		//long valid list
		String[] d = lr.makeList("input/list-d.txt");
		assertTrue(lr.checkList(d));
		
	}

}
