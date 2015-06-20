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

import cz.geeklab.fotobox_tablet.apps.StatusButton;


/**
 * Created by Jaroslav on 20. 6. 2015.
 */
public class SocketClientTask extends AsyncTask<Void, Void, Void> {

    TextView dsTextResponse;
    StatusButton dsButton;
    String dstAddress;
    int dstPort;
    String response = "";

    public SocketClientTask(TextView textResponse, String addr, int port) {
        dsTextResponse = textResponse;
        dsButton = null;
        dstAddress = addr;
        dstPort = port;
    }


    public SocketClientTask(StatusButton button, String addr, int port) {
        dsTextResponse = null;
        dsButton = button;
        dstAddress = addr;
        dstPort = port;

    }

    @Override
    protected Void doInBackground(Void... arg0) {

        this.response = SocketClientTool.getStatusButton(dstAddress, dstPort);
        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        if (dsTextResponse != null) {
            dsTextResponse.setText(response);
        }
        if (dsButton != null) {
            if ("ZAPNUTO".startsWith(response)) {
                dsButton.setState(StatusButton.TouchButtonState.BUTTON_DOWN);
            } else {
                dsButton.setState(StatusButton.TouchButtonState.BUTTON_UP);
            }
        }

        super.onPostExecute(result);
    }

}

