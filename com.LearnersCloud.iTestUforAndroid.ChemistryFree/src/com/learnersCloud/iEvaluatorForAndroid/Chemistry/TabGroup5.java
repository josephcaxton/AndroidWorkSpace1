package com.learnersCloud.iEvaluatorForAndroid.Chemistry;

import android.content.Intent;
import android.os.Bundle;

public class TabGroup5 extends TabGroupActivity5{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startChildActivity("Video",new Intent(this,Video.class));
		
	}

}
