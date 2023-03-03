import Models.User;
import Models.UserData;

import Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Main {
    private static ServerSocket server;
    private static int port = 9876;
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        server = new ServerSocket(port);
        Socket socket;
        UserService userService = new UserService();

        while( true ){
            System.out.println("\u001B[32m| Waiting for the user request");
            socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream oos = null;
            JSONObject message;
            try{
                message = new JSONObject(ois.readObject().toString());
                System.out.println("\u001B[32m| Message Received: \u001B[34m" + message);
                ObjectMapper objectMapper = new ObjectMapper();
                UserData userData = objectMapper.readValue(message.toString(), UserData.class);
                System.out.println("\u001B[32m| \u001B[37mUser: \u001B[34m" + userData.getUser().getInfo() + "\n");
                if( userData.isRegistrationStatus() ){
                    List<User> users = userService.getAllUsers();
                    boolean existing = false;
                    for ( User us : users ) {
                        if(us.getLogin().equals(userData.getUser().getLogin())){
                            System.out.println("\u001B[32m| \u001B[31mUser Login Already Exists!\n");
                            oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject("\u001B[32m| \u001B[31mUser Login Already Exists!\n");
                            existing = true;
                            break;
                        }
                    }
                    if( !existing ){
                        userService.saveUser(userData.getUser());
                        System.out.println("\u001B[32m| Registration successful!\n");
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject("\u001B[32m| Registration successful!\n");
                    }
                }
                else{
                    List<User> users = userService.getAllUsers();
                    boolean authorization = false;
                    for ( User iter : users ) {
                        if( iter.getLogin().equals(userData.getUser().getLogin()) && iter.getPassword().equals(userData.getUser().getPassword()) ){
                            System.out.println("\u001B[32m| Authorization successful!\n");
                            oos = new ObjectOutputStream(socket.getOutputStream());
                            oos.writeObject("\u001B[32m| Authorization successful!\n");
                            authorization = true;
                            break;
                        }
                    }
                    if( !authorization ){
                        System.out.println("\u001B[32m| \u001B[31mWrong Data!\n");
                        oos = new ObjectOutputStream(socket.getOutputStream());
                        oos.writeObject("\u001B[32m| \u001B[31mWrong Data!\n");
                    }
                }
                ois.close();
                oos.close();
                socket.close();

            }
            catch (Exception ex){
                System.out.println("\u001B[32m| \u001B[31m" + ex.getMessage());
                break;
            }
        }
        System.out.println("\u001B[32m| \u001B[31mShutting down Socket server!!\n");
        server.close();
        }
    }
