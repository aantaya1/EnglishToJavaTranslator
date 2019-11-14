import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class MyToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        MyToolWindow myToolWindow = null;
        try {
            myToolWindow = new MyToolWindow(toolWindow);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindow.getContent(), "", false);
        toolWindow.getContentManager().addContent(content);
    }
}
