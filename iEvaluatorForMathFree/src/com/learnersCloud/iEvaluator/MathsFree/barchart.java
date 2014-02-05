package com.learnersCloud.iEvaluator.MathsFree;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.learnersCloud.iEvaluator.MathsFree.R.color;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.util.Log;

public class barchart {
	
	private static DataBaseHelper myDbHelper;
	private List<Integer> CorrectAnswerValues;
	private List<Integer> WrongAnswerValues;
	private List<Integer> TotalArrays;
	Context c;
	
	public Intent getIntent(Context context){
		
		c = context;
		
		CorrectAnswerValues = new ArrayList<Integer>();
		WrongAnswerValues = new ArrayList<Integer>();
	    TotalArrays = new ArrayList<Integer>();
	    
		 GetDataFromDatabase();
		 
		/*CorrectAnswerValues = new ArrayList<Integer>();
		CorrectAnswerValues.add(12);
		CorrectAnswerValues.add(14);
		CorrectAnswerValues.add(15);
		CorrectAnswerValues.add(1);
		CorrectAnswerValues.add(21);
		
		WrongAnswerValues = new ArrayList<Integer>();
		WrongAnswerValues.add(1);
		WrongAnswerValues.add(2);
		WrongAnswerValues.add(11);
		WrongAnswerValues.add(14);
		WrongAnswerValues.add(19);*/
		
		//Data 1
		 CategorySeries series = new CategorySeries("Correct Answers");
		 for(int i = 0; i < CorrectAnswerValues.size(); i++){
			
			 series.add("bar" + (i+1), CorrectAnswerValues.get(i));
			//Log.e("Joseph correct", CorrectAnswerValues.get(i).toString());
		 }
			
		 //Data 2
		 CategorySeries series1 = new CategorySeries("Correct Answers");
		 for(int i = 0; i < WrongAnswerValues.size(); i++){
				
			 series1.add("bar" + (i+1), WrongAnswerValues.get(i));
			 //Log.e("Joseph Wrong", WrongAnswerValues.get(i).toString());
		 }
		 
		 //Add to dataaset
		 XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		 dataset.addSeries(series.toXYSeries());
		 dataset.addSeries(series1.toXYSeries());
		 
		 //Customization for the graph
		 XYMultipleSeriesRenderer mRenderer = new  XYMultipleSeriesRenderer();
		 mRenderer.setChartTitle("Results");
		 mRenderer.setXTitle("Attempts");
		 mRenderer.setYTitle("Marks");
		 mRenderer.setZoomButtonsVisible(true);
		 mRenderer.setBarSpacing((float)0.2);
		
				 
		 //Custom bar 1
		 XYSeriesRenderer mRenderer1 = new  XYSeriesRenderer();
		 mRenderer1.setColor(Color.BLUE);
		 mRenderer1.setChartValuesSpacing((float)0.5);
		 mRenderer1.setDisplayChartValues(true);
		// mRenderer1.setChartValuesTextSize((float)10);
		
		 //Custom bar 2
		 XYSeriesRenderer mRenderer2 = new  XYSeriesRenderer();
		 mRenderer2.setColor(Color.RED);
		 mRenderer2.setChartValuesSpacing((float)0.5);
		 mRenderer2.setDisplayChartValues(true);
		// mRenderer2.setChartValuesTextSize((float)10);
		 // Add series to graph
		 mRenderer.addSeriesRenderer( mRenderer1);
		 mRenderer.addSeriesRenderer( mRenderer2);
		 
		 Intent intent = ChartFactory.getBarChartIntent(context, dataset, mRenderer, Type.DEFAULT);
		
		 return intent;
	}




private void GetDataFromDatabase() {
	
	openDatabaseConnection();
	
	
	String[] Columns = {"CorrectAnswers","TotalQuestions"};
	
	Cursor c = myDbHelper.query1("RESULTS", Columns,
			null, null, null, null, "_id DESC", null);
	
	 if (c.getCount() > 0){
			
		 	
		 
		 if (c.moveToFirst()) {
	    		
	    		do {
	    			
	    			int CorrectVal = c.getInt(c.getColumnIndex("CorrectAnswers"));
	    			CorrectAnswerValues.add(CorrectVal);
	    			
	    			int TotalVal = c.getInt(c.getColumnIndex("TotalQuestions"));
	    			int WrongVal = TotalVal - CorrectVal;
	    			WrongAnswerValues.add(WrongVal);
	    			
	    			
	    			
	    			TotalArrays.add(TotalVal);
	    			

	            } while (c.moveToNext());
	    		
			
			 }
	
	
	 }
	 c.close();
	myDbHelper.close();
	 
}

private void openDatabaseConnection() {
	myDbHelper = new DataBaseHelper(c);
	
	try {
		
		myDbHelper.openDataBase();
		
 
	}catch(SQLException sqle){
 
		throw sqle;
 
	}
}

}