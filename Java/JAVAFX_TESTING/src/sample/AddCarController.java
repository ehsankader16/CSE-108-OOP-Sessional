package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddCarController {
    private Main main;
    private Car car;
    @FXML
    private TextField regNoText;
    @FXML
    private TextField makeText;
    @FXML
    private TextField modelText;
    @FXML
    private TextField colorsText;
    @FXML
    private TextField yearText;
    @FXML
    private TextField priceText;

    @FXML
    private Button addButton;

    @FXML
    private Button saveButton;

    void load() {
        saveButton.setVisible(false);
        addButton.setVisible(true);
    }

    void load(Car car) {
        this.car = car;
        addButton.setVisible(false);
        saveButton.setVisible(true);
        regNoText.setText(car.getRegistrationNumber());
        makeText.setText(car.getCarMake());
        modelText.setText(car.getCarModel());
        colorsText.setText(car.getColors());
        yearText.setText(car.getYearMade());
        priceText.setText(car.getPrice());
    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            main.showViewerList("manufacturer");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void addAction(ActionEvent event) {
        boolean flag = true;
        try{
            int temp = Integer.parseInt(yearText.getText());
            if(temp<1950 || temp>2020) {
                main.showIntegerAlert("Input for 'Production Year' field  must be a number and in between 1950 and 2020");
                flag = false;
            }
        } catch (Exception e) {
            main.showIntegerAlert("Input for 'Production Year' field  must be a number and in between 1950 and 2020");
            flag = false;
        }

        try{
            int temp = Integer.parseInt(priceText.getText());
        } catch (Exception e) {
            main.showIntegerAlert("Input for 'Price' field must be a number");
            flag = false;
        }
        if(flag){
            String carString = regNoText.getText()+"/"+makeText.getText()+"/"+modelText.getText()+"/"+colorsText.getText()+"/"+
                    yearText.getText()+"/"+priceText.getText();
            ClientController.getInstance().sendRequest("ADD:",carString);
            regNoText.clear();
            makeText.clear();
            modelText.clear();
            colorsText.clear();
            yearText.clear();
            priceText.clear();

        }

    }

    public void saveAction(ActionEvent actionEvent) {
        boolean flag = true;
        try{
            int temp = Integer.parseInt(yearText.getText());
            if(temp<1950 || temp>2020) {
                main.showIntegerAlert("Input for 'Production Year' field must be a number between 1950 and 2020");
                flag = false;
            }
        } catch (Exception e) {
            main.showIntegerAlert("Input for 'Production Year' field must be a number between 1950 and 2020");
            flag = false;
        }

        try{
            int temp = Integer.parseInt(priceText.getText());
        } catch (Exception e) {
            flag = false;
            main.showIntegerAlert("Input for 'Price' field must be a number");
        }
        if(flag){
            ClientController.getInstance().sendRequest("DEL:",car.getRegistrationNumber());
            String carString = regNoText.getText()+"/"+makeText.getText()+"/"+modelText.getText()+"/"+colorsText.getText()+"/"+
                    yearText.getText()+"/"+priceText.getText();
            ClientController.getInstance().sendRequest("ADD:",carString);

        }

    }
    void setMain(Main main) {
        this.main = main;
    }
}
