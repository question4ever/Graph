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
    public int distance;
    public int eccentricity;
    public ArrayList<Node> adjacent;
    public Circle shape;
    public boolean visited;
    public Node() {
       num_nodes++;
       value = num_nodes;
       adjacent = new ArrayList<Node>();
       this.shape = new Circle(20,20);
       visited = false;
       distance = -1;
    }
    public Node(int x, int y){
        num_nodes++;
        value = num_nodes;
        adjacent = new ArrayList<Node>();
        this.shape = new Circle(x, y);
        visited = false;
        distance = -1;
        eccentricity = distance;
    }
}

public class GraphPanel extends JPanel {
    public ArrayList<Node> vertices;
    public ArrayList<Node> visited;
    private final JLabel text;

    public GraphPanel(){
        vertices = new ArrayList<Node>();
        visited = new ArrayList<Node>();
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
                g.drawString("" + vertices.get(i).eccentricity ,nCircle.x - 5, nCircle.y - 5);
                if(!vertices.get(i).adjacent.isEmpty()){
                    for (Node var : vertices.get(i).adjacent) {
                        g.drawLine(nCircle.x, nCircle.y, var.shape.x, var.shape.y);
                    }
                }
            }
        }
    }

    public void ResetVisited(){
        visited = new ArrayList<Node>();
        for (Node n : vertices) {
            n.shape.c = Color.BLACK;
            if(n.visited){
                n.visited = false;
            }
        }
    }

    private void VisitNode(Node n){
        n.visited = true;
        visited.add(n);
    }

    private void UnVisitNode(Node n){
        n.visited = false;
        visited.remove(n);
        n.shape.c = Color.BLACK;
    }

    private Node FindMin(ArrayList<Node> v){
        Node min = null;
        for (Node n : v) {
            if(!n.visited){
                if(min == null){
                    min = n;
                }
                if(n.distance < min.distance){
                    min = n;
                }
            }
        }
        return min;
    }

    private ArrayList<Node> BestPath(Node start){
        //Mark selected initial node with a current distance of 0 and the 
        //rest with infinity
        ArrayList<Node> bp = new ArrayList<Node>();
        for (Node n : vertices) {
            if(n.value != start.value){
                n.distance = vertices.size();
            }
            else{
                n.distance = 0;
            }
        }
        while(visited.size() < vertices.size()){
            Node current = FindMin(vertices);
            for (Node neighbor : current.adjacent) {
                int distance = current.distance + 1;
                if(neighbor.distance > distance){
                    neighbor.distance = distance;
                }
            }
            VisitNode(current);
            bp.add(current);
        }
        return bp;
    }

    public int GetEccentricity(Node start){
        ArrayList<Node> bp = BestPath(start);
        int max = 0;
        for (Node var : bp) {
            if(var.distance > max){
                max = var.distance;
            }
        }
        return max;
    }

    public void DeleteVertex(int value){
        for(int i = 0; i < vertices.size(); i++){
            Node n = vertices.get(i);
            for(int j = 0; j < n.adjacent.size(); j++){
                Node adj = n.adjacent.get(j);
                if(adj.value == value){
                    n.adjacent.remove(j);
                }
            }
        }
        for (int i = 0; i < vertices.size(); i++) {
            Node n = vertices.get(i);
            if(n.value == value){
                vertices.remove(n);
            }
        }
    }

    public ArrayList<Node> FindCenter() {
        int min_eccentricity = vertices.size();
        ArrayList<Node> center = new ArrayList<Node>();
        for(Node n : vertices){
            if(n.eccentricity < min_eccentricity){
                min_eccentricity = n.eccentricity;
            }
        }
        for(Node n : vertices){
            if(n.eccentricity == min_eccentricity){
                n.shape.c = Color.RED;
                center.add(n);
            }
        }
        return center;
    }

    public Node addNode(int x, int y){
        Node n = new Node(x, y);
        vertices.add(n);
        return n;
    }
}