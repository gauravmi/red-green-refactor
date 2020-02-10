import com.intellij.execution.testframework.AbstractTestProxy;
import com.intellij.execution.testframework.TestStatusListener;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FailureEventListener extends TestStatusListener {

    @Override
    public void testSuiteFinished(@Nullable AbstractTestProxy root) {
        List<? extends AbstractTestProxy> allTests = root.getAllTests();

        boolean testPassedOrFailed = allTests
                .stream()
                .allMatch(AbstractTestProxy::hasPassedTests);

        System.out.println("final result = " + testPassedOrFailed);
    }

    public void testSuiteFinished(@Nullable AbstractTestProxy root, Project project) {
        testSuiteFinished(root);
    }
}
