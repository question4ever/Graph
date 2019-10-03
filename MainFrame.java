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
        JButton outputButton = new JButton("Output");
        outputButton.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e){
                String x = "";
                for (Node var : graph.vertices) {
                    x = x + "Node: " + var.value + " [";
                    for (Node n : var.adjacent) {
                        x = x + n.value + " ";
                    }
                    x += "]\n";
                }
                System.out.println(x);
            }
        });
        //Add swing component
        Container c = getContentPane();

        c.add(graph, BorderLayout.CENTER);
        c.add(outputButton, BorderLayout.WEST);
    }
}