import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static UserData instance;
    private List<String>users;
    private List<String>passwords;

    private UserData() {
        try {
            String info;
            BufferedReader br = new BufferedReader(new FileReader("userinfo.txt"));
            users = new ArrayList<>();
            passwords = new ArrayList<>();
            while (true) {
                info = br.readLine();
                if (info == null) break;
                String [] user= info.split(" ");
                users.add(user[0]);
                passwords.add(user[1]);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static UserData getInstance(){
        if( instance == null ){
            instance = new UserData();
        }
        return instance;
    }

    public boolean checkCredentinals(String user,String password){
        for(int i=0;i<users.size();i++) {
            if(users.get(i).equals(user) && passwords.get(i).equals(password))
                return true;
        }
        return false;
    }
}
