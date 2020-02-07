import com.intellij.ui.JBColor;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

public class CustomButtonUI extends BasicButtonUI {
    @Override
    protected void paintText(Graphics g, AbstractButton b, Rectangle r, String t) {
        super.paintText(g, b, r, t);
        g.setColor(JBColor.RED);
        g.drawRect(r.x, r.y, r.width, r.height);
    }
}
