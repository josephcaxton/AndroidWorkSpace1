package com.learnersCloud.iEvaluatorForAndroid.Chemistry;

import android.app.ActivityGroup;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Questions extends ActivityGroup implements OnClickListener{

	Button bk;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.questions);
		 bk = (Button)findViewById(R.id.btnBack);
		 bk.setOnClickListener(this);
	}
	public void onClick(View v) {
		
		finish();

		

	}
	// to prevent systems before android.os.Build.VERSION_CODES.ECLAIR
			// from calling their default KeyEvent.KEYCODE_BACK during onKeyDown.
			@Override
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					//preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
					return true;
					}
					return super.onKeyDown(keyCode, event);
				}
			

			//Overrides the default implementation for KeyEvent.KEYCODE_BACK
			// so that all systems call onBackPressed().
			@Override
			public boolean onKeyUp(int keyCode, KeyEvent event) {
				
				if (keyCode == KeyEvent.KEYCODE_BACK) {
					TabGroupActivity parentActivity = (TabGroupActivity)getParent(); 
					parentActivity.onBackPressed();
					return true;
					}
					return super.onKeyUp(keyCode, event);
					}
			
	

}
