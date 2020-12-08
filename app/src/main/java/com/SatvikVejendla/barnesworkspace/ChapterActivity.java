package com.SatvikVejendla.barnesworkspace;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ChapterActivity extends AppCompatActivity {
    PDFView web;
    ProgressBar pbar;
    public int b;
    ImageButton backbutton;
    TextView chaptertext;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_chapter);

        backbutton = (ImageButton) findViewById(R.id.backbutton);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        web = (PDFView) findViewById(R.id.web);
        pbar = (ProgressBar) findViewById(R.id.pbar);
        pbar.setVisibility(View.VISIBLE);
        chaptertext = (TextView) findViewById(R.id.textView3);
        Intent i = getIntent();
        b = i.getIntExtra("Chapter", 0);
        int section = i.getIntExtra("Section", 0);
        chaptertext.setText("Chapter " + (b+1));
        int c = i.getIntExtra("Class", 0);

        int[] chp1 = {0, 4, 8, 12, 18};
        int[] chp2 = {0, 4, 7, 15, 17, 24, 28, 34};
        int[] chp3 = {0, 4, 16, 23, 33, 47, 57, 66};
        int[] chp4 = {0, 11, 14, 21, 25, 36, 39};
        int[] chp5 = {0, 6, 19, 27, 36, 43, 52};
        int[] chp6 = {0, 4, 11, 19, 24, 41, 54, 61};
        int[] chp7 = {0, 5, 14, 22, 30, 33, 45};
        int[] chp8 = {0, 5, 11, 19, 23, 28, 33};
        int[] chp9 = {0, 10, 15, 24, 31, 43, 48, 55, 60, 65, 72};
        int[] chp10 = {0, 7, 12, 18, 29, 31, 34, 37, 45};
        int[] chp11 = {0, 6, 14, 23, 37, 53, 60};
        int[] chpt1 = {0, 2, 12, 23, 33, 40};
        int[] chpt2 = {0, 15, 26, 40, 53, 67, 79};
        int[] chpt3 = {0, 9, 22, 44, 60, 77, 95, 116, 125, 133};
        int[] chpt4 = {0, 14, 27, 36, 47, 63, 73};
        int[] chpt5 = {0, 12, 23, 34, 48, 60, 72, 81, 91};
        int[] chpt6 = {0, 13, 24, 38, 49};
        int[] chpt7 = {0, 17, 32, 43, 55, 60, 72, 76};
        int[][] apc = {chpt1, chpt2, chpt3, chpt4, chpt5, chpt6, chpt7};
        int[][] hAl = {chp1, chp2, chp3, chp4, chp5, chp6, chp7, chp8, chp9, chp10, chp11};
        int[][][] arr = {hAl, apc};

        String name = "chp";
        if(c==0){
            name+="t";
        }
        int page = arr[c][b][section];

        name = name + (b+1);
        name+=".pdf";

        web.fromAsset(name).defaultPage(page).load();
        pbar.setVisibility(View.GONE);

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent n = new Intent(ChapterActivity.this, AssignmentActivity.class);
                n.putExtra("Chapter", b);
                startActivity(n);
                overridePendingTransition(R.anim.slideout2, R.anim.slidein2);
                finish();
            }
        });

        SharedPreferences s = getSharedPreferences("user", MODE_APPEND);
        int color = s.getInt("color", 0);
        toolbar.setBackgroundColor(color);

    }

    @Override
    public void onBackPressed() {
        Intent n = new Intent(ChapterActivity.this, AssignmentActivity.class);
        n.putExtra("Chapter", b);
        startActivity(n);
        overridePendingTransition(R.anim.slideout2, R.anim.slidein2);
        finish();
    }
}