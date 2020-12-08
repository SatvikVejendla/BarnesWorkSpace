package com.SatvikVejendla.barnesworkspace.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.SatvikVejendla.barnesworkspace.AssignmentActivity;
import com.SatvikVejendla.barnesworkspace.ChapterActivity;
import com.SatvikVejendla.barnesworkspace.R;
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

public class APCalcAB extends Fragment {
    String url = "https://barnessbhsmath.blogspot.com/p/blog-page.html";
    String[] links;
    ListView lv;
    ArrayAdapter<String> adapter;
    AdView adview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.apcalc, container, false);
        lv = (ListView) view.findViewById(R.id.listview);
        final String[] chapters = {"Chapter 1", "Chapter 2", "Chapter 3", "Chapter 4", "Chapter 5", "Chapter 6", "Chapter 7"};
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item, R.id.chapter, chapters);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity(), AssignmentActivity.class);
                i.putExtra("Chapter", position);
                i.putExtra("Class", 1);
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
