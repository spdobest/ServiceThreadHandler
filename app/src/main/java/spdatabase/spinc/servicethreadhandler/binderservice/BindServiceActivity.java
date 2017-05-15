package spdatabase.spinc.servicethreadhandler.binderservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import spdatabase.spinc.servicethreadhandler.R;
import spdatabase.spinc.servicethreadhandler.event.Events;
import spdatabase.spinc.servicethreadhandler.event.GlobalBus;

public class BindServiceActivity extends AppCompatActivity  {

    private static final String TAG = "BindServiceActivity";

    BindService mBoundService;
    boolean mServiceBound = false;
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.print_timestamp)
    Button printTimestamp;
    @BindView(R.id.timestamp_text)
    TextView timestampText;
    @BindView(R.id.stop_service)
    Button stopService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BindService.MyBinder myBinder = (BindService.MyBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_service);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.imageView1, R.id.print_timestamp, R.id.timestamp_text, R.id.stop_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageView1:
                break;
            case R.id.print_timestamp:
                if (mServiceBound) {
                    timestampText.setText(mBoundService.getTimestamp());
                }
                break;
            case R.id.timestamp_text:
                break;
            case R.id.stop_service:
                if (mServiceBound) {
                    unbindService(mServiceConnection);
                    mServiceBound = false;
                }
                Intent intent = new Intent(BindServiceActivity.this,
                        BindService.class);
                stopService(intent);
                break;
        }
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
        GlobalBus.getBus().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        GlobalBus.getBus().register(this);
    }

    @Subscribe
    public void getMessage(Events.BindServiceTOActivity activityFragmentMessage) {
        Log.i(TAG, "getMessage: " + activityFragmentMessage.getMessage());
        timestampText.setText(activityFragmentMessage.getMessage());
    }
}
