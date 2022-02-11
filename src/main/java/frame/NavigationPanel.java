package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationPanel extends JPanel implements ActionListener {

    JButton addDataBtn = new JButton();
    JButton viewDataBtn = new JButton();

    public NavigationPanel(){
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(150,100));

        // addData button
        addDataBtn.setText("Add data");
        addDataBtn.setFocusable(false);
        addDataBtn.addActionListener(this);
        addDataBtn.setToolTipText("Open panel that allows to add data to register.");
        this.add(addDataBtn);
        // viewData button
        viewDataBtn.setText("View data");
        viewDataBtn.setFocusable(false);
        viewDataBtn.addActionListener(this);
        viewDataBtn.setToolTipText("Open panel that allows to view data from register.");
        this.add(viewDataBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addDataBtn){
            ProgramFrame.mainPanel.AddDataPanel();
        }
        else if(e.getSource() == viewDataBtn){
            ProgramFrame.mainPanel.ViewDataPanel();
        }
    }
}
