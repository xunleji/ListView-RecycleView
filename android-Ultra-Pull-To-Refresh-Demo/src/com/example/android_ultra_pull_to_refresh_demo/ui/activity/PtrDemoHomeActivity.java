package com.example.android_ultra_pull_to_refresh_demo.ui.activity;

import in.srain.cube.mints.base.MintsBaseActivity;
import android.os.Bundle;

import com.example.android_ultra_pull_to_refresh_demo.R;
import com.example.android_ultra_pull_to_refresh_demo.ui.PtrDemoHomeFragment;

public class PtrDemoHomeActivity extends MintsBaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        pushFragmentToBackStack(PtrDemoHomeFragment.class, null);
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.id_fragment;
    }
}