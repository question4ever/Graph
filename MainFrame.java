import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MainFrame extends JFrame {


    public MainFrame(String title){
        super(title);
        //Set Layout mananger
        setLayout(new BorderLayout());
        //Create Swing Component
        final GraphPanel graph = new GraphPanel();
        JButton outputButton = new JButton("Output");
        JButton clearButton = new JButton("Clear");
        JButton findCenterButton = new JButton("Hightlight Center");
        JLabel vertexLabel = new JLabel("Vertex to delete");
        JTextField vertexTextField = new JTextField(10);
        JButton vertexButton = new JButton("Delete");
        JButton getEccentricityButton = new JButton("Get Eccentricity");
        JTextArea aMatrixArea = new JTextArea();
        JTextArea centerArea = new JTextArea();

        JPanel controlJPanel = new JPanel();
        Dimension size = controlJPanel.getPreferredSize();
        size.width = 250;
        controlJPanel.setPreferredSize(size);

        controlJPanel.setBorder(BorderFactory.createTitledBorder("Controls"));
        controlJPanel.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 1;
        controlJPanel.add(getEccentricityButton, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        controlJPanel.add(clearButton, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        controlJPanel.add(vertexLabel, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        controlJPanel.add(vertexTextField, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        controlJPanel.add(vertexButton, gc);

        gc.gridx = 0;
        gc.gridy = 4;
        controlJPanel.add(findCenterButton, gc);

        gc.gridx = 0;
        gc.gridy = 5;
        controlJPanel.add(centerArea, gc);
        
        gc.gridx = 0;
        gc.gridy = 6;
        controlJPanel.add(outputButton, gc);

        gc.gridx = 0;
        gc.gridy = 7;
        controlJPanel.add(aMatrixArea, gc);

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
                aMatrixArea.setText(x);
            }
        });

        findCenterButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                String x = "";
                for (Node center_node : graph.FindCenter()) {
                    x = x + "Node: " + center_node.value + " [";
                    for (Node n : center_node.adjacent) {
                        x = x + n.value + " ";
                    }
                    x += "]\n";
                }
                centerArea.setText(x);
                repaint();
            }        
        });

        vertexButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                graph.DeleteVertex(Integer.parseInt(vertexTextField.getText()));
            }
        });

        getEccentricityButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                for (Node n : graph.vertices) {
                    graph.ResetVisited();
                    n.eccentricity = graph.GetEccentricity(n);
                    repaint();
                }
            }
        });

        clearButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                graph.vertices.clear();
                repaint();
            }
        });

        //Add swing component
        Container c = getContentPane();

        c.add(graph, BorderLayout.CENTER);
        c.add(controlJPanel, BorderLayout.WEST);
    }
}