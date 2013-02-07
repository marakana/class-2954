package com.cisco.yamba;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

public class StatusProvider extends ContentProvider {
	private static final String TAG = "StatusProvider";
	private DbHelper dbHelper;

	/*
	 * Status Table: 
	 * All records: content://com.cisco.yamba.provider.timeline/status 
	 * One record: content://com.cisco.yamba.provider.timeline/status/47
	 */

	private static UriMatcher matcher;
	static {
		matcher = new UriMatcher(UriMatcher.NO_MATCH);
		matcher.addURI(StatusContract.AUTHORITY, StatusContract.TABLE,
				StatusContract.CONTENT_TYPE_DIR);
		matcher.addURI(StatusContract.AUTHORITY, StatusContract.TABLE + "/#",
				StatusContract.CONTENT_TYPE_ITEM);
	}

	@Override
	public boolean onCreate() {
		dbHelper = new DbHelper(getContext());
		Log.d(TAG, "onCreated");
		return (dbHelper != null) ? true : false;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// Assert
		if(matcher.match(uri) != StatusContract.CONTENT_TYPE_DIR) {
			throw new IllegalArgumentException("Wrong uri: "+uri);
		}
		
		SQLiteDatabase db = dbHelper.getWritableDatabase();

		long id = db.insertWithOnConflict(StatusContract.TABLE, null, values,
				SQLiteDatabase.CONFLICT_IGNORE);

		Log.d(TAG, "inserted id: " + id);
		return (id == -1) ? null : ContentUris.withAppendedId(uri, id);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	// uri: content://com.cisco.yamba.provider.timeline/status/47
	// selection: user="?"
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		
		if(matcher.match(uri) == StatusContract.CONTENT_TYPE_ITEM) {
			long id = ContentUris.parseId(uri);
			String where = StatusContract.Columns.ID + "=" + id;
			if(!TextUtils.isEmpty(selection))
				selection = selection + " AND " + where;
			else 
				selection = where;
		}
		
		int recs = db.delete(StatusContract.TABLE, selection, selectionArgs);
		Log.d(TAG, "delete records: " + recs);
		return recs;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// TODO Auto-generated method stub
		return null;
	}
}