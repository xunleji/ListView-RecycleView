package com.example.android_ultra_pull_to_refresh_demo.app;

import in.srain.cube.Cube;
import in.srain.cube.image.ImageLoaderFactory;
import in.srain.cube.request.RequestCacheManager;
import in.srain.cube.util.CLog;
import in.srain.cube.util.CubeDebug;
import in.srain.cube.views.ptr.PtrFrameLayout;
import android.app.Application;

import com.example.android_ultra_pull_to_refresh_demo.image.DemoDuiTangImageReSizer;
import com.example.android_ultra_pull_to_refresh_demo.image.PtrImageLoadHandler;

public class PtrDemoApplication extends Application {

	public static PtrDemoApplication instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		String environment = "";

		if (environment.equals("production")) {
			CLog.setLogLevel(CLog.LEVEL_ERROR);
		} else if (environment.equals("beta")) {
			CLog.setLogLevel(CLog.LEVEL_WARNING);
		} else {
			// development
			CLog.setLogLevel(CLog.LEVEL_VERBOSE);
		}
		CubeDebug.DEBUG_IMAGE = true;
		PtrFrameLayout.DEBUG = true;
		PtrFrameLayout.DEBUG = false;
		
		ImageLoaderFactory.setDefaultImageReSizer(DemoDuiTangImageReSizer.getInstance());
        ImageLoaderFactory.setDefaultImageLoadHandler(new PtrImageLoadHandler());
        String dir = "request-cache";
        // ImageLoaderFactory.init(this);
        RequestCacheManager.init(this, dir, 1024 * 10, 1024 * 10);
        Cube.onCreate(this);
	}
}