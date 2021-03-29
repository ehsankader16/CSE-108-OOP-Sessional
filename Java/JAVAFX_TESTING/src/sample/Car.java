package sample;

public class Car {
    private final String registrationNumber;
    private final String carMake;
    private final String carModel;
    private final String colors;
    private final String yearMade;
    private final String price;


    public Car(String registrationNumber, String carMake, String carModel, String colors, String yearMade , String price) {
        this.registrationNumber = registrationNumber;
        this.carMake = carMake;
        this.carModel = carModel;
        this.colors = colors;
        this.yearMade = yearMade;
        this.price = price;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getCarMake() {
        return carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getColors() {
        return colors;
    }

    public String getYearMade() {
        return yearMade;
    }

    public String  getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return registrationNumber+"/"+ carMake +"/"+carModel+"/"+colors+"/"+yearMade+"/"+price;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

}
