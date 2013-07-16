package com.giorgio.peladadequinta2.provider;

import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteCursorDriver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQuery;

public class HistoryCursor extends SQLiteCursor {
	
	static final String CONSULTA = "select matchdate, firstteam, secondteam from history order by matchdate desc ";
	
	private HistoryCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
		super(driver, editTable, query);
	}
	
	static class Factory implements SQLiteDatabase.CursorFactory {
		@Override
		public Cursor newCursor(SQLiteDatabase db, SQLiteCursorDriver driver, String editTable, SQLiteQuery query) {
			return new HistoryCursor(db, driver, editTable, query);
		}
	}
	
	public String getMatchDate() {
		return getString(getColumnIndexOrThrow("matchdate"));
	}
	
	public String getFirstTeam() {
		return getString(getColumnIndexOrThrow("firstteam"));
	}
	
	public String getSecondTeam() {
		return getString(getColumnIndexOrThrow("secondteam"));
	}		

}