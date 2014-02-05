package com.learnersCloud.iEvaluator.MathsFree;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class BarChartDisplay extends Activity implements OnClickListener {
	
	Button btnDisplay;
	 @Override
	    public void onCreate(Bundle savedInstanceState)   {
	        super.onCreate(savedInstanceState);
	        
	        setContentView(R.layout.barchartdisplay);
	        
	        btnDisplay = (Button)findViewById(R.id.displaychart);
	        btnDisplay.setOnClickListener(this);
	        
	        barchart bc = new barchart();
	        Intent barIntent = bc.getIntent(this);
	        startActivity(barIntent);
	 }
	@Override
	public void onClick(View v) {
	
		if(v.getId() == R.id.displaychart){
			
			  barchart bc = new barchart();
		        Intent barIntent = bc.getIntent(this);
		        startActivity(barIntent);
		}
		 
		
		
	}

}
