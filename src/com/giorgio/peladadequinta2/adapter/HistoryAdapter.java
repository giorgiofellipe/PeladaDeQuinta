package com.giorgio.peladadequinta2.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.giorgio.peladadequinta2.R;
import com.giorgio.peladadequinta2.model.HistoryModel;
import com.giorgio.peladadequinta2.util.StringUtils;

public class HistoryAdapter extends ArrayAdapter<HistoryModel> {
    private Context context;
    private List<HistoryModel> historyList;
    
    public HistoryAdapter(Activity context, List<HistoryModel> historylist){
        super(context, R.layout.listview_histories, historylist);
    	this.context = context;
        this.historyList = historylist;
    }
    
    @Override
    public int getCount() {
        return historyList.size();
    }
 
    @Override
    public HistoryModel getItem(int position) {
        return historyList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        HistoryModel history = historyList.get(position);
        
        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listview_histories, null);
        
        // Data
        TextView matchDate = (TextView)view.findViewById(R.id.matchDate);
        matchDate.setText(StringUtils.dateToString(history.getMatchDate(), "dd/MM/yyyy"));
        
        //First Team
        TextView firstTeam = (TextView)view.findViewById(R.id.firstTeam);
        firstTeam.setText(history.getFirstTeam());
        
        //Second Team
        TextView secondTeam = (TextView)view.findViewById(R.id.secondTeam);
        secondTeam.setText(history.getSecondTeam());
        
        return view;
    }
}