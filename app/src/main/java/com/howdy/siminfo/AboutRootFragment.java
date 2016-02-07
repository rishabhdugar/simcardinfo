package com.howdy.siminfo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class AboutRootFragment extends Fragment {
    private static final String ARG_TEXT = "text";
    private Tracker mTracker;

    public AboutRootFragment() {
    }

    public static AboutRootFragment newInstance(String text) {
        AboutRootFragment fragment = new AboutRootFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.about_root, container, false);

        /*Google Analytics: send screen Name*/

        //Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
        mTracker = application.getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);

        // Send a screen view.
        mTracker.setScreenName("SIM Info");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        mTracker.send(new HitBuilders.ScreenViewBuilder()
                .set("SIMInfo", "SIM Info")
                .build());
        /*

        AdView mAdView = (AdView) view.findViewById(R.id.adView3);

        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);*/


        return view;
    }
}

