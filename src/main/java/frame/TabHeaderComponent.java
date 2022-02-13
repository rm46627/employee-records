package frame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Jpanel that contains title and close btn
class TabHeaderComponent extends JPanel {
    private final JTabbedPane tabPane = ProgramFrame.mainPanel.tabbedPane;

    public TabHeaderComponent(String title){
        //
        add(new JLabel(title));

        setOpaque(false);

        // close button
        CloseBtn btn = new CloseBtn();
        // hover effect for "X" button
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.RED);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setForeground(Color.BLACK);
            }
        });
        add(btn);
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
            int i = tabPane.indexOfTabComponent(TabHeaderComponent.this);
            if (i != -1) {
                tabPane.remove(i);
            }
        }
    }
}
