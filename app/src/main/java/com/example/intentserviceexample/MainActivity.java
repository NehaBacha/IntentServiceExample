package com.example.intentserviceexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private CashbackReciver cashbackReceiver;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=(TextView)findViewById(R.id.cb_results);
        registerCashbackReceiver();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(cashbackReceiver);
    }

    public void startCashbackService(View view){
        EditText et=(EditText) findViewById(R.id.cashback_cat);

        Intent cbIntent=new Intent();
        cbIntent.setClass(this,CashbackIntentService.class);
        cbIntent.putExtra("cashback_cat",et.getText().toString());
        startService(cbIntent);

    }

    private void registerCashbackReceiver() {
        cashbackReceiver=new CashbackReciver();
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(CashbackIntentService.CASHBACK_INFO);

        registerReceiver(cashbackReceiver,intentFilter);
    }

    private class CashbackReciver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String cbinfo = intent.getStringExtra("cashback");
            tv.setText(cbinfo);
        }
    }
}
