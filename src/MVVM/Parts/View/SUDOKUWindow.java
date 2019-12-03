package MVVM.Parts.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Bootstrap.Tools.DateTools.addSeconds;

public class SUDOKUWindow extends JFrame {
    SUDOKUPenal sudokuPenal = new SUDOKUPenal();
    private JComboBox difficultyComBox;
    private JPanel mainPanel;
    private JLabel difficultyLabel;
    private JLabel timeLable;
    private Date passTimeDate;
    private SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private int round = 0;

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
                JComboBox cb = (JComboBox) e.getSource();
                ComboItem petName = (ComboItem) cb.getSelectedItem();
                sudokuPenal.generate(petName.getValue());
                passTimeDate = new Date(0, 0, 0, 0, 0);
                TimeSetter timeSetter = new TimeSetter();
                Thread t = new Thread(timeSetter);
                t.start();
                round++;
            }
        });
        /*Default difficulty*/
        passTimeDate = new Date(0, 0, 0, 0, 0);
        sudokuPenal.generate(1);

    }

    private class ComboItem {
        private String key;
        private int value;

        @Override
        public String toString() {
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

    class TimeSetter implements Runnable {
        @Override
        public void run() {
            try {
                int thisRound = round;
                while (!sudokuPenal.isDone() && thisRound == round) {
                    Thread.sleep(1000);
                    passTimeDate = addSeconds(passTimeDate, 1);
                    String formattedDate = formatter.format(passTimeDate);
                    timeLable.setText(formattedDate);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
