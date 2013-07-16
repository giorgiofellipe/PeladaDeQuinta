package com.giorgio.peladadequinta2.ui.fragments;


import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

import com.giorgio.peladadequinta2.adapter.PlayerAdapter;
import com.giorgio.peladadequinta2.model.PlayerModel;
import com.giorgio.peladadequinta2.provider.ContextoDados;
import com.giorgio.peladadequinta2.provider.PlayersCursor;

public class PlayersFragment extends ListFragment {
	List<PlayerModel> players = new ArrayList<PlayerModel>();
	PlayerAdapter adapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.players_fragment, container, false);
		initPlayers();
		
        return super.onCreateView(inflater, container, savedInstanceState);   
    }
	
	@Override
	public void onResume() {
		super.onResume();
		
		initPlayers();
	}
	
	@Override
	public void onStart() {
	    super.onStart();
	}
	
	public void deletePlayer(int id) {
		ContextoDados db = new ContextoDados(this.getActivity());
		db.delPlayer(id);
	}
	
	public void togglePlayerStatus(int id, boolean status) {
		ContextoDados db = new ContextoDados(this.getActivity());
		db.updateStatus(id, status);
	}
	
	public void initPlayers() {
		ContextoDados db = new ContextoDados(this.getActivity());
		PlayersCursor pc = db.getAllPlayers();
		players = new ArrayList<PlayerModel>();
		pc.moveToPosition(-1);
		while (pc.moveToNext()) {
			players.add(new PlayerModel(
					pc.getID(), 
					pc.getName(), 
					pc.getQuality(), 
					pc.getStatus(), 
					pc.getIsGoalKeeper()));
		}
		
		adapter = new PlayerAdapter(this.getActivity(), players);
		
		setListAdapter(adapter);
	}
	
	@Override
    public void onListItemClick(ListView l, View v, int position, long id) {
    	//System.out.println(position);
	}
	
	@Override
	public void onActivityCreated(Bundle savedState) {
	    super.onActivityCreated(savedState);
	
	    getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

	        @Override
	        public boolean onItemLongClick(final AdapterView<?> arg0, View arg1, final int position, long arg3) {
	        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        	builder
	        	.setTitle("Delete")
	        	.setMessage("Confirma?")
	        	.setIcon(android.R.drawable.ic_dialog_alert)
	        	.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
	        	    public void onClick(DialogInterface dialog, int which) {			      	
	        	    	//Yes button clicked, do something
	        	    	deletePlayer(adapter.getItem(position).getId());
	    	        	initPlayers();
	        	    }
	        	})
	        	.setNegativeButton("Não", null)
	        	.show();
	        	return true;
	        }
	    });
	}
}
