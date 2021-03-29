package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


public class Main extends Application {
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        ClientController.getInstance().setMain(this);
        stage = primaryStage;
        showHomePage();
    }
    @Override
    public void stop() throws Exception {
        ClientController.getInstance().sendRequest("exit:","exit");
        ClientController.getInstance().closeSocket();
    }
    public void showHomePage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("HomePage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        HomepageController controller = loader.getController();
        controller.setMain(this);


        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 600, 510));
        stage.show();
    }

    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("login.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 510));
        stage.show();
    }


    public void showViewerList(String str) throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewerList.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ViewerListController controller = loader.getController();
        controller.load(str);
        controller.setMain(this);

        // Set the primary stage
        if(str.equals("viewer")) {
            stage.setTitle("Customer View");
        } else {
            stage.setTitle("Manufacturer View");
        }
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }



//    public void showManufacturerList() throws Exception {
//        // XML Loading using FXMLLoader
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("manufacturerList.fxml"));
//        Parent root = loader.load();
//
//        // Loading the controller
//        ManufacturerListController controller = loader.getController();
//        controller.load();
//        controller.setMain(this);
//
//        // Set the primary stage
//        stage.setTitle("view");
//        stage.setScene(new Scene(root, 600, 480));
//        stage.show();
//    }
    public void showAddCarView() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addCarView.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddCarController controller = loader.getController();
        controller.load();
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Add Car");
        stage.setScene(new Scene(root, 600, 510));
        stage.show();
    }

    public void showAddCarView(Car car) throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addCarView.fxml"));
        Parent root = loader.load();

        // Loading the controller
        AddCarController controller = loader.getController();
        controller.load(car);
        controller.setMain(this);

        // Set the primary stage
        stage.setTitle("Edit Car");
        stage.setScene(new Scene(root, 600, 510));
        stage.show();
    }

    public void showLoginAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }

    public void showAddAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR ADDING CAR");
        alert.setHeaderText("ERROR ADDING CAR");
        alert.setContentText("This car is already on the database");
        alert.showAndWait();
    }

    public void showDeleteAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("CAR NOT FOUND");
        alert.setHeaderText("CAR NOT FOUND");
        alert.setContentText("The selected car is not in the database");
        alert.showAndWait();
    }

    public void showIntegerAlert(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText("Invalid Input");
        alert.setContentText(s);
        alert.setHeight(200);
        alert.showAndWait();
    }
    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);
    }

}
