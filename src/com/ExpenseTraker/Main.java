package com.ExpenseTraker;

import javax.swing.*;
import  java.awt.*;

public class Main {

    static JTextField text;
    static JTextField num;
    static JTextField leftDataField;
    static JTextField rightDataField;
    static DefaultListModel<String> model;
    static DefaultListModel<String> model2;
    static DefaultListModel<String> model3;
    static DefaultListModel<String> allAmount;
    static DefaultListModel<String> allNames;
    static DefaultListModel<String> allCategories;
    static DefaultListModel<String> allDates;
    static JList<String> list;
    static JList<String> list2;
    static JList<String> list3;
    static JComboBox<String> box;
    static JComboBox<String> box2;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Expense Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridLayout(1,2));

        model = new DefaultListModel<>();
        model2 = new DefaultListModel<>();
        model3 = new DefaultListModel<>();

        allAmount = new DefaultListModel<>();
        allCategories = new DefaultListModel<>();
        allNames = new DefaultListModel<>();
        allDates = new DefaultListModel<>();

        list = new JList<>(model);
        list2 = new JList<>(model2);
        list3 = new JList<>(model3);

        list.setBackground(Color.BLACK);
        list.setForeground(Color.WHITE);
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));

        list2.setBackground(Color.BLACK);
        list2.setForeground(Color.WHITE);
        list2.setLayout(new BoxLayout(list2, BoxLayout.Y_AXIS));

        list3.setBackground(Color.BLACK);
        list3.setForeground(Color.WHITE);
        list3.setLayout(new BoxLayout(list3, BoxLayout.Y_AXIS));

        JPanel left = createPanel();
        left.setLayout(new GridLayout(4,1));
        frame.add(left);

        String[] them = {"Food", "Transport", "Home", "Fun", "Other"};
        box = new JComboBox<>(them);
        left.add(box);

        JPanel datePanel = createPanel();
        datePanel.setLayout(new BorderLayout());
        left.add(datePanel);

        leftDataField = createField();
        datePanel.add(leftDataField, BorderLayout.CENTER);

        JTextArea dataArea = createTextArea("\nDate: ");
        datePanel.add(dataArea, BorderLayout.WEST);

        JPanel textPanel = createPanel();
        textPanel.setLayout(new BorderLayout());
        left.add(textPanel);

        text = createField();
        textPanel.add(text, BorderLayout.CENTER);

        JTextArea textArea = createTextArea("\nSubject: ");
        textPanel.add(textArea, BorderLayout.WEST);

        JPanel botPanel = createPanel();
        botPanel.setLayout(new BorderLayout());
        left.add(botPanel);

        JTextArea numArea = createTextArea("\nAmount: ");
        botPanel.add(numArea, BorderLayout.WEST);

        num = createField();
        botPanel.add(num, BorderLayout.CENTER);

        JButton addBtn = createBtn("ADD");
        addBtn.addActionListener(e -> click("ADD"));
        botPanel.add(addBtn, BorderLayout.EAST);

        JPanel right = createPanel();
        right.setLayout(new BorderLayout());
        frame.add(right);

        JPanel topPanel = createPanel();
        topPanel.setLayout(new BorderLayout());
        right.add(topPanel, BorderLayout.NORTH);

        String[] them2 = {"All", "Food", "Transport", "Home", "Fun", "Other"};
        box2 = new JComboBox<>(them2);
        topPanel.add(box2, BorderLayout.CENTER);

        JButton SBtn = createBtn("Show");
        SBtn.addActionListener(e -> click("Show"));
        topPanel.add(SBtn, BorderLayout.EAST);

        rightDataField = createField();
        topPanel.add(rightDataField, BorderLayout.NORTH);

        JPanel titlePanel = createPanel();
        titlePanel.setLayout(new BorderLayout());

        JPanel listPanel = createPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.add(list, BorderLayout.CENTER);
        listPanel.add(list2, BorderLayout.EAST);
        listPanel.add(list3, BorderLayout.WEST);
        listPanel.add(titlePanel, BorderLayout.NORTH);
        right.add(listPanel, BorderLayout.CENTER);

        JPanel bottomPanel = createPanel();
        bottomPanel.setLayout(new GridLayout(1,2));
        right.add(bottomPanel, BorderLayout.SOUTH);

        JButton CBtn = createBtn("Clear All");
        CBtn.addActionListener(e -> click("Clear All"));
        bottomPanel.add(CBtn);

        JButton RBtn = createBtn("Remove");
        RBtn.addActionListener(e -> click("Remove"));
        bottomPanel.add(RBtn);

        frame.setVisible(true);
    }
    private static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setForeground(Color.WHITE);
        return panel;
    }
    private static JTextField createField() {
        JTextField field = new JTextField();
        field.setBackground(Color.BLACK);
        field.setForeground(Color.WHITE);
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        return field;
    }
    private static JTextArea createTextArea(String title) {
        JTextArea area = new JTextArea();
        area.setBackground(Color.BLACK);
        area.setForeground(Color.WHITE);
        area.setFont(new Font("Arial", Font.BOLD, 16));
        area.setText(title);
        return area;
    }
    private static JButton createBtn(String title) {
        JButton btn = new JButton(title);
        btn.setBackground(Color.GRAY);
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        return btn;
    }
