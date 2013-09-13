package tachyon;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

/**
 * Unit tests for {@link PrefixList}
 */
public final class PrefixListTest {

	@Test
	public void prefixListTest() {
		PrefixList prefixList = new PrefixList(ImmutableList.<String> of("test", "apple", "sun"));
		Assert.assertTrue(prefixList.inList("test"));
		Assert.assertTrue(prefixList.inList("apple"));
		Assert.assertTrue(prefixList.inList("sun"));
		
		Assert.assertTrue(prefixList.inList("test123"));
		Assert.assertTrue(prefixList.inList("testing-1012"));
		Assert.assertTrue(prefixList.inList("apple12nmzx91l"));
		Assert.assertTrue(prefixList.inList("sunn1i2080-40mx"));
		
		Assert.assertFalse(prefixList.outList("test123"));
		Assert.assertFalse(prefixList.outList("testing-1012"));
		Assert.assertFalse(prefixList.outList("apple12nmzx91l"));
		Assert.assertFalse(prefixList.outList("sunn1i2080-40mx"));
		
		Assert.assertTrue(prefixList.outList("tes"));
		Assert.assertTrue(prefixList.outList("a"));
		Assert.assertTrue(prefixList.outList("s"));
		Assert.assertTrue(prefixList.outList("su"));
		Assert.assertTrue(prefixList.outList("ap"));
		Assert.assertTrue(prefixList.outList(""));
		Assert.assertTrue(prefixList.outList(null));
	}
	
	@Test
	public void prefixListTest2() {
		PrefixList prefixList = new PrefixList("test;apple;sun", ";");
		Assert.assertTrue(prefixList.inList("test"));
		Assert.assertTrue(prefixList.inList("apple"));
		Assert.assertTrue(prefixList.inList("sun"));
		
		Assert.assertTrue(prefixList.inList("test123"));
		Assert.assertTrue(prefixList.inList("testing-1012"));
		Assert.assertTrue(prefixList.inList("apple12nmzx91l"));
		Assert.assertTrue(prefixList.inList("sunn1i2080-40mx"));
		
		Assert.assertFalse(prefixList.outList("test123"));
		Assert.assertFalse(prefixList.outList("testing-1012"));
		Assert.assertFalse(prefixList.outList("apple12nmzx91l"));
		Assert.assertFalse(prefixList.outList("sunn1i2080-40mx"));
		
		Assert.assertTrue(prefixList.outList("tes"));
		Assert.assertTrue(prefixList.outList("a"));
		Assert.assertTrue(prefixList.outList("s"));
		Assert.assertTrue(prefixList.outList("su"));
		Assert.assertTrue(prefixList.outList("ap"));
		Assert.assertTrue(prefixList.outList(""));
		Assert.assertTrue(prefixList.outList(null));
	}
	
	@Test
	public void emptyPrefixTest() {
		PrefixList prefixList = new PrefixList(ImmutableList.<String> of(""));
		Assert.assertTrue(prefixList.inList("a"));
		
		Assert.assertTrue(prefixList.outList(""));
		
	}
}
