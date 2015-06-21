package cz.geeklab.fotobox_tablet.Receiver;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import cz.geeklab.fotobox_tablet.R;
import cz.geeklab.fotobox_tablet.socket.SocketClientTool;
import cz.geeklab.fotobox_tablet.socket.SocketPictureTaks;

/**
 * Created by Jaroslav on 20. 6. 2015.
 */
public class ResultReceiverActivity extends Activity{



        //	http://stackoverflow.com/questions/3197335/restful-api-service/3197456#3197456
        Intent intent;
        TextView txtview;
        MyResultReceiver resultReceiver;
        ImageView picture;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result_reciever);

            resultReceiver = new MyResultReceiver(null);

            txtview = (TextView) findViewById(R.id.resultRecieverStatus);

            picture = (ImageView) findViewById(R.id.imageViewResultPicture);

            final Button button = (Button) findViewById(R.id.buttonResultPicture);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                   setPicture (v);
                }
            });

            intent = new Intent(this, StatusReceiverService.class);
            intent.putExtra("receiver", resultReceiver);
            startService(intent);


        }

    private void setPicture(View v) {
        SocketPictureTaks pictureTask = new SocketPictureTaks(picture, "192.168.15.108", 4322);
        pictureTask.execute();
    }

    @Override
        protected void onDestroy() {
            super.onDestroy();
            stopService(intent);
        }

        class UpdateUI implements Runnable
        {
            String updateString;

            public UpdateUI(String updateString) {
                this.updateString = updateString;
            }
            public void run() {
                txtview.setText(updateString);
            }
        }

        class MyResultReceiver extends ResultReceiver
        {
            public MyResultReceiver(Handler handler) {
                super(handler);
            }

            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {

                if(resultCode == 100){
                    runOnUiThread(new UpdateUI(resultData.getString("start")));
                }
                else if(resultCode == 200){
                    runOnUiThread(new UpdateUI(resultData.getString("end")));
                }
                else if (resultCode == 1050) {
                    runOnUiThread(new UpdateUI("Button " + resultData.getString("status")));
                }
                else{
                    runOnUiThread(new UpdateUI("Result Received "+resultCode));
                }
            }
        }
    }
