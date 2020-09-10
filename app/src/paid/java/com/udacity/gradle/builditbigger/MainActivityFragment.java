package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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

    private Unbinder mUnbinder;

    @BindView(R.id.progressbar_loading)
    ProgressBar mProgressLoading;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mUnbinder = ButterKnife.bind(this, root);

        return root;
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
            startJokeScreen();
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
