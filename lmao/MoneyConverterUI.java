package lmao;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.Scanner;

class MoneyConverterUI {

    public static void main(String[] args) throws Exception {
        int confirm;
        firstCurrency firstCurrencyUI = new firstCurrency();
        while (true) {
            System.out.print("");
            if (firstCurrency.confirm == 1) {
                confirm = 1;
                break;
            }
        }
        String firstCurrency = null;
        if (confirm == 1) {
            firstCurrency = lmao.firstCurrency.firstCurrency;
        }

        confirm = 0;

        secondCurrency secondCurrencyUI = new secondCurrency();
        while (true) {
            System.out.print("");
            if (secondCurrency.confirm == 1) {
                confirm = 1;
                break;
            }
        }
        String secondCurrency = null;
        if (confirm == 1) {
            secondCurrency = lmao.secondCurrency.secondCurrency;
        }

        confirm = 0;

        amountToConvert amountToConvertUI = new amountToConvert();
        while (true) {
            System.out.print("");
            if (amountToConvert.confirm == 1) {
                confirm = 1;
                break;
            }
        }
        String amountToConvert = null;
        if (confirm == 1) {
            amountToConvert = lmao.amountToConvert.amountToConvert;
        }

        BufferedReader key = new BufferedReader(new FileReader("key.txt"));
        String userApiKey = key.readLine();
        URL api = new URL("https://free.currconv.com/api/v7/convert?q="+firstCurrency+"_"+secondCurrency+"&compact=ultra&apiKey="+userApiKey);
        Scanner fancyApiString = new Scanner(api.openStream());
        StringBuilder sb = new StringBuilder();
        while(fancyApiString.hasNext()) {
            sb.append(fancyApiString.next());
        }
        String result = sb.toString();
        result = result.replaceAll("<[^>]*>:" , " ");
        int start = result.indexOf(":");
        int end = result.indexOf("}");

        String answer = amountToConvert+" "+firstCurrency.toUpperCase()+" is equal to "+ (Double.parseDouble(result.substring(start+1, end)) * Double.parseDouble(amountToConvert)) +" "+secondCurrency.toUpperCase()+".";
        JFrame answerDisplayJFrame = new JFrame();
        JTextArea answerDisplay = new JTextArea(answer);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        answerDisplayJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        answerDisplayJFrame.setLocation((dim.width/2-answerDisplayJFrame.getSize().width/2), (dim.height/2-answerDisplayJFrame.getSize().height/2));
        answerDisplayJFrame.setLayout(new FlowLayout());
        answerDisplayJFrame.add(answerDisplay);
        answerDisplayJFrame.pack();
        answerDisplayJFrame.setVisible(true);
    }
}
