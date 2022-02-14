package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    JPanel labelAndButtons = new JPanel();
    JLabel panelTitle = new JLabel("Main panel");
    JButton addManagerBtn = new JButton("Add manager");
    JButton addTraderBtn = new JButton("Add trader");
    JTabbedPane tabbedPane = new JTabbedPane();


    MainPanel(){
        // hide components
        panelTitle.setVisible(false);
        addManagerBtn.setVisible(false);
        addTraderBtn.setVisible(false);

        // setting up layouts
        this.setLayout(new BorderLayout());
        this.add(tabbedPane, BorderLayout.CENTER);
        labelAndButtons.setPreferredSize(new Dimension(1,100));
        this.add(labelAndButtons, BorderLayout.NORTH);
        labelAndButtons.setLayout(new GridBagLayout());

        // adding label and buttons
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 0;
        labelAndButtons.add(panelTitle, c);
        c.insets = new Insets(15,0,0,0);
        c.ipady = 20;
        c.gridx = 0;
        c.gridy = 1;
        labelAndButtons.add(addManagerBtn, c);
        c.gridx = 2;
        c.gridy = 1;
        labelAndButtons.add(addTraderBtn, c);
    }

    public void AddDataPanel() {
        panelTitle.setText("Add data panel");
        panelTitle.setVisible(true);
        addManagerBtn.setVisible(true);
        addTraderBtn.setVisible(true);
        addManagerBtn.addActionListener(this);
        addTraderBtn.addActionListener(this);
    }

    public void ViewDataPanel() {
        panelTitle.setText("View data panel");
        addManagerBtn.setVisible(false);
        addTraderBtn.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addManagerBtn){
            AddManagerTab managerTab = new AddManagerTab();
            tabbedPane.addTab("Manager", managerTab);
            tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 1, managerTab.header);
        }
        if(e.getSource() == addTraderBtn){
            JComponent traderTab = addTraderTab();
            tabbedPane.addTab("Trader", traderTab);
        }

    }

    protected JComponent addTraderTab() {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel();
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}
