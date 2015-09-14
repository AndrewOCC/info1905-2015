import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

import simpletree.SimplePosition;

public class TreeTraversalsTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

	private MyTree<String> treeLargeUnbalanced;
	private MyTree<Integer> treeInteger;
	private MyTree<String> treeNonBinary;
	private MyTree<String> treePathAbcd;
    
    @Before
    public void setup(){
     	
    	// A larger, more complex (unbalanced) tree
    	treeLargeUnbalanced= new MyTree<String>();
    	treeLargeUnbalanced.setRoot(new SimplePosition<String>("a"));
    	treeLargeUnbalanced.insert(treeLargeUnbalanced.root(), new SimplePosition<String>("b"));
    	treeLargeUnbalanced.insert(treeLargeUnbalanced.root(), new SimplePosition<String>("c"));
    	treeLargeUnbalanced.insert(treeLargeUnbalanced.root().getChildren().get(0), new SimplePosition<String>("d"));
    	treeLargeUnbalanced.insert(treeLargeUnbalanced.root().getChildren().get(0), new SimplePosition<String>("e"));
    	SimplePosition<String> position = new SimplePosition<String>("f");
    	treeLargeUnbalanced.insert(treeLargeUnbalanced.root().getChildren().get(0).getChildren().get(0), position);
    	treeLargeUnbalanced.insert(position, new SimplePosition<String>("g"));
    	treeLargeUnbalanced.insert(position, new SimplePosition<String>("h"));
    	
    	// A tree with three children on one node
    	treeNonBinary = new MyTree<String>();
    	treeNonBinary.setRoot(new SimplePosition<String>("a"));
    	treeNonBinary.insert(treeNonBinary.root(), new SimplePosition<String>("b"));
    	treeNonBinary.insert(treeNonBinary.root().getChildren().get(0), new SimplePosition<String>("c"));
    	treeNonBinary.insert(treeNonBinary.root().getChildren().get(0), new SimplePosition<String>("d"));
    	treeNonBinary.insert(treeNonBinary.root().getChildren().get(0), new SimplePosition<String>("e"));

    	// A tree with integer items
    	treeInteger= new MyTree<Integer>();
    	treeInteger.setRoot(new SimplePosition<Integer>(1));
    	treeInteger.insert(treeInteger.root(), new SimplePosition<Integer>(2));
    	treeInteger.insert(treeInteger.root(), new SimplePosition<Integer>(3));
    	treeInteger.insert(treeInteger.root().getChildren().get(0), new SimplePosition<Integer>(4));
    	treeInteger.insert(treeInteger.root().getChildren().get(0), new SimplePosition<Integer>(5));

    	
    	// A 'path' tree where each element has a single child 
    	treePathAbcd = new MyTree<String>();
    	treePathAbcd.setRoot(new SimplePosition<String>("a"));
    	treePathAbcd.insert(treePathAbcd.root(), new SimplePosition<String>("b"));
    	treePathAbcd.insert(treePathAbcd.root().getChildren().get(0), new SimplePosition<String>("c"));
    	treePathAbcd.insert(treePathAbcd.root().getChildren().get(0).getChildren().get(0), new SimplePosition<String>("d"));

    }
    
	@Test (timeout=1000)
	public void testPreOrder() {
		
		
		//Construction Test (tests empty, single element and a basic tree)
		MyTree<String> tree = new MyTree<String>();
		
		assertEquals(new ArrayList<String>(), tree.preOrder());
		
		tree.setRoot(new SimplePosition<String>("root"));
		assertEquals(Arrays.asList(new String[] {"root"}), tree.preOrder());

		tree.insert(tree.root(), new SimplePosition<String>("a"));
		assertEquals(Arrays.asList(new String[] {"root", "a"}), tree.preOrder());

		tree.insert(tree.root(), new SimplePosition<String>("b"));
		assertEquals(Arrays.asList(new String[] {"root", "a", "b"}), tree.preOrder());
		
		// Test large, unbalanced tree
		assertEquals(Arrays.asList(new String[] {"a", "b", "d", "f", "g", "h", "e", "c"}), treeLargeUnbalanced.preOrder());
		
		// Test non-binary tree
		assertEquals(Arrays.asList(new String[] {"a", "b", "c", "d", "e"}), treeNonBinary.preOrder());
		
		// Test a 'path' tree
		assertEquals(Arrays.asList(new String[] {"a", "b", "c", "d"}), treePathAbcd.preOrder());
		
		// Tests a tree of integers (testing type independence)
		assertEquals(Arrays.asList(new Integer[] {1,2,4,5,3}), treeInteger.preOrder());
		
	}

	@Test (timeout=1000)
	public void testPostOrder() {
		
		//Construction Test (tests empty, single element and a basic tree)
		MyTree<String> tree = new MyTree<String>();
		
		assertEquals(new ArrayList<String>(), tree.postOrder());
		
		tree.setRoot(new SimplePosition<String>("root"));
		assertEquals(Arrays.asList(new String[] {"root"}), tree.postOrder());

		tree.insert(tree.root(), new SimplePosition<String>("a"));
		assertEquals(Arrays.asList(new String[] {"a", "root"}), tree.postOrder());

		tree.insert(tree.root(), new SimplePosition<String>("b"));
		assertEquals(Arrays.asList(new String[] {"a", "b", "root"}), tree.postOrder());
		
		// Test large, unbalanced tree
		assertEquals(Arrays.asList(new String[] {"g", "h", "f", "d", "e", "b", "c", "a"}), treeLargeUnbalanced.postOrder());
		
		// Test non-binary tree
		assertEquals(Arrays.asList(new String[] {"c", "d", "e", "b", "a"}), treeNonBinary.postOrder());
		
		// Test a 'path' tree
		assertEquals(Arrays.asList(new String[] {"d", "c", "b", "a"}), treePathAbcd.postOrder());
		
		// Tests a tree of integers (testing type independence)
		assertEquals(Arrays.asList(new Integer[] {4,5,2,3,1}), treeInteger.postOrder());

	}
	
	@Test (timeout=1000)
	public void testInOrder() {
		
		MyTree<String> tree = new MyTree<String>();
		
		assertEquals(new ArrayList<String>(), tree.inOrder());
		
		tree.setRoot(new SimplePosition<String>("root"));
		assertEquals(Arrays.asList(new String[] {"root"}), tree.inOrder());

		tree.insert(tree.root(), new SimplePosition<String>("a"));
        exception.expect(UnsupportedOperationException.class);
		tree.inOrder();

		tree.insert(tree.root(), new SimplePosition<String>("b"));
		assertEquals(Arrays.asList(new String[] {"a", "root", "b"}), tree.inOrder());

		tree.insert(tree.root(), new SimplePosition<String>("c"));
        exception.expect(UnsupportedOperationException.class);
		tree.inOrder();
		
		// Test large, unbalanced tree
		exception.expect(UnsupportedOperationException.class);
		treeLargeUnbalanced.inOrder();
		
		// add another element which makes the tree proper
		SimplePosition<String> position = new SimplePosition<String>("i");
    	treeLargeUnbalanced.insert(treeLargeUnbalanced.root().getChildren().get(0).getChildren().get(0), position);
		assertEquals(Arrays.asList(new String[] {"g", "f", "h", "d", "i", "b", "e", "a", "c"}), treeLargeUnbalanced.preOrder());
		
		// Test non-binary tree
		exception.expect(UnsupportedOperationException.class);
		treeNonBinary.inOrder();
		
		// Test a 'path' tree
		exception.expect(UnsupportedOperationException.class);
		treePathAbcd.inOrder();
		
		// Tests a tree of integers (testing type independence)
		assertEquals(Arrays.asList(new Integer[] {4,2,5,3,1}), treeInteger.postOrder());
		

	}

}
