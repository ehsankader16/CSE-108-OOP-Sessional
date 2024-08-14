import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scn=new Scanner(System.in);
    private static List<Car> carList = new ArrayList();
    public static void main(String[] args) {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("cars.txt"));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String[] out = line.split(",");
                carList.add(new Car(out[0],Integer.parseInt(out[1]),out[2],out[3],out[4],out[5],out[6],Integer.parseInt(out[7])));
            }
            br.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

        while(true){
            System.out.println("(1) Search Cars");
            System.out.println("(2) Add Car");
            System.out.println("(3) Delete Car");
            System.out.println("(4) Exit System");
            String str = scn.nextLine();
            if(str.length()!=1 || str.charAt(0)<'1' || str.charAt(0)>'4'){
                System.out.println("ERROR! insert a number between 1 to 4");
                continue;
            }
            int choice = Integer.parseInt(str);
            if(choice==1){
                searchCar();
            } else if(choice==2){
                addCar();
            } else if(choice==3){
                deleteCar();
            } else {
                break;
            }

        }

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


    public static void searchCar() {

        while (true) {
            System.out.println("Search:");
            System.out.println("(1) By Registration Number");
            System.out.println("(2) By Car Make and Car Model");
            System.out.println("(3) Back to Main Menu");
            String str = scn.nextLine();
            if(str.length()!=1 || str.charAt(0)<'1' || str.charAt(0)>'3') {
                System.out.println("ERROR! insert a number between 1 to 3");
                continue;
            }
            int choice = Integer.parseInt(str);
            if (choice == 1) {
                boolean flag = false;
                System.out.println("enter registration number : ");
                String regNo=scn.nextLine();
                for (int i=0;i<carList.size();i++) {
                    if(carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNo)){
                        System.out.println(carList.get(i));
                        flag = true;
                        break;
                    }
                }
                if(flag == false)
                    System.out.println("CAR NOT FOUND");
            } else if (choice == 2) {
                boolean flag = false;
                System.out.println("enter Car Maker : ");
                String carMaker=scn.nextLine();
                System.out.println("enter Car Model : ");
                String carModel=scn.nextLine();
                for (int i=0;i<carList.size();i++) {
                    if(carList.get(i).getCarMaker().equalsIgnoreCase(carMaker)){
                        if(carList.get(i).getCarModel().equalsIgnoreCase(carModel) || carModel.equalsIgnoreCase("ANY")) {
                            System.out.println(carList.get(i));
                            flag = true;
                        }
                    }
                }
                if(flag == false)
                    System.out.println("CAR NOT FOUND");
            } else {
                break;
            }
        }
    }

    private static void addCar() {
        System.out.println("Add:");
        System.out.println("enter registration number : ");
        String regNo=scn.nextLine();
        for (int i=0;i<carList.size();i++) {
            if(carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNo)){
                System.out.println("A car with registration number "+regNo+" is already on the database ");
                return;
            }
        }
        System.out.println("enter production year : ");
        int yearMade=scn.nextInt();
        scn.nextLine();
        System.out.println("enter colour 1 :");
        String col1=scn.nextLine();
        System.out.println("enter colour 2 (if there is none, type '-1') : ");
        String col2=scn.nextLine();
        if(col2.equals("-1"))
            col2="";
        System.out.println("enter colour 3 (if there is none, type '-1') : ");
        String col3=scn.nextLine();
        if(col3.equals("-1"))
            col3="";
        System.out.println("enter Car Maker : ");
        String carMaker=scn.nextLine();
        System.out.println("enter Car Model : ");
        String carModel=scn.nextLine();
        System.out.println("enter price : ");
        int price=scn.nextInt();
        scn.nextLine();
        carList.add(new Car(regNo,yearMade,col1,col2,col3,carMaker,carModel,price));
        System.out.println("Car with registration number "+regNo+" has been successfully added to the database ");
    }

    private static void deleteCar() {
        System.out.println("Delete:");
        System.out.println("enter registration number : ");
        String regNo=scn.nextLine();
        for (int i=0;i<carList.size();i++) {
            if (carList.get(i).getRegistrationNumber().equalsIgnoreCase(regNo)) {
                carList.remove(i);
                System.out.println("The car has been deleted from the database ");
                return;
            }
        }
        System.out.println("CAR NOT FOUND");
    }

}
