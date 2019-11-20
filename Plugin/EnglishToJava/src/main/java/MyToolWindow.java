import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.IOException;

public class MyToolWindow {
    private JButton hideToolWindowButton;
    private JPanel myToolWindowContent;
    private JButton recordButton;
    private JLabel output;
    private JLabel javaOutput;

    public MyToolWindow(ToolWindow toolWindow) throws IOException {
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
        recordButton.addActionListener(e -> processRecording());
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

    public void processRecording() {
        JavaSoundRecorder jsr = new JavaSoundRecorder();
        jsr.startRecoding();
        SpeechToText stt = new SpeechToText();
        String textOutput = stt.getMockText();
        output.setText(textOutput);
        getJavaCode(textOutput);
    }

    public void getJavaCode(String input) {
        String java = "//This should be the java output for: \n" + input;
        javaOutput.setText(java);
        Project project = ProjectManager.getInstance().getOpenProjects()[0];
        FileEditorManager manager = FileEditorManager.getInstance(project);
        final Editor editor = manager.getSelectedTextEditor();
        assert editor != null;
        final int cursorOffset = editor.getCaretModel().getOffset();
        final Document document = editor.getDocument();

        new WriteCommandAction(project) {
            @Override
            protected void run(@NotNull Result result) throws Throwable {
                document.insertString(cursorOffset, java);
            }
        }.execute();
        new ReformatCodeProcessor(project, false).run();
    }
}