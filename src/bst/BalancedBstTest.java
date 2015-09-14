<<<<<<< HEAD:src/BSTtest.java
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import interfaces.Position;
import simpletree.SimplePosition;

public class BSTtest {
	@Test (timeout=1000)
	public
	@Test
	public void testAdd() {
		MyTree<Integer> tree = new MyTree<Integer>();
		Position<Integer> a = new SimplePosition<>(46);
		Position<Integer> b = new SimplePosition<>(18);
		Position<Integer> c = new SimplePosition<>(95);
		Position<Integer> d = new SimplePosition<>(10);
		Position<Integer> g = new SimplePosition<>(34);
		Position<Integer> e = new SimplePosition<>(70);
		Position<Integer> f = new SimplePosition<>(96);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		tree.insert(a, b);
		tree.insert(a, c);
		tree.insert(b, d);
		tree.insert(b, g);
		tree.insert(c, e);
		tree.insert(c, f);
		
		MyTree<String> pathTree = new MyTree<String>();
		pathTree.setRoot(new SimplePosition<String>("x"));
		pathTree.insert(pathTree.root(), new SimplePosition<String>("g"));
		
		MyTree<Integer> single = new MyTree<Integer>();
		single.setRoot(new SimplePosition<Integer>(8));
		
		MyTree<Character> empty = new MyTree<Character>();
		
		
		
    	//== Object Tree Test ==
		
		//Adding existing values
		assertEquals(false, tree.add(10));
		assertEquals(false, tree.add(70));
		//Normal adding of values
		assertEquals(true, tree.add(48));
		assertEquals(true, tree.add(100));
		assertEquals(true, tree.add(5));
		assertEquals(true, tree.add(3));
		assertEquals(true, tree.add(1));
		
		//Check the tree is still balanced
		assertEquals(true, tree.isBalancedBinary());
		
		
		//==Object pathTree test==
		assertEquals(true, pathTree.add("a"));
		assertEquals(true, pathTree.add("z"));
		
		//==Object empty test==
		
		assertEquals(true, empty.add('h'));
		
	}
	
	@Test
	public void testRemove(){
		MyTree<Integer> tree = new MyTree<Integer>();
		Position<Integer> a = new SimplePosition<>(79);
		Position<Integer> b = new SimplePosition<>(12);
		Position<Integer> c = new SimplePosition<>(60);
		Position<Integer> d = new SimplePosition<>(9);
		Position<Integer> e = new SimplePosition<>(18);
		Position<Integer> f = new SimplePosition<>(49);
		Position<Integer> g = new SimplePosition<>(116);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		tree.insert(a, b);
		tree.insert(a, c);
		tree.insert(b, d);
		tree.insert(b, e);
		tree.insert(c, f);
		tree.insert(c, g);
		
		MyTree<Character> empty = new MyTree<Character>();
		
		MyTree<Character> insertTree = new MyTree<Character>();
		insertTree.setRoot(new SimplePosition<Character>('x'));
		insertTree.insert(insertTree.root(), new SimplePosition<Character>('g'));
		insertTree.insert(insertTree.root(), new SimplePosition<Character>('y'));
		
		
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
		assertEquals(true, tree.isBalancedBinary());
	}

}
=======
package bst;

import static org.junit.Assert.*;

import org.junit.Test;

import simpletree.SimplePosition;

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

/*
	@Test
	public void testAdd() {
		BalancedBst<Integer> tree = new BalancedBst<String>();
		Position<Integer> a = new SimplePosition<>(46);
		Position<Integer> b = new SimplePosition<>(18);
		Position<Integer> c = new SimplePosition<>(95);
		Position<Integer> d = new SimplePosition<>(10);
		Position<Integer> g = new SimplePosition<>(34);
		Position<Integer> e = new SimplePosition<>(70);
		Position<Integer> f = new SimplePosition<>(96);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		tree.insert(a, b);
		tree.insert(a, c);
		tree.insert(b, d);
		tree.insert(b, g);
		tree.insert(c, e);
		tree.insert(c, f);
		
		MyTree<String> pathTree = new MyTree<String>();
		pathTree.setRoot(new SimplePosition<String>("x"));
		pathTree.insert(pathTree.root(), new SimplePosition<String>("g"));
		
		MyTree<Integer> single = new MyTree<Integer>();
		single.setRoot(new SimplePosition<Integer>(8));
		
		MyTree<Character> empty = new MyTree<Character>();
		
		
		
    	//== Object Tree Test ==
		
		//Adding existing values
		assertEquals(false, tree.add(10));
		assertEquals(false, tree.add(70));
		//Normal adding of values
		assertEquals(true, tree.add(48));
		assertEquals(true, tree.add(100));
		assertEquals(true, tree.add(5));
		assertEquals(true, tree.add(3));
		assertEquals(true, tree.add(1));
		
		//Check the tree is still balanced
		assertEquals(true, tree.isBalancedBinary());
		for(Integer i:tree.inOrder()){
			System.out.println(i);
		}
		
		//==Object pathTree test==
		assertEquals(true, pathTree.add("a"));
		assertEquals(true, pathTree.add("z"));
		
		//==Object empty test==
		
		assertEquals(true, empty.add('h'));
		
	}
	
	@Test
	public void testRemove(){
		BalancedBst<Integer> tree = new BalancedBst<Integer>();
		Position<Integer> a = new SimplePosition<>(79);
		Position<Integer> b = new SimplePosition<>(12);
		Position<Integer> c = new SimplePosition<>(60);
		Position<Integer> d = new SimplePosition<>(9);
		Position<Integer> e = new SimplePosition<>(18);
		Position<Integer> f = new SimplePosition<>(49);
		Position<Integer> g = new SimplePosition<>(116);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		tree.insert(a, b);
		tree.insert(a, c);
		tree.insert(b, d);
		tree.insert(b, e);
		tree.insert(c, f);
		tree.insert(c, g);
		
		MyTree<Character> empty = new MyTree<Character>();
		
		MyTree<Character> insertTree = new MyTree<Character>();
		insertTree.setRoot(new SimplePosition<Character>('x'));
		insertTree.insert(insertTree.root(), new SimplePosition<Character>('g'));
		insertTree.insert(insertTree.root(), new SimplePosition<Character>('y'));
		
		
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
		assertEquals(true, tree.isBalancedBinary());
	}
*/

}
>>>>>>> c975791314b71b52bdabe4543756140bbc0348b0:src/bst/BalancedBstTest.java
