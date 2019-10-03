import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {


    public MainFrame(String title){
        super(title);

        //Set Layout mananger
        setLayout(new BorderLayout());
        //Create Swing Component
        final GraphPanel graph = new GraphPanel();
        
        //Add swing component
        Container c = getContentPane();

        c.add(graph, BorderLayout.CENTER);
    }
}