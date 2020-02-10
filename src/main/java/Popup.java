import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBRadioButton;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionListener;

import static com.intellij.ui.JBColor.*;

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
        JBRadioButton green = new JBRadioButton();
        JBRadioButton refactor = new JBRadioButton();
        paintBasicButtonUI(red, RED);
        paintBasicButtonUI(green, GREEN);
        paintBasicButtonUI(refactor, BLUE);
        red.setBackground(BLACK);

        red.addActionListener(getActionListener(green, refactor));
        green.addActionListener(getActionListener(red, refactor));
        refactor.addActionListener(getActionListener(red, green));

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
    private ActionListener getActionListener(JBRadioButton button1, JBRadioButton button2) {
        return e -> {
            JBRadioButton component = (JBRadioButton) e.getSource();
            component.setBackground(BLACK);
            button1.setBackground(WHITE);
            button2.setBackground(WHITE);
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
