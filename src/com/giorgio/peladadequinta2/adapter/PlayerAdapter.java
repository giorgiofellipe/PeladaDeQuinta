package com.giorgio.peladadequinta2.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.giorgio.peladadequinta2.R;
import com.giorgio.peladadequinta2.model.PlayerModel;
import com.giorgio.peladadequinta2.provider.ContextoDados;

public class PlayerAdapter extends ArrayAdapter<PlayerModel> {
    private Context context;
    private List<PlayerModel> playerList;
    
    public PlayerAdapter(Activity context, List<PlayerModel> playerlist){
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
        View view = inflater.inflate(R.layout.listview_players, null);
        
        // Nome
        TextView textName = (TextView)view.findViewById(R.id.playerName);
        textName.setText(player.getName().toUpperCase());
        
        // ID
        TextView textId = (TextView)view.findViewById(R.id.playerId);
        textId.setText(String.valueOf(player.getId()));
        textId.setVisibility(textId.INVISIBLE);
        // Quality
        TextView textQuality = (TextView)view.findViewById(R.id.playerQuality);
        String qualityDesc = null;
        switch (player.getQuality()) {
        case 1:
        	qualityDesc = "Regular";
        	break;
        case 2:
        	qualityDesc = "Bom";
        	break;
        case 3:
        	qualityDesc = "Excelente";
        	break;
        }
        textQuality.setText(qualityDesc);
 
        final CheckBox check = (CheckBox) view.findViewById(R.id.playerStatus);
        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {
					PlayerModel element = (PlayerModel) check.getTag();
					element.setStatus(buttonView.isChecked());

					ContextoDados db = new ContextoDados(buttonView.getContext());
					db.updateStatus(element.getId(), element.getStatus());
				}
		    });
        check.setTag(playerList.get(position));
        check.setChecked(player.getStatus());
        
        ImageView imageGoalkeeper = (ImageView) view.findViewById(R.id.imageGoalkeeper);
        if (player.getIsGoalkeeper()) {
        	imageGoalkeeper.setVisibility(imageGoalkeeper.VISIBLE);
        }
        return view;
    }
}