package com.giorgio.peladadequinta2.provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

public class PlayersCursor extends SQLiteCursor {
	
	static final String CONSULTA = "SELECT ID, name, quality, status, isgoalkeeper FROM players ORDER BY isgoalkeeper desc, quality, name, ID ";
	static final String ATIVOS = "SELECT ID, name, quality, status, isgoalkeeper FROM players WHERE status = 1 ORDER BY isgoalkeeper desc, quality, name, ID ";
	static final String GOLEIROS_ATIVOS = "SELECT ID, name, quality, status, isgoalkeeper FROM players WHERE status = 1 and isgoalkeeper = 1 ORDER BY isgoalkeeper desc, quality, name, ID ";
	static final String REGULARES_ATIVOS = "SELECT ID, name, quality, status, isgoalkeeper FROM players WHERE status = 1 and quality = 1 and isgoalkeeper = 0 ORDER BY isgoalkeeper desc, quality, name, ID ";
	static final String BONS_ATIVOS = "SELECT ID, name, quality, status, isgoalkeeper FROM players WHERE status = 1 and quality = 2 and isgoalkeeper = 0 ORDER BY isgoalkeeper desc, quality, name, ID ";
	static final String EXCELENTES_ATIVOS = "SELECT ID, name, quality, status, isgoalkeeper FROM players WHERE status = 1 and quality = 3 and isgoalkeeper = 0 ORDER BY isgoalkeeper desc, quality, name, ID ";
	
	
	private PlayersCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
		super(driver, editTable, query);
	}
	
	static class Factory implements SQLiteDatabase.CursorFactory {
		@Override
		public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
			return new PlayersCursor(db, driver, editTable, query);
		}
	}
	
	public int getID() {
		return getInt(getColumnIndexOrThrow("ID"));
	}
	
	public String getName() {
		return getString(getColumnIndexOrThrow("name"));
	}
	
	public int getQuality() {
		return getInt(getColumnIndexOrThrow("quality"));
	}		

	public boolean getStatus() {
		return (getInt(getColumnIndexOrThrow("status")) > 0) ? true : false;
	}

	public boolean getIsGoalKeeper() {
		return (getInt(getColumnIndexOrThrow("isgoalkeeper")) > 0) ? true : false;
	}
}