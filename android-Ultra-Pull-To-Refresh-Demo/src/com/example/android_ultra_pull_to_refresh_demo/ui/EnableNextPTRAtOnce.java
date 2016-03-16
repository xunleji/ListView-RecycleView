package com.example.android_ultra_pull_to_refresh_demo.ui;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

import com.example.android_ultra_pull_to_refresh_demo.R;
import com.example.android_ultra_pull_to_refresh_demo.ui.classic.WithTextViewInFrameLayoutFragment;

public class EnableNextPTRAtOnce extends WithTextViewInFrameLayoutFragment {

    @Override
    protected void setupViews(PtrClassicFrameLayout ptrFrame) {
        setHeaderTitle(R.string.ptr_demo_enable_next_ptr_at_once);
        ptrFrame.setEnabledNextPtrAtOnce(true);
        ptrFrame.setPullToRefresh(false);
    }
}