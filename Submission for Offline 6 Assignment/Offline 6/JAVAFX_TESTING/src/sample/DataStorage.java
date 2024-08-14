package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStorage {
    private static DataStorage instance;
    private final ObservableList<Car> carList;

    private DataStorage() {
        carList = FXCollections.observableArrayList();
    }

    public ObservableList<Car> getCarList() {
        return carList;
    }

    public static DataStorage getInstance() {
        if(instance == null) {
            instance = new DataStorage();
        }
        return instance;
    }

    public void addCar(Car car) {
        carList.add(car);
    }

    public void reset(String data) {
        carList.clear();
        String[] str = data.split("&");
        int j=0;
        while(true){
            if(str[j].equals("END")) break;
            String[] car = str[j].split("/");
            carList.add(new Car(car[0], car[1], car[2], car[3], car[4], car[5]));
            j++;
        }
        //System.out.println(carList.size());
    }
    public void deleteCar(String registrationNo) {
        for(int i=0;i<carList.size();i++){
            if(carList.get(i).getRegistrationNumber().equals(registrationNo)){
                carList.remove(i);
            }
        }
    }
}