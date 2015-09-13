import java.util.ArrayList;
import java.util.List;

import interfaces.BalancedBST;
import interfaces.Position;
import interfaces.Tree;
import interfaces.TreeArithmetic;
import interfaces.TreeProperties;
import interfaces.TreeTraversals;
import simpletree.SimpleTree;


/**
 * @author krus4334
 * @author acam3311
 * 
 * This class, MyTree, should be your solution to the assignment
 * It should remain in the (default package)
 * 
 * Implement as many of the required methods as you can.
 */

public class MyTree<E extends Comparable<E>> extends SimpleTree<E> implements
				TreeTraversals<E>,      //PART 1
				TreeProperties,         //PART 2
				//Comparable<Tree<E>>,  //PART 3 (only if enrolled in INFO1105)
				BalancedBST<E>,       	//PART 3 (only if enrolled in INFO1905)
				TreeArithmetic          //PART 4
{

	//constructor
	public MyTree() {
		super(); //call the constructor of SimpleTree with no arguments
	}

	
	// Output the values of a pre-order traversal of the tree
	public List<E> preOrder(){
		if(this.root()==null){
			return new ArrayList<E>();
		}
		List<E> list = preOrder(this.root());
		return list;
	}
	
	public List<E> preOrder(Position<E> root){
		List<E> list = new ArrayList<E>();
		
        list.add(root.getElement());
        
        if (root.getChildren() != null){
        	for(Position<E> i : root.getChildren()){
        		list.addAll(preOrder(i));
        	}
        }
        return list;
	}
	
	// Output the values of a post-order traversal of the tree
	public List<E> postOrder(){
		if(this.root()==null){
			return new ArrayList<E>();
		}
		List<E> list = postOrder(this.root());
		return list;
	}
	
	public List<E> postOrder(Position<E> root){
		List<E> list = new ArrayList<E>();
        
        if (root.getChildren() != null){
        	for(Position<E> i : root.getChildren()){
        		list.addAll(postOrder(i));
        	}
        }
        list.add(root.getElement());
        
        return list;
	}
	
	
	// Output the values of a in-order traversal of the tree
	// This operation should only be performed if the tree is a proper binary tree.
	// If it is not, then throw an UnsupportedOperationException instead of returning a value
	// Otherwise, perform the traversal with child 0 on the left, and child 1 on the right.
	//TO FIX
	
	public List<E> inOrder(){
		if(this.root()==null){
			return new ArrayList<E>();
		}
		List<E> list = inOrder(this.root());
		return list;
	}
	
	public List<E> inOrder(Position<E> root){
		List<E> list = new ArrayList<E>();
		
		if(!isProperBinary()){
			throw new UnsupportedOperationException();
		}
		
    	if(root.getChildren().get(0) != null){
    		list.addAll(inOrder(root.getChildren().get(0)));//get left child
    	}
    	list.add(root.getElement());
    	
    	if(root.getChildren().get(1) != null){
    		list.addAll(inOrder(root.getChildren().get(1)));//get right child
    	}
    	
    	return list;
	}
	
	//Helper function to call recursion
	public boolean isProperBinary(){
		if(this.root()==null){
			return this.isEmpty();
		}
		boolean isProper = isProperBinary(this.root());
		return isProper;
		
	}
	// is the tree a proper binary tree?
	// every position in a proper binary tree has either zero or two children
	public boolean isProperBinary(Position<E> root){
		if (root.getChildren() != null){
			for(Position<E> i:root.getChildren()){
	        	if(root.getChildren().size()!=2 && root.getChildren().size()!=0){
	        		return false;
	        	}
	        	else{
	        		isProperBinary(i);
	        	}
			}
        }
		return true;	
	}
	
	// calculate the height of the tree (the maximum depth of any position in the tree.)
	// a tree with only one position has height 0.
	// a tree where the root has children, but no grandchildren has height 1.
	// a tree where the root has grandchildren, but no great-grandchildren has height 2.
	
	public int height(){
		if(this.root()==null){
			return -1;
		}
		int height = height(this.root());
		return height;
	}
	
	public int height(Position<E> root){
		int height = 0;
		for(Position<E> i : root.getChildren()){
			//System.out.println(height + "normal height");
			height = 1+height(i);
		}
		return height;
		
	}
	
	
	//we can use this.root to get the root!!!!!! do something if it's empty? FIX
	
	// calculate the height of the tree, but do not descend deeper than �depth� edges into the tree
	// do not visit any nodes deeper than maxDepth while calculating this
	// do not call your height() method
	// (some trees are very, very, very big!) TO DO
	
	public int height(int maxDepth){
		if(isEmpty()){
			return -1;
		}
		int height = height(maxDepth, 0, this.root());
		return height;
	}
	public int height(int maxDepth, int height, Position<E> root){

		if(height == maxDepth){
			return height;
		}
		else if(height<maxDepth){
			for(Position<E> i : root.getChildren()){
				height++;
				//System.out.println(1+height(maxDepth,height, i));
				height = height(maxDepth, height, i);
			}
		}
		//System.out.println(height);
		return height;	
	}
	
	// calculate the number of leaves of the tree (positions with no children)
	public int numLeaves(){ //helper function for recursion call
		//deal with empty tree
		if(this.root() == null){
			return 0;
		}
		int leaves = numLeaves(this.root(), 0);
		return leaves;
	}
	
	public int numLeaves(Position<E> node, int leaves){
		if(node.getChildren().size()==0){
			leaves++;
		}
		else{
			for(Position<E> i : node.getChildren()){
				leaves = numLeaves(i, leaves);
			}
		}
		return leaves;
		
	}
	
	// calculate the number of leaves of the tree at exactly depth depth.
	// the root is at depth 0. The children of the root are at depth 1.
	public int numLeaves(int depth); //TO DO
	

	public int numPositions(int depth); 
	// calculate the number of positions at exactly depth depth. TO DO!!!!!
	
	// is the tree a binary tree?
	// every position in a binary tree has no more than 2 children
	public boolean isBinary(){
		if(this.root()==null){
			return this.isEmpty();
		}
		boolean isBin = isBinary(this.root());
		return isBin;
	}
	
	public boolean isBinary(Position<E> root){
		if (root.getChildren() != null){
			for(Position<E> i:root.getChildren()){
	        	if(root.getChildren().size()>2){
	        		return false;
	        	}
	        	else{
	        		isBinary(i);
	        	}
			}
        }
		return true;
	}

	public boolean isCompleteBinary();
	// is the tree complete?
	// a complete tree is one where:
	// 1) all the levels except the last must be full 
	// 2) all leaves in the last level are filled from left to right (no gaps)
	
	public boolean isBalancedBinary(){
		if(isEmpty()){
			return isEmpty();
		}
		boolean balanced = isBalancedBinary(this.root());
		return balanced;
	}
	
	public boolean isBalancedBinary(Position<E> root){
		int leftHeight, rightHeight=0;
		if(this.root().getChildren().size()==1){ //you know there is only a left tree
			leftHeight = height(this.root().getChildren().get(0));
		}
		
		else{
			
			leftHeight= height(this.root().getChildren().get(0));
			rightHeight = height(this.root().getChildren().get(1));
		}
		if(leftHeight-rightHeight>1 || leftHeight-rightHeight<0){
			return false;
		}
		return true;
	}
	/*public boolean isBalancedBinary(){
		int leftSub, rightSub;
		if(isEmpty()){
			return isEmpty();
		}
		if(!isBinary()){
			return false;
		}

		if(this.root().getChildren().size()>0){
			if(this.root().getChildren().get(0)!=null){
				leftSub = height(this.root().getChildren().get(0));
			}
			else{
				leftSub = 0;
			}
			if(this.root().getChildren().get(1)==null){
				rightSub = 0;
			}
			else{
				rightSub = height(this.root().getChildren().get(1));
			}
			if(leftSub-rightSub>1 || leftSub-rightSub<0){
				return false;
				}
		}
		return true;	
	}*/
	
	// is the tree a balanced binary tree?
	// a balanced tree is one where for every position in the tree, the
	// subtrees rooted at each of the children of the that position have
	// heights that differ by no more than one.
	// NOTE: if a node has only one child, the other child is considered to be a subtree of height ­1

	public boolean isHeap(boolean min);
	// is the tree a min-heap (if min is True), or is the tree a max-heap (if min is False)
	// heaps are trees which are both complete and have the heap property:
	// in a min-heap, the value of a node is less than or equal to the value of each of its children
	// similarly, in a max-heap the value of a node is greater than or equal to the value of each child

	public boolean isBinarySearchTree();
	// is the tree a binary search tree?
	// a binary search tree is a binary tree such that for any node with value v:
	// - if there is a left child (child 0 is not null), it contains a value strictly less than v.
	// - if there is a right child (child 1 is not null), it contains a value strictly greater than v.
	

	public boolean add(E value);
	// if value is already in the balanced BST, do nothing and return false
	// otherwise, add value to the balanced binary search tree (BST) and return true
	// use the algorithm shown in the week 6 lecture ­ the BST must remain balanced

}
