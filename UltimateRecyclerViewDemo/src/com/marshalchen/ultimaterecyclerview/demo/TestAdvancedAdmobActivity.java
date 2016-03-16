package com.marshalchen.ultimaterecyclerview.demo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.marshalchen.ultimaterecyclerview.AdmobAdapter;
import com.marshalchen.ultimaterecyclerview.URLogs;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.marshalchen.ultimaterecyclerview.UltimateRecyclerviewViewHolder;
import com.marshalchen.ultimaterecyclerview.demo.modules.SampleDataboxset;
import com.marshalchen.ultimaterecyclerview.quickAdapter.easyRegularAdapter;
import com.marshalchen.ultimaterecyclerview.quickAdapter.simpleAdmobAdapter;
import com.marshalchen.ultimaterecyclerview.quickAdapter.BiAdAdapterSwitcher;
import com.marshalchen.ultimaterecyclerview.ui.AdGoogleDisplaySupport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hesk on 4/8/15.
 */
@SuppressLint("NewApi")
public class TestAdvancedAdmobActivity extends AppCompatActivity {
	private UltimateRecyclerView ultimateRecyclerView;
	private boolean admob_test_mode = false;
	private LinearLayoutManager linearLayoutManager;
	private Toolbar toolbar;
	private BiAdAdapterSwitcher bi_sw;

	public static class adap extends
			simpleAdmobAdapter<String, VMoler, RelativeLayout> {

		public adap(RelativeLayout v, boolean insertOnce, int setInterval,
				List<String> L, AdviewListener listener) {
			super(v, insertOnce, setInterval, L, listener);
		}

		@Override
		protected void withBindHolder(VMoler var1, String var2, int var3) {
			bindthisInhere(var1, var2, var3);
		}

		@Override
		protected int getNormalLayoutResId() {
			return getIdRV();
		}

		@Override
		protected VMoler newViewHolder(View mview) {
			return new VMoler(mview);
		}

	}

	public static class regular extends easyRegularAdapter<String, VMoler> {

		public regular(List list) {
			super(list);
		}

		@Override
		protected int getNormalLayoutResId() {
			return getIdRV();
		}

		@Override
		protected VMoler newViewHolder(View view) {
			return new VMoler(view);
		}

		@Override
		protected void withBindHolder(VMoler holderm, String data, int position) {
			bindthisInhere(holderm, data, position);
		}
	}

	private static void bindthisInhere(VMoler d, String data, int pos) {
		d.textViewSample.setText(data);
		d.num.setText("@:" + pos);
	}

	private static int getIdRV() {
		return R.layout.countable_rv_adp;
	}

	private RelativeLayout createadmob() {

		LinearLayout btn = new LinearLayout(TestAdvancedAdmobActivity.this);

		DisplayMetrics dm = getResources().getDisplayMetrics();

		double density = dm.density * 160;
		double x = Math.pow(dm.widthPixels / density, 2);
		double y = Math.pow(dm.heightPixels / density, 2);
		double screenInches = Math.sqrt(x + y);

		if (screenInches > 8) { // > 728 X 90
			btn.setLayoutParams(new LayoutParams(728, 90));
		} else if (screenInches > 6) { // > 468 X 60
			btn.setLayoutParams(new LayoutParams(468, 60));
		} else { // > 320 X 50
			btn.setLayoutParams(new LayoutParams(320, 50));
		}
		// Start loading the ad.
		DisplayMetrics displaymetrics = new DisplayMetrics();
		final RelativeLayout layout = AdGoogleDisplaySupport.initialSupport(
				this, displaymetrics);
		final double ratio = AdGoogleDisplaySupport
				.ratioMatching(displaymetrics);
		final int ad_height = AdGoogleDisplaySupport
				.defaultHeight(displaymetrics);
		AdGoogleDisplaySupport.panelAdjust(btn, (int) (ad_height * ratio));
		// get display info
		/*
		 * G.display_w = displayMetrics.widthPixels; G.display_h =
		 * displayMetrics.heightPixels; G.scale = Math.max(G.display_w/1280.0f,
		 * G.display_h/800.0f);
		 */
		layout.addView(btn);
		return layout;
	}

