package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class HomepageController {
    private Main main;

    @FXML
    private ImageView image;

    @FXML
    private Button viewerButton;

    @FXML
    private Button manufacturerButton;


    @FXML
    void viewerButtonAction(ActionEvent event) {
        try {
            ClientController.getInstance().dataRequest();
            main.showViewerList("viewer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void manufacturerButtonAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void setMain(Main main) {
        this.main = main;
    }

}
