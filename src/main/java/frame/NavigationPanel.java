package frame;

import register.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class NavigationPanel extends JPanel implements ActionListener {

    JButton addDataBtn = new JButton();
    JButton viewDataBtn = new JButton();
    JButton closeTabsBtn = new JButton();
    JButton addExampleBtn = new JButton();

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
        // close all tabs in tabbedPane
        closeTabsBtn.setText("Close all tabs");
        closeTabsBtn.setFocusable(false);
        closeTabsBtn.addActionListener(this);
        this.add(closeTabsBtn);
        // add example data to the register
        addExampleBtn.setText("Add example data");
        addExampleBtn.setFocusable(false);
        addExampleBtn.addActionListener(this);
        addExampleBtn.setBackground(Color.white);
        addExampleBtn.setToolTipText("Adds one manager and two trader data to register.");
        this.add(addExampleBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addDataBtn){
            ProgramFrame.mainPanel.AddDataPanel();
        }
        else if(e.getSource() == viewDataBtn){
            ProgramFrame.mainPanel.ViewDataPanel();
        }else if(e.getSource() == addExampleBtn){
            Register reg = ProgramFrame.reg;
            reg.addTrader("98083005544", "Andrzej", "Grabowski", new BigDecimal(5500), "505976372", new BigDecimal(5), new BigDecimal(3999));
            reg.addTrader("95022254632", "Michal", "Dowacki", new BigDecimal(4050), "8764387647", new BigDecimal(2), new BigDecimal(2000));
            reg.addManager("93061175647", "Krzysztof", "Braniecki", new BigDecimal(5000), "879243445", new BigDecimal(500), "007", new BigDecimal(5000));
            addExampleBtn.setEnabled(false);
        }else if(e.getSource() == closeTabsBtn){
            MainPanel.tabbedPane.removeAll();
        }
    }
}