	public static class VMoler extends UltimateRecyclerviewViewHolder implements
			View.OnClickListener, View.OnLongClickListener {
		public TextView textViewSample, num;
		public ImageView imageViewSample;
		public ProgressBar progressBarSample;

		public VMoler(View itemView) {
			super(itemView);
			textViewSample = (TextView) itemView.findViewById(R.id.textview);
			num = (TextView) itemView.findViewById(R.id.numb_coun);
			imageViewSample = (ImageView) itemView.findViewById(R.id.imageview);
			progressBarSample = (ProgressBar) itemView
					.findViewById(R.id.progressbar);
			progressBarSample.setVisibility(View.GONE);
		}

		@Override
		public void onClick(@NonNull View v) {
			URLogs.d(textViewSample.getText() + " clicked!");
		}

		@Override
		public boolean onLongClick(@NonNull View v) {
			URLogs.d(textViewSample.getText() + " long clicked!");
			return true;
		}
	}

	/**
	 * example 1 implementation of the switch view
	 */
	private BiAdAdapterSwitcher imple_switch_view(final UltimateRecyclerView rv) {
		final adap adp1 = new adap(createadmob(), false, 10,
				new ArrayList<String>(), new AdmobAdapter.AdviewListener() {
					@Override
					public RelativeLayout onGenerateAdview() {
						return createadmob();
					}
				});
		final regular adp2 = new regular(new ArrayList<String>());
		final BiAdAdapterSwitcher switchable = new BiAdAdapterSwitcher(rv,
				adp2, adp1);
		return switchable;
	}

	private Handler osh = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		toolbar = (Toolbar) findViewById(R.id.tool_bar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		ultimateRecyclerView = (UltimateRecyclerView) findViewById(R.id.ultimate_recycler_view);
		ultimateRecyclerView.setHasFixedSize(false);
		linearLayoutManager = new LinearLayoutManager(this);
		ultimateRecyclerView.setLayoutManager(linearLayoutManager);
		ultimateRecyclerView.setRecylerViewBackgroundColor(Color
				.parseColor("#ffffff"));

		/**
		 * example 2 implementation enhancement of list view
		 */
		bi_sw = imple_switch_view(ultimateRecyclerView)
				.onEnableRefresh(100)
				.EnableAutoDisableLoadMoreByMaxPages()
				.onEnableLoadmore(R.layout.custom_bottom_progressbar, 2000,
						new BiAdAdapterSwitcher.onLoadMore() {
							@Override
							public boolean request_start(int current_page_no,
									int itemsCount, int maxLastVisiblePosition,
									final BiAdAdapterSwitcher bi,
									boolean refresh) {

								osh.postDelayed(new Runnable() {
									@Override
									public void run() {
										bi.load_more_data(SampleDataboxset
												.newList(5));
										// test the max pages
										bi.setMaxPages(10);
									}
								}, 2000);

								return true;
							}
						});

		TextView b = (TextView) findViewById(R.id.del);
		b.setText("with Ad");
		TextView a = (TextView) findViewById(R.id.add);
		a.setText("with out Ad");
		a.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * example 2 implementation enhancement of list view without
				 * advertisement configurations
				 */
				bi_sw.init(false);
			}

		});
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				/**
				 * example 2 implementation enhancement of list view with
				 * advertisement configuration
				 */
				bi_sw.init(true);
			}
		});
		setup_spinner();
	}

	private void remove_all_items() {
		bi_sw.removeALL();
	}

	private void setup_spinner() {
		final Spinner spinner = (Spinner) findViewById(R.id.spinner);
		spinner.setPrompt("test functions");
		ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1);
		spinnerAdapter.add("noth");
		spinnerAdapter.add("remove all");
		spinner.setAdapter(spinnerAdapter);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// ultimateRecyclerView.setItemAnimator(Type.values()[position].getAnimator());
				// ultimateRecyclerView.getItemAnimator().setAddDuration(300);
				// ultimateRecyclerView.getItemAnimator().setRemoveDuration(300);
				if (position == 1) {
					remove_all_items();
					spinner.setSelection(0, true);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});
	}
}
