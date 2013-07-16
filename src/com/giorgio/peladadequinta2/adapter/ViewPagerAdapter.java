package com.giorgio.peladadequinta2.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
 
	  private List<Fragment> mFragments;
	
	  public ViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		  super(fm);
	    
		  mFragments = fragments;
	  }
	
	  @Override
	  public Fragment getItem(int i) {  
		  return mFragments.get(i);
	  }
	
	  @Override 
	  public int getCount() {
		  return mFragments.size();
	  }
}
