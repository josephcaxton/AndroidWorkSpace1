package com.LearnersCloud.iEvaluatorForAndroid.Chemistry1000;

import android.content.Intent;
import android.os.Bundle;

public class TabGroup2 extends TabGroup2Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		startChildActivity("Result",new Intent(this,Result.class));
		
	}

}
