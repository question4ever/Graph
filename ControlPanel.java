import javax.swing.event.EventListenerList;
import javax.swing.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel{
    
    public ControlPanel(){
        Dimension size = getPreferredSize();
        size.width = 250;
        setPrefferedSize(size);

        setBorder(BorderFactory.createTitledBorder("Controls"))
        JButton outputButton = new JButton("Output");
        JTextArea adjacentArea = new JTextArea();

        outputButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        add(outputButton, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(adjacentArea, gc);

    }
}