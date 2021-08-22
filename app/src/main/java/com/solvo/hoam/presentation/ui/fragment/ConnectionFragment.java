package com.solvo.hoam.presentation.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.solvo.hoam.R;

import moxy.MvpAppCompatFragment;

public class ConnectionFragment extends MvpAppCompatFragment {

    public static final String TAG = ConnectionFragment.class.getSimpleName();

    private Button tryAgainButton;
    private OnClickListener listener;

    public static ConnectionFragment newInstance() {
        return new ConnectionFragment();
    }

    public interface OnClickListener {

        void onClick();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.error_connection_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tryAgainButton = (Button) view.findViewById(R.id.try_again_button);
        tryAgainButton.setOnClickListener(v -> {
            listener.onClick();
        });
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
