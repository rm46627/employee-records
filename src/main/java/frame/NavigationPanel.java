package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigationPanel extends JPanel {

    JButton addEmployeeBtn = new JButton();

    public NavigationPanel(){
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(200,100));

        // addEmployee button
        addEmployeeBtn.setBounds(0,0,100,100);
        addEmployeeBtn.setText("Add data");
        addEmployeeBtn.setFocusable(false);
        addEmployeeBtn.addActionListener(e -> ActionPanel.setAddDataAction());
        this.add(addEmployeeBtn);
    }
}
