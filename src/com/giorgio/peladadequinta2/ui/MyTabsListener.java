package com.giorgio.peladadequinta2.ui;


import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.giorgio.peladadequinta2.R;

public class MyTabsListener implements TabListener {
	public Fragment fragment;
	 
	public MyTabsListener(Fragment fragment) {
		this.fragment = fragment;
	}
	
	
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		//Toast.makeText(MainActivity.appContext, "Reselected!", Toast.LENGTH_LONG).show();
		ft.replace(R.id.fragment_container, fragment);
	}
	 
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fragment);
	}
	 
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fragment);
	}
 
}
