package musicinformationclient.socket;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

public class WebView extends JFXPanel {
    public WebView(String link){
        JFXPanel jfxPanel = new JFXPanel();

        Platform.runLater(() -> {
            javafx.scene.web.WebView webView = new javafx.scene.web.WebView();
            jfxPanel.setScene(new Scene(webView));
            webView.getEngine().load(link);
        });
    }

}
