import com.intellij.ui.JBColor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

import static java.awt.Color.*;

public class RoundButton extends JButton {

    JBColor buttonColor;

    public RoundButton(String label, JBColor color) {
        super(label);
        buttonColor = color;
        setBackground(color);
        setFocusable(false);
        setForeground(WHITE);
        setFont(new java.awt.Font("Arial", Font.PLAIN, 30));
        setName(label);

    /*
     These statements enlarge the button so that it
     becomes a circle rather than an oval.
    */
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);

    /*
     This call causes the JButton not to paint the background.
     This allows us to paint a round background.
    */
        setContentAreaFilled(false);
    }

    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(buttonColor);
        } else {
            g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width, getSize().height);

        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(BLACK);
//        Graphics2D g2d = (Graphics2D) g;
//        g2d.setStroke(new BasicStroke(4.0F));
//        g2d.drawOval(0, 0, getSize().width, getSize().height);
        g.drawOval(0, 0, getSize().width, getSize().height);
    }

    // Hit detection.
    Shape shape;

    public boolean contains(int x, int y) {
        // If the button has changed size,  make a new shape object.
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
    }

}