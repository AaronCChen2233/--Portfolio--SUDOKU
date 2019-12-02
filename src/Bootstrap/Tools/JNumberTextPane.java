package Bootstrap.Tools;

import javax.print.attribute.AttributeSet;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.KeyEvent;

public class JNumberTextPane extends JTextPane {
    @Override
    public void processKeyEvent(KeyEvent ev) {
        if (Character.isDigit(ev.getKeyChar()) || ev.getKeyChar() == 8) {
            super.processKeyEvent(ev);
        }
        ev.consume();
        return;
    }
}
