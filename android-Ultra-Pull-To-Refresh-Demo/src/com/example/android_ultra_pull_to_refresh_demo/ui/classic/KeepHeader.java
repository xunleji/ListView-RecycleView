package com.example.android_ultra_pull_to_refresh_demo.ui.classic;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;

import com.example.android_ultra_pull_to_refresh_demo.R;

public class KeepHeader extends WithTextViewInFrameLayoutFragment {

    @Override
    protected void setupViews(PtrClassicFrameLayout ptrFrame) {
        setHeaderTitle(R.string.ptr_demo_block_keep_header);
        ptrFrame.setKeepHeaderWhenRefresh(true);
    }
}
