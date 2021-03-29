import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerController extends Thread{
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private static List<Socket> clientList = new ArrayList();
    public ServerController(Socket socket) {
        try {
            this.socket = socket;
            reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            clientList.add(this.socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(true) {
                String clientInput = reader.readLine();
                String[] in = clientInput.split(":");
//                System.out.println("Received client input: " + clientInput);
//                System.out.println(in[0]);
//                System.out.println(in[1]);
                if (in[0].equals("LIN")){
                    loginRequest(in[1]);
                } else if (in[0].equals("DATA")) {
                    String dataString= "DATA:"+Warehouse.getInstance().getCarListString()+"END";
                    writer.println(dataString);
                } else if (in[0].equals("ADD")) {
                    addRequest(in[1]);
                } else if (in[0].equals("DEL")) {
                    deleteRequest(in[1]);
                } else if(in[0].equals("exit")) {
                    socket.close();
                    clientList.remove(this.socket);
                    break;
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void loginRequest(String userInfo) {
        String[] info = userInfo.split(" ");
        System.out.println(userInfo);
        String outgoingString="";
        if(UserData.getInstance().checkCredentinals(info[0],info[1])) {
            outgoingString = "LIN:TRUE";
        } else {
            outgoingString = "LIN:FALSE";
        }
        writer.println(outgoingString);
    }

    public void addRequest(String carString) {
        String[] car = carString.split("/");
        String outgoingString="";
        if (Warehouse.getInstance().addCar(new Car(car[0], car[1], car[2], car[3], car[4], car[5]))) {
            outgoingString= "ADD:TRUE&"+carString;
            for(Socket socket:clientList) {

                try {
                    PrintWriter update = new PrintWriter(socket.getOutputStream(), true);
                    update.println(outgoingString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            outgoingString = "ADD:FALSE";
            writer.println(outgoingString);
        }
        //System.out.println(outgoingString);

    }

    public void deleteRequest(String regNo) {
        String outgoingString="";
        if (Warehouse.getInstance().deleteCar(regNo)) {
            outgoingString = "DEL:TRUE&"+regNo;
            for(Socket socket:clientList) {
                try {
                    PrintWriter update = new PrintWriter(socket.getOutputStream(), true);
                    update.println(outgoingString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            outgoingString = "DEL:FALSE";
            writer.println(outgoingString);
        }
        //System.out.println(outgoingString);


    }

}
