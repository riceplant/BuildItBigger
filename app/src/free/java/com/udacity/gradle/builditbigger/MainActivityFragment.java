package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.riceplant.androidlibrary.JokeActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private boolean mTest = false;
    private String mJoke;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        // getting the Ad displayed
        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        ButterKnife.bind(this, root);

        return root;
    }

    @OnClick(R.id.joke_button)
    public void tellJoke() {
        EndpointAsyncTask getJokeAsyncTask = new EndpointAsyncTask(new EndpointAsyncTask.OnEventListener<String>() {
            @Override
            public void onSuccess(String joke) {
                onJokeReceived(joke);
            }
        });
        getJokeAsyncTask.execute();
    }

    private void onJokeReceived(String joke) {
        mJoke = joke;
        if (!mTest) {
            openJoke();
        }
    }

    private void openJoke() {
        startActivity(JokeActivity.jokeIntent(getActivity(), mJoke));
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
        mTest = true;
    }
}
