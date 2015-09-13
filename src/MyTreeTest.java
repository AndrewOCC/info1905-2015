import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import interfaces.Position;
import simpletree.SimplePosition;



public class MyTreeTest {

	@Test
	public void testPreOrder() {
		MyTree<Integer> tree = new MyTree<Integer>();
		//create node objects
		Position<Integer> a = new SimplePosition<>(5);
		Position<Integer> b = new SimplePosition<>(10);
		Position<Integer> c = new SimplePosition<>(14);
		Position<Integer> d = new SimplePosition<>(4);
		//insert nodes into tree --> parent, child
		tree.insert(a, b);
		tree.insert(b, c);
		tree.insert(b, d);
		assertEquals(Arrays.asList(5, 10, 14, 4), tree.preOrder(a));	
	}
	
	@Test
	public void testPostOrder() {
		MyTree<Integer> tree = new MyTree<Integer>();
		//create node objects
		Position<Integer> a = new SimplePosition<>(5);
		Position<Integer> b = new SimplePosition<>(10);
		Position<Integer> c = new SimplePosition<>(14);
		Position<Integer> d = new SimplePosition<>(4);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		tree.insert(a, b);
		tree.insert(b, c);
		tree.insert(b, d);
		assertEquals(Arrays.asList(14, 4, 10, 5), tree.postOrder(a));
		assertEquals(false, tree.isProperBinary()); //test is proper binary tree
	}
	@Test
	public void testInOrder() {
		MyTree<Integer> tree = new MyTree<Integer>();
		//create node objects
		Position<Integer> a = new SimplePosition<>(5);
		Position<Integer> b = new SimplePosition<>(10);
		Position<Integer> c = new SimplePosition<>(14);
		Position<Integer> d = new SimplePosition<>(4);
		Position<Integer> e = new SimplePosition<>(7);
		Position<Integer> f= new SimplePosition<>(1);
		Position<Integer> g = new SimplePosition<>(8);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		tree.insert(a, b);
		tree.insert(a, c);
		tree.insert(b, d);
		tree.insert(b, e);
		tree.insert(d, f);
		tree.insert(d, g);
		
		//assertEquals(Arrays.asList(14, 4, 10, 5), tree.postOrder(a));
		assertEquals(true, tree.isProperBinary()); //test is proper binary tree
		//System.out.println(tree.isProperBinary());
		assertEquals(Arrays.asList(1, 4, 8, 10, 7, 5, 14), tree.inOrder(a));
	}
	
	
	@Test
	public void testHeight() {
		MyTree<Integer> tree = new MyTree<Integer>();
		//create node objects
		Position<Integer> a = new SimplePosition<>(5);
		Position<Integer> b = new SimplePosition<>(10);
		Position<Integer> c = new SimplePosition<>(14);
		Position<Integer> d = new SimplePosition<>(4);
		Position<Integer> e = new SimplePosition<>(15);
		//insert nodes into tree --> parent, child
		//tree.setRoot(a);
		tree.insert(a, b);
		tree.insert(b, c);
		tree.insert(b, d);
		tree.insert(b, e);
		assertEquals(2, tree.height(a));
		assertEquals(1, tree.height(1, a));
		assertEquals(0, tree.height(0,a));
		//assertEquals(1, )  // to do! for max depth
	}
	
	@Test
	public void testNumLeaves() {
		MyTree<Integer> tree = new MyTree<Integer>();
		assertEquals(0, tree.numLeaves());
		//create node objects
		Position<Integer> a = new SimplePosition<>(5);
		Position<Integer> b = new SimplePosition<>(10);
		Position<Integer> c = new SimplePosition<>(14);
		Position<Integer> d = new SimplePosition<>(4);
		Position<Integer> e = new SimplePosition<>(15);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		assertEquals(1, tree.numLeaves());
		tree.insert(a, b);
		tree.insert(b, c);
		tree.insert(b, d);
		tree.insert(b, e);
		assertEquals(3, tree.numLeaves());	
		assertEquals(3, tree.numLeaves(2));
		assertEquals(0, tree.numLeaves(1));
	}

}
