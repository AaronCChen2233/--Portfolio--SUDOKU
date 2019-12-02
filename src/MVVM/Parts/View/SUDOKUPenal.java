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
    public SUDOKUPenal() {
        super();
        setLayout(new GridLayout(3, 3));
    }

    public void generate(int difficulty) {
        removeAll();
        SUDOKUQuestionTable = SUDOKUGeneraterModel.generate(difficulty);
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
                                    String inputString = ev.getKeyChar()+"";
                                    JNumberTextPane thisPane = ((JNumberTextPane) ev.getSource());
                                    thisPane.setText(inputString);
                                    SUDOKUQuestionTable[ip][jp] = inputString;

                                    if(SUDOKUGeneraterModel.allowPutChecker(SUDOKUQuestionTable,ip,jp,inputString)){
                                        thisPane.setForeground(Color.gray);
                                    }else{
                                        thisPane.setForeground(Color.red);
                                    }
                                }
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
                    }
                }
                smallPanel.updateUI();
                add(smallPanel);
            }
        }
        updateUI();
    }
}
