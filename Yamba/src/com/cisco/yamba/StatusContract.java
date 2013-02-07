package com.cisco.yamba;

import android.net.Uri;
import android.provider.BaseColumns;

public class StatusContract {

	private StatusContract() {}
	
	public static final String DB_NAME = "timeline.db";
	public static final int DB_VERSION = 1;
	public static final String TABLE = "status";
	
	public static final String AUTHORITY = "com.cisco.yamba.provider.timeline";
	public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+TABLE);
	
	class Columns {
		public static final String ID = BaseColumns._ID;
		public static final String USER = "user";
		public static final String MESSAGE = "message";
		public static final String CREATED_AT = "created_at";
	}
}
