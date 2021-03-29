package sample;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientController extends Thread{
    private static ClientController instance;
    private Main main;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    private ClientController() {
        try {
            socket = new Socket("localhost", 3600);
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.start();
    }

    public static ClientController getInstance(){
        if( instance == null ){
            instance = new ClientController();
        }
        return instance;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String response = reader.readLine();
                System.out.println(response);
                responseActions(response);
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    public void sendRequest(String requestType, String info) {
        if(requestType.equals("exit:")) {
            writer.println(requestType+info);
        } else if(requestType.equals("LIN:")) {
            writer.println(requestType+info);
           // System.out.println(info);
        } else if(requestType.equals("ADD:")) {
            writer.println(requestType + info);
        } else if(requestType.equals("DEL:")) {
            writer.println(requestType + info);
        }
        else {
            String[] editData = info.split("\n");
            writer.println("ADD:" + editData[0] );
            writer.println("DEL:" + editData[1]);
        }
    }

    public void dataRequest() {
        writer.println("DATA:");
    }

    public void responseActions(String str) {
        String[] response = str.split(":");
        if(response[0].equals("LIN")) {
            loginResponse(response[1]);
        } else if (response[0].equals("DATA")) {
            carListResponse(response[1]);
        } else if (response[0].equals("ADD")) {
            addCarResponse(response[1]);
        } else if(response[0].equals("DEL")) {
            deleteCarResponse(response[1]);
        }
    }

    public void loginResponse(String loginCheck) {
        if(loginCheck.equals("TRUE")){
            dataRequest();
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showViewerList("manufacturer");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showLoginAlert();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void carListResponse(String data) {
        DataStorage.getInstance().reset(data);
    }

    public void addCarResponse(String addData) {
        String[] str = addData.split("&");
        if(str[0].equals("TRUE")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        String[] car = str[1].split("/");
                        DataStorage.getInstance().addCar(new Car(car[0], car[1], car[2], car[3], car[4], car[5]));
//                        main.showViewerList("manufacturer");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showAddAlert();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void deleteCarResponse(String deleteData) {
        String[] str = deleteData.split("&");
        if(str[0].equals("TRUE")){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        DataStorage.getInstance().deleteCar(str[1]);
//                        if(action.equals("BUY")){
//                            main.showViewerList("viewer");
//                        }
//                        else {
//                            main.showViewerList("manufacturer");
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        } else{
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.showDeleteAlert();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void setMain(Main main){
        this.main=main;
    }

    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
