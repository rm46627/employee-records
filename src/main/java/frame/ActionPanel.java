package frame;

import javax.swing.*;
import java.awt.*;

public class ActionPanel extends JPanel {

    JPanel header = new JPanel();
    static JLabel titleLabel = new JLabel();
    static JPanel center = new JPanel();

    public ActionPanel(){
        this.setPreferredSize(new Dimension(100,100));
        this.setLayout(new BorderLayout());

        // header
        header.setPreferredSize(new Dimension(100,30));
        this.add(header, BorderLayout.NORTH);
        header.add(titleLabel);

        // center
        this.add(center);
    }

    public static void setAddDataAction(){
        // setup buttons
        titleLabel.setText("Add data");
        JButton addDirectorBtn = new JButton();
        addDirectorBtn.setBounds(0,0,100,100);
        addDirectorBtn.setText("Add director");
        addDirectorBtn.addActionListener(e -> ActionPanel.setAddDirectorAction());
        center.add(addDirectorBtn);
        JButton addTraderBtn = new JButton();
        addTraderBtn.setBounds(0,0,100,100);
        addTraderBtn.setText("Add trader");
        addTraderBtn.addActionListener(e -> ActionPanel.setAddDirectorAction());
        center.add(addTraderBtn);
        JButton addCustomBtn = new JButton();
        addCustomBtn.setBounds(0,0,100,100);
        addCustomBtn.setText("Add custom data");
        addCustomBtn.addActionListener(e -> ActionPanel.setAddDirectorAction());
        center.add(addCustomBtn);
    }

    public static void setAddDirectorAction(){

        DirectorForm df = new DirectorForm();
        center.add(df);
    }
}
