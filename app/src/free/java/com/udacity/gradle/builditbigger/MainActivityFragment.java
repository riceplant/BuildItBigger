package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.riceplant.androidlibrary.JokeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private boolean mIsTesting = false;
    private String mJoke;
    private InterstitialAd mInterstitialAd;

    private Unbinder mUnbinder;

    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.progressbar_loading)
    ProgressBar mProgressLoading;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        MobileAds.initialize(getActivity(), "ca-app-pub-3940256099942544/1033173712");

        mUnbinder = ButterKnife.bind(this, root);

        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                startJokeScreen();
            }
        });

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @OnClick(R.id.button_main_get_joke)
    public void tellJoke() {
        showLoading();
        EndpointAsyncTask getJokeAsyncTask = new EndpointAsyncTask(new EndpointAsyncTask.OnEventListener<String>() {
            @Override
            public void onSuccess(String joke) {
                hideLoading();
                onJokeRetrieved(joke);
            }
        });
        getJokeAsyncTask.execute();
    }

    private void onJokeRetrieved(String joke) {
        mJoke = joke;
        if (!mIsTesting) {
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                Log.d("TAG", "The interstitial wasn't loaded yet.");
            }
        }
    }

    private void startJokeScreen() {
        startActivity(JokeActivity.jokeScreenIntent(getActivity(), mJoke));
    }

    private void showLoading() {
        mProgressLoading.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        mProgressLoading.setVisibility(View.GONE);
    }

    @VisibleForTesting
    public String getJoke() {
        return mJoke;
    }

    @VisibleForTesting
    public void setJoke(String joke) {
        mJoke = joke;
    }

    @VisibleForTesting
    public void setTesting() {
        mIsTesting = true;
    }
}
