import java.awt.Graphics;
import java.awt.*;
import java.lang.Runnable;
import java.lang.Thread;
import javax.swing.*;


class GUI implements Runnable{

   public void createWindow(){
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
       //frame.add(new GraphCanvas(g));
       frame.getContentPane().add(mb, BorderLayout.PAGE_START);
       frame.setVisible(true);
   }

   public void run()
   {
      createWindow();
      System.out.println("GUI running.........");
   }
}

class Node {
   public static int num_nodes = 0;
   public int value;
   public Node adjacent[];
   public Node(){
      num_nodes++;
      value = num_nodes;
   }
}

public class Graph{
   public Node vertices[];
   public int adjacency_matrix[][];

   public Graph Cycle(int size){
      vertices = new Node[size];
      for(int i = 0; i < size; i++){
         vertices[i] = new Node();
      }
      for(int i = 0; i < size; i++){
         vertices[i].adjacent = new Node[2];
         if(i == 0) {
           vertices[i].adjacent[0] = vertices[i + 1];
           vertices[i].adjacent[1] = vertices[size - 1];
         } 
         else if(i == 9) {
           vertices[i].adjacent[0] = vertices[0];
           vertices[i].adjacent[1] = vertices[i - 1];
         }
         else{
           vertices[i].adjacent[0] = vertices[i + 1];
           vertices[i].adjacent[1] = vertices[i - 1];
         }
         String x = "Vertex: " + vertices[i].value + " has 2 edges " + vertices[i].adjacent[0].value + ", " 
         + vertices[i].adjacent[1].value;
         System.out.println(x) ;
      }
      return this;
   }

   public static void main(String[] args) {
      GUI gui = new GUI();
      Thread gui_t = new Thread(gui);
      gui_t.start();
      Graph g = new Graph();
      g.Cycle(10);
   }
}