package ru.geekbrains.Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class cal1 extends JFrame {
    public cal1 () {
        setTitle("Калькулятор");
        getContentPane().add(new CalculatorPanel());
        getContentPane().setBackground(new Color(100, 150, 245));
    }


    public static void main(String[] args) {
        cal1 frame = new cal1 ();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(300,200,300,400);
        frame.setVisible(true);
    }
}
class CalculatorPanel extends JPanel {
    private JLabel display;
    private JPanel panel;
    private double result;
    private String lastCommand;
    private boolean start;

    public CalculatorPanel() {
        setLayout(new BorderLayout());
        result = 0;
        lastCommand = "=";
        start = true;
        display = new JLabel("0");
        add(display, BorderLayout.NORTH);
        ActionListener insert = new InsertAction();
        ActionListener command = new CommandAction();
        ActionListener clear = new ClearAction();
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));
        addButton("C", clear);
        addButton("x²", command);
        addButton("%", command);
        addButton("/", command);
        addButton("7", insert);
        addButton("8", insert);
        addButton("9", insert);
        addButton("*", command);
        addButton("4", insert);
        addButton("5", insert);
        addButton("6", insert);
        addButton("-", command);
        addButton("1", insert);
        addButton("2", insert);
        addButton("3", insert);
        addButton("+", command);
        addButton("0", insert);
        addButton(".", insert);
        addButton("√", command);
        addButton("=", command);

        add(panel, BorderLayout.CENTER);
    }

    private void addButton(String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

    private class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            if (start) {
                display.setText("");
                start = false;
            }
            display.setText(display.getText() + input);
        }
    }

    private class ClearAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            display.setText("0");
        }
    }


    private class CommandAction implements ActionListener {
        public void actionPerformed(ActionEvent evt) {
            String command = evt.getActionCommand();
            if (start) {
                if (command.equals("-")) {
                    display.setText(command);
                    start = false;
                } else
                    lastCommand = command;
            } else if (command.equals("√")) {
                display.setText("" + Math.sqrt(Double.parseDouble(display.getText())));
                start = true;
            } else if (command.equals("x²")) {
                display.setText("" + Math.pow(Double.parseDouble(display.getText()), 2));
                start = true;
            } else if (command.equals("C")) {
                display.setText("");
                start = true;
            } else {
                calculate(Double.parseDouble(display.getText()));
                lastCommand = command;
                start = true;
            }
        }

        public void calculate(double x) {
            if (lastCommand.equals("+")) {
                result += x;
                display.setText("" + result);
            } else if (lastCommand.equals("-")) {
                result -= x;
                display.setText("" + result);
            } else if (lastCommand.equals("*")) {
                result *= x;
                display.setText("" + result);
            } else if (lastCommand.equals("/")) {
                if (x == 0) {
                    display.setText("ОШИБКА");
                } else {
                    result /= x;
                    display.setText("" + result);
                }
            } else if (lastCommand.equals("√")) {
                result = x;
                display.setText("" + result);
            } else if (lastCommand.equals("x²")) {
                result = x;
                display.setText("" + result);
            } else if (lastCommand.equals("=")) {
                result = x;
                display.setText("" + result);
            }
        }

    }

}