private static void click(String value) {
        String text = Main.text.getText();
        String num = Main.num.getText();
        String category = (String) box.getSelectedItem();
        String date = Main.leftDataField.getText();
    switch (value) {
            case "ADD":
                if (!text.isEmpty() && !num.isEmpty()) {
                        try {
                            double amount = Double.parseDouble(num);

                            allNames.addElement(text);
                            allAmount.addElement(String.valueOf(amount));
                            allCategories.addElement(category);
                            allDates.addElement(date);

                            model.addElement(text);
                            model2.addElement(String.valueOf(amount));
                            model3.addElement(category + " |");

                            Main.text.setText("");
                            Main.num.setText("");

                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Enter only numbers!");
                        }
                } else {
                    JOptionPane.showMessageDialog(null, "You don't have any " + category + " expenses!");
                }
                break;
        case "Show":
            if (allNames.isEmpty()) {
                JOptionPane.showMessageDialog(null, "You don't have any expenses!");
                break;
            }
            String selectedCategory = (String) box2.getSelectedItem();
            String selectedDate = rightDataField.getText();

            model.clear();
            model2.clear();
            model3.clear();

            boolean found = false;

            for (int i = 0; i < allNames.size(); i++) {
                String savedCategory = allCategories.get(i);
                String savedDate = allDates.get(i);

                boolean categoryMatches = selectedCategory.equals("All") || savedCategory.equals(selectedCategory);
                boolean dateMatches = selectedDate.isEmpty() || selectedDate.equals(savedDate);

                if (categoryMatches && dateMatches) {
                    model.addElement(allNames.get(i));
                    model2.addElement(allAmount.get(i));
                    model3.addElement(savedCategory);
                    found = true;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(null, "You don't have any expenses!");
            }
            break;
        case "Clear All":
            if (!allNames.isEmpty()) {
                int answer = JOptionPane.showConfirmDialog(null, "Are you sure?", "Confirm to clear", JOptionPane.YES_NO_OPTION);
                if (answer == JOptionPane.YES_OPTION) {
                    model.clear();
                    model2.clear();
                    model3.clear();

                    allNames.clear();
                    allAmount.clear();
                    allCategories.clear();
                    allDates.clear();

                    rightDataField.setText("");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Your expenses are already empty!");
            }
            break;
        case "Remove":
           int in = list.getSelectedIndex();
           allNames.remove(in);
           allDates.remove(in);
           allCategories.remove(in);
           allAmount.remove(in);
           model.remove(in);
           model2.remove(in);
           model3.remove(in);
           break;
        }
}
}