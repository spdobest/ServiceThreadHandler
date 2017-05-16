package spdatabase.spinc.servicethreadhandler.intentService;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class QuoteServiceReceiver extends ResultReceiver {
 
  private Listener listener;
 
  public QuoteServiceReceiver(Handler handler) {
    super(handler);
  }
 
  public void setListener(Listener listener) {
    this.listener = listener;
  }
 
  @Override
  protected void onReceiveResult(int resultCode, Bundle resultData) {
   if (listener != null)
    listener.onReceiveResult(resultCode, resultData);
   }
  }
