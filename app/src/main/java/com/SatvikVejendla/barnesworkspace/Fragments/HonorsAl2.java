package com.SatvikVejendla.barnesworkspace.Fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SatvikVejendla.barnesworkspace.AssignmentActivity;
import com.SatvikVejendla.barnesworkspace.ChapterActivity;
import com.SatvikVejendla.barnesworkspace.R;
import com.SatvikVejendla.barnesworkspace.SettingsActivity;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

public class HonorsAl2 extends Fragment {
    String url = "https://barnessbhsmath.blogspot.com/p/mr.html";
    String[] links;
    ListView lv;
    ArrayAdapter<String> adapter;
    AdView adview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.al2, container, false);
        lv = (ListView) view.findViewById(R.id.listview);
        final String[] chapters = {"Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5", "Chapter 6", "Chapter 7", "Chapter 8", "Chapter 9", "Chapter 10", "Chapter 11"};
        adapter = new ArrayAdapter<>(getActivity(), R.layout.list_item, R.id.chapter, chapters);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), AssignmentActivity.class);
                i.putExtra("Chapter", position);
                i.putExtra("Class", 0);
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.slidein, R.anim.slideout);
                getActivity().finish();

            }
        });

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                adview = (AdView) view.findViewById(R.id.adView);
                AdRequest adRequest = new AdRequest.Builder().build();
                adview.loadAd(adRequest);
            }
        });
        return view;
    }
}
