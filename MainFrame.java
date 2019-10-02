import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    
    private DetailsPanel detailsPanel;

    public MainFrame(String title){
        super(title);

        //Set Layout mananger
        setLayout(new BorderLayout());
        //Create Swing Component
        final GraphPanel graph = new GraphPanel();

        detailsPanel = new DetailsPanel();
        detailsPanel.addDetailListener(new DetailListener(){
            public void detailEventOccurred(DetailEvent event){
                graph.vertices.add(event.getNode());
                System.out.print("Vertices size: " + graph.vertices.size());
                repaint();
            }
        });

        //Add swing component
        Container c = getContentPane();

        c.add(graph, BorderLayout.EAST);
        c.add(detailsPanel, BorderLayout.WEST);
    }
}