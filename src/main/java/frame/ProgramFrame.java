package frame;

import register.Register;

import javax.swing.*;
import java.awt.*;

public class ProgramFrame extends JFrame {

    static MainPanel mainPanel = new MainPanel();
    HeaderPanel header = new HeaderPanel();
    FooterPanel footer = new FooterPanel();
    NavigationPanel navigation = new NavigationPanel();

    public static Register reg = new Register();

    public ProgramFrame(){
        // frame options
        this.setLayout(new BorderLayout(3,3));
        this.setTitle("Employee register");
        this.setSize(1024,720);
        this.setVisible(true); // make this visible
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // defines action to X button - default is HIDE_ON_CLOSE
        this.getContentPane().setBackground(Color.black);

        // setting panels
        this.add(header, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);
        this.add(navigation, BorderLayout.WEST);
        this.add(mainPanel, BorderLayout.CENTER);

    }
}
