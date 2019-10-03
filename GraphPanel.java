import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.awt.Color;
import java.util.Collections;

class Circle extends JComponent {

    int x, y, width, height;
    Color c;

    public Circle(int x, int y){
        this.x = x;
        this.y = y;
        c = Color.BLACK;
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Ellipse2D.Double circle = new Ellipse2D.Double(x-5, y-5, 10, 10);

        g2d.setColor(c);
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
       adjacent = new ArrayList<Node>();
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
                int x = vertices.get(vertices.size()- 1).shape.x;
                int y = vertices.get(vertices.size()- 1).shape.y;
                if(e.getX() !=  x || e.getY() != y){
                    x=e.getX();
                    y=e.getY();
                    for (Node var : vertices) {
                        boolean inRangeX = (x <= (var.shape.x + 5) && x >= (var.shape.x - 5));
                        boolean inRangeY = (y <= (var.shape.y + 5) && y >= (var.shape.y - 5));
                        if(inRangeX && inRangeY){
                            var.shape.c = Color.GREEN;
                            vertices.get(vertices.size()- 1).adjacent.add(var);
                            var.adjacent.add(vertices.get(vertices.size()- 1));
                            repaint();
                            return;
                        }
                    }
                    Node n = addNode(x, y);
                    repaint();
                    vertices.get(vertices.size()- 2).adjacent.add(n);
                    n.adjacent.add(vertices.get(vertices.size()- 2));
                }
                return;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x=e.getX();
                int y=e.getY();
                for (Node var : vertices) {
                    boolean inRangeX = (x <= (var.shape.x + 5) && x >= (var.shape.x - 5));
                    boolean inRangeY = (y <= (var.shape.y + 5) && y >= (var.shape.y - 5));
                    if(inRangeX && inRangeY){
                        var.shape.c = Color.BLUE;
                        Collections.swap(vertices, vertices.indexOf(var), vertices.size() - 1);
                        repaint();
                        return;
                    }
                }
                addNode(x, y);
                repaint();
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

    public Node addNode(int x, int y){
        Node n = new Node(x, y);
        vertices.add(n);
        return n;
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