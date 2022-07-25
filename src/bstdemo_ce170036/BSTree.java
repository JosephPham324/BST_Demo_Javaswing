package bstdemo_ce170036;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Phạm Nhật Quang CE170036
 */
public class BSTree {

    private BSTNode root;//Root of tree
    private ArrayList<Integer> path;//Values of nodes during traversal stored in order
    private HashMap<Integer, Integer> path2;//Values of nodes during traversal not in order, for easy access
    private ArrayList<Integer> countOfPathNodes;//Store the count of individudal values inserted in the tree

    private int countData;//Store the total number of insertions

    //===For drawing===//
    int screenWidth;//Width of screen
    int yMin;       //y coordinate to start drawing
    //================//
    String traversalResult;//Store traversal result to display

    /**
     * Create new empty BSTree
     */
    public BSTree() {
        root = null;
    }

    /**
     * Create new empty BSTree
     *
     * @param screenWidth Width of screen
     * @param yMin y coordinate to start drawing the tree
     */
    public BSTree(int screenWidth, int yMin) {
        root = null;
        this.path = new ArrayList<>();
        this.path2 = new HashMap<>();
        countData = 0;
        this.screenWidth = screenWidth;
        this.yMin = yMin;
    }

    /**
     * Get the path ArrayList
     *
     * @return path ArrayList
     */
    public ArrayList<Integer> getPath() {
        return path;
    }

    /**
     * Get the path2 HashMap
     *
     * @return path2 HashMap
     */
    public HashMap<Integer, Integer> getPath2() {
        return path2;
    }

    /**
     * Set traversal path with an ArrayList
     *
     * @param path ArrayList to set
     */
    public void setPath(ArrayList<Integer> path) {
        this.path = path;
    }

    /**
     * Set traversal path with a HashMap
     *
     * @param path2 HashMap to set
     */
    public void setPath2(HashMap<Integer, Integer> path2) {
        this.path2 = path2;
    }

    /**
     * Get total number of insertions
     *
     * @return the number of insertions
     */
    public int getCountData() {
        return countData;
    }

    /**
     * Get root of tree
     *
     * @return root node of tree
     */
    public BSTNode getRoot() {
        return root;
    }

    /**
     * Add new node to tree
     *
     * @param data Data of node
     */
    public void addNode(int data) {
        if (this.root == null) {//If tree is empty, set new root
            this.root = new BSTNode(data, yMin, screenWidth);
            ++countData;
        } else {
            BSTNode node = this.root;//Current root to compare data
            do {
                if (data < node.getData()) {//If data < current root's data, check the left child
                    if (node.hasLeftChild()) {//If current node has left child, set new current node as the child
                        node = node.getLeftChild();
                    } else {//Else set left child as new node with the data
                        node.setLeftChild(new BSTNode(data));
                        ++countData;
                        node = null;
                    }
                } else if (data > node.getData()) {//If data > current root's data, check the right child
                    if (node.hasRightChild()) {//If current node has right child, set new current node as the child
                        node = node.getRightChild();
                    } else {//Else set right child as new node with the data
                        node.setRightChild(new BSTNode(data));
                        ++countData;
                        node = null;
                    }
                } else {//If data is the same as current node's data, increment the count
                    node.setCount(node.getCount() + 1);
                    ++countData;
                    node = null;
                }
            } while (node != null); //Until current node is null
        }
    }

    /**
     * Trim the traversal result of unnecessary ", " or "-&gt;" at the end
     */
    public void cleanTraversalResult() {
        String result = this.traversalResult.substring(traversalResult.length() - 2, traversalResult.length());
        if (result.equals(", ")) {
            this.traversalResult = replaceLast(this.traversalResult, ", ", "");
        } else if (result.equals("->")) {
            this.traversalResult = replaceLast(this.traversalResult, "->", "");
        }
    }

    /**
     * Do PreOrder traversal of tree and set traversal result
     */
    public void preOrder() {
        this.traversalResult = "";
        this.resetSearch();
        preOrder(root);
    }

    /**
     * Do PreOrder traversal of a tree starting from a root
     *
     * @param tree Root to start traversing
     */
    public void preOrder(BSTNode tree) {

        if (tree == null) {
            return;
        }
        for (int i = 0; i < tree.getCount(); i++) {
            this.traversalResult += tree.getData() + ", ";
        }
        this.path.add(tree.getData());
        this.countOfPathNodes.add(tree.getCount());
        preOrder(tree.getLeftChild());
        preOrder(tree.getRightChild());
    }

    /**
     * Do InOrder traversal of tree and set traversal result
     */
    public void inOrder() {
        this.traversalResult = "";
        this.resetSearch();
        inOrder(root);
    }

