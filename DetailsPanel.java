import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;

import javax.swing.*;
import javax.swing.event.EventListenerList;

public class DetailsPanel extends JPanel{
    private static final long serialVersionUID = 1L;

    private EventListenerList listenerList = new EventListenerList();

    public DetailsPanel() {
        Dimension size = getPreferredSize();
        size.width = 250;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Personal Details"));

        JLabel posXLabel = new JLabel("X: ");
        JLabel posYLabel = new JLabel("Y: ");

        final JTextField posXField = new JTextField(10);
        final JTextField posYField = new JTextField(10);

        JButton addBtn = new JButton("Add");
        addBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int posX = Integer.parseInt(posXField.getText());
                int posY = Integer.parseInt(posYField.getText());

                fireDetailEvent(new DetailEvent(this, posX, posY));
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //First column
        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx = 0.5;
        gc.weighty = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;
        add(posXLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(posYLabel, gc);

        //Second column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;
        add(posXField, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        add(posYField, gc);

        //Final row
        gc.weighty = 10;

        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.gridx = 1;
        gc.gridy = 2;
        add(addBtn, gc);         
    }

    public void fireDetailEvent(DetailEvent dEvent){
        Object[] listeners = listenerList.getListenerList();

        for(int i = 0; i < listeners.length; i++){
            if(listeners[i] == DetailListener.class){
                ((DetailListener)listeners[i+1]).detailEventOccurred(dEvent);
            }
        }
    }

    public void addDetailListener(DetailListener dListener){
        listenerList.add(DetailListener.class, dListener);
    }

    public void removeDetailListener(DetailListener dListener){
        listenerList.remove(DetailListener.class, dListener);
    }
}