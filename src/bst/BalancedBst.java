package bst;

import interfaces.Position;

/**
 * This section is for INFO1905 students only.
 * 
 * @author krus4334
 * @author acam3311
 * 
 * This class, BalanacedBst, should be your solution to part
 * 3 of the INFO1905 assignment.
 * 
 * It should remain in the "bst" package.
 */

public class BalancedBst<E extends Comparable<E>> extends SimpleBst<E>{

	//constructor
	public BalancedBst() {
		super(); //call the constructor of SimpleTree with no arguments
	}

	/**
	 * PART 3 (INFO1905)
	 * 
	 * If you are enrolled in INFO1905, implement the following methods, which
	 * allow balanced insertion and deletion to a binary search tree. You may
	 * assume that the tree is a balanced binary search tree before either of
	 * these methods are called.
	 * 
	 */

	@Override
	public boolean insert(E value) {
		// if value is already in the balanced BST, do nothing and return false
		// otherwise, add value to the balanced binary search tree (BST) and return true
		// use the algorithm shown in the week 6 lecture - the BST must remain balanced
		boolean add = insert(value, this.root());
        if(!this.isBalancedBinary()){
        	returnTrinodes(this.root());
        }
        return add;
	}
	
	private void returnTrinodes(BstPosition<E> node){
		int leftSub = 0, rightSub = 0;
		
		//Find the nodes that need restructuring
		if(node.getLeft() != null || node.getRight() != null){
			if(node.getLeft() != null || node.getRight() != null){
				leftSub = 1 + height(node.getLeft()); //+1 to account for root
			}
			else{
				leftSub = 0;
			}
			if(node.getLeft() != null && node.getRight() != null){
				rightSub = 1 + height(node.getRight()); //+1 to account for root
			}
			else{
				rightSub = 0;
			}
			//When difference in subtrees is greater than 1
			//Restructure the nodes in that area
			System.out.println(Math.abs(leftSub-rightSub));
			if(Math.abs(leftSub-rightSub)>1){
				trinodeRestructure(this.root(), this.root().getLeft(),this.root().getRight());
			}
		}
	}
	
	private void trinodeRestructure(BstPosition<E> node, BstPosition<E> leftChild, BstPosition<E> rightChild ){
		//temporary variables to track children of the new top node
		BstPosition<E> tempL = null, tempR = null;
		BstPosition<E> top = null, left = null, right = null;
		
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

		//If our new top position has only one child
		if(top.getLeft() != null){
			tempL = top.getLeft();
			top.setLeft(null);
		}
		if(top.getLeft() != null && top.getRight() != null){
			tempL = top.getLeft();
			tempR = top.getRight();
			top.setLeft(null);
			top.setRight(null);
		}
		
		left.setParent(top);
		right.setParent(top);
		top.setLeft(left);
		top.setRight(right);
		
		if(tempL != null){
			insert(tempL.getElement(), top);
		}
		if(tempR != null){
			insert(tempR.getElement(), top);
		}
		
		
	}
	
	//Helper function for recursive calls
	private boolean insert(E value, BstPosition<E> node){
		
		//get left and right children if they exist
		BstPosition<E> left=null, right=null;
		BstPosition<E> valuePos = null;
		left = node.getLeft();
		right = node.getRight();
		
		
		//Set the value as the root if the tree is empty
		if(this.root()==null){
			this.setRoot(new SimpleBstPosition<E>(value));
			return true;
		}
		while(true){
			//If a leaf is reached, insert the value
			if(left == null && right == null){
				//If the value is smaller than current, insert to the left
				if(value.compareTo(node.getElement())<0){
					this.insert(value, node);
					this.insert(null, node);
					return true;
				}
				//If the value is larger than current, insert to the right
				else if(value.compareTo(node.getElement())>0){
					this.setLeft(null, node);
					this.setRight(new SimpleBstPosition<E>(value), node);
					return true;
				}		
			}
			
			
			//Insert new node to the left if smaller than current
			if(value.compareTo(node.getElement())<0){
				if(left == null){
					this.setLeft(value);
					return true;
				}
				node = left;	
				continue;
			}
			
			//Insert new node to the right if value greater than current
			else if(value.compareTo(node.getElement())>0){
				if(right == null){
					this.setRight(value);
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
		// if value is in the balanced BST, remove it and return true
		// otherwise, do nothing and return false
		// use the algorithm shown in the week 6 lecture - the BST must remain balanced
		return false;
	}
	
	
	

	
	// Other Functions
	
	public int height(){
		
		if (this.isEmpty()){
			return -1;
		}
		
		return height(this.root());
		
	}
	
	// Helper function for recursive calls
	private int height(BstPosition<E> root){
		int height = 0;
		
		// Finds maximum height of children
		height = height(root.getLeft());
		int childHeight = height(root.getRight());
		
		if (1+childHeight > height){
			height = 1 + childHeight;
		}
		
		return height;
		
	}
	
	// Checks if function is a balanced binary function
	public boolean isBalancedBinary(){
		if(isEmpty()){
			return isEmpty();
		}
		boolean balanced = isBalancedBinary(this.root());
		return balanced;
    }

	
	//find out if balanced binary tree by comparing height of left and right subtrees
	public boolean isBalancedBinary(BstPosition<E> node){
		int leftSub, rightSub;
		if(this.isEmpty()){
			return isEmpty();
		}

		if(node.getLeft() != null || node.getRight() != null){
			if(node.getLeft() != null && node.getRight() != null){
				leftSub = 1 + height(node.getLeft()); //+1 to account for root
			}
			else{
				leftSub = 0;
			}
			if(node.getLeft() != null && node.getRight() != null){
				rightSub = 1 + height(node.getRight()); //+1 to account for root
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

	
	
}
	




	