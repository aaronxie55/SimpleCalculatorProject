package com.aaroncoded;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class Main implements ActionListener {

    JFrame frame;
    JButton[] numButtons = new JButton[10];
    JButton[] funcButtons = new JButton[8];
    JButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    JButton buttonAdd, buttonSub, buttonMul, buttonDiv, buttonDec, buttonEqu, buttonClear, buttonRpn;
    JTextField textField;
    JLabel labelRpn;
    JPanel panel;

    private String num1 = "";
    private String num2 = "0";
    private String store = "";
    private double result = 0;
    private boolean rpn = false;
    private char operator = ' ';

    Font font = new Font("Arial", Font.PLAIN, 40);


    Main() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 650);
        frame.setLayout(null);

        buttonAdd = new JButton("+");
        buttonSub = new JButton("-");
        buttonMul = new JButton("*");
        buttonDiv = new JButton("/");
        buttonDec = new JButton(".");
        buttonEqu = new JButton("=");
        buttonClear = new JButton("Clear");
        buttonRpn = new JButton("RPN");

        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");

        labelRpn = new JLabel("RPN mode off", JLabel.CENTER);

        funcButtons[0] = buttonAdd;
        funcButtons[1] = buttonSub;
        funcButtons[2] = buttonMul;
        funcButtons[3] = buttonDiv;
        funcButtons[4] = buttonDec;
        funcButtons[5] = buttonEqu;
        funcButtons[6] = buttonClear;
        funcButtons[7] = buttonRpn;

        numButtons[0] = button0;
        numButtons[1] = button1;
        numButtons[2] = button2;
        numButtons[3] = button3;
        numButtons[4] = button4;
        numButtons[5] = button5;
        numButtons[6] = button6;
        numButtons[7] = button7;
        numButtons[8] = button8;
        numButtons[9] = button9;

        panel = new JPanel();
        panel.setBounds(50, 95, 375, 375);
        panel.setLayout(new GridLayout(4, 5));

        for (int i = 0; i < 10; i++) {
            numButtons[i].addActionListener(this);
            numButtons[i].setFont(font);
            numButtons[i].setFocusable(false);
        }
        for (int i = 0; i < 8; i++) {
            funcButtons[i].addActionListener(this);
            funcButtons[i].setFont(font);
            funcButtons[i].setFocusable(false);
        }

        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(funcButtons[3]);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(funcButtons[2]);
        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(funcButtons[1]);
        panel.add(numButtons[0]);
        panel.add(funcButtons[4]);
        panel.add(funcButtons[5]);
        panel.add(funcButtons[0]);


        buttonRpn.setBounds(50, 470, 187, 94);
        buttonClear.setBounds(240, 470, 187, 94);
        labelRpn.setBounds(50, 560, 187, 30);
        textField = new JTextField();
        textField.setBounds(50, 35, 375, 50);

        textField.setFont(font);
        textField.setText("0");
        textField.setEditable(false);

        frame.add(panel);
        frame.add(textField);
        frame.add(buttonRpn);
        frame.add(buttonClear);
        frame.add(labelRpn);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numButtons[i]) {
                if (textField.getText().equals("0")) {
                    textField.setText("");
                } else if (num2.isEmpty()) {
                    textField.setText("");
                }
                textField.setText(textField.getText().concat(String.valueOf(i)));
                num2 = textField.getText();

            }
        }
        if (e.getSource() == buttonDec) {
            if (!textField.getText().contains("."))
                textField.setText(textField.getText().concat("."));
        }
