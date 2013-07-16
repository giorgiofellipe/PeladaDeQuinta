package com.giorgio.peladadequinta2.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.giorgio.peladadequinta2.R;
import com.giorgio.peladadequinta2.model.PlayerModel;

public class RandomizeAdapter extends ArrayAdapter<PlayerModel> {
    private Context context;
    private ArrayList<PlayerModel> playerList;
    
    public RandomizeAdapter(Activity context, ArrayList<PlayerModel> playerlist){
        super(context, R.layout.listview_players, playerlist);
    	this.context = context;
        this.playerList = playerlist;
    }
    
    @Override
    public int getCount() {
        return playerList.size();
    }
 
    @Override
    public PlayerModel getItem(int position) {
        return playerList.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        PlayerModel player = playerList.get(position);

        
        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_item, null);
        
        // Nome
        TextView textName = (TextView)view.findViewById(R.id.playerName);
        textName.setText(player.getName().toUpperCase());
        return view;
    }
}