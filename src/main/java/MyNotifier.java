import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.project.Project;

import javax.annotation.Nullable;

public class MyNotifier {

    public static void notifyInformation(@Nullable Project project, String content) {
        NotificationGroupManager.getInstance().getNotificationGroup("demo.notifications.group")
                .createNotification(content, NotificationType.INFORMATION)
                .notify(project);
    }

}
