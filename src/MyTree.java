import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import interfaces.BalancedBST;
import interfaces.Position;
import interfaces.TreeArithmetic;
import interfaces.TreeProperties;
import interfaces.TreeTraversals;
import simpletree.SimpleTree;
import simpletree.SimplePosition;


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
				//Comparable<Tree<E>>,	//PART 3 (only if enrolled in INFO1105)
				BalancedBST<E>,       //PART 3 (only if enrolled in INFO1905)
				TreeArithmetic          //PART 4
{

//------------------------------------------------------------------------------------
//											PART I	
//------------------------------------------------------------------------------------
	
	
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
	
	private List<E> preOrder(Position<E> root){
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
	
	private List<E> postOrder(Position<E> root){
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
	
	public List<E> inOrder(){
		if(this.root()==null){
			return new ArrayList<E>();
		}
		List<E> list = inOrder(this.root());
		return list;
	}
	
	private List<E> inOrder(Position<E> root){
		List<E> list = new ArrayList<E>();
		
		if(!isProperBinary()){
			throw new UnsupportedOperationException();
		}
	
		// create variables to determine if the children exist
		boolean isLeftChild = false, isRightChild = false;
		
		if (root.getChildren().size() == 2){
			isLeftChild = isRightChild = true;
		}
		else if (root.getChildren().size() == 1){
			isLeftChild = true;
		}
		
    	if(isLeftChild == true){
    		list.addAll(inOrder(root.getChildren().get(0)));//get left child
    	}
    	list.add(root.getElement());
    	
    	if(isRightChild == true){
    		list.addAll(inOrder(root.getChildren().get(1)));//get right child
    	}
    	
    	return list;
	}
	
//------------------------------------------------------------------------------------
//									PART II	
//------------------------------------------------------------------------------------
	
	
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
	private boolean isProperBinary(Position<E> root){
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
		
		if (this.isEmpty()){
			return -1;
		}
		
		return height(this.root());
		
	}
	
	// Helper function for recursive calls
	private int height(Position<E> root){
		int height = 0;
		
		// Finds maximum height of children
		for(Position<E> i : root.getChildren()){
			int childHeight = height(i);
			
			if (1+childHeight > height){
				height = 1 + childHeight;
			}
			
		}
		return height;
		
	}
	
	
	
	// calculate the height of the tree, but do not descend deeper than depth edges into the tree
	// do not visit any nodes deeper than maxDepth while calculating this
	// do not call your height() method
	// (some trees are very, very, very big!) TO DO
	
	public int height(int maxDepth){
		if(isEmpty()){
			return -1;
		}
		
		// Deals with depth parameters outside the possible depths in the tree
		if(maxDepth < 0){
			return 0;
		}
		
		int height = height(maxDepth, 0, this.root());
		return height;
	}
	
	// Helper function for recursive calls
	private int height(int maxDepth, int height, Position<E> root){
		
		int maxHeight = 0;
		
		if(height == maxDepth){
			return 0;
		}
		
		else if(height<maxDepth){
			for(Position<E> i : root.getChildren()){
				int childHeight = height(maxDepth, height + 1, i);
				
				//find maximum height from children
				if (1 + childHeight > maxHeight){
					maxHeight = 1 + childHeight;
				}
			}
		}
		return maxHeight;	
	}
	
	
	
	
	// calculate the number of leaves of the tree (positions with no children)
	public int numLeaves(){
		//deal with empty tree
		if(this.root() == null){
			return 0;
		}
		
		int leaves = numLeaves(this.root(), 0);
		return leaves;
	}
	
	//helper function for recursion call
	private int numLeaves(Position<E> node, int leaves){
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
	
	
	
	// calculate the number of leaves of the tree at exactly *depth* depth.
	// the root is at depth 0. The children of the root are at depth 1.

	public int numLeaves(int depth){
		
		// Deal with empty tree
		if(this.root() == null){
			return 0;
		}
		
		// Deals with depth parameters outside the possible depths in the tree
		if(depth > this.height() || depth < 0){
			return 0;
		}
		
		int leaves = numLeavesDepth(this.root(), depth, 0);
		return leaves;
		
	}
	
	// Helper function for recursive call
	private int numLeavesDepth(Position<E> node, int depth, int currentDepth){
		
		int leaves = 0;
		
		// If at the requested depth
		if(currentDepth == depth){
			if (node.getChildren().size() == 0){
				leaves ++;
			} 
			else {
				return 0;
			}
		} 

		else {
			if(node.getChildren().size() == 0){
				return 0;
			} 
			else {
				for(Position<E> i : node.getChildren()){
					leaves += numLeavesDepth(i, depth, currentDepth+1);
				}
			}
				
		}
		return leaves;
	}
	
	
	public int numPositions(int depth){
	// calculate the number of positions at exactly depth depth.
	
		// Deal with empty tree
		if(this.root() == null){
			return 0;
		}
		
		// Deals with depth parameters outside the possible depths in the tree
		if(depth > this.height() || depth < 0){
			return 0;
		}
		
		int leaves = numPositionsDepth(this.root(), depth, 0);
		return leaves;
		
	}
	
	// Helper function for numPositions(int depth)
	private int numPositionsDepth(Position<E> node, int depth, int currentDepth){
		
		int leaves = 0;
		
		// If node at the requested depth
		if(currentDepth == depth){
			leaves ++; 
		} 
		
		// Otherwise, call recursively on any children
		else {
			if(node.getChildren().size() == 0){
				return 0;
			} 
			else {
				for(Position<E> i : node.getChildren()){
					leaves += numPositionsDepth(i, depth, currentDepth+1);
				}
			}
				
		}
		
		return leaves;
	}
		
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

	
	public boolean isCompleteBinary(){
	// is the tree complete?
	// a complete tree is one where:
	// 1) all the levels except the last must be full 
	// 2) all leaves in the last level are filled from left to right (no gaps)
        if(this.isBinary() == false){
			return false;
		}
		
		if(this.size() == 0){
			return true;
		}
		
		else {
			return isCompleteBinary(this.root(), 0);
		}
		
	}
	
	// Recursively inspects the array, checking that the current index (using the index
	// calculations in an array-based binary tree) is not greater than or equal to the
	// number of items in the tree. If it is, then it cannot be a complete binary tree
	private boolean isCompleteBinary(Position<E> node, int currentIndex){
		
		boolean isComplete = true;
		boolean isChildComplete = true;
				
		if(currentIndex >= this.size()){
			return false;
		} else {
			for (int i = 0; i < node.getChildren().size(); i++){
				isChildComplete = isCompleteBinary(node.getChildren().get(i), 
						currentIndex*2+1+i); 
				isComplete = (isComplete && isChildComplete);
				
				//skips further checks once a false value is found
				if (isComplete == false){
					break;
				}
			}
			return isComplete;
		}
		
	}
		
	public boolean isBalancedBinary(){
		if(isEmpty()){
			return isEmpty();
		}
		boolean balanced = isBalancedBinary(this.root());
		return balanced;
    }

	
	//find out if balanced binary tree by comparing height of left and right subtrees
	public boolean isBalancedBinary(Position<E> node){
		int leftSub, rightSub;
		if(this.isEmpty()){
			return isEmpty();
		}
		if(!isBinary()){
			return false;
		}
		if(node.getChildren().size()>0){
			if(node.getChildren().size()>=1){
				leftSub = 1 + height(node.getChildren().get(0)); //+1 to account for root
			}
			else{
				leftSub = 0;
			}
			if(node.getChildren().size()>1){
				rightSub = 1 + height(node.getChildren().get(1)); //+1 to account for root
			}
			else{
				rightSub = 0;
			}
			//Find the difference in the heights
			if(Math.abs(leftSub-rightSub)>1){
				return false;
			}
		}
		return true;	
	}
	


	
	public boolean isHeap(boolean min){
	// is the tree a min-heap (if min is True), or is the tree a max-heap (if min is False)
	// heaps are trees which are both complete and have the heap property:
	// in a min-heap, the value of a node is less than or equal to the value of each of its children
	// similarly, in a max-heap the value of a node is greater than or equal to the value of each child

		if(this.size() == 0){
			return true;
		}
		else if (this.isCompleteBinary() == false){
			return false;
		}
		else {
			return isHeap(this.root(), min);
		}
		
		
	}
	
	// Helper function for Recursion
	private boolean isHeap(Position<E> node, boolean min){
		Position<E> leftChild = null, rightChild = null;
		boolean isHeap = false;	//if none of the below conditions are met, then false will be returned
		
		// set child variables
		if(node.getChildren().size() == 2){
			leftChild = node.getChildren().get(0);
			rightChild = node.getChildren().get(1);
		}
		// if only one child, must be left child
		else if(node.getChildren().size() == 1){
			leftChild = node.getChildren().get(0);
		} 
		// an empty tree is a heap
		else {	
			return true;
		}
		
		// min-heap
		if(min == true){
			if(leftChild.getElement().compareTo(node.getElement()) >= 0){
				if(rightChild == null){
					isHeap = isHeap(leftChild, min);		// overrides initial false value for isHeap
				}
				else if (rightChild.getElement().compareTo(node.getElement()) >= 0){
					isHeap = isHeap(leftChild, min);
					isHeap = isHeap && isHeap(rightChild, min);
				}
			}
		}
		
		//max heap
		else {
			if(leftChild.getElement().compareTo(node.getElement()) <= 0){
				if(rightChild == null){
					isHeap = isHeap(leftChild, min);
				}
				else if (rightChild.getElement().compareTo(node.getElement()) <= 0){
					isHeap = isHeap(leftChild, min);
					isHeap = isHeap && isHeap(rightChild, min);
				}
			}
		}
		
		return isHeap;
	}
	
	
	
	public boolean isBinarySearchTree(){
	// is the tree a binary search tree?
	// a binary search tree is a binary tree such that for any node with value v:
	// - if there is a left child (child 0 is not null), it contains a value strictly less than v.
	// - if there is a right child (child 1 is not null), it contains a value strictly greater than v.
	// if there is only one child, you may assume that is a left child.
		if(this.size() == 0){
			return true;
		}
		
		else {
			return isBinarySearchTree(this.root());
		}
		
	}
	
	// Helper function for recursive calls
	private boolean isBinarySearchTree(Position<E> node){
		
		Position<E> leftChild = null, rightChild = null;
		boolean isBinarySearchTree = false;	//if none of the below conditions are met, then false will be returned
		
		// set child variables
		if(node.getChildren().size() == 2){
			leftChild = node.getChildren().get(0);
			rightChild = node.getChildren().get(1);
		}
		// if only one child, must be left child
		else if(node.getChildren().size() == 1){
			leftChild = node.getChildren().get(0);
		} 
		
		// an empty tree is a binary search tree
		else {	
			return true;
		}
		
		// Check children's values
		if(leftChild.getElement().compareTo(node.getElement()) <= 0){
			if(rightChild == null){
				isBinarySearchTree = isBinarySearchTree(leftChild);
			}
			else if (rightChild.getElement().compareTo(node.getElement()) >= 0){
				isBinarySearchTree = isBinarySearchTree(leftChild);
				isBinarySearchTree = isBinarySearchTree && isBinarySearchTree(rightChild);
			}
		}
	
		
		return isBinarySearchTree;
		
	}
	
	
	

//------------------------------------------------------------------------------------
//											PART III	
//------------------------------------------------------------------------------------

	public boolean add(E value){
        boolean add = insert(value, this.root());
        if(!this.isBalancedBinary()){
        	returnTrinodes(this.root());
        }
        return add;  
	}
	
	private void returnTrinodes(Position<E> node){
		int leftSub = 0, rightSub = 0;
		//Find the nodes that need restructuring
		if(node.getChildren().size()>0){
			if(node.getChildren().size()>=1){
				leftSub = 1 + height(node.getChildren().get(0)); //+1 to account for root
			}
			else{
				leftSub = 0;
			}
			if(node.getChildren().size()>1){
				rightSub = 1 + height(node.getChildren().get(1)); //+1 to account for root
			}
			else{
				rightSub = 0;
			}
			//When difference in subtrees is greater than 1
			//Restructure the nodes in that area
			System.out.println(Math.abs(leftSub-rightSub));
			if(Math.abs(leftSub-rightSub)>1){
				trinodeRestructure(this.root(), this.root().getChildren().get(0),this.root().getChildren().get(1));
			}
		}
	}
	//node = x, leftChild = y, rightChild = z
	private void trinodeRestructure(Position<E> node, Position<E> leftChild, Position<E> rightChild ){
		//temp variables to track children of the new top node
		Position<E> tempL = null, tempR = null;
		Position<E> top = null, left = null, right = null;
		
		if(node.getElement().compareTo(leftChild.getElement())<0 && leftChild.getElement().compareTo(rightChild.getElement())<0){
			top = leftChild;
			left = node;
			right = rightChild;
		}
		if(node.getElement().compareTo(leftChild.getElement())>0 && leftChild.getElement().compareTo(rightChild.getElement())>0){
			top = leftChild;
			left = rightChild;
			right = node;
		}
		if(node.getElement().compareTo(rightChild.getElement())<0 && rightChild.getElement().compareTo(leftChild.getElement())<0){
			top = rightChild;
			left = node;
			right = leftChild;
		}
		if(node.getElement().compareTo(rightChild.getElement())>0 && rightChild.getElement().compareTo(leftChild.getElement())>0){
			top = rightChild;
			left = leftChild;
			right = node;
		}
		//System.out.println(top.getElement());
		//If our new top position has only one child
		if(top.getChildren().size()==1){
			tempL = top.getChildren().get(0);
			top.removeChild(top.getChildren().get(0));
		}
		if(top.getChildren().size()==2){
			tempL = top.getChildren().get(0);
			tempR = top.getChildren().get(1);
			top.removeChild(top.getChildren().get(0));
			top.removeChild(top.getChildren().get(1));
		}
		left.setParent(top);
		right.setParent(top);
		top.addChild(left);
		top.addChild(right);
		if(tempL != null){
			insert(tempL.getElement(), top);
		}
		if(tempR != null){
			insert(tempR.getElement(), top);
		}
		
		
	}
	
	private boolean insert(E value, Position<E> node){
		Position<E> left=null, right=null;
		//Set the value as the root if the tree is empty
		if(this.root()==null){
			this.setRoot(new SimplePosition<E>(value));
			return true;
		}
		while(true){
			//If a leaf is reached, insert the value
			if(node.getChildren().size()==0){
				//If the value is smaller than current, insert to the left
				if(value.compareTo(node.getElement())<0){
					this.insert(node, new SimplePosition<E>(value));
					this.insert(node, new SimplePosition<E>(null));
					return true;
				}
				//If the value is larger than current, insert to the right
				else if(value.compareTo(node.getElement())>0){
					this.insert(node, new SimplePosition<E>(null));
					this.insert(node, new SimplePosition<E>(value));
					return true;
				}		
			}
			
			//get left and right children if they exist
			if(node.getChildren().size()==1){
				left = node.getChildren().get(0);
				right = null;
			}
			else if(node.getChildren().size()==2){
				left = node.getChildren().get(0);
				right = node.getChildren().get(1);
				
			}
		
			//Insert new node to the left if smaller than current
			if(value.compareTo(node.getElement())<0){
				if(left == null){
					this.insert(node, new SimplePosition<E>(value));
					return true;
				}
				node = left;	
				continue;
			}
			
			//Insert new node to the right if value greater than current
			else if(value.compareTo(node.getElement())>0){
				if(right == null){
					this.insert(node, new SimplePosition<E>(value));
					return true;
				}
				node = right;
				continue;
			}
			//If current is equal to value
			else{ 
				return false;
			}
		}
	}


	@Override
	public boolean remove(E value) {
		// if v​alue i​s in the balanced BST , remove it and return true
		// otherwise, do nothing and return false
		// implement the algorithm shown in the week 6 lecture to ensure that the BST remains balanced
		
		if (this.isEmpty()){
			return false;		
		}
		else {
			return remove(value, this.root());
		}
				
	}
	
	// Helper Function for recursive calls
	private boolean remove(E value, Position<E> node){
		Position<E> left=null, right=null;
		//Set the value as the root if the tree is empty
		if(this.root()==null){
			this.setRoot(new SimplePosition<E>(value));
			return true;
		}
		while(true){
			//If a leaf is reached, return false
			if(node.getChildren().size()==0){
				//If the value is smaller than current, insert to the left
				if(value.compareTo(node.getElement())<0){
					this.insert(node, new SimplePosition<E>(value));
					this.insert(node, new SimplePosition<E>(null));
					return true;
				}
				//If the value is larger than current, insert to the right
				else if(value.compareTo(node.getElement())>0){
					this.insert(node, new SimplePosition<E>(null));
					this.insert(node, new SimplePosition<E>(value));
					return true;
				}		
			}
			
			//get left and right children if they exist
			if(node.getChildren().size()==1){
				left = node.getChildren().get(0);
				right = null;
			}
			else if(node.getChildren().size()==2){
				left = node.getChildren().get(0);
				right = node.getChildren().get(1);
				
			}
		
			//Insert new node to the left if smaller than current
			if(value.compareTo(node.getElement())<0){
				if(left == null){
					this.insert(node, new SimplePosition<E>(value));
					return true;
				}
				node = left;	
				continue;
			}
			
			//Insert new node to the right if value greater than current
			else if(value.compareTo(node.getElement())>0){
				if(right == null){
					this.insert(node, new SimplePosition<E>(value));
					return true;
				}
				node = right;
				continue;
			}
			//If current is equal to value
			else{ 
				return false;
			}
		}
	}
	
//------------------------------------------------------------------------------------
//											PART IV	
//------------------------------------------------------------------------------------
	
	// is this tree a valid arithmetic tree
	// every leaf in an arithmetic tree is a numeric value, for example: “1”, “2.5”, or “­0.234” 
	// every internal node is a binary operation: “+”, “­”, “*”, “/”
	// binary operations must act on exactly two sub­expressions (i.e. two children)
	// note: all the values and operations are stored as String objects
	
	@Override
	public boolean isArithmetic() {
		
		if(this.size() == 0){
			return false;
		} else {
			return isArithmetic(this.root());
		}
		
	}
		
	private boolean isArithmetic(Position<E> node){
		
		boolean isArithmetic = false;
		String numberRegEx = "^[-+]?[0-9]*\\.?[0-9]+$";
		String symbolRegEx = "^(\\+|\\-|\\*|/)$";
		
		// All elements must be strings
		if (node.getElement().getClass() != "".getClass()) {
			return false;
		}
		
		// All leaves must be numbers
		if (node.getChildren().size() == 0){
			if (Pattern.matches(numberRegEx, node.getElement().toString())){
				return true;
			}
		}
		
		// Nodes cannot have one child
		else if (node.getChildren().size() == 1){
			return false;
		}
		
		else{
			isArithmetic = Pattern.matches(symbolRegEx, node.getElement().toString());
			for (int i = 0; i < node.getChildren().size(); i++){
				isArithmetic = isArithmetic && isArithmetic(node.getChildren().get(i));
			}
		
		}
		
		return isArithmetic;
		
	}
	


	@Override
	public double evaluateArithmetic() {
		// Do a post-order traversal of the tree to evaluate pairs of leaves
		// with their parent operators
		
		// evaluate an arithmetic tree to get the solution
		// if a position is a numeric value, then it has that value
		// if a position is a binary operation, then apply that operation on the value of it’s children
		// use floating point maths for all calculations, not integer maths
		// if a position contains “/”, its left subtree evaluated to 1.0, and the right to 2.0, then it is 0.5
		
		
		// An empty tree results in a zero output
		if(this.isArithmetic() == false){
			throw new UnsupportedOperationException();
		}
		else {
			return evaluateArithmetic(this.root());
		}
		
	}
	
	// Helper function for recursive calls
	private double evaluateArithmetic(Position<E> node) {
		
		// All elements must be strings
		if (node.getElement().getClass() != "".getClass()) {
			return 0;
		}
		
		double x, y;
		String operator;
		
		// Set child variables (note that there must be 0 or two children)
		Position<E> leftChild = null, rightChild = null;
		if(node.getChildren().size() != 0){
			leftChild = node.getChildren().get(0);
			rightChild = node.getChildren().get(1);
		} else {
			return Double.valueOf(node.getElement().toString());
		}
		
		x = evaluateArithmetic(leftChild);
		y = evaluateArithmetic(rightChild);
		operator = node.getElement().toString();
	
		if (operator == "+"){
			return x + y;
		} else if (operator == "-"){
			return x - y;
		} else if (operator == "*"){
			return x * y;
		} else {	// if operator == "/"
			return x / y;
		}
	}


	@Override
	public String getArithmeticString() {
		
		return getArithmeticString(this.root());
		
	}
	
	private String getArithmeticString(Position<E> node){
		
		// All elements must be strings
		if (node.getElement().getClass() != "".getClass()) {
			return "";
		}
		
		// Set child variables (note that there must be 0 or two children)
		Position<E> leftChild = null, rightChild = null;
		if(node.getChildren().size() != 0){
			leftChild = node.getChildren().get(0);
			rightChild = node.getChildren().get(1);
		}
		
		String combinedString = "";
		
		// Performs an in-order traversal to print the terms in the correct order
		if (leftChild != null) {
			combinedString = combinedString + "(";
			combinedString = combinedString + getArithmeticString(leftChild);
		}
		
		combinedString = combinedString + (node.getElement().toString());
		
		if (rightChild != null){
			combinedString = combinedString + getArithmeticString(rightChild);
			combinedString = combinedString + ")";
		}
		
		return combinedString;
	}


	

}