package com.simcard.siminfo;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by rishabh on 31/12/15.
 */
public class SIMInfoFragment extends Fragment {
    @Bind(R.id.is_root)TextView root_text;
    @Bind(R.id.is_root_available)TextView t1;
    @Bind(R.id.super_user_text)TextView su_text;
    @Bind(R.id.is_su_found)TextView t2;
    @Bind(R.id.is_busy_box_installed)TextView t3;
    @Bind(R.id.path)TextView t4;
    @Bind(R.id.voicemail_tag)TextView voicemail;
    @Bind(R.id.sim_serial)TextView sim_serial;
    @Bind(R.id.device_id)TextView device_id;
    @Bind(R.id.device_software_version)TextView device_software_version;
    private static final String ARG_TEXT = "text";
    private Tracker mTracker;

    public SIMInfoFragment() {
    }

    public static SIMInfoFragment newInstance(String text) {
        SIMInfoFragment fragment = new SIMInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.sim_info, container, false);

        /*Google Analytics: send screen Name*/

        // Obtain the shared Tracker instance.
        AnalyticsApplication application = (AnalyticsApplication) getActivity().getApplication();
        mTracker = application.getTracker(AnalyticsApplication.TrackerName.APP_TRACKER);

        // Send a screen view.
        mTracker.setScreenName("SIM Info");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
        mTracker.send(new HitBuilders.ScreenViewBuilder()
                .set("SIMInfo", "SIM Info")
                .build());

        /*
        AdView mAdView = (AdView) view.findViewById(R.id.adView1);

        AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);*/

        Log.d("on detail create view", "created");
        ButterKnife.bind(this, view);
        //GENERAL

        TelephonyManager manager=(TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        t1.setText(manager.getSimCountryIso());
        t2.setText(manager.getNetworkOperatorName());
        t3.setText(manager.getSimOperatorName());
        t4.setText(String.valueOf(manager.isNetworkRoaming()));
        device_id.setText(manager.getDeviceId());
        voicemail.setText(manager.getVoiceMailAlphaTag());
        device_software_version.setText(manager.getDeviceSoftwareVersion());
        sim_serial.setText(manager.getSimSerialNumber());
        return view;
    }

}