    /**
     * Do InOrder traversal of a tree starting from a root
     *
     * @param tree Root to start traversing
     */
    public void inOrder(BSTNode tree) {
        if (tree == null) {
            return;
        }
        inOrder(tree.getLeftChild());
        for (int i = 0; i < tree.getCount(); i++) {
            this.traversalResult += tree.getData() + ", ";
        }
        this.path.add(tree.getData());
        this.countOfPathNodes.add(tree.getCount());
        inOrder(tree.getRightChild());
    }

    /**
     * Reset fields associated with search result
     */
    public void resetSearch() {
        this.path = new ArrayList<>();
        this.path2 = new HashMap<>();
        this.countOfPathNodes = new ArrayList<>();
    }

    /**
     * Do PostOrder traversal of tree and set traversal result
     */
    public void postOrder() {
        this.traversalResult = "";
        postOrder(root);
        resetSearch();
    }

    /**
     * Do PostOrder traversal of a tree starting from a root
     *
     * @param tree Root to start traversing
     */
    public void postOrder(BSTNode tree) {
        if (tree == null) {
            return;
        }
        postOrder(tree.getLeftChild());
        postOrder(tree.getRightChild());
        for (int i = 0; i < tree.getCount(); i++) {
            this.traversalResult += tree.getData() + ", ";
        }
    }

