package bstdemo_ce170036;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JPanel;

/**
 *
 * @author Phạm Nhật Quang CE170036
 */
public class BSTPaper extends JPanel {

    BSTree tree; //Tree to draw
    int screenWidth; //Width of screen to be drawn on
    int yMin;    //y coordinate to start drawing
    Graphics2D g; //To draw

    /**
     * Create new BSTPaper
     *
     * @param tree To initialize tree field
     */
    public BSTPaper(BSTree tree) {
        this.tree = tree;
        this.screenWidth = tree.screenWidth;
        this.yMin = tree.yMin;
    }

    /**
     * Add node to the tree and draw on paperr
     *
     * @param data Data of new Node
     */
    public void addNode(int data) {
        this.tree.addNode(data); //Add node to tree
        repaint(); //Repaint the tree
    }

    public void setTree(BSTree tree) {
        this.tree = tree;
    }
    

    /**
     * This method centers a <code>String</code> in a bounding
     * <code>Rectangle</code>.
     *
     * @param g - The <code>Graphics</code> instance.
     * @param r - The bounding <code>Rectangle</code>.
     * @param s - The <code>String</code> to center in the bounding rectangle.
     * @param font - The display font of the <code>String</code>
     *
     * @see java.awt.Graphics
     * @see java.awt.Rectangle
     * @see java.lang.String
     */
    public void centerString(Graphics g, Rectangle r, String s,
            Font font) {
        FontRenderContext frc
                = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }

    /**
     * Draw a tree starting from a Node
     *
     * @param node Node to start drawing from
     */
    public void drawNode(BSTNode node) {
        if (node == null) { //If the node is null, quit method
            return;
        }

        //Coordinates and sizes
        int x = node.getX();
        int y = node.getY();
        int r = 15; //Radius of oval / circle
        g.setColor(Color.BLACK); //To draw line connecting parent to children
        if (node.hasLeftChild()) {//Draw line to left child
            if (tree.getPath2().containsKey(node.getLeftChild().getData())) {
                g.setColor(Color.red);
            }
            g.drawLine(x, y, node.getLeftChild().getX(), node.getLeftChild().getY());
        }
        g.setColor(Color.black);
        if (node.hasRightChild()) {//Draw line to right child
            if (tree.getPath2().containsKey(node.getRightChild().getData())) {
                g.setColor(Color.red);
            }
            g.drawLine(x, y, node.getRightChild().getX(), node.getRightChild().getY());
        }

        //Draw the node
        //Fill the inside of the node
        if (tree.getPath2().containsKey(node.getData())) {
            g.setColor(Color.yellow);
        } else {
            g.setColor(Color.WHITE);
        }
        g.fillOval(x - r, y - r, r * 2, r * 2);
        //Draw outline of node
        if (tree.getPath2().containsKey(node.getData())) {
            g.setColor(Color.red);
        } else {
            g.setColor(Color.BLACK);
        }
        g.drawOval(x - r, y - r, r * 2, r * 2);
        //Draw value of node
        centerString(this.g, 
                new Rectangle(x - r, y - r, r * 2, r * 2),
                node.getData() + "", 
                new Font("Arial", Font.PLAIN, 12));
        g.setColor(Color.RED);
        centerString(this.g,
                new Rectangle(x - r, y + r, r * 2, r * 2),
                "C=" + node.getCount() + ";L=" + node.getLevel(),
                new Font("Arial", Font.PLAIN, 12));
        drawNode(node.getLeftChild());//Draw the node's left child
        drawNode(node.getRightChild());//Draw the node's right child
    }

    /**
     * Get search result as a String of nodes path
     * @return Search result String
     */
    public String getSearchPath() {
        String result = new String();
        //Convert search path into a String by appending into result String
        for (int i = 0; i < tree.getPath().size(); i++) {
            if (i != tree.getPath().size() - 1) {
                result += tree.getPath().get(i) + "->";
            } else {
                result += tree.getPath().get(i);
            }
        }
        return result;
    }

    /**
     *Reset search results in tree
     */
    public void resetSearch() {
        tree.setPath(new ArrayList<>());
        tree.setPath2(new HashMap<>());
        repaint();
    }

    /**
     * Paint method for BSTPaper
     *
     * @param graph To draw with
     */
    @Override
    public void paint(Graphics graph) {
        super.paint(graph);

        this.g = (Graphics2D) graph;

        drawNode(this.tree.getRoot());//Draw nodes of this the tree

    }

}
