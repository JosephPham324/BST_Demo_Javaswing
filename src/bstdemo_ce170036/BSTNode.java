package bstdemo_ce170036;

/**
 *
 * @author Pham Nhat Quang CE170036
 */public class BSTNode {

    private int data; //Data of Node
    private int count;//How many occurences of this data in the tree

    private BSTNode parent,//Parent of this Node
            leftChild, //Left child of this Node
            rightChild;    //Right child of this Node

    private int level;//The height level of this Node in the tree, a root's level will be 0
    private int order;//Order of each individual Node in a tree

    /**
     * Types of Node
     */
    public enum NodeType {

        /**
         * Left child of another Node
         */
        LEFT_CHILD,
        /**
         * Right child of another Node
         */
        RIGHT_CHILD
    }

    /*=====FOR DRAWING=====*/
    private static final int LEVEL_DY = 80;//Distance from each tree height level
    private int x; //Horizontal coordinate
    private int y; //Vertical coordinate
    private int width; //The horizontal distance from left corner of the screen or another Node

    /*=====FOR DRAWING=====*/
    /**
     * Create new BSTNode that is not attached to any other Node
     *
     * @param data Data of Node
     */
    public BSTNode(int data) {
        this.data = data;
        this.count = 1; //Count default 1
        this.parent = this.leftChild = this.rightChild = null; //No child or parent
        this.level = 0; //Level 0
        this.order = 0; //Order 0

        //No coordinates to draw
        this.x = 0;
        this.y = 0;
        this.width = 0;
    }

    /**
     * Create new BSTNode that is not attached no any other Node, and has y
     * coordinate, screen width
     *
     * @param data Data to show
     * @param y Y coordinate in a canvas
     * @param screenWidth Screen width
     */
    public BSTNode(int data, int y, int screenWidth) {
        this.data = data;
        this.count = 1; //Count default 1
        this.parent = this.leftChild = this.rightChild = null; //No parent or child
        this.level = 0; //Level 0
        this.order = 0; //Order 0

        this.x = this.width = screenWidth / 2; //Set x coordinate as middle of the screen
        this.y = y; //set y coordinate
    }

    /**
     * Check if this Node is a root
     *
     * @return true if its parent is null, otherwise false
     */
    public boolean isRoot() {
        return parent == null;
    }

    /**
     * Check if this Node has left child
     *
     * @return true if its left child is not null, otherwise false
     */
    public boolean hasLeftChild() {
        return this.leftChild != null;
    }

    /**
     * Check if this Node has right child
     *
     * @return true if its right child is not null, otherwise false
     */
    public boolean hasRightChild() {
        return this.rightChild != null;
    }

    /**
     * Check if this Node has any child
     *
     * @return true if it has at least a child, false if not
     */
    public boolean hasChild() {
        return hasRightChild() || hasLeftChild();
    }

    /**
     * Check if this Node is a leaf
     *
     * @return true it doesn't have any child
     */
    public boolean isLeaf() {
        return !this.hasChild();
    }

    /**
     * Check if this node is an inner node
     *
     * @return true if it is not a leaf and root
     */
    public boolean isInnerNode() {
        return !isLeaf() && !isRoot();
    }

    /**
     * Get data
     *
     * @return Value of data field
     */
    public int getData() {
        return data;
    }

    /**
     * Set data field value
     *
     * @param data Value to set
     */
    public void setData(int data) {
        this.data = data;
    }

    /**
     * Get count
     *
     * @return Value of count field
     */
    public int getCount() {
        return count;
    }

    /**
     * Set count field value
     *
     * @param count Value to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Get parent of Node
     *
     * @return Parent node of node
     */
    public BSTNode getParent() {
        return parent;
    }

    /**
     * Set parent of this node
     *
     * @param parent Parent Node of this node
     * @param type What node type this node is
     */
    public void setParent(BSTNode parent, NodeType type) {
        this.parent = parent; //Set parent

        this.level = parent.getLevel() + 1; //Calculate level from parent's level
        this.order = parent.getOrder() * 2 + ((type == NodeType.LEFT_CHILD) ? 1 : 2);//Calculate order from parent's order

        //===For drawing===//
        this.width = this.getParent().getWidth() / 2;//Calculate width using parent's width
        this.x = this.getParent().getX() + ((type == NodeType.LEFT_CHILD) ? -this.width : this.width);//Calculate x coordinate
        this.y = this.getParent().getY() + LEVEL_DY; //Calculate y coordinate
    }

    /**
     * Get left child of Node
     *
     * @return left child node or null
     */
    public BSTNode getLeftChild() {
        return leftChild;
    }

    /**
     * Set left child of Node
     *
     * @param leftChild Left child to set
     */
    public void setLeftChild(BSTNode leftChild) {
        this.leftChild = leftChild; //Set left child of this node
        if (leftChild != null) {
            this.leftChild.setParent(this, NodeType.LEFT_CHILD);//Set parent of child as this node
        }
    }

    /**
     * Get right child of Node
     *
     * @return right child node or null
     */
    public BSTNode getRightChild() {
        return rightChild;
    }

    /**
     * Set right child of Node
     *
     * @param rightChild Right child to set
     */
    public void setRightChild(BSTNode rightChild) {
        this.rightChild = rightChild; //Set right child of this node 
        if (rightChild != null) {
            this.rightChild.setParent(this, NodeType.RIGHT_CHILD);//Set parent of child as this node
        }
    }

    /**
     * Get level of this node
     *
     * @return Value of level field
     */
    public int getLevel() {
        return level;
    }

    /**
     * Set value for level for
     *
     * @param level Value to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Get order of this node
     *
     * @return Value of order
     */
    public int getOrder() {
        return order;
    }

    /**
     * Set value for order
     *
     * @param order Value to set
     */
    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * Get x coordinate of this Node
     *
     * @return Value of x field
     */
    public int getX() {
        return x;
    }

    /**
     * Set x coordinate of this Node
     *
     * @param x Value to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Get y coordinate of this Node
     *
     * @return Value of y field
     */
    public int getY() {
        return y;
    }

    /**
     * Set y coordinate of thisn ode
     *
     * @param y Value to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Get width of node
     *
     * @return Value of width field
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set width of node
     *
     * @param width Value to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    
    /**
     * Delete a leaf child of this Node
     * @param leafChild leaf child to delete
     */
    public void deleteLeafChild(BSTNode leafChild) {
        if (!leafChild.isLeaf()) {
            return;
        }
        if (this.hasLeftChild()) {
            if (this.getLeftChild().getData() == leafChild.getData()) {
                this.setLeftChild(null);
                return;
            }
        }
        if (this.hasRightChild()) {
            if (this.getRightChild().getData() == leafChild.getData()) {
                this.setRightChild(null);
                return;
            }
        }
    }

}
