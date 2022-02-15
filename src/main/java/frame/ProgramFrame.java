package frame;

import register.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ProgramFrame extends JFrame implements ActionListener {

    static MainPanel mainPanel = new MainPanel();
    HeaderPanel header = new HeaderPanel();
    FooterPanel footer = new FooterPanel();
    NavigationPanel navigation = new NavigationPanel();

    // menu items
    JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_T);
    JMenuItem readItem = new JMenuItem("Open", KeyEvent.VK_T);

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

        // menu
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_A);
        saveItem.addActionListener(this);
        readItem.addActionListener(this);

        fileMenu.add(saveItem);
        fileMenu.add(readItem);
        menuBar.add(fileMenu);
        this.setJMenuBar(menuBar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == saveItem){
            SaveWindow sw = new SaveWindow();
        }
        if(e.getSource() == readItem){
            LoadWindow sw = new LoadWindow();
        }
    }
}
