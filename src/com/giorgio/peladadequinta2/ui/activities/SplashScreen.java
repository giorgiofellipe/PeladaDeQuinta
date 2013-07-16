package com.giorgio.peladadequinta2.ui.activities;

import com.giorgio.peladadequinta2.MainActivity;
import com.giorgio.peladadequinta2.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity implements Runnable {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove o título
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove a barra de notificação
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash_screen);

		Handler handler = new Handler();
		handler.postDelayed(this, 3000);
	}

	public void run(){
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}
	
	@Override
	public void onAttachedToWindow() {
	    super.onAttachedToWindow();
	    Window window = getWindow();
	    window.setFormat(PixelFormat.RGBA_8888);
	}
}
