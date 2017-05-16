package spdatabase.spinc.servicethreadhandler.intentService;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import spdatabase.spinc.servicethreadhandler.R;

public class IntentServiceActivity extends AppCompatActivity implements Listener {

    private TextView askValue;
    private TextView bidValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_service);

        final EditText symEdt = (EditText) findViewById(R.id.edtSym);
        Button b = (Button) findViewById(R.id.btnGo);
        askValue = (TextView) findViewById(R.id.askValue);
        bidValue = (TextView) findViewById(R.id.bidValue);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Srv", "Client get quote!");
                // Create the stock
                final Stock stock = new Stock();
                stock.setSymbol(symEdt.getText().toString());
                Log.d("SwA", "Calling method");
                startService(createCallingIntent(stock));

            }
        });
    }


    private Intent createCallingIntent(Stock stock) {
        Intent i = new Intent(this, QuoteService.class);
        QuoteServiceReceiver receiver = new QuoteServiceReceiver(new Handler());
        receiver.setListener(this);
        i.putExtra("rec", receiver);
        i.putExtra("stock", stock);
        return i;
    }


    // Callback method
    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        Stock stock = resultData.getParcelable("stock");
        Log.d("Srv", "Stock ["+stock+"]");

        askValue.setText("" + stock.getAskValue());
        bidValue.setText("" + stock.getBidValue());
    }


}