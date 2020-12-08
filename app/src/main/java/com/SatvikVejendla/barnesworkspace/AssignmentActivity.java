package com.SatvikVejendla.barnesworkspace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class AssignmentActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter<String> adapter;
    TextView chaptername;
    ImageButton backbutton;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_assignment);

        Intent get = getIntent();
        final int a = get.getIntExtra("Class", 0);
        final int b = get.getIntExtra("Chapter", 0);

        String[] chp1 = {"1.1", "1.2", "1.3", "1.4", "Chapter 1 Review"};
        String[] chp2 = {"2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "2.7", "Chapter 2 Review"};
        String[] chp3 = {"3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "Chapter 3 Review"};
        String[] chp4 = {"4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "Chapter 4 Review"};
        String[] chp5 = {"5.1", "5.2", "5.3", "5.4", "5.5", "5.6", "Chapter 5 Review"};
        String[] chp6 = {"6.1", "6.2", "6.3", "6.4", "6.5", "6.6", "6.7", "Chapter 6 Review"};
        String[] chp7 = {"7.1", "7.2", "7.3", "7.4", "7.5", "7.6", "Chapter 7 Review"};
        String[] chp8 = {"8.1", "8.2", "8.3", "8.4", "8.5", "8.6", "Chapter 8 Review"};
        String[] chp9 = {"9.1", "9.2", "9.3", "9.4", "9.5", "9.6", "9.7", "9.8", "9.9", "9.10", "Chapter 9 Review"};
        String[] chp10 = {"10.1", "10.2", "10.3", "10.4", "10.5", "10.6", "10.7", "10.8", "Chapter 10 Review"};
        String[] chp11 = {"11.1", "11.2", "11.3", "11.4", "11.5", "11.6", "Chapter 11 Review"};
        String[][] al2 = { chp1, chp2, chp3, chp4, chp5, chp6, chp7, chp8, chp9, chp10, chp11};

        String[] chpt1 = {"1.1", "1.2", "1.3", "1.4", "1.5", "Chapter 1 Review"};
        String[] chpt2 = {"2.1", "2.2", "2.3", "2.4", "2.5", "2.6", "Chapter 2 Review"};
        String[] chpt3 = {"3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8", "3.9", "Chapter 3 Review"};
        String[] chpt4 = {"4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "Chapter 4 Review"};
        String[] chpt5 = {"5.1", "5.2", "5.3", "5.4", "5.5", "5.6", "5.7", "5.8", "Chapter 5 Review"};
        String[] chpt6 = {"6.1", "6.2", "6.3", "6.4", "Chapter 6 Review"};
        String[] chpt7 = {"7.1", "7.2", "7.3", "7.4", "7.5", "7.6", "7.7", "Chapter 7 Review"};
        String[][] apcalc = { chpt1, chpt2, chpt3, chpt4, chpt5, chpt6, chpt7};
        String[][][] current = {al2, apcalc};
        String[] sections = current[a][b];
        lv = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<>(AssignmentActivity.this, R.layout.list_item, R.id.chapter, sections);
        lv.setAdapter(adapter);

        chaptername = (TextView) findViewById(R.id.textView3);
        chaptername.setText("Chapter " + (b+1));
        backbutton = (ImageButton) findViewById(R.id.backbutton);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(AssignmentActivity.this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.slideout2, R.anim.slidein2);
                finish();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(AssignmentActivity.this, ChapterActivity.class);
                i.putExtra("Section", position);
                i.putExtra("Chapter", b);
                i.putExtra("Class", a);
                startActivity(i);
                overridePendingTransition(R.anim.slidein, R.anim.slideout);
                finish();

            }
        });

        SharedPreferences s = getSharedPreferences("user", MODE_APPEND);
        int color = s.getInt("color", 0);
        toolbar.setBackgroundColor(color);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(AssignmentActivity.this, MainActivity.class));
        overridePendingTransition(R.anim.slideout2, R.anim.slidein2);
        finish();
    }
}