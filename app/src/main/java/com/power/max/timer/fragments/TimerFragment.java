package com.power.max.timer.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.power.max.timer.R;

/**
 * Created by Karsten on 19.09.2015.
 */
public class TimerFragment extends Fragment {

    public static final String sARGUMENT_TIMER_CODE = "timer";

    public static TimerFragment newInstance(Bundle bundle)
    {
        final TimerFragment timerFragment = new TimerFragment();

        if (bundle != null)
        {
            timerFragment.setArguments(bundle);
        }

        return timerFragment;
    }

    @Override
    public View onCreateView
            (
                    LayoutInflater inflater,
                    @Nullable ViewGroup container,
                    @Nullable Bundle savedInstanceState
            )
    {
        // The last two arguments ensure LayoutParams are inflated properly
        final View view = inflater.inflate(R.layout.image_fragment, container, false);
        initialise(view);

        return view;
    }

    /**
     * Creates, binds and sets up the resources
     *
     * @param view is the view to get the bindings, context...
     */
    private void initialise(@NonNull final View view)
    {
        switch (getArguments().getInt(sARGUMENT_TIMER_CODE))
        {
            case 0:
                break;

            case 1:
                break;

        }
    }
}
