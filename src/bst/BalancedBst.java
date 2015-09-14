package bst;
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
	
	//Helper function for recursive calls
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
		// if value is in the balanced BST, remove it and return true
		// otherwise, do nothing and return false
		// use the algorithm shown in the week 6 lecture - the BST must remain balanced
		return false;
	}

}
	

	