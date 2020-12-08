package com.SatvikVejendla.barnesworkspace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.ArrayList;

import petrov.kristiyan.colorpicker.ColorPicker;

public class SettingsActivity extends AppCompatActivity {
    Spinner spn;
    Button btn;
    CardView c1, c2;
    Button barnesemail, satvikemail;
    private AdView adview;
    LinearLayout ll, total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_settings);

        total = (LinearLayout) findViewById(R.id.total);
        ll = (LinearLayout) findViewById(R.id.ll);
        ll.setVisibility(View.VISIBLE);
        barnesemail = (Button) findViewById(R.id.barnesemail);
        satvikemail = (Button) findViewById(R.id.satvikemail);
        barnesemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?to=" + "gordon.barnes@sbschools.org");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));
            }
        });
        satvikemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?to=" + "satvej1@gmail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));
            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                adview = (AdView) findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                adview.loadAd(adRequest);
            }
        });



        SharedPreferences s = getSharedPreferences("user", MODE_APPEND);
        final int color = s.getInt("color", -740056);
        c1 = (CardView) findViewById(R.id.c1);
        c2 = (CardView) findViewById(R.id.c2);
        c1.setCardBackgroundColor((color+20));
        c2.setCardBackgroundColor((color+20));
        spn = (Spinner) findViewById(R.id.spn);
        btn = (Button) findViewById(R.id.btncolor);
        ll = (LinearLayout) findViewById(R.id.ll);
        total.setBackgroundColor(color);
        String[] classname = {"Honors Algebra II", "AP Calculus AB"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, classname);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        SharedPreferences s1 = getSharedPreferences("user", MODE_APPEND);
        int num = s1.getInt("class", 0);
        spn.setSelection(num);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences.Editor setclass = getSharedPreferences("user", MODE_PRIVATE).edit();
                setclass.putInt("class", position);
                setclass.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn.setBackgroundColor(color);
        btn.setVisibility(View.VISIBLE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ColorPicker colorPicker = new ColorPicker(SettingsActivity.this);
                colorPicker.setOnFastChooseColorListener(new ColorPicker.OnFastChooseColorListener() {
                    @Override
                    public void setOnFastChooseColorListener(int position, int color2) {
                        SharedPreferences.Editor ed = getSharedPreferences("user", MODE_PRIVATE).edit();
                        ed.putInt("color", color2);
                        ed.commit();
                        btn.setBackgroundColor(color2);
                        total.setBackgroundColor(color2);
                        c1.setCardBackgroundColor((color2+20));
                        c2.setCardBackgroundColor((color2+20));
                        Log.d("COLORVALUE", String.valueOf(color2));
                    }

                    @Override
                    public void onCancel(){
                        // put code
                    }
                }).setColumns(5).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SettingsActivity.this, MainActivity.class);
        i.putExtra("back", true);
        startActivity(i);
    }
}