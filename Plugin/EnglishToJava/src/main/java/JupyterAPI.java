import java.net.URI;
import java.net.URISyntaxException;

public class JupyterAPI {
    public static void main(String[] args) {
        try {
            // open websocket
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8888/api/kernels/37a6c1d5-208a-4c44-a75d-a49c5da125e7/channels?token=b01ea6f1c0a3e66131bff9bd28e5f29a9e76bc29e5580e3e"));

            // add listener
            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });
            // send message to websocket
            clientEndPoint.sendMessage("{test}");

            // wait 5 seconds for messages from websocket
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }
    }
}
