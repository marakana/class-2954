package com.cisco.yamba;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class StatusActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);

		setContentView(R.layout.activity_status);
	}
	
}
