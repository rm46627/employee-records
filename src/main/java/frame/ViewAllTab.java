package frame;

import register.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewAllTab extends JPanel implements ActionListener {

    TabComponent header = new TabComponent("All data");
    JPanel panel = new JPanel(false);
    Font font1 = new Font("SansSerif", Font.PLAIN, 20);
    JButton copyBtn = new JButton("Copy to clipboard");

    String text = "<html>";

    public ViewAllTab(){
        // setting text data
        Register reg = ProgramFrame.reg;
        if(reg.getEmployeeCounter() == 0){
            text += "There is no data added to register yet.";
        } else {
            for(int i = 0; i < reg.getEmployeeCounter(); i++ ){
                text += reg.getEmployee(i).printData();
                text += "<br><br>";
            }
            text = text.replaceAll("(\r\n|\n)", "<br />");
        }

        // setting layouts and components
        JLabel data = new JLabel(text);
        data.setFont(font1);
        panel.setLayout(new BorderLayout());
        panel.add(data, BorderLayout.CENTER);
        copyBtn.addActionListener(this);
        panel.add(copyBtn, BorderLayout.SOUTH);
        JScrollPane scroller = new JScrollPane(panel);
        scroller.getVerticalScrollBar().setUnitIncrement(16);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == copyBtn){
            StringSelection stringSelection = new StringSelection(text);
            Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
            clpbrd.setContents(stringSelection, null);
            copyBtn.setText("Copied!");
            copyBtn.setBackground(Color.GREEN);
            repaint();
            revalidate();
        }
    }
}
