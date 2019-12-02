package MVVM.Parts.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SUDOKUWindow extends JFrame {
    SUDOKUPenal sudokuPenal = new SUDOKUPenal();
    private JComboBox difficultyComBox;
    private JPanel mainPanel;
    private JLabel difficultyLabel;

    public SUDOKUWindow() {
        super();

        setVisible(true);
//        sudokuPenal.generate(2);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        add(mainPanel);
        mainPanel.add(sudokuPenal);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 650);
        setTitle("Aaron SUDOKU");

        difficultyComBox.addItem(new ComboItem("1", 1));
        difficultyComBox.addItem(new ComboItem("2", 2));
        difficultyComBox.addItem(new ComboItem("3", 3));
        difficultyComBox.addItem(new ComboItem("4", 4));
        difficultyComBox.addItem(new ComboItem("5", 5));
        difficultyComBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox)e.getSource();
                ComboItem petName = (ComboItem)cb.getSelectedItem();
                sudokuPenal.generate(petName.getValue());
            }
        });
    }

    private class ComboItem {
        private String key;
        private int value;

        @Override
        public String toString()
        {
            return key;
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public ComboItem(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
