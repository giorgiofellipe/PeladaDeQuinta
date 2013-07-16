package com.giorgio.peladadequinta2.ui.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.giorgio.peladadequinta2.R;
import com.giorgio.peladadequinta2.provider.ContextoDados;

public class AddPlayerActivity extends Activity {

	EditText playerName, playerStatus;
	CheckBox playerIsGoalkeeper;
	RatingBar playerQuality;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.add_player);
        playerName = (EditText)findViewById(R.id.playerName);
        playerQuality = (RatingBar)findViewById(R.id.playerQuality);
        playerIsGoalkeeper = (CheckBox)findViewById(R.id.playerIsGoalkeeper);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_player, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_ok:
	        	initAddPlayer();
	            return true;
	        case R.id.menu_cancel:
	        	finish();
	            return true;    
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public boolean initAddPlayer() {
		if (playerName.getText().toString().isEmpty()) {
			Toast.makeText(this, R.string.errorRatingBar_EmptyName, Toast.LENGTH_LONG).show();
			return false;
		}
		if ((int) playerQuality.getRating() == 0) {
			Toast.makeText(this, R.string.errorRatingBar_Zero, Toast.LENGTH_LONG).show();
			return false;
		}
		ContextoDados db = new ContextoDados(this);
		db.addPlayer(playerName.getText().toString(), (int) playerQuality.getRating(), playerIsGoalkeeper.isChecked());
		setContentView(R.layout.add_player);
		this.recreate();
		return true;
	}
	
}
