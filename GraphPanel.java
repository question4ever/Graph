import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.Color;

class Circle extends JComponent {

    int x, y, width, height;

    public Circle(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, 10, 10);

        g2d.setColor(Color.BLACK);
        g2d.fill(circle);
    }
}

class Node {
    public static int num_nodes = 0;
    public int value;
    public ArrayList<Node> adjacent;
    public Circle shape;
    public Node() {
       num_nodes++;
       value = num_nodes;
       this.shape = new Circle(20,20);
    }
    public Node(int x, int y){
        num_nodes++;
        value = num_nodes;
        adjacent = new ArrayList<Node>();
        this.shape = new Circle(x, y);
    }
}

public class GraphPanel extends JPanel {
    public ArrayList<Node> vertices;
    public int adjacency_matrix[][];
    private final JLabel text;

    public GraphPanel(){
        vertices = new ArrayList<Node>();
        text = new JLabel();
        add(text);
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(250, 250));
        addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                addNode(x, y);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e){
                return;
            }

            @Override
            public void mouseEntered(MouseEvent e){
                return;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                return;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                return;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g){
       super.paintComponent(g);
       if(vertices.size() > 0){
            for (int i = 0; i < vertices.size(); i++) {
                Circle nCircle = vertices.get(i).shape;
                text.setText("Node " + vertices.get(i).value + " at (" + nCircle.x + ", " + nCircle.y + ")");
                nCircle.draw(g);
                if(!vertices.get(i).adjacent.isEmpty()){
                    for (Node var : vertices.get(i).adjacent) {
                        g.drawLine(nCircle.x, nCircle.y, var.shape.x, var.shape.y);
                    }
                }
            }
        }
    }

    public void addNode(int x, int y){
        vertices.add(new Node(x, y));
        if(vertices.size() >=  2){
            infoBox(vertices.size() - 1);
        }
    }

    public void infoBox(int index)
    {
        int dialog = JOptionPane.showConfirmDialog(null, "Does an edge exist between last node?", "Yes/No : Edge", JOptionPane.YES_NO_OPTION);
        if(dialog == JOptionPane.YES_OPTION){
            vertices.get(index).adjacent.add(vertices.get(index - 1));
            vertices.get(index - 1).adjacent.add(vertices.get(index));
        }
    }
}