package cz.geeklab.fotobox_tablet.socket;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.os.AsyncTask;
import android.widget.TextView;


/**
 * Created by Jaroslav on 20. 6. 2015.
 */
public class SocketClientTask extends AsyncTask<Void, Void, Void> {

        TextView dsTextResponse;
        String dstAddress;
        int dstPort;
        String response = "";

    public SocketClientTask(TextView textResponse, String addr, int port){
            dsTextResponse = textResponse;
            dstAddress = addr;
            dstPort = port;

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            Socket socket = null;

            try {
                socket = new Socket(dstAddress, dstPort);
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
            }finally{
                if(socket != null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            if (dsTextResponse != null) {
                dsTextResponse.setText(response);
            }
            super.onPostExecute(result);
        }

    }

