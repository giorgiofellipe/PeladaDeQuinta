package com.giorgio.peladadequinta2.ui.fragments;



import android.app.Fragment;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Chronometer.OnChronometerTickListener;

import com.giorgio.peladadequinta2.R;

public class ChronometerFragment extends Fragment {
	private Chronometer cChronometer;
	boolean isClickPause = false;
	long tempoQuandoParado = 0;
	private SoundPool soundPool;
    private int soundID;
    boolean loaded = false;
    float volume;
    EditText howManyTime;
    CheckBox playSound;
    boolean bPlaySound;
    int time;
    
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
        
		View view = inflater.inflate(R.layout.chronometer_fragment, container, false);
		// Inflate the layout for this fragment
	    getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
	    soundPool.setOnLoadCompleteListener(new OnLoadCompleteListener() {
	      @Override
	      public void onLoadComplete(SoundPool soundPool, int sampleId,
	          int status) {
	        loaded = true;
	      }
	    });
	    soundID = soundPool.load(getActivity(), R.raw.alert, 1);
		return view;
    }
	
	@Override
	public void onViewCreated(final View view, final Bundle savedInstanceState) {
	    howManyTime = (EditText) getView().findViewById(R.id.howManyTime);
	    playSound   = (CheckBox) getView().findViewById(R.id.playSound);
	    super.onViewCreated(view, savedInstanceState);
		cChronometer = (Chronometer) getView().findViewById(R.id.chronometer);
        ((Button) getView().findViewById(R.id.start_button)).setOnClickListener(
	        		new OnClickListener() {
	        			@Override
	        		    public void onClick(View v) {
	        		    	if(isClickPause){ 
	        		    		cChronometer.setBase(SystemClock.elapsedRealtime() + tempoQuandoParado);
	        		    		cChronometer.start();
	        		    		tempoQuandoParado = 0;
	        		    		isClickPause = false;
	        		    	} else {
	        		    		cChronometer.setBase(SystemClock.elapsedRealtime());
	        		    		cChronometer.start();
	        		    		tempoQuandoParado = 0;
	        		    	}
	        		    }
	        		}
        		);
        
        cChronometer.setOnChronometerTickListener(new OnChronometerTickListener() {
			@Override
			public void onChronometerTick(Chronometer chronometer) {
				time = Integer.parseInt(howManyTime.getText().toString());
			    if (!(time > 0)) {
			    	time = 15;
			    }
			    bPlaySound  = playSound.isChecked();
				
			    if ((((SystemClock.elapsedRealtime() - chronometer.getBase())/1000)/60) >= time) {
					cChronometer.stop();
			    	cChronometer.setText("00:00");
			    	if (bPlaySound) {
					    AudioManager audioManager = (AudioManager) getActivity().getSystemService(getActivity().AUDIO_SERVICE);
					    float actualVolume = (float) audioManager
					          .getStreamVolume(AudioManager.STREAM_MUSIC);
					    float maxVolume = (float) audioManager
					          .getStreamMaxVolume(AudioManager.STREAM_MUSIC);
					    volume = actualVolume / maxVolume;
					    if (loaded) {
					    	soundPool.play(soundID, volume, volume, 1, 0, 1f);
					    }
			    	}
				    cChronometer.setBase(SystemClock.elapsedRealtime());
		    		cChronometer.start();
				}
			}
		});
        
        ((Button) getView().findViewById(R.id.pause_button)).setOnClickListener(new OnClickListener() {
        				@Override
					    public void onClick(View v) {
					    	if(isClickPause == false){ 
					    		tempoQuandoParado = cChronometer.getBase() - SystemClock.elapsedRealtime();
					    	}
					    	cChronometer.stop();
					    	isClickPause = true;   
			    		}
					}
				);
        ((Button) getView().findViewById(R.id.stop_button)).setOnClickListener(new OnClickListener() {
        				@Override
					    public void onClick(View v) {
					    	cChronometer.stop();
					    	cChronometer.setText("00:00");
					    	tempoQuandoParado = 0;
					    }
					}
				);
	}
}
