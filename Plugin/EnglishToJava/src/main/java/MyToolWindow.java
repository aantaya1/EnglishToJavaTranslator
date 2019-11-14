import com.intellij.openapi.wm.ToolWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MyToolWindow {
    private JButton hideToolWindowButton;
    private JPanel myToolWindowContent;
    private JButton recordButton;

    public MyToolWindow(ToolWindow toolWindow) throws IOException {
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}