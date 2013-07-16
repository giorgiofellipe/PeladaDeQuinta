package com.giorgio.peladadequinta2.provider;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.giorgio.peladadequinta2.R;
import com.giorgio.peladadequinta2.model.PlayerModel;
import com.giorgio.peladadequinta2.util.StringUtils;

public class ContextoDados extends SQLiteOpenHelper {

	/** O nome do arquivo de base de dados no sistema de arquivos */
	private static final String NOME_BD = "peladadequinta";
	/** A versão da base de dados que esta classe compreende. */
	private static final int VERSAO_BD = 7;
	private static final String LOG_TAG = "peladadequinta";
	/** Mantém rastreamento do contexto que nós podemos carregar SQL */
	private final Context contexto;
	
	public ContextoDados(Context context) {
		super(context, NOME_BD, null, VERSAO_BD);
		this.contexto = context;
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String[] sql = contexto.getString(R.string.ContextoDados_onCreate).split("\n");
		db.beginTransaction();
		
		try {
			// Cria a tabela e testa os dados
			ExecutarComandosSQL(db, sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Erro ao criar as tabelas e testar os dados", e.toString());
		} finally {
			db.endTransaction();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(LOG_TAG, "Atualizando a base de dados da versão " + oldVersion + " para " + newVersion + ".");
		String[] sql = contexto.getString(R.string.ContextoDados_onUpgrade).split("\n");
		db.beginTransaction();
		
		try {
			ExecutarComandosSQL(db, sql);
			db.setTransactionSuccessful();
		} catch (SQLException e) {
			Log.e("Erro ao atualizar as tabelas e testar os dados", e.toString());
			throw e;
		} finally {
			db.endTransaction();
		}
		
		onCreate(db);
	}
	
	/**
	* Executa todos os comandos SQL passados no vetor String[]
	* @param db A base de dados onde os comandos serão executados
	* @param sql Um vetor de comandos SQL a serem executados
	*/
	private void ExecutarComandosSQL(SQLiteDatabase db, String[] sql) {
		for( String s : sql )
			if (s.trim().length()>0)	
				db.execSQL(s);
	}
	
	public PlayersCursor getAllPlayers() {
		String sql = PlayersCursor.CONSULTA;
		SQLiteDatabase bd = getReadableDatabase();
		PlayersCursor cc = (PlayersCursor) bd.rawQueryWithFactory(new PlayersCursor.Factory(), sql, null, null);
		cc.moveToFirst();
		return cc;
	}
	
	public PlayersCursor getAllPlayersAtivos() {
		String sql = PlayersCursor.ATIVOS;
		SQLiteDatabase bd = getReadableDatabase();
		PlayersCursor cc = (PlayersCursor) bd.rawQueryWithFactory(new PlayersCursor.Factory(), sql, null, null);
		cc.moveToFirst();
		return cc;
	}
	

	public PlayersCursor getAllGoleirosAtivos() {
		String sql = PlayersCursor.GOLEIROS_ATIVOS;
		SQLiteDatabase bd = getReadableDatabase();
		PlayersCursor cc = (PlayersCursor) bd.rawQueryWithFactory(new PlayersCursor.Factory(), sql, null, null);
		cc.moveToFirst();
		return cc;
	}
	
	public PlayersCursor getAllRegularesAtivos() {
		String sql = PlayersCursor.REGULARES_ATIVOS;
		SQLiteDatabase bd = getReadableDatabase();
		PlayersCursor cc = (PlayersCursor) bd.rawQueryWithFactory(new PlayersCursor.Factory(), sql, null, null);
		cc.moveToFirst();
		return cc;
	}

	public PlayersCursor getAllBonsAtivos() {
		String sql = PlayersCursor.BONS_ATIVOS;
		SQLiteDatabase bd = getReadableDatabase();
		PlayersCursor cc = (PlayersCursor) bd.rawQueryWithFactory(new PlayersCursor.Factory(), sql, null, null);
		cc.moveToFirst();
		return cc;
	}

	public PlayersCursor getAllExcelentesAtivos() {
		String sql = PlayersCursor.EXCELENTES_ATIVOS;
		SQLiteDatabase bd = getReadableDatabase();
		PlayersCursor cc = (PlayersCursor) bd.rawQueryWithFactory(new PlayersCursor.Factory(), sql, null, null);
		cc.moveToFirst();
		return cc;
	}
	
	public ArrayList<PlayerModel> getGoleiros() {
		ArrayList<PlayerModel> aPlayers = new ArrayList<PlayerModel>();
		/** SEPARANDO OS GOLEIROS */
		PlayersCursor pc = getAllGoleirosAtivos();
		pc.moveToPosition(-1);
		while (pc.moveToNext()) {
			PlayerModel Player = new PlayerModel(
					pc.getID(), 
					pc.getName(), 
					pc.getQuality(), 
					pc.getStatus(), 
					pc.getIsGoalKeeper());
			aPlayers.add(Player);
		}
		return aPlayers;
	}
	
	public ArrayList<PlayerModel> getRegulares() {
		ArrayList<PlayerModel> aPlayers = new ArrayList<PlayerModel>();
		/** SEPARANDO OS REGULARES */
		PlayersCursor pc = getAllRegularesAtivos();
		pc.moveToPosition(-1);
		while (pc.moveToNext()) {
			PlayerModel Player = new PlayerModel(
					pc.getID(), 
					pc.getName(), 
					pc.getQuality(), 
					pc.getStatus(), 
					pc.getIsGoalKeeper());
			aPlayers.add(Player);
		}
		return aPlayers;
	}
	
	public ArrayList<PlayerModel> getBons() {
		ArrayList<PlayerModel> aPlayers = new ArrayList<PlayerModel>();
		/** SEPARANDO OS REGULARES */
		PlayersCursor pc = getAllBonsAtivos();
		pc.moveToPosition(-1);
		while (pc.moveToNext()) {
			PlayerModel Player = new PlayerModel(
					pc.getID(), 
					pc.getName(), 
					pc.getQuality(), 
					pc.getStatus(), 
					pc.getIsGoalKeeper());
			aPlayers.add(Player);
		}
		return aPlayers;
	}
	
	public ArrayList<PlayerModel> getExcelentes() {
		ArrayList<PlayerModel> aPlayers = new ArrayList<PlayerModel>();
		/** SEPARANDO OS REGULARES */
		PlayersCursor pc = getAllExcelentesAtivos();
		pc.moveToPosition(-1);
		while (pc.moveToNext()) {
			PlayerModel Player = new PlayerModel(
					pc.getID(), 
					pc.getName(), 
					pc.getQuality(), 
					pc.getStatus(), 
					pc.getIsGoalKeeper());
			aPlayers.add(Player);
		}
		return aPlayers;
	}
	
	
	
	public long addPlayer(String nome, int quality, boolean isGoalkeeper) {
		SQLiteDatabase db = getReadableDatabase();
		int goalkeeper = (isGoalkeeper) ? 1 : 0;
		try {
			ContentValues initialValues = new ContentValues();
			initialValues.put("name", nome);
			initialValues.put("quality", quality);
			initialValues.put("status", 1);
			initialValues.put("isgoalkeeper", goalkeeper);
			return db.insert("players", null, initialValues);
		} finally {
			db.close();
		}
	}

	public long delPlayer(int id) {
		if (id > 0) {
			SQLiteDatabase db = getReadableDatabase();
			String where = "ID = " + id;
			try {
				return db.delete("players", where, null);
			} finally {
				db.close();
			}
		} else {
			return 0;
		}
	}
	
	public long updateStatus(int id, boolean status) {
		if (id > 0) {
			SQLiteDatabase db = getReadableDatabase();
			
			String where = "ID = " + id;
			ContentValues values = new ContentValues();
			int st = (status) ? 1 : 0;
			values.put("status", st);
			try {
				return db.update("players", values, where, null);
			} finally {
				db.close();
			}
		} else {
			return 0;
		}
	}
	
/************** HISTORY *********************/	
	
	public HistoryCursor getHistory() {
		String sql = HistoryCursor.CONSULTA;
		SQLiteDatabase bd = getReadableDatabase();
		HistoryCursor cc = (HistoryCursor) bd.rawQueryWithFactory(new HistoryCursor.Factory(), sql, null, null);
		cc.moveToFirst();
		return cc;
	}
	
	public boolean addHistory(ArrayList<PlayerModel> aTime1, ArrayList<PlayerModel> aTime2) {
		String sTime1 = "";
		String[] aNomesTime1 = new String[aTime1.size()];
		String sTime2 = "";
		String[] aNomesTime2 = new String[aTime2.size()];
		int c = 0;
		for (PlayerModel Player: aTime1) {
			aNomesTime1[c] = Player.getName().toUpperCase();
			c++;
		}
		c = 0;
		for (PlayerModel Player: aTime2) {
			aNomesTime2[c] = Player.getName().toUpperCase();
			c++;
		}
		sTime1 = StringUtils.join(aNomesTime1,", ");
		sTime2 = StringUtils.join(aNomesTime2,", ");
		
		SQLiteDatabase db = getReadableDatabase();
		try {
			ContentValues initialValues = new ContentValues();
			initialValues.put("firstteam", sTime1);
			initialValues.put("secondteam", sTime2);
			db.insert("history", null, initialValues);
			return true;
		} finally {
			db.close();
		}
	}

	public int delHistory(java.util.Date matchDate) {
		SQLiteDatabase db = getReadableDatabase();
		String where = "matchdate = '" + matchDate +"'";
		try {
			return db.delete("history", where, null);
		} finally {
			db.close();
		}
	}
	
}