    /**
     * Replace last occurrence of a RegularExpression in a String with a
     * replacement
     *
     * @param text String to perform replacement
     * @param regex RegularExpression to replace
     * @param replacement Replacement for the RegularExpression
     * @return Modified String
     */
    public String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)" + regex + "(?!.*?" + regex + ")", replacement);
    }

    /**
     * Get traversal result in form of a String
     *
     * @return String representation of traversal result
     */
    public String getTraversalResult() {
        cleanTraversalResult();
        return traversalResult;
    }

    /**
     * Search a Node that has the designated value in this tree, also update the
     * search path in the meantime
     *
     * @param data Value to search
     * @return Node with data same as the value
     */
    public BSTNode findNode(int data) {
        resetSearch();//Reset search associated fields
        BSTNode currentNode = this.root;//Begin search from root
        if (currentNode != null) {//Updating paths
            path.add(currentNode.getData());
            path2.put(currentNode.getData(), currentNode.getData());
        }
        //Do the search
        while (currentNode != null) {
            if (data == currentNode.getData()) {
                return currentNode;
            }
            if (data > currentNode.getData()) {
                currentNode = currentNode.getRightChild();
            } else if (data < currentNode.getData()) {
                currentNode = currentNode.getLeftChild();
            }
            if (currentNode != null) {
                path.add(currentNode.getData());
                path2.put(currentNode.getData(), currentNode.getData());
            }
        }
        resetSearch();//If not found, reset paths
        return null;
    }

    /**
     * Find node with maximum value in a tree / subtree
     *
     * @param startingNode The node to start finding (AKA root of subtree)
     * @return Root with maximum value in the subtree with root as the starting
     * Node
     */
    public BSTNode findMax(BSTNode startingNode) {
        BSTNode result = startingNode;
        while (result != null) {
            if (!result.hasRightChild()) {
                return result;
            } else {
                result = result.getRightChild();
            }
        }
        return startingNode;
    }

    /**
     * Find node with minimum value in a tree / subtree
     *
     * @param startingNode The node to start finding (AKA root of subtree)
     * @return Root with minimum value in the subtree with root as the starting
     * Node
     */
    public BSTNode findMin(BSTNode startingNode) {
        BSTNode result = startingNode;
        while (result != null) {
            if (!result.hasLeftChild()) {
                return result;
            } else {
                result = result.getLeftChild();
            }
        }
        return startingNode;
    }

    /**
     * Delete a Node that has the designated value, or decrease its count
     *
     * @param data Value to delete
     * @return true if successful, false if not
     */
    public boolean deleteValue(int data) {
        BSTNode toDelete = this.findNode(data);
        if (toDelete != null) {
            return deleteNode(toDelete);
        } else {
            return false;
        }
    }

    /**
     * Delete a Node in a BSTree
     *
     * @param toDelete Node to delete
     * @return true if successful, false if not
     */
    private boolean deleteNode(BSTNode toDelete) {
        if (toDelete == null) {
            return false;
        }
        toDelete.setCount(toDelete.getCount() - 1);//Decrease count of node
        if (toDelete.getCount() > 0) {//If count still > 0, return true
            return true;
        } else {//If count = 0 perform deletion of node
            BSTNode replacement;//Replacement of this node for its position in the tree 
            if (!toDelete.isLeaf()) {//If the node is not a leaf
                if (toDelete.hasLeftChild()) {//Find replacement from its leaf child
                    replacement = findMax(toDelete.getLeftChild());//Get replacement
                    //Set info of the node same as replacement
                    toDelete.setData(replacement.getData());
                    toDelete.setCount(replacement.getCount());
                    //Set replacement count to 1
                    replacement.setCount(1); //Delete the replacement
                    return deleteNode(replacement);
                } else {//If it doesn't have left child, find replacement from its right child
                    replacement = findMin(toDelete.getRightChild());//Get replacement
                    //Set info
                    toDelete.setData(replacement.getData());
                    toDelete.setCount(replacement.getCount());
                    //Set replacement count
                    replacement.setCount(1);
                    return deleteNode(replacement); //Delete replacement
                }
            } else {//If node is leaf
                resetSearch();
                return deleteLeaf(toDelete);//Delete the node
            }
        }
    }

    /**
     * Delete a leaf Node
     *
     * @param node Node to delete
     * @return True if successful, false if not
     */
    private boolean deleteLeaf(BSTNode node) {
        if (node.isLeaf()) {//If the node is a leaf
            if (!node.isRoot()) {//If node is not root
                BSTNode parent = node.getParent();//Get parent of node
                node.setCount(node.getCount() - 1);//Decrease count of node
                if (node.getCount() > 0) {//If count still > 0 return true
                    return true;
                }
                if (parent.getRightChild() == node) {//Delete node (if node is right child)
                    parent.setRightChild(null);
                    return true;
                } else if (parent.getLeftChild() == node) {//Delete node if node is left child
                    parent.setLeftChild(null);
                    return true;
                }
            } else {//If node is root and also leaf, set it to null
                this.root = null;
            }
            return false;
        } else {//If not leaf, return false
            return false;
        }
    }

    /**
     * Clear the tree
     */
    public void clear() {
        this.traversalResult = "No results yet";
        clearNode(this.root);
        this.root = null;
        this.countData = 0;
    }

    /**
     * Clear all nodes starting from a node
     *
     * @param node Node to start clearing
     */
    public void clearNode(BSTNode node) {
        if (node != null) {
            if (node.hasLeftChild()) {
                clearNode(node.getLeftChild());
            }
            if (node.hasRightChild()) {
                clearNode(node.getRightChild());
            }
            if (!node.isRoot()) {
                node.getParent().deleteLeafChild(node);
            }
        }
    }

    /**
     * Balance the current tree
     */
    public void balanceTree() {
        this.path = new ArrayList<>();
        this.countOfPathNodes = new ArrayList<>();
        this.inOrder();
        this.clear();
        if (this.path.size() >= 1) {
            createBalancedTree(0, path.size() - 1, this.path, this.countOfPathNodes);
        }

    }

    /**
     * Make this tree a balanced tree using an ArrayList of increasing values
     * and ArrayList storing the count of each value
     *
     * @param leftIndex Left index of the values ArrayList to do insertion
     * @param rightIndex Right index of the values ArrayList to do insertion
     * @param values The values to insert in the new tree, in the same order
     * @param countOfValues Number of occurrences of each value
     */
    public void createBalancedTree(int leftIndex, int rightIndex, ArrayList<Integer> values, ArrayList<Integer> countOfValues) {
        if (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;
            BSTNode newRoot = new BSTNode(values.get(middleIndex));
            newRoot.setCount(countOfValues.get(middleIndex));
            for (int i = 0; i < countOfValues.get(middleIndex); i++) {
                this.addNode(values.get(middleIndex));
            }
            createBalancedTree(leftIndex, middleIndex - 1, values, countOfValues);
            createBalancedTree(middleIndex + 1, rightIndex, values, countOfValues);
        }
    }

    /**
     * Perform Breadth First Search on this tree
     */
    public void BFS() {
        this.traversalResult = "";
        Queue<BSTNode> queue = new LinkedList<>();
        queue.add(root);
        BSTNode node;
        while (!queue.isEmpty()) {
            node = queue.poll();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    this.traversalResult += node.getData() + ", ";
                }
                queue.add(node.getLeftChild());
                queue.add(node.getRightChild());
            }

        }
    }

    /**
     * Perform Depth First Search on this tree
     */
    public void DFS() {
        this.traversalResult = "";
        Stack<BSTNode> stack = new Stack<>();
        stack.push(root);
        BSTNode node;
        while (!stack.isEmpty()) {
            node = stack.pop();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    this.traversalResult += node.getData() + ", ";
                }
                stack.push(node.getRightChild());
                stack.push(node.getLeftChild());
            }
        }
    }

    /**
     * Get the countOfPathNodes field
     *
     * @return the field
     */
    public ArrayList<Integer> getCountOfPathNodes() {
        return countOfPathNodes;
    }
}
