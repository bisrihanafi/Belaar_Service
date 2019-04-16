package com.gmail.bisrihanafi.belajar_service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText editWaktu;
    Button playbt;
    Button stopbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TODO 1 : Menghubungkan variable dengan komponen view
        editWaktu=(EditText) findViewById(R.id.et_waktu);
        playbt=(Button)findViewById(R.id.bt_play);
        stopbt=(Button)findViewById(R.id.bt_stop);

        //TODO 2 : Membuat Klik Listener
        playbt.setOnClickListener(this);
        stopbt.setOnClickListener(this);
    }

    //TODO 3 : Bagian ini Membuat case dari tombol play dan stop tombol dieksekusi berdasarkan id pada view
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.bt_play):
                callplay();
                break;
            case  (R.id.bt_stop):
                stopplay();
                break;
        }
    }
    //TODO 4 : fungsi pada tombol play
    public void callplay(){
      //TODO 5 : mengambil nilai angka dari edit text dan mengubahnya menjadi int
        int detik= Integer.parseInt(editWaktu.getText().toString());
        //TODO 6 : Mmbuat Intent kepada service
        Intent intent=new Intent(MainActivity.this, MyService.class);
        //TODO 7 : membuat pending intent untuk intent service berdasarkan jeda waktu yang dimasukan pada edit text
        PendingIntent pending= PendingIntent.getService(MainActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //TODO 8 : membuat alrm trigger
        AlarmManager alrm=(AlarmManager) getSystemService(ALARM_SERVICE);
        if (alrm!=null){
            //TODO 9 : dengan alrm managaer membuat intent dimulai sepertihalnya cool down
            alrm.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+(detik*1000),pending);
            Toast.makeText(getApplicationContext(),"lagu dimulai dalam "+detik+" detik",Toast.LENGTH_LONG).show();

        }
    }
    //TODO 10 : fungsi untuk menghetikan service dengan fungsi stopService
    public void stopplay(){
        stopService(new Intent(MainActivity.this, MyService.class));
    }
}
