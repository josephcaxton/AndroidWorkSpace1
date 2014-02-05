package com.learnersCloud.iEvaluatorForAndroid.Maths;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

import com.learnersCloud.iEvaluatorForAndroid.Maths.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.text.Html;
import android.text.util.Linkify;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class Video  extends Activity  
{
   
	//MediaPlayer mediaplayer;
	MediaController mediaController;
	VideoView videoView;
	Context c;
	SurfaceHolder holder;
	LocalCache cache;
	TextView Ad;
	long DownloadedSize;   // used to check patial download
	private ProgressBar mProgress;
    private static String Video_PATH =  "http://www.learnersCloud.com/Android"; //"/data/data/com.learnersCloud.iEvaluatorForAndroid.Chemistry/Videos";
    private String LocalVideoPath;
    long FileSizeOnServer;
    int DownloadProgress;
    TextView Videobottomtxt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videopage);
        c = this.getBaseContext();
        
        View LLView = findViewById(R.id.LLVideoPage);
		View root = LLView.getRootView();
		root.setBackgroundColor(Color.BLACK);
		//root.setBackgroundColor(Color.argb(0, 238, 244, 228));
		
        Gallery gallery = (Gallery) findViewById(R.id.VideoGallery);
       videoView = (VideoView) findViewById(R.id.videoView2);
       
       Ad = (TextView)findViewById(R.id.txtVideoAd);
       Ad.setText("For more videos visit www.learnersCloud.com");
       Linkify.addLinks( Ad, Linkify.WEB_URLS ); 
       
       Videobottomtxt = (TextView)findViewById(R.id.txtVideoBottomText);
       String myHtml = "<p>LearnersCloud combines cutting edge technology with top quality content to offer you the best in e-learning.</p>" + " " +
       	"<p>Regardless your age, location or past GCSE experience, LearnersCloud helps you revise efficiently for your exams. ";
      
       Videobottomtxt.setText(Html.fromHtml(myHtml));
       
       mProgress = (ProgressBar)findViewById(R.id.VideoprogressBar);
       mProgress.setVisibility( View.INVISIBLE);

        
       
       
        holder = videoView.getHolder();
        holder.setFixedSize(300,300);
        
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);

       
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        
        
        cache = ((LocalCache)getApplicationContext());
        
        LocalVideoPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/LCVideos";
        
        gallery.setAdapter(new ImageAdapter(this));
        
        gallery.setOnItemClickListener(new OnItemClickListener() {
           
        	public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                
        		
        		
        		switch(position){
        		
        		case 0:
        			
        			if (IsThereVideosOnDeviceSDCard("mathsdroid.mp4")){
        				 Ad.setText("For more videos visit www.learnersCloud.com");
        				 
        				// It possible that video is partly downloaded so download again.
        				 if(!checkFileSize(Video_PATH,"mathsdroid.mp4")){
        					 downloadFiles(Video_PATH, "mathsdroid.mp4");
        					 break;
        					
        				 }
        				
        				// play video from SDCard
        				// LocalVideoPath Location = mnt/sdcard
        				 mProgress.setVisibility( View.INVISIBLE );
        				Uri videoUri = Uri.parse(LocalVideoPath + "/mathsdroid.mp4");
        				videoView.setMediaController(mediaController);
        				videoView.setVideoURI(videoUri);
            			videoView.requestFocus();
            			mediaController.show(0);
            			
            			videoView.start();
        				 
            			
        			}
        			
        			else
        				
        			{
        				if(CheckFreeSpaceOnSDCard()){
        				// Download Video From Server
        				downloadFiles(Video_PATH, "mathsdroid.mp4");
        				
        				}
        				else
        				{
        					Toast.makeText(getBaseContext(),
        		    	               "You need do not have enough space on your device",
        		    	                Toast.LENGTH_LONG).show();
        				}
        			}
        			
        			
	
        			break;
        		case 1:
        			
        			if (IsThereVideosOnDeviceSDCard("englishdroid.mp4")){
        				Ad.setText("For more videos visit www.learnersCloud.com");
        				
        				// It possible that video is partly downloaded so download again.
       				 if(!checkFileSize(Video_PATH,"englishdroid.mp4")){
       					 downloadFiles(Video_PATH, "englishdroid.mp4");
       					 break;
       					
       				 }
        				// play video from SDCard
        				// LocalVideoPath Location = mnt/sdcard
       				 	mProgress.setVisibility( View.INVISIBLE );
        				Uri videoUri = Uri.parse(LocalVideoPath + "/englishdroid.mp4");
        				videoView.setMediaController(mediaController);
        				videoView.setVideoURI(videoUri);
            			videoView.requestFocus();
            			mediaController.show(0);
            			
            			videoView.start();
        				
        			}
        			
        			else
        				
        			{
        				if(CheckFreeSpaceOnSDCard()){
        				// Download Video From Server
        				downloadFiles(Video_PATH, "englishdroid.mp4");
        				}
        				else
        				{
        					Toast.makeText(getBaseContext(),
        		    	               "You need do not have enough space on your device",
        		    	                Toast.LENGTH_LONG).show();
        				}
        			}
        			
        			break;
        		case 2:
        			
        			if (IsThereVideosOnDeviceSDCard("physicsdroid.mp4")){
        				Ad.setText("For more videos visit www.learnersCloud.com");
        				
        				// It possible that video is partly downloaded so download again.
          				 if(!checkFileSize(Video_PATH,"physicsdroid.mp4")){
          					 downloadFiles(Video_PATH, "physicsdroid.mp4");
          					 break;
          				 }
        				// play video from SDCard
        				// LocalVideoPath Location = mnt/sdcard
          				mProgress.setVisibility( View.INVISIBLE );
        				Uri videoUri = Uri.parse(LocalVideoPath + "/physicsdroid.mp4");
        				videoView.setMediaController(mediaController);
        				videoView.setVideoURI(videoUri);
            			videoView.requestFocus();
            			mediaController.show(0);
            			
            			videoView.start();
        				
        			}
        			
        			else
        				
        			{
        				if(CheckFreeSpaceOnSDCard()){
        				// Download Video From Server
        				downloadFiles(Video_PATH, "physicsdroid.mp4");
        				}
        				else
        				{
        					Toast.makeText(getBaseContext(),
        		    	               "You need do not have enough space on your device",
        		    	                Toast.LENGTH_LONG).show();
        				}
        			}
        			
        			break;
        		case 3:
        			
        			if (IsThereVideosOnDeviceSDCard("Chemistrydroid.mp4")){
        				Ad.setText("For more videos visit www.learnersCloud.com");
        				
        				// It possible that video is partly downloaded so download again.
         				 if(!checkFileSize(Video_PATH,"Chemistrydroid.mp4")){
         					 downloadFiles(Video_PATH, "Chemistrydroid.mp4");
         					 break;
         				 }
         				 
        				// play video from SDCard
        				// LocalVideoPath Location = mnt/sdcard
         				mProgress.setVisibility( View.INVISIBLE );
        				Uri videoUri = Uri.parse(LocalVideoPath + "/Chemistrydroid.mp4");
        				videoView.setMediaController(mediaController);
        				videoView.setVideoURI(videoUri);
            			videoView.requestFocus();
            			mediaController.show(0);
            			
            			videoView.start();
        				
        			}
        			
        			else
        				
        			{
        				if(CheckFreeSpaceOnSDCard()){
        				// Download Video From Server
        				downloadFiles(Video_PATH, "Chemistrydroid.mp4");
        				}
        				else
        				{
        					Toast.makeText(getBaseContext(),
        		    	               "You need do not have enough space on your device",
        		    	                Toast.LENGTH_LONG).show();
        				}
        			}
        		
	
        			break;
        		}
            }
        });
        
        
       


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
    		//if(DownloadedSize > 0){  // I am not using this any more I have decided to  delete the file first, reason some time the file size is bigger on the phone
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
             FileSizeOnServer = Long.parseLong(c.getHeaderField("Content-Length"));
             FileOutputStream f = new FileOutputStream(LocalVideoPath + "/" + VideoName);
             InputStream in = c.getInputStream();
             final DecimalFormat df = new DecimalFormat("#.##");
              

             
			
			 byte[] buffer = new byte[1024];
		        int read= 0;
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
    	
    	Ad.setText("Download complete, click again to play video");
    	mProgress.setVisibility( View.INVISIBLE );
    }
    
    private boolean checkFileSize(final String url, final String VideoName){
    	
    	try {
    	File videofile = new File(LocalVideoPath + "/" +VideoName);
    	long LocalFileSize = (long)videofile.length();
    	DownloadedSize = (long)videofile.length();
    	URL u = new URL(url + "/" +VideoName);
		HttpURLConnection c = (HttpURLConnection) u.openConnection();
		//long Remotesize = c.getContentLength();
		long Remotesize= Long.parseLong(c.getHeaderField("Content-Length"));

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
	public void onBackPressed() {
		// TODO Auto-generated method stub
    	
    	videoView.stopPlayback();
		TabGroup5 parentActivity = (TabGroup5)getParent();
		parentActivity.onBackPressed();
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



	public class ImageAdapter extends BaseAdapter {
        int mGalleryItemBackground;
        private Context mContext;

        private Integer[] mImageIds = {
                R.drawable.maths,
                R.drawable.english,
                R.drawable.physics,
                R.drawable.chemistry
        };

        public ImageAdapter(Context c) {
            mContext = c;
            TypedArray attr = mContext.obtainStyledAttributes(R.styleable.VideoGalleryStyle);
            mGalleryItemBackground = attr.getResourceId(
                    R.styleable.VideoGalleryStyle_android_galleryItemBackground, 0);
            attr.recycle();
        }

        public int getCount() {
            return mImageIds.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView = new ImageView(mContext);

            imageView.setImageResource(mImageIds[position]);
            imageView.setLayoutParams(new Gallery.LayoutParams(150, 100));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setBackgroundResource(mGalleryItemBackground);

            return imageView;
        }

		
    }



 /*
	public void onPrepared(MediaPlayer mp) {
		
		
		mProgress.setVisibility(View.INVISIBLE);
		
		
		mp.setOnBufferingUpdateListener(new OnBufferingUpdateListener()
		{
				
				public void onBufferingUpdate(MediaPlayer mp, int percent) {
					
						
					//mProgress.setVisibility(View.VISIBLE);
					
						
					
						
				}
			});
		
	} */
	
	private void DeleteVideoFile(String VideoName) {
		
		
		File videofile = new File(LocalVideoPath + "/" +VideoName);
		
		videofile.delete();
		
	}
}
