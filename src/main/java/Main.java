import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import nyx.Nyx;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Nyx nyx = new Nyx();

    @Override
    public void start(Stage stage) {
        try {
            Font.loadFont(getClass().getResourceAsStream("/fonts/SFMonoRegular.otf"), 12);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();

            stage.setTitle("Nyx");
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(420);
            fxmlLoader.<MainWindow>getController().setDuke(nyx); // inject the Nyx instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
