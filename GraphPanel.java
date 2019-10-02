import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.Color;

class Circle {

    int x, y, width, height;

    public Circle(int x, int y) {
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
    public Node adjacent[];
    public Circle shape;
    public Node() {
       num_nodes++;
       value = num_nodes;
       this.shape = new Circle(20,20);
    }
    public Node(int x, int y){
        num_nodes++;
        value = num_nodes;
        this.shape = new Circle(x, y);
    }
}

public class GraphPanel extends JPanel {
    public ArrayList<Node> vertices;
    public int adjacency_matrix[][];

    public GraphPanel(){
        vertices = new ArrayList<Node>();
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(250, 250));
    }

    @Override
    protected void paintComponent(Graphics g){
       super.paintComponent(g);
       if(vertices.size() > 0){
            for (int i = 0; i < vertices.size(); i++) {
                Circle nCircle = vertices.get(i).shape;
                nCircle.draw(g);
            }
        }
    }

    public void addNode(int x, int y){
        vertices.add(new Node(x, y));
    }
}