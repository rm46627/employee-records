package frame;

import register.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class LoadWindow extends JFrame implements ActionListener {

    JTextField path = new JTextField();
    JButton selectPathBtn = new JButton("Select path");
    JButton loadBtn = new JButton("Load");

    public LoadWindow(){
        this.setTitle("Load register");
        this.setSize(600,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        selectPathBtn.addActionListener(this);
        loadBtn.addActionListener(this);

        // layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        path.setPreferredSize(new Dimension(300,25));
        add(path, c);
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        add(selectPathBtn,c);
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(30,0,0,0);
        add(loadBtn,c);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == selectPathBtn){
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            int response = chooser.showOpenDialog(null);

            if(response == JFileChooser.APPROVE_OPTION){
                path.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        }
        else if(actionEvent.getSource() == loadBtn){
            Register newReg = null;
            try {
                newReg = ProgramFrame.reg.readRegistryBackup(path.getText());
            } catch (ExecutionException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
            ProgramFrame.reg = newReg;
            loadBtn.setBackground(Color.green);
            loadBtn.setText("Loaded!");
        }
    }
}
