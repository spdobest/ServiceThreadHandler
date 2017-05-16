package spdatabase.spinc.servicethreadhandler.aidl;


import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import spdatabase.spinc.servicethreadhandler.R;
import spdatabase.spinc.servicethreadhandler.binderservice.BindService;
import spdatabase.spinc.servicethreadhandler.event.GlobalBus;

public class AIDLActivity extends Activity {

    private static final String TAG = "AIDLExample";
    IAddService service;
    AddServiceConnection connection;

    EditText value1,value2,result;
    Button buttonResult;

    boolean mServiceBound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl);
        initService();


        value1 = (EditText) findViewById(R.id.editTextValue1);
        value2 = (EditText) findViewById(R.id.editTextValue2);
        result = (EditText) findViewById(R.id.editTextResult);
        buttonResult = (Button) findViewById(R.id.buttonResult);

        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n1 = 0, n2 = 0, res = -1;
                n1 = Integer.parseInt(value1.getText().toString());
                n2 = Integer.parseInt(value2.getText().toString());
                try {
                    res = service.add(n1, n2);
                } catch (RemoteException e) {
                    Log.i(TAG, "Data fetch failed with: " + e);
                    e.printStackTrace();
                }
                result.setText(new Integer(res).toString());
            }
        });
    }
    /**
     * This is our function which binds our activity(MainActivity) to our service(AddService).
     */
    private void initService() {
        Log.i(TAG, "initService()");
        connection = new AddServiceConnection();
        Intent i = new Intent();
        i.setClassName("spdatabase.spinc.servicethreadhandler.aidl", AddService.class.getName());
        boolean ret = bindService(i, connection, Context.BIND_AUTO_CREATE);
        Log.i(TAG, "initService() bound value: " + ret);
    }

    /**
     * This is our function to un-binds this activity from our service.
     */
    private void releaseService() {
        unbindService(connection);
        connection = null;
        Log.d(TAG, "releaseService(): unbound.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseService();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, BindService.class);
        startService(intent);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }

    /**
     * This is the class which represents the actual service-connection.
     * It type casts the bound-stub implementation of the service class to our AIDL interface.
     */
    class AddServiceConnection implements ServiceConnection {

        public void onServiceConnected(ComponentName name, IBinder boundService) {
            service = IAddService.Stub.asInterface((IBinder) boundService);
            Log.i(TAG, "onServiceConnected(): Connected");
            Toast.makeText(AIDLActivity.this, "AIDLExample Service connected", Toast.LENGTH_LONG).show();
        }

        public void onServiceDisconnected(ComponentName name) {
            service = null;
            Log.i(TAG, "onServiceDisconnected(): Disconnected");
            Toast.makeText(AIDLActivity.this, "AIDLExample Service Connected", Toast.LENGTH_LONG).show();
        }
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            service = null;
            Log.i(TAG, "onServiceDisconnected(): Disconnected");
            Toast.makeText(AIDLActivity.this, "AIDLExample Service Connected", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder boundService) {
            service = IAddService.Stub.asInterface((IBinder) service);
            Log.i(TAG, "onServiceConnected(): Connected");
            Toast.makeText(AIDLActivity.this, "AIDLExample Service connected", Toast.LENGTH_LONG).show();
        }
    };

}