import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.TestStatusListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.components.JBRadioButton;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static com.intellij.ui.JBColor.BLACK;
import static com.intellij.ui.JBColor.WHITE;

public class TestRunEventListener extends TestStatusListener {

    @Override
    public void testSuiteFinished(@Nullable AbstractTestProxy root) {
    }

    public void testSuiteFinished(@Nullable AbstractTestProxy root, Project project) {
        testSuiteFinished(root);
        JComponent jComponent = getJpanel(project);
        Component[] components = jComponent.getComponents();

        for (Component component : components) {
            if (component instanceof JBRadioButton) {
                JBRadioButton radioButton = (JBRadioButton) component;
                highLightButtonsBasedOnTestStatus(root, radioButton);
            }
        }
    }

    private void highLightButtonsBasedOnTestStatus(@Nullable AbstractTestProxy root, JBRadioButton radioButton) {
        if (hasPassedAllTest(root)) {
            highLightButtonByName(radioButton, "refactor");
        } else {
            highLightButtonByName(radioButton, "green");
        }
    }

    private void highLightButtonByName(JBRadioButton radioButton, String name) {
        String radioButtonName = radioButton.getName();
        if (name.equals(radioButtonName)) {
            radioButton.setBackground(BLACK);
        } else {
            radioButton.setBackground(WHITE);
        }
    }

    private JComponent getJpanel(Project project) {
        ToolWindow tdd = ToolWindowManager.getInstance(project).getToolWindow("TDD");
        Content content = tdd.getContentManager().getContent(0);
        return content.getComponent();
    }

    private boolean hasPassedAllTest(@Nullable AbstractTestProxy root) {
        List<? extends AbstractTestProxy> allTests = root.getAllTests();

        return allTests
                .stream()
                .allMatch(AbstractTestProxy::hasPassedTests);
    }
}