//-----------------------------------------------------------------------------------------------------------------
        String rpnResult;
        char rpnOperator;
        if (e.getSource() == buttonAdd) {
            if (!rpn) {
                if (operator == ' ') {
                    num1 = num2;
                } else {
                    calculation(Double.parseDouble(num1), operator, Double.parseDouble(num2));
                }
                textField.setText(num1);
                num2 = "";
                operator = '+';
/*               System.out.println("num1 after cal=" + num1);
                System.out.println("num2 after cal=" + num2);
 */
            } else {
                rpnOperator = '+';
                if (num2.isEmpty())
                    store = store + "," + rpnOperator;
                else
                    store = store + "," + num2 + "," + rpnOperator;
                num2 = "";
                rpnResult = rpnCalculation(store);
                textField.setText(rpnResult);
//                System.out.println(rpnResult);
//                System.out.println(store);
            }
        }

        if (e.getSource() == buttonSub) {
            if (!rpn) {
                if (operator == ' ') {
                    num1 = num2;
                } else {
                    calculation(Double.parseDouble(num1), operator, Double.parseDouble(num2));
                }
                textField.setText(num1);
                num2 = "";
                operator = '-';
            } else {
                rpnOperator = '-';
                if (num2.isEmpty())
                    store = store + "," + rpnOperator;
                else
                    store = store + "," + num2 + "," + rpnOperator;
                num2 = "";
                rpnResult = rpnCalculation(store);
                textField.setText(rpnResult);
//                System.out.println(store);
            }
        }

        if (e.getSource() == buttonMul) {
            if (!rpn) {
                if (operator == ' ') {
                    num1 = num2;
                } else {
                    calculation(Double.parseDouble(num1), operator, Double.parseDouble(num2));
                }
                textField.setText(num1);
                num2 = "";
                operator = '*';
            } else {
                rpnOperator = '*';
                if (num2.isEmpty())
                    store = store + "," + rpnOperator;
                else
                    store = store + "," + num2 + "," + rpnOperator;
                num2 = "";
                rpnResult = rpnCalculation(store);
                textField.setText(rpnResult);
//                System.out.println(store);
            }
        }

        if (e.getSource() == buttonDiv) {
            if (!rpn) {
                if (operator == ' ') {
                    num1 = num2;
                } else {
                    calculation(Double.parseDouble(num1), operator, Double.parseDouble(num2));
                }
                textField.setText(num1);
                num2 = "";
                operator = '/';
            } else {
                rpnOperator = '/';
                if (num2.isEmpty())
                    store = store + "," + rpnOperator;
                else
                    store = store + "," + num2 + "," + rpnOperator;
                num2 = "";
                rpnResult = rpnCalculation(store);
                textField.setText(rpnResult);
//                System.out.println(store);
            }
        }

        if (e.getSource() == buttonEqu) {
            if (!rpn) {
                if (operator == ' ') {
                    num1 = num2;
                } else {
                    calculation(Double.parseDouble(num1), operator, Double.parseDouble(num2));
                }
                textField.setText(num1);
                num2 = "";
                operator = ' ';
            } else {
                String rpnNum = num2 = textField.getText();
                if (store.isEmpty())
                    store = rpnNum;
                else
                    store = store + "," + rpnNum;
                num2 = "";
//                System.out.println(store);
            }
        }
        if (e.getSource() == buttonClear) {
            textField.setText("");
            num1 = "0";
            num2 = "0";
            store = "";
        }
        if (e.getSource() == buttonRpn) {
            if (rpn) {
                rpn = false;
                labelRpn.setText("RPN mode off");
                textField.setText("0");
            } else {
                rpn = true;
                labelRpn.setText("RPN mode on");
                textField.setText("0");
            }
        }
    }

    private void calculation(double ans, char operator, double nk) {
        switch (operator) {
            case '+' -> result = ans + nk;
            case '-' -> result = ans - nk;
            case '*' -> result = ans * nk;
            case '/' -> result = ans / nk;
        }
        num1 = String.valueOf(result);
        System.out.println("num1= " + num1);
    }

    private String rpnCalculation(String storedString) {
        Stack<String> stack = new Stack<>();
        String[] split = storedString.split(",");
        ArrayList<String> list = new ArrayList<>();

        for (String splitToken : split) {
            list.add(splitToken);
        }
        for (String splitToken : list) {
            if (splitToken.matches("\\d+")) {
                stack.push(splitToken);
            } else {
                Double num1 = Double.parseDouble(stack.pop());
                Double num2 = Double.parseDouble(stack.pop());
                double ans = switch (splitToken) {
                    case "+" -> num1 + num2;
                    case "-" -> num2 - num1;
                    case "*" -> num2 * num1;
                    case "/" -> num2 / num1;
                    default -> 0;
                };
                stack.push(ans + "");
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        // write your code here
        Main main = new Main();
    }

}