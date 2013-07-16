package com.giorgio.peladadequinta2.ui.fragments;


import java.util.ArrayList;
import java.util.Date;
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

import com.giorgio.peladadequinta2.adapter.HistoryAdapter;
import com.giorgio.peladadequinta2.model.HistoryModel;
import com.giorgio.peladadequinta2.provider.ContextoDados;
import com.giorgio.peladadequinta2.provider.HistoryCursor;
import com.giorgio.peladadequinta2.util.StringUtils;

public class HistoryFragment extends ListFragment {
	List<HistoryModel> history = new ArrayList<HistoryModel>();
	HistoryAdapter adapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
		initHistory();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
	
	public void deleteHistory(Date matchDate) {
		ContextoDados db = new ContextoDados(this.getActivity());
		db.delHistory(matchDate);
	}

	@Override
	public void onActivityCreated(Bundle savedState) {
	    super.onActivityCreated(savedState);
	
	    getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

	        @Override
	        public boolean onItemLongClick(final AdapterView<?> arg0, View arg1, int position, long arg3) {
	        	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	        	builder
	        	.setTitle("Delete")
	        	.setMessage("Confirma?")
	        	.setIcon(android.R.drawable.ic_dialog_alert)
	        	.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
	        	    public void onClick(DialogInterface dialog, int which) {			      	
	        	    	//Yes button clicked, do something
	    	        	//deleteHistory(adapter.getItem(position).getMatchDate());
	    	        	//initHistory();
	        	    }
	        	})
	        	.setNegativeButton("Não", null)
	        	.show();
	        	return true;
	        }
	    });
	}
	
	public void initHistory() {
		ContextoDados db = new ContextoDados(this.getActivity());
		HistoryCursor hc = db.getHistory();
		history = new ArrayList<HistoryModel>();
		hc.moveToPosition(-1);
		while (hc.moveToNext()) {
			history.add(new HistoryModel(
					StringUtils.stringToDate(hc.getMatchDate(), "yyyy-MM-dd"), 
					hc.getFirstTeam(), 
					hc.getSecondTeam()));
		}
		
		adapter = new HistoryAdapter(this.getActivity(), history);
		
		setListAdapter(adapter);
	}
}

