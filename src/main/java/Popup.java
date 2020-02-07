import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBRadioButton;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

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
        red.setHorizontalAlignment(CENTER);
        paintBasicButtonUI(red, RED);
        JBRadioButton green = new JBRadioButton();
        paintBasicButtonUI(green, GREEN);
        JBRadioButton refactor = new JBRadioButton();
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

    private void paintBasicButtonUI(JBRadioButton red, final JBColor color) {
        red.setUI(new BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                super.paint(g, c);
                g.setColor(color);
                g.fillOval(c.getX(), c.getY(), 200, 200);
            }
        });
    }
}
