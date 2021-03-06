package frame;

import register.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddManagerTab extends JPanel implements ActionListener{

    TabComponent header = new TabComponent("Manager");
    JPanel panel = new JPanel(false);
    Font font1 = new Font("SansSerif", Font.ITALIC, 25);
    JButton submitBtn = new JButton("Submit");
    JButton addCustomFieldsBtn = new JButton("Add custom informations");

    List<JTextField> standardFields = new ArrayList<>();
    List<JTextField> customFields = new ArrayList<>();


    public AddManagerTab() {
        panel.setLayout(new GridLayout(0, 1, 0, 7));
        JScrollPane scroller = new JScrollPane(panel);
        scroller.getVerticalScrollBar().setUnitIncrement(16);
        setLayout(new BorderLayout());
        add(scroller, BorderLayout.CENTER);

        JTextField pesel = new JTextField("PESEL");
        JTextField name = new JTextField("Name", 30);
        JTextField surname = new JTextField("Surname");
        JTextField salary = new JTextField("Salary");
        JTextField number = new JTextField("Phone number");
        JTextField bonus = new JTextField("Bonus");
        JTextField card = new JTextField("Bussines card");
        JTextField limit = new JTextField("Cost limit");

        standardFields.add(pesel);
        standardFields.add(name);
        standardFields.add(surname);
        standardFields.add(salary);
        standardFields.add(number);
        standardFields.add(bonus);
        standardFields.add(card);
        standardFields.add(limit);

        for (JTextField textfield : standardFields) {
            textfield.setFont(font1);
            panel.add(textfield);
        }

        panel.add(new JSeparator());
        addCustomFieldsBtn.addActionListener(this);
        panel.add(addCustomFieldsBtn);
        submitBtn.addActionListener(this);
        panel.add(submitBtn);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // adds new menager to data register
        if(e.getSource() == submitBtn){
            Register reg = ProgramFrame.reg;

            standardFields.get(0).setBackground(Color.white);
            standardFields.get(3).setBackground(Color.white);
            standardFields.get(5).setBackground(Color.white);
            standardFields.get(7).setBackground(Color.white);

            String pesel = standardFields.get(0).getText();
            String salary = standardFields.get(3).getText();
            String bonus = standardFields.get(5).getText();
            String limit = standardFields.get(7).getText();

            // checking data
            if(!reg.isPeselValid(pesel) || !reg.isPeselUnique(pesel)){
                standardFields.get(0).setBackground(Color.red);
                standardFields.get(0).setText("Wrong PESEL!");
            }
            else if(!reg.isNumeric(salary)){
                standardFields.get(3).setBackground(Color.red);
                standardFields.get(3).setText("Salary has to be numeric!");
            }
            else if(!reg.isNumeric(bonus)){
                standardFields.get(5).setBackground(Color.red);
                standardFields.get(5).setText("Bonus has to be numeric!");
            }
            else if(!reg.isNumeric(limit)){
                standardFields.get(7).setBackground(Color.red);
                standardFields.get(7).setText("Limit has to be numeric!");
            }
            else {
                // adding employee to register
                reg.addManager(
                        pesel,
                        standardFields.get(1).getText(), // name
                        standardFields.get(2).getText(), // surname
                        new BigDecimal(Integer. parseInt(salary)),
                        standardFields.get(4).getText(), // phone number
                        new BigDecimal(Integer. parseInt(bonus)),
                        standardFields.get(6).getText(), // business card
                        new BigDecimal(Integer. parseInt(limit))
                );

                // adding data to employee from custom fields
                for(int i=0; i < customFields.size();){
                    reg.getEmployee(reg.getEmployeeCounter()-1).addCustom(
                            customFields.get(i).getText(), // label
                            customFields.get(i+1).getText() // value
                    );
                    i = i+2;
                }

                // setting textfields not accesable
                submitBtn.setEnabled(false);
                addCustomFieldsBtn.setEnabled(false);
                for(JTextField field : standardFields){
                    field.setEnabled(false);
                }
                for(JTextField field : customFields){
                    field.setEnabled(false);
                }
            }
        }

        // adding textfields for custom data
        else if (e.getSource() == addCustomFieldsBtn){
            int cnt = customFields.size();
            String num = String.valueOf(cnt/2+1);
            JTextField label = new JTextField("Label #" + num);
            JTextField value = new JTextField("Value #" + num);
            label.setFont(font1);
            value.setFont(font1);

            customFields.add(label);
            customFields.add(value);
            panel.add(value, 8+cnt);
            panel.add(label, 8+cnt);
            panel.repaint();
            panel.revalidate();
        }
    }
}
