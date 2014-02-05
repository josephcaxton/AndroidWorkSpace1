package com.learnersCloud.iEvaluatorForAndroid.Chemistry;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity  implements OnClickListener {
    /** Called when the activity is first created. */
	
	Button Start;
	Intent intent;
	TextView Version;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        setContentView(R.layout.main);
        View LLView = findViewById(R.id.LLMainPage);
		View root = LLView.getRootView();
		root.setBackgroundColor(Color.WHITE);
        
        Start =(Button) findViewById(R.id.btnStartPractice);
        Version =(TextView)findViewById(R.id.txtMainpgVersion);
        Start.setOnClickListener(this);
        
        ConfigureVersion();
        
        
        // testing the cache HERE
       // LocalCache cache = ((LocalCache)getApplicationContext());
        
        //List<QuestionLookupItem> items = cache.getQuestionsIds();
        
        //if(items != null){
        
        //Toast.makeText(getBaseContext(),
        //        "Your query returned " + String.valueOf(items.size()) + " records.",
        //        Toast.LENGTH_LONG).show(); 
        //} 
    }
    
 
	

private void ConfigureVersion() {
		
		SharedPreferences sharedPrefs = getPreferences(0);
		
		if(sharedPrefs.getInt("versionPref", 0) == 0 ){
			
			// leave TextVersion as it is but set versionPref
		
			SharedPreferences.Editor editor =  getPreferences(0).edit();
			editor.putInt("versionPref", 1);
			editor.commit();
		}
		else
		{
			Integer intVersion = sharedPrefs.getInt("versionPref",0);
		
		
			switch(intVersion){
			
				case 1:
					
					Version.setText("GCSE Chemistry Free Version");
				
				break;
				case 2:
					
					Version.setText("GCSE Chemistry 250 Questions");
					
					break;
				case 3:
					
					Version.setText("GCSE Chemistry 500 Questions");
					
					break;
				case 4:
					
					Version.setText("GCSE Chemistry 750 Questions");
					
					break;
				case 5:
					
					Version.setText("GCSE Chemistry 1000+ Questions");
					
					break;
					
			}
		}
	}




	public void onClick(View v) {
		
		switch (v.getId()){
		 
		case(R.id.btnStartPractice):
			OtherPreferences sharedPrefs = new OtherPreferences(this.getBaseContext());
			
			if(sharedPrefs.getPreference("sound").equalsIgnoreCase("on")){
			LocalCache cache = ((LocalCache)getApplicationContext());
			cache.PlaySound(this, R.raw.arrowwoodimpact);
			}
			
			Intent CustomiseIntent = new Intent(getParent(),Customise.class);
			TabGroupActivity parentActivity = (TabGroupActivity)getParent(); 
			parentActivity.startChildActivity("Customise",CustomiseIntent);
			
			
			
			break;
		
		}
		
		
		 
	}
	




	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		TabGroup1 parentActivity = (TabGroup1)getParent();
		parentActivity.onBackPressed();
	}


	
}