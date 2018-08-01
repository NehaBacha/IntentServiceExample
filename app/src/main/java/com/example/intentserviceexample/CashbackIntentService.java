package com.example.intentserviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class CashbackIntentService extends IntentService {
    final static String CASHBACK_INFO="cashback_info";
    public CashbackIntentService(){
        super("Cashback IntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String cb_category=intent.getStringExtra("cashback_cat");

        String cb_info=getCashbackInfo(cb_category);
        sendCashbackInfoClient(cb_info);

    }

    private void sendCashbackInfoClient(String msg) {
        Intent intent=new Intent();
        intent.setAction(CASHBACK_INFO);
        intent.putExtra("cashback",msg);
        sendBroadcast(intent);

    }

    private String getCashbackInfo(String cb_category) {
        String cashback;
        if("electronics".equals(cb_category)){
            cashback="upto 20% cashback on electronics";
        }else if("fashion".equals(cb_category)){
            cashback="upto 60% cashback on all fashion items";
        }else{
            cashback="All other categories except fashion and electronics, flat 30% cashback";
        }
        return cashback;
    }
}
