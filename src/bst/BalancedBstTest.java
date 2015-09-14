package bst;

import static org.junit.Assert.*;

import org.junit.Test;

import bst.SimpleBstPosition;
public class BalancedBstTest {
	
	
	@Test
	public void testAdd() {
		BinarySearchTree<Integer> tree = new SimpleBst<Integer>();
		BstPosition<Integer> a = new SimpleBstPosition<>(46);
		BstPosition<Integer> b = new SimpleBstPosition<>(18);
		BstPosition<Integer> c = new SimpleBstPosition<>(95);
		BstPosition<Integer> d = new SimpleBstPosition<>(10);
		BstPosition<Integer> g = new SimpleBstPosition<>(34);
		BstPosition<Integer> e = new SimpleBstPosition<>(70);
		BstPosition<Integer> f = new SimpleBstPosition<>(96);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		a.setLeft(b);
		a.setRight(c);
		b.setLeft(d);
		b.setRight(g);
		c.setLeft(e);
		c.setRight(f);
		
		BinarySearchTree<String> pathTree = new SimpleBst<String>();
		pathTree.setRoot(new SimpleBstPosition<String>("x"));
		pathTree.setLeft(new SimpleBstPosition<String>("g"));
		
		BinarySearchTree<Integer> single = new SimpleBst<Integer>();
		single.setRoot(new SimpleBstPosition<Integer>(8));
		
		BinarySearchTree<Character> empty = new SimpleBst<Character>();
		
		
		
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
		BinarySearchTree<Integer> tree = new SimpleBst<Integer>();
		BstPosition<Integer> a = new SimpleBstPosition<>(79);
		BstPosition<Integer> b = new SimpleBstPosition<>(12);
		BstPosition<Integer> c = new SimpleBstPosition<>(60);
		BstPosition<Integer> d = new SimpleBstPosition<>(9);
		BstPosition<Integer> e = new SimpleBstPosition<>(18);
		BstPosition<Integer> f = new SimpleBstPosition<>(49);
		BstPosition<Integer> g = new SimpleBstPosition<>(116);
		//insert nodes into tree --> parent, child
		tree.setRoot(a);
		tree.insert(a, b);
		tree.insert(a, c);
		tree.insert(b, d);
		tree.insert(b, e);
		tree.insert(c, f);
		tree.insert(c, g);
		
		BinarySearchTree<Character> empty = new SimpleBst<Character>();
		
		BinarySearchTree<Character> insertTree = new SimpleBst<Character>();
		insertTree.setRoot(new SimpleBstPosition<Character>('x'));
		insertTree.insert(insertTree.root(), new SimpleBstPosition<Character>('g'));
		insertTree.insert(insertTree.root(), new SimpleBstPosition<Character>('y'));
		
		
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
