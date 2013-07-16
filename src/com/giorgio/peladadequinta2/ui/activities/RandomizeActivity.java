package com.giorgio.peladadequinta2.ui.activities;


import java.util.ArrayList;
import java.util.Collections;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.giorgio.peladadequinta2.R;
import com.giorgio.peladadequinta2.adapter.RandomizeAdapter;
import com.giorgio.peladadequinta2.model.PlayerModel;
import com.giorgio.peladadequinta2.provider.ContextoDados;


public class RandomizeActivity extends Activity {
	ContextoDados db = new ContextoDados(this);
	int ultimoTime = 0; //0 = time1 E 1 = time2
	int tentativas = 0;
	ArrayList<PlayerModel> aTime1 			= new ArrayList<PlayerModel>();
	ArrayList<PlayerModel> aTime2 			= new ArrayList<PlayerModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.randomize);
        randomize();
	}
	
	public void randomize() {
		aTime1 			= new ArrayList<PlayerModel>();
		aTime2 			= new ArrayList<PlayerModel>();
		
		final ListView listview  = (ListView) findViewById(R.id.playersTeam1);
		final ListView listview2 = (ListView) findViewById(R.id.playersTeam2);
		
		ArrayList<PlayerModel> aGoleiros 	= db.getGoleiros();
		ArrayList<PlayerModel> aRegulares 	= db.getRegulares();
		ArrayList<PlayerModel> aBons 		= db.getBons();
		ArrayList<PlayerModel> aExcelentes 	= db.getExcelentes();
		
		Collections.shuffle(aGoleiros);
		Collections.shuffle(aRegulares);
		Collections.shuffle(aBons);
		Collections.shuffle(aExcelentes);
		
		for (int i=0; i < aGoleiros.size(); i++) {
			if (getMelhorTime(aTime1, aTime2) == 2) {
				aTime1.add(aGoleiros.get(i));
			} else {
				aTime2.add(aGoleiros.get(i));
			}
		}

		for (int i=0; i < aExcelentes.size(); i++) {
			if (getMelhorTime(aTime1, aTime2) == 2) {
				aTime1.add(aExcelentes.get(i));
			} else {
				aTime2.add(aExcelentes.get(i));
			}
		}
		
		for (int i=0; i < aBons.size(); i++) {
			if (getMelhorTime(aTime1, aTime2) == 2) {
				aTime1.add(aBons.get(i));
			} else {
				aTime2.add(aBons.get(i));
			}
		}

		for (int i=0; i < aRegulares.size(); i++) {
			if (getMelhorTime(aTime1, aTime2) == 2) {
				aTime1.add(aRegulares.get(i));
			} else {
				aTime2.add(aRegulares.get(i));
			}
		}
		
		/* SE FICOU DESEQUILIBRADO, RESORTEIA*/
		if ((getDiferencaPesoTimes(aTime1, aTime2) > 2 || getDiferencaPesoTimes(aTime1, aTime2) < -2) && tentativas <= 3) {
			tentativas++;
			randomize();
		}
		
		//ArrayAdapter<PlayerModel> adapter 	= new ArrayAdapter<PlayerModel>(this, R.layout.list_item, aTime1);
		ArrayAdapter<PlayerModel> adapter 	= new RandomizeAdapter(this, aTime1);
		listview.setAdapter(adapter);
		//ArrayAdapter<PlayerModel> adapter2 	= new ArrayAdapter<PlayerModel>(this, R.layout.list_item, aTime2);
		ArrayAdapter<PlayerModel> adapter2 	= new RandomizeAdapter(this, aTime2);
		listview2.setAdapter(adapter2);
	}
	
	public static ArrayList<PlayerModel> merge(ArrayList<PlayerModel>... arrays) {
	    // Count the number of arrays passed for merging and the total size of resulting array
	    int arrCount = 0;
	    int count = 0;
	    for (ArrayList<PlayerModel> array: arrays) {
	    	arrCount++;
	    	//count += array.length;
	    	int i=0;
	    	for (i=0; i < array.size(); i++) {
	    		if (array.get(i) == null) {
	    			break;
	    		}
	    		count++;
	    	}
	    }
	    System.out.println("Arrays passed for merging : "+arrCount);
	    System.out.println("Array size of resultig array : "+count);
	 
	    // Create new array and copy all array contents
	    ArrayList<PlayerModel> mergedArray = new ArrayList<PlayerModel>(); 
	    for (ArrayList<PlayerModel> array: arrays) {
	    	int i=0;
	    	for (i=0; i < array.size(); i++) {
	    		if (array.get(i) == null) {
	    			break;
	    		}
	    		mergedArray.add(array.get(i));
	    	}
	    	
	    }
	    return mergedArray;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.randomize, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.menu_ok:
	        	acceptTeams();
	            return true;
	        case R.id.menu_cancel:
	        	finish();
	            return true;    
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	
	public void acceptTeams() {
		db.addHistory(aTime1, aTime2);
		finish();
	}
	
	
	public int getPesoTime(ArrayList<PlayerModel> aTime) {
		int peso = 0;
		for (PlayerModel Player: aTime) {
			peso += Player.getQuality();
		}
		return peso;
	}
	
	public int getDiferencaPesoTimes(ArrayList<PlayerModel> aTime1, ArrayList<PlayerModel> aTime2) {
		return getPesoTime(aTime1) - getPesoTime(aTime2);
	}
	

	public int getMelhorTime(ArrayList<PlayerModel> aTime1, ArrayList<PlayerModel> aTime2) {
		return (getPesoTime(aTime1) > getPesoTime(aTime2)) ? 1 : 2;
	}
}
