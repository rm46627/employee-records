package frame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AddManagerTab extends JPanel {

    TabHeaderComponent header;

    public AddManagerTab() {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel();
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        header = new TabHeaderComponent("Manager");
    }
}
