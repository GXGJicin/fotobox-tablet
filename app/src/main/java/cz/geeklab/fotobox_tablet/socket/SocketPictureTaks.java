package cz.geeklab.fotobox_tablet.socket;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.AsyncTask;
import android.widget.TextView;

import cz.geeklab.fotobox_tablet.apps.StatusButton;

/**
 * Created by Jaroslav on 21. 6. 2015.
 */
public class SocketPictureTaks extends AsyncTask<Void, Void, Void> {

    Bitmap bitmap;
    String dstAddress;
    int dstPort;
    ImageView dsImageView;

    public SocketPictureTaks(ImageView imageView, String addr, int port) {
        dsImageView= imageView;
        dstAddress = addr;
        dstPort = port;
    }


    @Override
    protected Void doInBackground(Void... arg0) {

        this.bitmap = SocketClientTool.getPicture(dstAddress, dstPort);
        return null;

    }

    @Override
    protected void onPostExecute(Void result) {
        if (dsImageView != null) {
            dsImageView.setImageBitmap(this.bitmap);
        }

        super.onPostExecute(result);
    }

}

