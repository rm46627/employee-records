package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SaveWindow extends JFrame implements ActionListener {

    JTextField path = new JTextField();
    JButton selectPathBtn = new JButton("Select path");
    JButton saveBtn = new JButton("Save");
    JRadioButton gzipOption = new JRadioButton("gzip");
    JRadioButton zipOption = new JRadioButton("zip");
    ButtonGroup extensionGroup = new ButtonGroup();
    boolean gzipFlag = false;

    public SaveWindow(){
        this.setTitle("Save register");
        this.setSize(600,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        extensionGroup.add(gzipOption);
        extensionGroup.add(zipOption);
        selectPathBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        gzipOption.addActionListener(this);
        zipOption.addActionListener(this);

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
        c.gridx = 1;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_END;
        add(gzipOption,c);
        c.gridx = 2;
        c.gridy = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(zipOption,c);
        c.gridx = 2;
        c.gridy = 3;
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(30,0,0,0);
        add(saveBtn,c);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == selectPathBtn){

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));
            int response = chooser.showSaveDialog(null);
            if(response == JFileChooser.APPROVE_OPTION){
                path.setText(chooser.getSelectedFile().getAbsolutePath());
            }
        }
        else if(actionEvent.getSource() == gzipOption){
            gzipFlag = true;
        }
        else if(actionEvent.getSource() == zipOption){
            gzipFlag = false;
        }
        else if(actionEvent.getSource() == saveBtn){
            try {
                if (gzipFlag){
                    ProgramFrame.reg.saveRegistryBackup(ProgramFrame.reg, path.getText(), "gz");
                }
                else {
                    ProgramFrame.reg.saveRegistryBackup(ProgramFrame.reg, path.getText(), "zip");
                }
            } catch (ExecutionException | IOException | InterruptedException e) {
                e.printStackTrace();
            }
            saveBtn.setBackground(Color.green);
            saveBtn.setText("Saved!");
        }
    }
}
