package spdatabase.spinc.servicethreadhandler.normalservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import spdatabase.spinc.servicethreadhandler.R;

public class ServiceActivity extends AppCompatActivity {

    @BindView(R.id.butonStartService)
    Button butonStartService;
    @BindView(R.id.buttonSTopService)
    Button buttonSTopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        ButterKnife.bind(this);


    }

    @OnClick({R.id.butonStartService, R.id.buttonSTopService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.butonStartService:
                startService(new Intent(getBaseContext(), NormalService.class));
                break;
            case R.id.buttonSTopService:
                stopService(new Intent(getBaseContext(), NormalService.class));
                break;
        }
    }
}
