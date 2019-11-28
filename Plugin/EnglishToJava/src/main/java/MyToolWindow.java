import com.intellij.codeInsight.actions.ReformatCodeProcessor;
import com.intellij.openapi.application.Result;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.wm.ToolWindow;
import org.json.simple.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyToolWindow {
    private JButton hideToolWindowButton;
    private JPanel myToolWindowContent;
    private JButton recordButton;
    private JLabel javaOutput;

    public MyToolWindow(ToolWindow toolWindow) {
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
        recordButton.addActionListener(e -> {
            try {
                processRecording();
            } catch (IOException | ParseException ex) {
                ex.printStackTrace();
            }
        });
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

    public void processRecording() throws IOException, ParseException {
        JavaSoundRecorder jsr = new JavaSoundRecorder();
        jsr.startRecoding();
        SpeechToText stt = new SpeechToText();
        String textOutput = stt.getMockText();
        String java = getJavaCode(textOutput);
        insertJava(java);
    }

    public String getJavaCode(String input) throws IOException, ParseException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost("http://127.0.0.1:5000/");
        List<BasicNameValuePair> params = new ArrayList<>(2);
        params.add(new BasicNameValuePair("text", input));
        httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        HttpResponse response = httpclient.execute(httppost);
        HttpEntity entity = response.getEntity();
        String responseJSON = EntityUtils.toString(entity);
        JSONObject element = (JSONObject) new JSONParser().parse(responseJSON);
        String java = (String) element.get("java");
        String line = (String) element.get("line");
        return java;
    }

    public void insertJava(String java){
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