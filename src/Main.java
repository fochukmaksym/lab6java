import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Lab6 extends JFrame {

    public Lab6() {
        setTitle("Лабораторна робота №6");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1, 10, 10));

        JButton btn1 = new JButton("Завдання 1: Генератор паролів");
        JButton btn2 = new JButton("Завдання 2: Конфігуратор ПК");

        btn1.addActionListener(e -> openTask1());
        btn2.addActionListener(e -> openTask2());

        add(btn1);
        add(btn2);
    }

    // Завдання 1: Генератор простих паролів
    private void openTask1() {
        JFrame f = new JFrame("Генератор паролів");
        f.setSize(300, 180);
        f.setLayout(new FlowLayout());

        JTextField txtLen = new JTextField("8", 5);
        JButton btnGen = new JButton("Згенерувати");
        JTextField txtRes = new JTextField(15);
        txtRes.setEditable(false);

        btnGen.addActionListener(e -> {
            try {
                int len = Integer.parseInt(txtLen.getText());
                if (len < 4 || len > 64) {
                    JOptionPane.showMessageDialog(f, "Введіть від 4 до 64!");
                    return;
                }
                String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
                StringBuilder sb = new StringBuilder();
                Random rnd = new Random();
                for (int i = 0; i < len; i++) {
                    sb.append(chars.charAt(rnd.nextInt(chars.length())));
                }
                txtRes.setText(sb.toString());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Помилка числа!");
            }
        });

        f.add(new JLabel("Довжина:"));
        f.add(txtLen);
        f.add(btnGen);
        f.add(new JLabel("Результат:"));
        f.add(txtRes);
        f.setVisible(true);
    }

    // Завдання 2: Конфігуратор ПК
    private void openTask2() {
        JFrame f = new JFrame("Конфігуратор ПК");
        f.setSize(350, 320);
        f.setLayout(new FlowLayout());

        String[] cpus = {"Intel i5 (8000 грн)", "AMD Ryzen 5 (7500 грн)"};
        JComboBox<String> cmbCpu = new JComboBox<>(cpus);

        JRadioButton rb16 = new JRadioButton("16 ГБ (2000 грн)", true);
        JRadioButton rb32 = new JRadioButton("32 ГБ (3800 грн)");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rb16);
        bg.add(rb32);

        JCheckBox chkMonitor = new JCheckBox("Монітор (+5500 грн)");
        JButton btnCalc = new JButton("Розрахувати");

        btnCalc.addActionListener(e -> {
            int total = cmbCpu.getSelectedIndex() == 0 ? 8000 : 7500;
            total += rb16.isSelected() ? 2000 : 3800;
            if (chkMonitor.isSelected()) total += 5500;

            JOptionPane.showMessageDialog(f, "Загальна вартість: " + total + " грн");
        });

        f.add(new JLabel("Процесор:"));
        f.add(cmbCpu);
        f.add(new JLabel("Пам'ять:"));
        f.add(rb16);
        f.add(rb32);
        f.add(chkMonitor);
        f.add(btnCalc);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Lab6().setVisible(true));
    }
}