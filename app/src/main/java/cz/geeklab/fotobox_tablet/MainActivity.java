package cz.geeklab.fotobox_tablet;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.view.View;

import cz.geeklab.fotobox_tablet.Receiver.ResultReceiverActivity;
import cz.geeklab.fotobox_tablet.socket.SocketClientTask;


public class MainActivity extends ActionBarActivity {

    TextView textResponse;
    EditText editTextAddress, editTextPort;
    Button buttonConnect, buttonClear, buttonResultReciever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.TestButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button pressed", Toast.LENGTH_SHORT).show();
            }
        });

        buttonResultReciever = (Button)findViewById(R.id.ResultRecieverButton);
        buttonResultReciever.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                ShowResultRecieverActivity(arg0);
            }});

        editTextAddress = (EditText)findViewById(R.id.address);
        editTextAddress.setText("192.168.15.108");
        editTextPort = (EditText)findViewById(R.id.port);
        buttonConnect = (Button)findViewById(R.id.connect);
        buttonClear = (Button)findViewById(R.id.clear);
        textResponse = (TextView)findViewById(R.id.response);

        buttonConnect.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                SocketClientTask myClientTask = new SocketClientTask(textResponse,
                        editTextAddress.getText().toString(),
                        Integer.parseInt(editTextPort.getText().toString()));
                myClientTask.execute();
            }});

        buttonClear.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                textResponse.setText("");
            }});
    }


    public void ShowResultRecieverActivity (View view) {
        Intent intent = new Intent(this, ResultReceiverActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
