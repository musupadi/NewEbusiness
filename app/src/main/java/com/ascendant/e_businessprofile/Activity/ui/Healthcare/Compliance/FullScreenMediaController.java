package com.ascendant.e_businessprofile.Activity.ui.Healthcare.Compliance;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;

public class FullScreenMediaController extends MediaController {

    private ImageButton fullScreen;
    private String isFullScreen;

    public FullScreenMediaController(Context context) {
        super(context);
    }

    @Override
    public void setAnchorView(View view) {

        super.setAnchorView(view);

        //image button for full screen to be added to media controller
        fullScreen = new ImageButton (super.getContext());

        LayoutParams params =
                new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT;
        params.rightMargin = 80;
        addView(fullScreen, params);

        //fullscreen indicator from intent
        isFullScreen =  ((Activity)getContext()).getIntent().
                getStringExtra("fullScreenInd");
    }
}

