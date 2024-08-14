package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewerListController {


    private Main main;

    @FXML
    private TextField regSearch;
    @FXML
    private TextField makeSearch;
    @FXML
    private TextField modelSearch;
    @FXML
    private ComboBox searchOptions;
    @FXML
    private TableView carTableView;
    @FXML
    TableColumn<Car, String> regNoCol;
    @FXML
    TableColumn<Car, String> makeCol;
    @FXML
    TableColumn<Car, String> modelCol ;
    @FXML
    TableColumn<Car, String> colorsCol ;
    @FXML
    TableColumn<Car, String> yearCol ;
    @FXML
    TableColumn<Car, String> priceCol;
    @FXML
    private Button search;
    @FXML
    private Button buyButton;
    @FXML
    private Button backButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;
    @FXML
    private Button viewAllButton;
    @FXML
    private Button editButton;

    ObservableList<Car> carList = FXCollections.observableArrayList();

    void load(String check) {

        viewAllButton.setVisible(false);
        searchOptions.setValue("by registration no");
        ClientController.getInstance().dataRequest();
        if(check.equals("viewer")){
            logoutButton.setVisible(false);
            deleteButton.setVisible(false);
            addButton.setVisible(false);
            editButton.setVisible(false);

        } else{
            buyButton.setVisible(false);
            backButton.setVisible(false);
        }

        carTableView.setEditable(false);

        makeSearch.setVisible(false);
        modelSearch.setVisible(false);
        regSearch.setVisible(true);

        regNoCol.setCellValueFactory(new PropertyValueFactory<Car,String>("registrationNumber"));
        makeCol.setCellValueFactory(new PropertyValueFactory<Car,String>("carMake"));
        modelCol.setCellValueFactory(new PropertyValueFactory<Car,String>("carModel"));
        colorsCol.setCellValueFactory(new PropertyValueFactory<Car,String>("colors"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Car,String>("yearMade"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Car,String>("price"));

        carList = DataStorage.getInstance().getCarList();
        carTableView.setItems(carList);

    }


    @FXML
    void searchOptionsAction(ActionEvent event){
        if(searchOptions.getValue().equals("by registration no")){
            makeSearch.setVisible(false);
            modelSearch.setVisible(false);
            regSearch.setVisible(true);
        }
        else if(searchOptions.getValue().equals("by car make and model")){
            makeSearch.setVisible(true);
            modelSearch.setVisible(true);
            regSearch.setVisible(false);
        }
    }

    @FXML
    void searchAction(){
        viewAllButton.setVisible(true);
        ObservableList <Car> data = FXCollections.observableArrayList();
        if (searchOptions.getValue().equals("by registration no")) {
            String regNo = regSearch.getText();
            System.out.println(regNo);
            for (int i = 0; i < carList.size(); i++) {
                if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNo)) {
                    data.add(carList.get(i));
                    break;
                }
            }

        } else if (searchOptions.getValue().equals("by car make and model")) {
            String carMaker = makeSearch.getText();
            String carModel = modelSearch.getText();
            for (int i = 0; i < carList.size(); i++) {
                if (carList.get(i).getCarMake().equalsIgnoreCase(carMaker)) {
                    if (carList.get(i).getCarModel().equalsIgnoreCase(carModel) || carModel.length()==0) {
                        data.add(carList.get(i));
                    }
                }
            }

        }
        regSearch.clear();
        makeSearch.clear();
        modelSearch.clear();
        carTableView.setItems(data);
    }


    @FXML
    public void deleteAction(ActionEvent actionEvent) {
        Car car = (Car) carTableView.getSelectionModel().getSelectedItem();
        ClientController.getInstance().sendRequest("DEL:",car.getRegistrationNumber());
    }
    @FXML
    public void addCarAction(ActionEvent actionEvent) {
        try {
            main.showAddCarView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void buyAction(ActionEvent actionEvent) {
        Car car = (Car) carTableView.getSelectionModel().getSelectedItem();
        ClientController.getInstance().sendRequest("BUY:",car.getRegistrationNumber());
    }

    @FXML
    void backAction(ActionEvent event) {
        try {
            main.showHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void viewAllAction(ActionEvent actionEvent) {
        if(buyButton.isVisible()){
            load("viewer");
        } else{
            load("manufacturer");
        }
    }

    @FXML
    public void editAction(ActionEvent actionEvent) {
        try {
            Car car = (Car) carTableView.getSelectionModel().getSelectedItem();
            main.showAddCarView(car);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void setMain(Main main) {
        this.main = main;
    }
}
