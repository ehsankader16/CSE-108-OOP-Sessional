
public class Car {
    private String registrationNumber;
    private int yearMade;
    private String colour1;
    private String colour2;
    private String colour3;
    private String carMaker;
    private String carModel;
    private int price;

    public Car(String registrationNumber, int yearMade, String colour1, String colour2, String colour3, String carMaker, String carModel, int price) {
        this.registrationNumber = registrationNumber;
        this.yearMade = yearMade;
        this.colour1 = colour1;
        this.colour2 = colour2;
        this.colour3 = colour3;
        this.carMaker = carMaker;
        this.carModel = carModel;
        this.price = price;
    }


    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public int getYearMade() {
        return yearMade;
    }

    public String getColour1() {
        return colour1;
    }

    public String getColour2() {
        return colour2;
    }

    public String getColour3() {
        return colour3;
    }

    public String getCarMaker() {
        return carMaker;
    }

    public String getCarModel() {
        return carModel;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return registrationNumber+','+yearMade+','+colour1+','+colour2+','+
                colour3+','+carMaker+','+carModel+','+price;
    }


}
