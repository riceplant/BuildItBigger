package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        ButterKnife.bind(this, root);

        return root;
    }

    private void onJokeReceived(String joke) {
        mJoke = joke;
        if (!mTest) {
            openJoke();
        }
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
