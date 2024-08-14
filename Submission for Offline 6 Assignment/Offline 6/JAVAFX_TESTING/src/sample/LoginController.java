package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    private Main main;
    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button login;

    @FXML
    private Button back;

    @FXML
    void loginAction(ActionEvent event) throws Exception {
        String userName = userText.getText();
        String password = passwordText.getText();
        String loginRequest = userName+" "+password;
        ClientController.getInstance().sendRequest("LIN:",loginRequest);

    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            main.showHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main) {
        this.main = main;
    }
}
