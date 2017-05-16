package spdatabase.spinc.servicethreadhandler.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class QuoteService extends IntentService {

    private static String YAHOO_FINANCE_URL = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22#sym#%22)&env=store://datatables.org/alltableswithkeys";

	public QuoteService() {
		super("QuoteService");		
	}


	@Override
	protected void onHandleIntent(Intent intent) {
		
		
		Stock stock = intent.getParcelableExtra("stock");
		final ResultReceiver rec = (ResultReceiver) intent.getParcelableExtra("rec");
		
		// Here we retrieve the stock quote using Yahoo! Finance
    	Log.d("Srv", "Get stock");
        String url = YAHOO_FINANCE_URL.replaceAll("#sym#", stock.getSymbol());
        try {
            HttpURLConnection con = (HttpURLConnection) ( new URL(url)).openConnection();
            con.setRequestMethod("GET");
            con.connect();
            InputStream is = con.getInputStream();

            // Start parsing XML
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(is, null);
            int event = parser.getEventType();
            String tagName = null;
            String currentTag = null;
            Stock stockResult = new Stock();
            
            while (event != XmlPullParser.END_DOCUMENT) {
                tagName = parser.getName();
                Log.d("Srv", "Tag:" + tagName);
                if (event == XmlPullParser.START_TAG) {
                	currentTag = tagName;
                }
                else if (event == XmlPullParser.TEXT) {
                	if ("ASK".equalsIgnoreCase(currentTag)) 
                		stockResult.setAskValue(Double.parseDouble(parser.getText()));
                    else if	("BID".equalsIgnoreCase(currentTag)) {                    	
                    	stockResult.setBidValue(Double.parseDouble(parser.getText()));
                    	Bundle b = new Bundle();
                    	b.putParcelable("stock", stockResult);
                    	rec.send(0, b);
                	}
                }
                
                event = parser.next();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

}