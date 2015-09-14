import simpletree.*;

import static org.junit.Assert.*;

import org.junit.Test;


public class TreeArithmeticTest {
	

	@Test (timeout=1000)
	public void testIsArithmetic() {
		
		MyTree<String> tree = new MyTree<String>();
		
		assertFalse(tree.isArithmetic());

		tree.setRoot(new SimplePosition<String>("+"));
		assertFalse(tree.isArithmetic());
		
		tree.insert(tree.root(), new SimplePosition<String>("1"));
		assertFalse(tree.isArithmetic());

		tree.insert(tree.root(), new SimplePosition<String>("1.2"));
		assertTrue(tree.isArithmetic());
		
	}

	@Test (timeout=1000)
	public void testEvaluateArithmetic() {

		MyTree<String> tree;
		
		// test 1 + 1.2 = 1.2
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("+"));
		tree.insert(tree.root(), new SimplePosition<String>("1"));
		tree.insert(tree.root(), new SimplePosition<String>("1.2"));
		assertEquals(2.2, tree.evaluateArithmetic(), 0.00001);
		
		// test 2.5 = 2.5
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("2.5"));
		assertEquals(2.5, tree.evaluateArithmetic(), 0.00001);

		// test 1+(1+1) = 3
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("+"));
		tree.insert(tree.root(), new SimplePosition<String>("1"));
		SimplePosition<String> position = new SimplePosition<String>("+");
		tree.insert(tree.root(), position);
		tree.insert(position, new SimplePosition<String>("1"));
		tree.insert(position, new SimplePosition<String>("1"));
		assertEquals(3.0, tree.evaluateArithmetic(), 0.00001);
		
		// NEGATIVE NUMBERS
		// test 1 + 1.2 = 1.2
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("+"));
		tree.insert(tree.root(), new SimplePosition<String>("-1"));
		tree.insert(tree.root(), new SimplePosition<String>("-1.2"));
		assertEquals(-2.2, tree.evaluateArithmetic(), 0.00001);
		
		// SUBTRACTION (with negative result)
		// test 5-(1+1) = 6
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("-"));
		tree.insert(tree.root(), new SimplePosition<String>("5"));
		position = new SimplePosition<String>("+");
		tree.insert(tree.root(), position);
		tree.insert(position, new SimplePosition<String>("5"));
		tree.insert(position, new SimplePosition<String>("1"));
		assertEquals(-1.0, tree.evaluateArithmetic(), 0.00001);
		
		// test 5-(1-2) = 6
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("-"));
		tree.insert(tree.root(), new SimplePosition<String>("5"));
		position = new SimplePosition<String>("-");
		tree.insert(tree.root(), position);
		tree.insert(position, new SimplePosition<String>("1"));
		tree.insert(position, new SimplePosition<String>("2"));
		assertEquals(6.0, tree.evaluateArithmetic(), 0.00001);
		
		// MULTIPLICATION
		// test 3*(1+1) = 6
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("*"));
		tree.insert(tree.root(), new SimplePosition<String>("3"));
		position = new SimplePosition<String>("+");
		tree.insert(tree.root(), position);
		tree.insert(position, new SimplePosition<String>("1"));
		tree.insert(position, new SimplePosition<String>("1"));
		assertEquals(6.0, tree.evaluateArithmetic(), 0.00001);
		
		// DIVISION
		// test 3/(1+1) = 1.5
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("/"));
		tree.insert(tree.root(), new SimplePosition<String>("3"));
		position = new SimplePosition<String>("+");
		tree.insert(tree.root(), position);
		tree.insert(position, new SimplePosition<String>("1"));
		tree.insert(position, new SimplePosition<String>("1"));
		assertEquals(1.5, tree.evaluateArithmetic(), 0.00001);
		
		// DIVISION BY ZERO
		// test 3/(1-1) = 1.5
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("/"));
		tree.insert(tree.root(), new SimplePosition<String>("3"));
		position = new SimplePosition<String>("-");
		tree.insert(tree.root(), position);
		tree.insert(position, new SimplePosition<String>("1"));
		tree.insert(position, new SimplePosition<String>("1"));
		assertEquals(Double.POSITIVE_INFINITY, tree.evaluateArithmetic(), 0.00001);
		
		
	}

	@Test (timeout=1000)
	public void testGetArithmeticString() {

		MyTree<String> tree;
		String arithmeticString;
		
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("+"));
		tree.insert(tree.root(), new SimplePosition<String>("1"));
		tree.insert(tree.root(), new SimplePosition<String>("1.2"));
		arithmeticString = tree.getArithmeticString();
		//the outermost brackets are optional, both these cases will be accepted:
		assertTrue(arithmeticString.equals("1+1.2") || arithmeticString.equals("(1+1.2)"));

		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("2.5"));
		arithmeticString = tree.getArithmeticString();
		//the outermost brackets are optional, both these cases will be accepted:
		assertTrue(arithmeticString.equals("2.5") || arithmeticString.equals("(2.5)"));

		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("+"));
		tree.insert(tree.root(), new SimplePosition<String>("1"));
		SimplePosition<String> position = new SimplePosition<String>("+");
		tree.insert(tree.root(), position);
		tree.insert(position, new SimplePosition<String>("1"));
		tree.insert(position, new SimplePosition<String>("1"));
		arithmeticString = tree.getArithmeticString();
		//the outermost brackets are optional, both these cases will be accepted:
		assertTrue(arithmeticString.equals("1+(1+1)") || arithmeticString.equals("(1+(1+1))"));
		
	}
	
	@Test
	public void testInvalidTree() {
		// test tree holding integer values
				
		MyTree<Integer>tree = new MyTree<Integer>();
		tree.setRoot(new SimplePosition<Integer>(2));
		tree.insert(tree.root(), new SimplePosition<Integer>(4));
		tree.insert(tree.root(), new SimplePosition<Integer>(19));
		assertFalse(tree.isArithmetic());
		assertEquals("", tree.getArithmeticString());
		assertEquals(0, tree.evaluateArithmetic(), 0);
	}
	
	@Test
	public void testLargeTree() {
		
		MyTree<String> tree;
		String arithmeticString;
		
		// test 1+((1+(2/5))+3) = 1.5
		tree = new MyTree<String>();
		tree.setRoot(new SimplePosition<String>("+"));
		tree.insert(tree.root(), new SimplePosition<String>("1"));
		
		SimplePosition<String> position = new SimplePosition<String>("+");
		tree.insert(tree.root(), position);
		
		SimplePosition<String> position2 = new SimplePosition<String>("+");
		tree.insert(position, position2);

		tree.insert(position, new SimplePosition<String>("3"));
		
		tree.insert(position2, new SimplePosition<String>("1"));
		
		SimplePosition<String> position3 = new SimplePosition<String>("/");
		tree.insert(position2, position3);
		
		tree.insert(position3, new SimplePosition<String>("2"));
		tree.insert(position3, new SimplePosition<String>("5"));
		
		
		arithmeticString = tree.getArithmeticString();
		
		// Test 1- is it arithmetic?
		assertTrue(tree.isArithmetic());
		
		// Test 2- is it printed correctly?
		assertTrue(arithmeticString.equals("1+((1+(2/5))+3)") || 
				arithmeticString.equals("(1+((1+(2/5))+3))"));
		
		// Test 3- does it evaluate?
		assertEquals(5.4, tree.evaluateArithmetic(), 0.00001);
		
	}
	
}