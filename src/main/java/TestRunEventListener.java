import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.TestStatusListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.content.Content;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TestRunEventListener extends TestStatusListener {

    @Override
    public void testSuiteFinished(@Nullable AbstractTestProxy root) {
    }

    @Override
    public void testSuiteFinished(@Nullable AbstractTestProxy root, Project project) {
        testSuiteFinished(root);
        JComponent jComponent = getJpanel(project);
        Component[] components = jComponent.getComponents();

        for (Component component : components) {
            if (component instanceof JButton) {
                JButton radioButton = (JButton) component;
                highLightButtonsBasedOnTestStatus(root, radioButton);
            }
        }
    }

    private void highLightButtonsBasedOnTestStatus(@Nullable AbstractTestProxy root, JButton radioButton) {
        if (hasPassedAllTest(root)) {
            highLightButtonByName(radioButton, "Refactor");
        } else {
            highLightButtonByName(radioButton, "Green");
        }
    }

    private void highLightButtonByName(JButton radioButton, String name) {
        String radioButtonName = radioButton.getName();
        if (name.equals(radioButtonName)) {
            radioButton.doClick();
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
