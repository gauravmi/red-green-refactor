import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.refactoring.listeners.RefactoringEventListener;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

import static com.intellij.ui.content.ContentFactory.SERVICE.getInstance;

public class WindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {

        JComponent jComponent = new Popup().createCenterPanel();

        assert jComponent != null;
        jComponent.setAutoscrolls(true);
        project.getMessageBus().connect(new Disposable() {
            @Override
            public void dispose() {

            }
        }).subscribe(RefactoringEventListener.REFACTORING_EVENT_TOPIC);
        ContentFactory instance = getInstance();
        Content content = instance
                .createContent(jComponent, "", false);
        toolWindow.getContentManager().addContent(content);
    }

    @Override
    public void init(ToolWindow window) {

    }

    @Override
    public boolean shouldBeAvailable(@NotNull Project project) {
        return true;
    }

    @Override
    public boolean isDoNotActivateOnStart() {
        return false;
    }
}
