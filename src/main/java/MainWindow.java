import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import nyx.Nyx;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nyx nyx;

    private final Image frierenImage = new Image(this.getClass().getResourceAsStream("/images/Frieren.png"));
    private final Image fernImage = new Image(this.getClass().getResourceAsStream("/images/Fern.png"));


    /**
     * Initializes the main window.
     * Binds the scroll pane's vertical value property to the height property of the dialog container.
     * Sets the font for the send button and user input field.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        userInput.setFont(Font.font("Lucida Sans", 20));
    }

    /** Injects the Nyx instance */
    public void setDuke(Nyx n) {
        nyx = n;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nyx.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, frierenImage),
                DialogBox.getDukeDialog(response, fernImage)
        );
        if (input.equals("bye")) {
            TimerTask timerTask = new TimerTask() {
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, 1000);
        }
        userInput.clear();
    }
}
