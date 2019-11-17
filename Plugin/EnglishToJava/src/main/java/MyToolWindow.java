import com.intellij.openapi.wm.ToolWindow;

import javax.swing.*;
import java.io.IOException;

public class MyToolWindow {
    private JButton hideToolWindowButton;
    private JPanel myToolWindowContent;
    private JButton recordButton;
    private JLabel output;

    public MyToolWindow(ToolWindow toolWindow) throws IOException {
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
        recordButton.addActionListener(e -> processRecording());
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

    public void processRecording(){
        JavaSoundRecorder jsr = new JavaSoundRecorder();
        jsr.startRecoding();
        SpeechToText stt = new SpeechToText();
        String textOutput = stt.getMockText();
        output.setText(textOutput);
    }
}