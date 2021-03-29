

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class Warehouse extends Thread{
    private static Warehouse instance;
    private  List<Car> carList = new ArrayList();

    private Warehouse() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("cars.txt"));
            while (true) {
                String line = br.readLine();
                if (line == null) break;
                String[] car = line.split("/");
                carList.add(new Car(car[0], car[1], car[2], car[3], car[4], car[5]));
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Warehouse getInstance(){
        if( instance == null ){
            instance = new Warehouse();
        }
        return instance;
    }

    synchronized public String getCarListString() {
        String carListString="";
        for (Car c: carList) {
            carListString = carListString+c.toString()+"&";
        }
        return carListString;
    }

    synchronized public boolean addCar(Car car) {

        for (int i=0;i<carList.size();i++) {
            if(carList.get(i).getRegistrationNumber().equalsIgnoreCase(car.getRegistrationNumber())){
                return false;
            }
        }

        carList.add(car);
        writeInFile();
        return true;
    }

    synchronized public boolean deleteCar(String regNo) {

        for (int i=0;i<carList.size();i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNo)) {
                carList.remove(i);
                writeInFile();
                return true;
            }
        }
        return false;
    }

    synchronized private void writeInFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("cars.txt"));
            for (Car c: carList) {
                bw.write(c.toString());
                bw.write("\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
