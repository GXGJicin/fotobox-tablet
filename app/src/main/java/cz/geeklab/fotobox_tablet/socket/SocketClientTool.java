package cz.geeklab.fotobox_tablet.socket;

import android.graphics.Bitmap;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.graphics.BitmapFactory;

import cz.geeklab.fotobox_tablet.apps.StatusButton;

/**
 * Created by Jaroslav on 21. 6. 2015.
 */
public class SocketClientTool {


    public static Bitmap getPicture(String addr, int port) {
        Bitmap bitmap = null;


        Socket socket = null;
        InputStream result = null;
        try {
            socket = new Socket(addr, port);
            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.writeUTF("nahled");

            //byte[] bytes = IOUtils.toByteArray();
            InputStream is =socket.getInputStream();
            if (is != null) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();

                int nRead;
                byte[] data = new byte[16384];

                while ((nRead = is.read(data, 0, data.length)) != -1) {
                    buffer.write(data, 0, nRead);
                }

                buffer.flush();

                byte[] bytes = buffer.toByteArray();
                try {
                    bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                } finally {
                    try {
                        is.close();
                        is = null;
                    } catch (IOException e) {
                    }
                }
            }


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

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



        return bitmap;
    }


    public static String getResponseStatusButton(String addr, int port) {
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

    public static StatusButton getStatusButton(String addr, int port) {
        StatusButton result = new StatusButton();
        String response = getResponseStatusButton(addr, port);
        if (response != null && response.startsWith(StatusButton.TouchButtonState.BUTTON_DOWN.toString())) {
            result.setState(StatusButton.TouchButtonState.BUTTON_DOWN);
        }

        return result;
    }


}
