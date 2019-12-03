package MVVM.Parts.View;

import Bootstrap.Tools.JNumberTextPane;
import MVVM.Parts.Model.SUDOKUGeneraterModel;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;

public class SUDOKUPenal extends JPanel {

    String[][] SUDOKUQuestionTable;
    boolean isDone;

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public SUDOKUPenal() {
        super();
        setLayout(new GridLayout(3, 3));
    }

    public void generate(int difficulty) {
        isDone = false;
        setEnabled(true);
        removeAll();
        SUDOKUQuestionTable = SUDOKUGeneraterModel.generate(difficulty);
        JNumberTextPane[][] jNumberTextPaneArray = new JNumberTextPane[9][9];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JPanel smallPanel = new JPanel(new GridLayout(3, 3));
                smallPanel.setBorder(BorderFactory.createLineBorder(Color.black));
                for (int si = 0; si < 3; si++) {
                    for (int sj = 0; sj < 3; sj++) {
                        int ip = i * 3 + si;
                        int jp = j * 3 + sj;
                        JNumberTextPane n = new JNumberTextPane() {
                            @Override
                            public void processKeyEvent(KeyEvent ev) {
                                if (Character.isDigit(ev.getKeyChar()) || ev.getKeyChar() == 8) {
                                    super.processKeyEvent(ev);
                                    String inputString = ev.getKeyChar() + "";
                                    JNumberTextPane thisPane = ((JNumberTextPane) ev.getSource());
                                    thisPane.setText(inputString);
                                    SUDOKUQuestionTable[ip][jp] = inputString;
                                    check();
                                }
                            }

                            private void check() {
                                boolean  checkIsDone = true;
                                for (int i = 0; i < 9; i++) {
                                    for (int j = 0; j < 9; j++) {
                                        /*skip disable*/
                                        JNumberTextPane jp = jNumberTextPaneArray[i][j];
                                        if (jp.isEnabled() && !jp.getText().equals("")) {
                                            if (SUDOKUGeneraterModel.allowPutChecker(SUDOKUQuestionTable, i, j, jp.getText())) {
                                                jp.setForeground(Color.gray);
                                            } else {
                                                jp.setForeground(Color.red);
                                                checkIsDone = false;
                                            }
                                        }
                                        else if(jp.getText().equals("")) {
                                            checkIsDone = false;
                                        }
                                    }
                                }
                                isDone = checkIsDone;
                            }
                        };

                        StyledDocument doc = n.getStyledDocument();
                        SimpleAttributeSet center = new SimpleAttributeSet();
                        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
                        doc.setParagraphAttributes(0, doc.getLength(), center, false);

                        n.setText(SUDOKUQuestionTable[ip][jp]);
                        n.setBorder(BorderFactory.createLineBorder(Color.black));
                        n.setFont(new Font("Microsoft JhengHei", Font.LAYOUT_NO_LIMIT_CONTEXT, 38));
                        n.setForeground(Color.gray);
                        n.setDisabledTextColor(Color.black);
                        if (!n.getText().equals("")) {
                            n.setEnabled(false);
                        }
                        smallPanel.add(n);
                        jNumberTextPaneArray[ip][jp] = n;
                    }
                }
                smallPanel.updateUI();
                add(smallPanel);
            }
        }
        updateUI();
    }
}
