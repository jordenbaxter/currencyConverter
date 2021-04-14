package lmao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class secondCurrency extends JFrame implements ActionListener {

    JButton submit;
    JTextField textField;
    JTextArea message;

    public static String secondCurrency;
    public static int confirm;

    secondCurrency() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setTitle("Currency Converter");

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(100, 30));

        submit = new JButton("Submit");
        submit.addActionListener(this);

        message = new JTextArea("To which currency do you want to convert this currency to?:- ");

        this.add(textField);
        this.add(submit);
        this.add(message);
        this.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width/2-this.getSize().width/2), (dim.height/2-this.getSize().height/2));
        this.setSize(400, 275);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit) {
            secondCurrency = textField.getText();
            confirm = 1;
            this.dispose();
        }
    }
}
