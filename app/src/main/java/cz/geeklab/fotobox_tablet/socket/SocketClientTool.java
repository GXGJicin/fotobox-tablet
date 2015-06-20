package cz.geeklab.fotobox_tablet.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Jaroslav on 21. 6. 2015.
 */
public class SocketClientTool {


    public static String getStatusButton (String addr, int port) {
        Socket socket = null;
        String response = "";
        try {
            socket = new Socket(addr, port);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.writeUTF("stav");

            InputStream inputStream = socket.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            response = bufferedReader.readLine();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "UnknownHostException: " + e.toString();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            response = "IOException: " + e.toString();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

}
