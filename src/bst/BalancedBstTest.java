package bst;

import static org.junit.Assert.*;

import org.junit.Test;

import bst.SimpleBstPosition;
public class BalancedBstTest {
	
	
	@Test (timeout=1000)
	public void testOrderedInsert() {

		BalancedBst<String> bst = new BalancedBst<String>();
		
		// insert a,b,c,d should result in:
		//    b
		//  a   c
		//       d
		bst.insert("a");
		bst.insert("b");
		bst.insert("c");
		bst.insert("d");
		assertEquals("a", bst.root().getLeft().getElement());
		assertEquals("b", bst.root().getElement());
		assertEquals("c", bst.root().getRight().getElement());
		assertEquals("d", bst.root().getRight().getRight().getElement());
		assertEquals(4, bst.size());
		
		// insert e should result in:
		//    b
		//  a   d
		//     c e
		bst.insert("d");
		bst.insert("e");
		assertEquals("a", bst.root().getLeft().getElement());
		assertEquals("b", bst.root().getElement());
		assertEquals("c", bst.root().getRight().getLeft().getElement());
		assertEquals("d", bst.root().getRight().getElement());
		assertEquals("e", bst.root().getRight().getRight().getElement());
		assertEquals(5, bst.size());
		
		// insert f should result in:
		//    d
		//  b   e
		// a c   f
		bst.insert("f");
		assertEquals("a", bst.root().getLeft().getLeft().getElement());
		assertEquals("b", bst.root().getLeft().getElement());
		assertEquals("c", bst.root().getLeft().getRight().getElement());
		assertEquals("d", bst.root().getElement());
		assertEquals("e", bst.root().getRight().getElement());
		assertEquals("f", bst.root().getRight().getRight().getElement());
		assertEquals(6, bst.size());
		
		// insert g should then give us:
		//    d
		//  b   f
		// a c e g
		bst.insert("g");
		assertEquals("a", bst.root().getLeft().getLeft().getElement());
		assertEquals("b", bst.root().getLeft().getElement());
		assertEquals("c", bst.root().getLeft().getRight().getElement());
		assertEquals("d", bst.root().getElement());
		assertEquals("e", bst.root().getRight().getElement());
		assertEquals("f", bst.root().getRight().getRight().getElement());
		assertEquals(7, bst.size());
	
	}
	@Test
	public void testAdd() {
		BalancedBst<Integer> tree = new BalancedBst<Integer>();
		
		/*
		BstPosition<Integer> a = new SimpleBstPosition<>(46);
		BstPosition<Integer> b = new SimpleBstPosition<>(18);
		BstPosition<Integer> c = new SimpleBstPosition<>(95);
		BstPosition<Integer> d = new SimpleBstPosition<>(10);
		BstPosition<Integer> g = new SimpleBstPosition<>(34);
		BstPosition<Integer> e = new SimpleBstPosition<>(70);
		BstPosition<Integer> f = new SimpleBstPosition<>(96);
		*/
		
		//insert nodes into tree --> parent, child
		tree.insert(46);
		tree.insert(18);
		tree.insert(95);
		tree.insert(10);
		tree.insert(34);
		tree.insert(70);
		tree.insert(96);
		
		BalancedBst<String> pathTree = new BalancedBst<String>();
		pathTree.setRoot(new SimpleBstPosition<String>("x"));
		pathTree.setLeft(new SimpleBstPosition<String>("g"));
		
		BalancedBst<Integer> single = new BalancedBst<Integer>();
		single.setRoot(new SimpleBstPosition<Integer>(8));
		
		BalancedBst<Character> empty = new BalancedBst<Character>();
		
		
		
    	//== Object Tree Test ==
		
		//Adding existing values
		assertEquals(false, tree.insert(10));
		assertEquals(false, tree.insert(70));
		//Normal adding of values
		assertEquals(true, tree.insert(48));
		assertEquals(true, tree.insert(100));
		assertEquals(true, tree.insert(5));
		assertEquals(true, tree.insert(3));
		assertEquals(true, tree.insert(1));
		
		//Check the tree is still balanced
		assertEquals(true, tree.isBalancedBinary());
		
		
		//==Object pathTree test==
		assertEquals(true, pathTree.insert("a"));
		assertEquals(true, pathTree.insert("z"));
		
		//==Object empty test==
		
		assertEquals(true, empty.insert('h'));
		
	}
	
	@Test
	public void testRemove(){
		BalancedBst<Integer> tree = new BalancedBst<Integer>();
		/*SimpleBstPosition<Integer> a = new SimpleBstPosition<>(79);
		SimpleBstPosition<Integer> b = new SimpleBstPosition<>(12);
		SimpleBstPosition<Integer> c = new SimpleBstPosition<>(60);
		SimpleBstPosition<Integer> d = new SimpleBstPosition<>(9);
		SimpleBstPosition<Integer> e = new SimpleBstPosition<>(18);
		SimpleBstPosition<Integer> f = new SimpleBstPosition<>(49);
		SimpleBstPosition<Integer> g = new SimpleBstPosition<>(116);
		
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		a.setLeft(b);
		a.setRight(c);
		b.setLeft(d);
		b.setRight(e);
		c.setLeft(f);
		c.setRight(g);
		*/
		
		tree.insert(79);
		tree.insert(12);
		tree.insert(60);
		tree.insert(9);
		tree.insert(18);
		tree.insert(49);
		tree.insert(116);
		
		
		BalancedBst<Character> empty = new BalancedBst<Character>();
		
		BalancedBst<Character> insertTree = new BalancedBst<Character>();
		insertTree.setRoot(new SimpleBstPosition<Character>('x'));
		insertTree.insert('g');
		insertTree.insert('y');
		
		
		//==Test tree object== Testing a regular binary tree
		assertEquals(true, tree.remove(18));
		assertEquals(false, tree.remove(1));
		assertEquals(true, tree.remove(12));
		assertEquals(true, tree.remove(60));
		assertEquals(true, tree.isBalancedBinary());
		
		//==test empty object== Testing an empty object
		assertEquals(false,empty.remove('A'));
		assertEquals(true, empty.isEmpty());
		
		//==test insertTree object== Testing that add and remove work when run together
		assertEquals(true, insertTree.remove('y'));
		assertEquals(true, insertTree.remove('x')); //remove root
		assertEquals(true, insertTree.isBalancedBinary());
	}

}
