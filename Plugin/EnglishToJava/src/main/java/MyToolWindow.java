import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;

public class MyToolWindow {
    private JButton hideToolWindowButton;
    private JPanel myToolWindowContent;

    public MyToolWindow(ToolWindow toolWindow) {
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}