package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddManagerTab extends JButton implements ActionListener {
    public AddManagerTab() {
    }


    public CloseBtn() {
        this.setPreferredSize(new Dimension(10, 10));
        setContentAreaFilled(false);
        setFocusable(false);
        setBorder(BorderFactory.createEtchedBorder());
        setBorderPainted(false);
//            addMouseListener(buttonMouseListener);
//            setRolloverEnabled(true);
        //Close the proper tab by clicking the button
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int i = tabbedPane.indexOfTabComponent(TabCloseBtn.this);
        if (i != -1) {
            pane.remove(i);
        }
    }
}
