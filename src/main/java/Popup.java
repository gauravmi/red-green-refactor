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

        JBRadioButton red = createJRadioButton("red");
        JBRadioButton green = createJRadioButton("green");
        JBRadioButton refactor = createJRadioButton("refactor");

        paintButtonsAsCircles(red, green, refactor);
        addActionListenersToAllButtons(red, green, refactor);

        return constructJPanel(red, green, refactor);
    }

    @NotNull
    private JPanel constructJPanel(JBRadioButton red, JBRadioButton green, JBRadioButton refactor) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3,1));
        jPanel.add(red);
        jPanel.add(green);
        jPanel.add(refactor);
        return jPanel;
    }

    @NotNull
    private JBRadioButton createJRadioButton(String name) {
        JBRadioButton button = new JBRadioButton();
        button.setName(name);
        return button;
    }

    private void paintButtonsAsCircles(JBRadioButton red, JBRadioButton green, JBRadioButton refactor) {
        paintBasicButtonUI(red, RED);
        paintBasicButtonUI(green, GREEN);
        paintBasicButtonUI(refactor, BLUE);
        red.setBackground(BLACK);
    }

    private void addActionListenersToAllButtons(JBRadioButton red, JBRadioButton green, JBRadioButton refactor) {
        red.addActionListener(getActionListener(green, refactor));
        green.addActionListener(getActionListener(red, refactor));
        refactor.addActionListener(getActionListener(red, green));
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
                int y = calculateCenter(c.getHeight());
                int x = calculateCenter(c.getWidth());
                g.fillOval(x, y, 200, 200);
            }
        });
    }

    private int calculateCenter(int height) {
        return (height - 200) / 2;
    }
}
