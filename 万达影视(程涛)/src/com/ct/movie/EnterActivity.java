package com.ct.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class EnterActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_enter);
		initEvent();
		
	}

	private void initEvent() {
		// TODO Auto-generated method stub
		enterMainAcitvity();
	}
	private void enterMainAcitvity(){
			new Thread(){
				public void run() {
					try {
						Thread.sleep(3000);
						Intent intent = new Intent(getApplicationContext(),MainAcitivity.class);
						startActivity(intent);
						finish();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
		}
}
