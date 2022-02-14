package frame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

// Jpanel that contains title and close button for tabbedPane tabs
class TabComponent extends JPanel {
    private final JTabbedPane tabPane = ProgramFrame.mainPanel.tabbedPane;

    public TabComponent(String title){
        // tab title
        add(new JLabel(title));

        // close button
        CloseBtn btn = new CloseBtn();
        add(btn);
        // hover effect for "X" button
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.RED);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.BLACK);
            }
        });

        setOpaque(false);
    }

    class CloseBtn extends JButton implements ActionListener {

        public CloseBtn() {
            setPreferredSize(new Dimension(15, 15));
            setContentAreaFilled(false);
            setUI(new BasicButtonUI());
            setFocusable(false);
            setBorder(BorderFactory.createEtchedBorder());
            setBorderPainted(false);
            setText("X");
            addActionListener(this);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // closing tab
            int i = tabPane.indexOfTabComponent(TabComponent.this);
            if (i != -1) {
                tabPane.remove(i);
            }
        }
    }
}
