package de.comparus.opensource.longmapTest;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.comparus.opensource.longmap.LongMapImpl;

public class LongMapImplTest {
	long l1 = 13617;
	long l2 = 13587;
	long l5 = 12587;
	long l6 = 11587;
	
	@Test
	public <V> void isEmpty() {
		LongMapImpl<V> lm = new LongMapImpl<V>();
		assertTrue(lm.isEmpty());
	}
	
	@Test
	public void clearTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		lm.put(l2, "Boris");
		lm.put(l1, "Vanda");
		lm.put(l5, "Valensia");
		lm.put(l6, "Bojomi");
		lm.clear();
		assertTrue(lm.isEmpty());
	}
	
	@Test
	public void sizeTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		assertTrue(lm.size()==16);
	}
	
	@Test
	public void valuesTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		lm.put(l2, "Boris");
		@SuppressWarnings("unchecked")
		Object[] arrayValues;
		arrayValues=(Object[]) lm.values();
		assertTrue(arrayValues[0].equals("Boris"));
	}
	
	@Test
	public void keysTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		lm.put(l2, "Boris");
		long[] tab ;
		tab = lm.keys();
		assertTrue(tab[0]==l2);
	}
	
	@Test
	public void containsValueTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		lm.put(l2, "Boris");
		assertTrue(lm.containsValue("Boris"));
	}
	
	@Test
	public void containsKeyTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		lm.put(l2, "Boris");
		assertTrue(lm.containsKey(l2));
	}
	
	@Test
	public void removeTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		lm.put(l2, "Boris");
		lm.remove(l2);
		assertTrue(lm.isEmpty());
	}
	
	@Test
	public void putTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		lm.put(l2, "Boris");
		assertTrue(lm.containsKey(l2) && lm.containsValue("Boris"));
	}
	
	@Test
	public void getTest() {
		LongMapImpl<String> lm = new LongMapImpl<String>();
		lm.put(l2, "Boris");
		String str = lm.get(l2);
		assertTrue(str.equals("Boris"));
	}

}