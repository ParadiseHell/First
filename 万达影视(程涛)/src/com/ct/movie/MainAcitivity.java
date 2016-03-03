package com.ct.movie;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ct.movie.data.MovieDBOpenHelper;
import com.ct.movie.data.MovieData;
import com.ct.movie.ui.IngFragment;
import com.ct.movie.ui.PastFragment;
import com.ct.movie.ui.WillFragment;

public class MainAcitivity extends FragmentActivity implements OnClickListener {
	private ViewPager viewPager;

	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mFragments;
	Fragment tabIng;
	Fragment tabWill;
	Fragment tabPast;

	private Button ingButton;
	private Button willButton;
	private Button pastButton;
	
	private ImageView galleryImage;
	
	private TextView chooseCity;
	
	private ImageButton popWindow;
	private SQLiteOpenHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initView();
		initEvent();
	}

	private void initEvent() {
		ingButton.setOnClickListener(this);
		willButton.setOnClickListener(this);
		pastButton.setOnClickListener(this);
		galleryImage.setOnClickListener(this);
		chooseCity.setOnClickListener(this);
		popWindow.setOnClickListener(this);
	}

	private void initView() {
		MovieDBOpenHelper helper = new MovieDBOpenHelper(this);
		helper.getWritableDatabase();
		popWindow = (ImageButton) findViewById(R.id.im_pop_window);
		ingButton = (Button) findViewById(R.id.zzsy);
		willButton = (Button) findViewById(R.id.jjsy);
		pastButton = (Button) findViewById(R.id.yxxyp);
		
		chooseCity = (TextView) findViewById(R.id.tv_choose_city);

		galleryImage = (ImageView) findViewById(R.id.iv_main_top_slide);
		
		viewPager = (ViewPager) findViewById(R.id.viewpager);

		SetViewPager();
	}

	private void SetViewPager() {
		mFragments = new ArrayList<Fragment>();
		tabIng = new IngFragment();
		tabWill = new WillFragment();
		tabPast = new PastFragment();
		mFragments.add(tabIng);
		mFragments.add(tabWill);
		mFragments.add(tabPast);
		setCityNameToViewPager();

		mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mFragments.size();
			}

			@Override
			public Fragment getItem(int arg0) {
				// TODO Auto-generated method stub
				return mFragments.get(arg0);
			}
		};
		viewPager.setAdapter(mAdapter);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zzsy:
			setSlect(0);
			break;
		case R.id.jjsy:
			setSlect(1);
			break;
		case R.id.yxxyp:
			setSlect(2);
			break;
		case R.id.iv_main_top_slide:
			goToOtherActivity(GalleryActivity.class);
			break;
		case R.id.tv_choose_city:
			chooseCityAlert();
			break;
		case R.id.im_pop_window:
			showPopWindow();
			break;
		default:
			break;
		}
	}

	@SuppressLint({ "InflateParams", "InlinedApi" })
	private void showPopWindow() {
		
		View contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_window, null);
		final PopupWindow popupWindow = new PopupWindow(contentView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
		Button popWindowMoView = (Button) contentView.findViewById(R.id.pop_window_movie);
		popWindowMoView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MainAcitivity.this, MovieCinemaListAcitivity.class);
				startActivity(intent);
				popupWindow.dismiss();
			}
		});
		Button popWindowAddData = (Button) contentView.findViewById(R.id.pop_window_add_data);
		popWindowAddData.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addData();
				Toast.makeText(getApplicationContext(), "添加数据成功~~", Toast.LENGTH_SHORT).show();
				popupWindow.dismiss();
			}
		});
		
		Button popWindowUser = (Button) contentView.findViewById(R.id.pop_window_user);
		popWindowUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainAcitivity.this, UserAcitivity.class);
				startActivity(intent);
				popupWindow.dismiss();
			}
		});

        popupWindow.setTouchable(true);

        popupWindow.setTouchInterceptor(new OnTouchListener() {

			@SuppressLint("ClickableViewAccessibility")
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
            
        });

        popupWindow.setBackgroundDrawable(getResources().getDrawable(
                R.drawable.pop_window_bg));
        popupWindow.showAsDropDown(popWindow);
	}

	private void setSlect(int i) {
		viewPager.setCurrentItem(i);
	}
	private void goToOtherActivity(@SuppressWarnings("rawtypes") Class other){
		Intent intent = new Intent(getApplicationContext(), other);
		intent.putExtra("city", chooseCity.getText());
		startActivity(intent);
	}
	private void chooseCityAlert(){
		Dialog  alertDialog = new AlertDialog.Builder(this).setTitle("为您定位").setMessage("利用位置获取当前城市影视信息,本操作不涉及个人隐私").setPositiveButton("确定", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(MainAcitivity.this, ChooseCityActivity.class);
				startActivityForResult(intent, 0);
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		}).create();
		
		alertDialog.show();		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (data == null) {
			//setCityNameToViewPager();
		}
		else if (resultCode == 0) {
			chooseCity.setText(data.getStringExtra("city"));			
		}		
	}
	private void addData(){
		MovieData mData = new MovieData();
		helper = new MovieDBOpenHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();
		for (int i = 0; i <1; i++) {
			for (int j = 0; j < mData.getMovieName().length; j++) {
				ContentValues values = new ContentValues();
				values.put("movieName", mData.getMovieName()[j]);
				values.put("movieCommentNumber", mData.getMovieCommentNumber()[j]);
				values.put("movieImage", mData.getMovieImage()[j]);
				values.put("movieScore", mData.getMovieScore()[j]);
				values.put("movieExactContent", mData.getMovieExactContent()[j]);
				values.put("movieInfoContent", mData.getMovieInfoContent()[j]);
				values.put("movieTag", mData.getMovieTag()[j]);
				values.put("movieInfoTime", mData.getMovieInfoTime()[j]);
				values.put("movieSimpleContent", mData.getMovieSimpleContent()[j]);
				db.insert("movie", null, values);				
			}
		}
		db.close();
	}
	private void setCityNameToViewPager() {
		Bundle bundle = new Bundle();
		bundle.putString("city", chooseCity.getText().toString());
		tabIng.setArguments(bundle);
		tabWill.setArguments(bundle);
		tabPast.setArguments(bundle);
	}
}
