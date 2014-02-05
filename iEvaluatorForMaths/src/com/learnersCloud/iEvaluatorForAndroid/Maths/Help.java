package com.learnersCloud.iEvaluatorForAndroid.Maths;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import com.learnersCloud.iEvaluatorForAndroid.Chemistry.LocalCache;
import com.learnersCloud.iEvaluatorForAndroid.Maths.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Help extends Activity implements OnClickListener{
    
	MediaController mediaController;
	VideoView videoView;
	Context c;
	SurfaceHolder holder;
	LocalCache cache;
	TextView Ad;
	Button btnHelp;
	int DownloadedSize;   // used to check patial download
	private ProgressBar mProgress;
    private static String Video_PATH =  "http://www.learnersCloud.com/Android"; //"/data/data/com.learnersCloud.iEvaluatorForAndroid.Chemistry/Videos";
    private String LocalVideoPath;
    int FileSizeOnServer;
    int DownloadProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        

        setContentView(R.layout.helppage);
        c = this.getBaseContext();
        
        View LLView = findViewById(R.id.LLHelpPage);
        View root = LLView.getRootView();
		root.setBackgroundColor(Color.BLACK);
		
		videoView = (VideoView) findViewById(R.id.videoHelpView2);
		Ad = (TextView)findViewById(R.id.txtHelpAd);
		btnHelp = (Button)findViewById(R.id.btnHelpPage);
		
		 btnHelp.setOnClickListener(this);
		 
		mProgress = (ProgressBar)findViewById(R.id.HelpVideoprogressBar);
		mProgress.setVisibility( View.INVISIBLE );
	    
	    holder = videoView.getHolder();
        holder.setFixedSize(300,300);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        
        
        cache = ((LocalCache)getApplicationContext());
        
        LocalVideoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LCVideos";
             
        
    }




	private void downloadAndPlayVideo() {
		
		
		 
		if (IsThereVideosOnDeviceSDCard("helpdroid.mp4")){
			// Ad.setText("For more videos visit www.learnersCloud.com");
			 
			// It possible that video is partly downloaded so download again.
			 if(!checkFileSize(Video_PATH,"helpdroid.mp4")){
				 downloadFiles(Video_PATH, "helpdroid.mp4");
				 return;
				
			 }
			
			// play video from SDCard
			// LocalVideoPath Location = mnt/sdcard
			
			Uri videoUri = Uri.parse(LocalVideoPath + "/helpdroid.mp4");
			videoView.setMediaController(mediaController);
			videoView.setVideoURI(videoUri);
			videoView.requestFocus();
			//mediaController.show(0);
			
			videoView.start();
			 
			
		}
		
		else
			
		{
			if(CheckFreeSpaceOnSDCard()){
			// Download Video From Server
			downloadFiles(Video_PATH, "helpdroid.mp4");
			
			}
			else
			{
				Toast.makeText(getBaseContext(),
	    	               "You need do not have enough space on your device",
	    	                Toast.LENGTH_LONG).show();
				 mProgress.setVisibility( View.INVISIBLE );
			}
		}
	}
    
    
 
    
    private boolean IsThereVideosOnDeviceSDCard(String VideoName){
    	
    	File videofile = new File(LocalVideoPath + "/" +VideoName);
    	File dir = new File(LocalVideoPath);
    	
    	if (!dir.exists()){
    		dir.mkdir();
            
            
    	}
    	if(!videofile.exists()){
    		
    		return false;
    	}
    	
    	else
    	{
    		return true;
    	}
          
    	
    }
    
 private boolean CheckFreeSpaceOnSDCard(){
    	
    	// I need at least 50mb
    	StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
    	double sdAvailSize = (double)stat.getAvailableBlocks() *(double)stat.getBlockSize();
    	//One binary gigabyte equals 1,073,741,824 bytes.
    	double gigaAvailable = sdAvailSize / 1073741824;
    	
    	if(gigaAvailable > 0.04883){
    		
    		return true;
    		
    	}
    	else{
    		
    		return false;
    	}

    }
 
 public void downloadFiles(final String url, final String VideoName){
 	
 	// Check if the users Wifi is connected
	 DownloadProgress = 0;
 	if(cache.GetConnectionType() == 3){  // = 0
 	Ad.setText("Downloading video.. please wait");
 	mProgress.setVisibility( View.VISIBLE );
 	Thread DownloadThread = new Thread(){
 		public void run(){
 			
 			
 			try {
 		URL u = new URL(url + "/" +VideoName);
 		HttpURLConnection c = (HttpURLConnection) u.openConnection();
 		/// This will sort out the problem of partial download
 		//if(DownloadedSize > 0){
 		//	File videofile = new File(LocalVideoPath + "/" + VideoName);
 		//	c.setRequestProperty("Range", "bytes="+ (videofile.length())+ "-");
 		//}
 		//else
 		//{
 		//	c.setRequestProperty("Range", "bytes=" + DownloadedSize + "-");

 		//}
 		
 		 c.setRequestMethod("GET");
          c.setDoOutput(true);
          c.connect();
          FileSizeOnServer = c.getContentLength(); 
          FileOutputStream f = new FileOutputStream(LocalVideoPath + "/" + VideoName);
          InputStream in = c.getInputStream();
          final DecimalFormat df = new DecimalFormat("#.##");
           

          
			
			 byte[] buffer = new byte[1024];
		        int read;
		        while ((read = in.read(buffer)) != -1) {
		            f.write(buffer, 0, read);
		            
		           
		            final int Reader = read;
		            runOnUiThread(new Runnable() {
				         public void run() {
				        	 // Update progress of download
				        	 
				        		// if (FileSizeOnServer > 0 && DownloadProgress > 0) {
				        	 	DownloadProgress += Reader;
				        			  double Percentage =(double)DownloadProgress/FileSizeOnServer * 100; 
				        			  //int Percentage = FileSizeOnServer; 
				        			 Ad.setText("Download progress... " + String.valueOf(df.format(Percentage)) + "%");
				        			 
				        		  //  }
				        		 
				        	 }
				     });
		            
		        }
		        in.close();
		        in = null;
		        f.flush();
		        f.close();
		        f = null;
		        runOnUiThread(new Runnable() {
			         public void run() {
			        	 finishedDownload();
			        	 btnHelp.setText("Play");
			        	
			         }
			     });
		        
             } 
 			
 	catch (Exception e) {
 		
			Log.e("Joseph", e.getMessage());
			runOnUiThread(new Runnable() {
		         public void run() {
		        	 Ad.setText("Cannot download try later");
		        	 mProgress.setVisibility( View.INVISIBLE );
		         }
		     });
			
		}
		
 			}
 		};
 		
 		DownloadThread.start();
 		
 	}
 	else {
 		
 		// No Wifi
 		Toast.makeText(getBaseContext(),
 	               "You need to connect to a Wifi to download our videos, please connect to Wifi",
 	                Toast.LENGTH_LONG).show();
 		
 	}
 	
 }
 
 private void finishedDownload(){
 	
 	Ad.setText("LearnersCloud App Help Video");
 	
 	mProgress.setVisibility( View.INVISIBLE );
 	videoView.start();
 	
 }
 
 private boolean checkFileSize(final String url, final String VideoName){
 	
 	try {
 	File videofile = new File(LocalVideoPath + "/" +VideoName);
 	int LocalFileSize = (int)videofile.length();
 	DownloadedSize = (int)videofile.length();
 	URL u = new URL(url + "/" +VideoName);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();
		int Remotesize = c.getContentLength();
		c.disconnect();
		c = null;
		
		
		if(Remotesize != LocalFileSize ){
			 DeleteVideoFile(VideoName);
			return false;
			}
 	}
 	
 	catch (Exception e) {
 		
 	
 	}
		
		return true;
 	
 }






	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		videoView.stopPlayback();
		
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//return super.onTouchEvent(event);
		
		
		if(videoView.isPlaying()){
		 videoView.pause();
		}
		else
		{
			videoView.start();
		}
		
		return false;
	}




	public void onClick(View arg0) {
		
		downloadAndPlayVideo();
		
		
	}
private void DeleteVideoFile(String VideoName) {
		
		
		File videofile = new File(LocalVideoPath + "/" +VideoName);
		
		videofile.delete();
		
	}


}
