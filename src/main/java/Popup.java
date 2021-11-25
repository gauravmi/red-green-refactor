import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static com.intellij.ui.JBColor.*;

public class Popup extends DialogWrapper {

    public Popup() {
        super(false);
    }

    JButton currentRunningRadioButton;
    Timer timer;
    int currentRunningTimeInSeconds = 0;
    JButton red, green, refactor;

    Map<String, Integer> colorTimeMap = new HashMap<>();

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        red = new RoundButton("Red", RED);
        green = new RoundButton("Green", GREEN);
        refactor = new RoundButton("Refactor", BLUE);

        resetColorTimeMap();

        currentRunningRadioButton = red;
        currentRunningRadioButton.setForeground(BLACK);
        startTimer();
        addActionListenersToAllButtons();

        return constructJPanel();
    }

    private void resetColorTimeMap() {
        colorTimeMap.put("Red", 0);
        colorTimeMap.put("Green", 0);
        colorTimeMap.put("Refactor", 0);
    }

    private void resetColorTimeMapAndText() {
        colorTimeMap.put("Red", 0);
        colorTimeMap.put("Green", 0);
        colorTimeMap.put("Refactor", 0);

        red.setText("Red");
        green.setText("Green");
        refactor.setText("Refactor");
    }

    @NotNull
    private JPanel constructJPanel() {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(3, 1));
        jPanel.add(red);
        jPanel.add(green);
        jPanel.add(refactor);
        return jPanel;
    }

    private void addActionListenersToAllButtons() {
        red.addActionListener(getActionListener());
        green.addActionListener(getActionListener());
        refactor.addActionListener(getActionListener());
    }

    @NotNull
    private ActionListener getActionListener() {
        return e -> {
            Project project = ProjectManager.getInstance().getDefaultProject();
            new MyNotifier().notifyInformation(project, "Complete this section and proceed to next section, if the cycle is completed restart the cycle manually by pressing ");
            colorTimeMap.put(currentRunningRadioButton.getName(), currentRunningTimeInSeconds);
            JButton component = (JButton) e.getSource();
            if (currentRunningRadioButton.getName().equals("Refactor") && component.getName().equals("Red")) {
                resetColorTimeMapAndText();
            }
            currentRunningRadioButton.setForeground(WHITE);
            currentRunningRadioButton = component;
            currentRunningRadioButton.setForeground(BLACK);
            currentRunningTimeInSeconds = colorTimeMap.get(component.getName());
        };
    }

    public static String formatSecondDateTime(int second) {
        if(second <= 0)return "";
        int h = second / 3600;
        int m = second % 3600 / 60;
        int s = second % 60;
        return h + ":" + m + ":"  + s;
    }

    private void startTimer() {
        timer = new Timer(1000, e -> {
            if (currentRunningTimeInSeconds == 3600) {
                JOptionPane.showMessageDialog(null, "Need help?");
            }
            currentRunningRadioButton.setText(formatSecondDateTime(currentRunningTimeInSeconds));
            currentRunningTimeInSeconds++;
        });
        timer.start();
    }
}
