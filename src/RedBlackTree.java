/**
 * Created by Federico Bertani on 13/03/16.
 */

/**
 * RedBlackTree is a data structure. It's a binary search tree with auto-balance system.
 *
 * @param <ItemType extends Comparable<ItemType>> the type of data that the tree will contain.
 *                  It must implement the comparable interface and being comparable with his own.
 */
public class RedBlackTree<ItemType extends Comparable<ItemType>> {

    /**
     * The parent tree.
     */
    private RedBlackTree<ItemType> parent;

    /**
     * The left subtree.
     */
    private RedBlackTree<ItemType> leftChild;
    /**
     * The right subtree.
     */
    private RedBlackTree<ItemType> rightChild;
    /**
     * The value of the node.
     */
    private ItemType value;
    /**
     * The color of the node.
     */
    private RBColor color;

    /**
     * Create a new Tree with a given value.
     *  Use this for create the root.
     * @param value ItemType: the value of the node to create.
     */
    public RedBlackTree(ItemType value) {
        //set the given value
        this.value = value;
        //the color of the root is black
        this.color = RBColor.BLACK;
        //set the leftchild as null because it doesn't exist yet.
        this.leftChild = null;
        //set the rightchild as null because it doesn't exist yet.
        this.rightChild = null;
    }

    /**
     * Create a new Tree with the given value and color.
     * Use this for creating every node.
     *
     * @param value ItemType: the value of the node to create.
     * @param color RBColor: the color of the new tree/node.
     */
    public RedBlackTree(ItemType value, RBColor color) {
        //set the given value
        this.value = value;
        //set the given color
        this.color = color;
        //set the leftchild as null because it doesn't exist yet.
        this.leftChild = null;
        //set the rightchild as null because it doesn't exist yet.
        this.rightChild = null;
    }

    /**
     * Search if a node contain the given item is contained on the three and if is return it.
     *
     * @param item ItemType: the item to search.
     * @return RedBlackTree&lt;ItemType&gt; : return the searched node if found, null otherwise.
     */
    public RedBlackTree<ItemType> lookUpNode(ItemType item) {
        //if the tree is a leaf and doens't cointains nothing return null
        if (this.value == null) return null;
            //otherwise if the node has a value
        else {
            //create a comparison variable with the comparison result with the researched item and the current value
            int comparison = this.value.compareTo(item);
            //if the node contains the searched value return himself.
            if (comparison == 0) return this;
                //else if the node value is less than the searched
            else if (comparison < 0) {
                //but the leftchild doesn't exist return null
                if (this.leftChild == null) return null;
                    //but if the leftchild exist call lookup on him
                else return this.leftChild.lookUpNode(item);
                //if the node value in greater of the searched instead
            } else {
                //but the rightchil doesn't exist return null
                if (this.rightChild == null) return null;
                    //but if the leftchild exist call lookup on him
                else return this.rightChild.lookUpNode(item);
            }
        }
    }

    /**
     * Get the next nearest node.
     *
     * @return RedBlackTree&lt;ItemType&gt; : the successor node.
     */
    private RedBlackTree<ItemType> successorNode() {
        return this.rightChild.min();
    }

    /**
     * Get the previous nearest node.
     *
     * @return RedBlackTree&lt;ItemType&gt; : the predecessor node.
     */
    private RedBlackTree<ItemType> predecessorNode() {
        return this.leftChild.max();
    }

    /**
     * Get the tree that cointans the minimum item of the tree.
     *
     * @return ItemType: the tree that cointans the the minimum item of the tree.
     */
    public RedBlackTree<ItemType> min() {
        if (this.leftChild == null) return this;
        else return this.leftChild.min();
    }

    /**
     * Get the tree that cointans the maximum item of the tree.
     *
     * @return ItemType: the tree that cointans the the maximum item of the tree.
     */
    public RedBlackTree<ItemType> max() {
        if (this.rightChild == null) return this;
        else return this.rightChild.max();
    }

    /**
     * Insert a new node on the tree.
     *
     * @param item ItemType: the item to insert in the tree.
     */
    public void insert(ItemType item) {

    }

    public void rotateleft() {
        //if the node has a rightchild
        if (this.rightChild != null) {
            //get the rightchild
            RedBlackTree<ItemType> rightChild = this.rightChild;
            //get the parent
            RedBlackTree<ItemType> parent = this.parent;
            //substitute the rightchild with the richilds's leftchild
            this.rightChild = rightChild.leftChild;
            //if the rightchild's leftchild exist set is parent to the current node
            if (rightChild.leftChild != null) rightChild.leftChild.parent = this;
            //set the rightchild's leftchild to the current note
            rightChild.leftChild = this;
            //the parent of the current node become his rightchild
            this.parent = rightChild;
            //the rightchild parent become the parent of the current node
            rightChild.parent = parent;
            //if the current node is not the root
            if (parent != null) {
                //and the current node was his leftchild
                if (parent.leftChild == this) {
                    //change the parent leftchild
                    parent.leftChild = rightChild;
                    //or if the current node was his rightchild
                } else {
                    //change the parent rightchild
                    parent.rightChild = rightChild;
                }
            }
        }
    }

    public void rotateRight() {
        if (this.leftChild != null) {
            RedBlackTree<ItemType> leftChild = this.leftChild;
            RedBlackTree<ItemType> parent = this.parent;
            this.leftChild = leftChild.rightChild;
            if (leftChild.rightChild != null) leftChild.rightChild.parent = this;
            leftChild.rightChild = this;
            this.parent = leftChild;
            leftChild.parent = parent;
            if (parent != null) {
                if (parent.leftChild == this) {
                    parent.leftChild = leftChild;
                } else {
                    parent.rightChild = leftChild;
                }
            }
        }
    }

    /**
     * Define an enum for the color of the node.
     */
    private enum RBColor {
        BLACK, RED
    }


}
