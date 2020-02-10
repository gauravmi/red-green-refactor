import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBRadioButton;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;
import junit.awtui.TestRunner;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static com.intellij.ui.JBColor.*;
import static javax.swing.SwingConstants.CENTER;

public class Popup extends DialogWrapper {

    public Popup() {
        super(false);
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,1));

        JBRadioButton red = new JBRadioButton();
        red.setBackground(BLACK);
        red.addFocusListener(focusListener());

        paintBasicButtonUI(red, RED);

        JBRadioButton green = new JBRadioButton();
        green.addFocusListener(focusListener());
        paintBasicButtonUI(green, GREEN);

        JBRadioButton refactor = new JBRadioButton();
        refactor.addFocusListener(focusListener());
        paintBasicButtonUI(refactor, BLUE);

        ButtonGroup group = new ButtonGroup();
        group.add(red);
        group.add(green);
        group.add(refactor);

        jPanel.add(red);
        jPanel.add(green);
        jPanel.add(refactor);

        return jPanel;
    }

    @NotNull
    private FocusListener focusListener() {
        return new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                Component component = e.getComponent();
                component.setBackground(BLACK);
            }

            @Override
            public void focusLost(FocusEvent e) {
                Component component = e.getComponent();
                component.setBackground(WHITE);
            }
        };
    }

    private void paintBasicButtonUI(JBRadioButton radioButton, final JBColor color) {
        radioButton.setBackground(WHITE);
        radioButton.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                super.paint(g, c);
                g.setColor(color);
                int y = ( c.getHeight() - 200 ) / 2;
                int x = ( c.getWidth() - 200 ) / 2;
                g.fillOval(x, y, 200, 200);
            }
        });
    }
}
