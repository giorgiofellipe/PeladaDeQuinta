package com.giorgio.peladadequinta2;


import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.giorgio.peladadequinta2.ui.MyTabsListener;
import com.giorgio.peladadequinta2.ui.activities.AddPlayerActivity;
import com.giorgio.peladadequinta2.ui.activities.RandomizeActivity;
import com.giorgio.peladadequinta2.ui.fragments.ChronometerFragment;
import com.giorgio.peladadequinta2.ui.fragments.HistoryFragment;
import com.giorgio.peladadequinta2.ui.fragments.PlayersFragment;

public class MainActivity extends Activity {
	public static Context appContext;
	Button btnRandomize;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//ActionBar gets initiated
        ActionBar actionbar = getActionBar();
        //Tell the ActionBar we want to use Tabs.
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        //initiating both tabs and set text to it.
        ActionBar.Tab PlayersTab = actionbar.newTab().setText("Jogadores");
        ActionBar.Tab HistoryTab = actionbar.newTab().setText("Histórico");
        ActionBar.Tab ChronometerTab = actionbar.newTab().setText("Cronômetro");
        
        //create the two fragments we want to use for display content
        Fragment PlayersFragment = new PlayersFragment();
        Fragment HistoryFragment = new HistoryFragment();
        Fragment ChronometerFragment = new ChronometerFragment();
        
        //set the Tab listener. Now we can listen for clicks.
        PlayersTab.setTabListener(new MyTabsListener(PlayersFragment));
        HistoryTab.setTabListener(new MyTabsListener(HistoryFragment));
        ChronometerTab.setTabListener(new MyTabsListener(ChronometerFragment));
        
        //add the two tabs to the actionbar
        actionbar.addTab(PlayersTab);
        actionbar.addTab(HistoryTab);
        actionbar.addTab(ChronometerTab);
        
        if(savedInstanceState != null) {
            int index = savedInstanceState.getInt("index");
            getActionBar().setSelectedNavigationItem(index);
        }
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    int i = getActionBar().getSelectedNavigationIndex();
	    outState.putInt("index", i);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_addplayer:
	        	startActivity(new Intent(MainActivity.this, AddPlayerActivity.class));
	            return true;
	        case R.id.menu_random:
	        	startActivity(new Intent(MainActivity.this, RandomizeActivity.class));
	            return true;    
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
