import java.awt.Graphics;
import java.lang.Runnable;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.EventObject;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTreeUI.MouseInputHandler;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.lang.Math;


class GUI {

   public JFrame createWindow(){
       JFrame frame = new JFrame("My First GUI");
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(500,500);
       //Creating the MenuBar and adding components
       JMenuBar mb = new JMenuBar();
       JMenu m1 = new JMenu("FILE");
       JMenu m2 = new JMenu("Help");
       mb.add(m1);
       mb.add(m2);
       JMenuItem m11 = new JMenuItem("Open");
       JMenuItem m22 = new JMenuItem("Save as");
       m1.add(m11);
       m1.add(m22);
       frame.getContentPane().add(mb, BorderLayout.PAGE_START);
       frame.setVisible(true);
       MouseInputHandler handler = new MouseInputHandler(source, destination, event)
       return frame;
   }

   public void run()
   {
      createWindow();
      System.out.println("GUI running.........");
   }
}

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
   public Node(){
      num_nodes++;
      value = num_nodes;
      this.shape = new Circle(20,20);
   }

}

public class Graph extends JPanel {
   public ArrayList<Node> vertices;
   public int adjacency_matrix[][];


   @Override
   protected void paintComponent(Graphics g){
      super.paintComponent(g);
      double angle = (Math.PI)/vertices.length;
      System.out.println("Angle :" + angle);
      int x = vertices.get(0).shape.x;
      int y = vertices.get(0).shape.y;
      for (int i = 0; i < vertices.length; i++) {
         Circle nCircle = vertices.get(i).shape;
         nCircle.draw(g);
      }
   }

   public Graph Cycle(int size){
      vertices = new ArrayList<Node>();
      for(int i = 0; i < size; i++){
         vertices.add(new Node()); 
      }
      for(int i = 0; i < size; i++){
         vertices.get(i).adjacent = new Node[2];
         if(i == 0) {
           vertices.get(i).adjacent[0] = vertices.get(i + 1);
           vertices.get(i).adjacent[1] = vertices.get(size - 1);
         } 
         else if(i == (size - 1)) {
           vertices.get(i).adjacent[0] = vertices.get(0);
           vertices.get(i).adjacent[1] = vertices.get(i - 1);
         }
         else{
           vertices.get(i).adjacent[0] = vertices.get(i + 1);
           vertices.get(i).adjacent[1] = vertices.get(i - 1);
         }
         String x = "Vertex: " + vertices.get(i).value + " has 2 edges " + vertices.get(i).adjacent[0].value + ", " 
         + vertices.get(i).adjacent[1].value;
         System.out.println(x) ;
      }
      return this;
   }

   public static void main(String[] args) {
      
   }
}