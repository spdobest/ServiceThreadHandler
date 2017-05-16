package spdatabase.spinc.servicethreadhandler;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import spdatabase.spinc.servicethreadhandler.aidl.AIDLActivity;
import spdatabase.spinc.servicethreadhandler.alarm.AlarmActivity;
import spdatabase.spinc.servicethreadhandler.binderservice.BindServiceActivity;
import spdatabase.spinc.servicethreadhandler.handler.HandlerActivity;
import spdatabase.spinc.servicethreadhandler.handlerwith_asynctask.HandlerWithAsyncActivity;
import spdatabase.spinc.servicethreadhandler.messenger.MessengerActivity;
import spdatabase.spinc.servicethreadhandler.normalservice.ServiceActivity;
import spdatabase.spinc.servicethreadhandler.scheduleService.ScheduleServiceActivity;
import spdatabase.spinc.servicethreadhandler.syncAdapter.SyncActivity;
import spdatabase.spinc.servicethreadhandler.syncAdapter.basicsyncadapter.EntryListActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.buttonHandler)
    AppCompatButton buttonHandler;
    @BindView(R.id.buttonThreadWithHandler)
    AppCompatButton buttonThreadWithHandler;
    @BindView(R.id.buttonService)
    AppCompatButton buttonService;
    @BindView(R.id.buttonBinderService)
    AppCompatButton buttonBinderService;
    @BindView(R.id.buttonBinderServiceMessanger)
    AppCompatButton buttonBinderServiceMessanger;
    @BindView(R.id.buttonAIDL)
    AppCompatButton buttonAIDL;
    @BindView(R.id.buttonIPC)
    AppCompatButton buttonIPC;

    @BindView(R.id.buttonScheduleService)
    AppCompatButton buttonScheduleService;

    @BindView(R.id.buttonAlarm)
    AppCompatButton buttonAlarm;
    @BindView(R.id.buttonSyncAdapter)
    AppCompatButton buttonSyncAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.buttonHandler, R.id.buttonThreadWithHandler, R.id.buttonService, R.id.buttonBinderService, R.id.buttonBinderServiceMessanger, R.id.buttonAIDL, R.id.buttonIPC,R.id.buttonScheduleService,R.id.buttonAlarm,R.id.buttonSyncAdapter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonHandler:
                    startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                break;
            case R.id.buttonThreadWithHandler:
                startActivity(new Intent(MainActivity.this, HandlerWithAsyncActivity.class));
                break;
            case R.id.buttonService:
                startActivity(new Intent(MainActivity.this, ServiceActivity.class));
                break;
            case R.id.buttonBinderService:
                startActivity(new Intent(MainActivity.this, BindServiceActivity.class));
                break;
            case R.id.buttonBinderServiceMessanger:
                startActivity(new Intent(MainActivity.this, MessengerActivity.class));
                break;
            case R.id.buttonAIDL:
                startActivity(new Intent(MainActivity.this, AIDLActivity.class));
                break;
            case R.id.buttonIPC:
                startActivity(new Intent(MainActivity.this, HandlerActivity.class));
                break;
            case R.id.buttonScheduleService :
                startActivity(new Intent(MainActivity.this, ScheduleServiceActivity.class));
                break;
            case R.id.buttonAlarm :
                startActivity(new Intent(MainActivity.this, AlarmActivity.class));
                break;
            case R.id.buttonSyncAdapter :
                startActivity(new Intent(MainActivity.this, EntryListActivity.class));
                break;
        }
    }
}
