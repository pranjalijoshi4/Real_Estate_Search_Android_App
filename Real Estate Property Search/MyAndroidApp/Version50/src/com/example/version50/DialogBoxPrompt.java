package com.example.version50;

import java.io.IOException;
import java.net.MalformedURLException;

import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class DialogBoxPrompt extends DialogFragment {

String name; 
String description;  
String link;   
int complete;
String picture;
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		  String val[] = Result.data;
    		String head[] = Result.titles;
    		String addr = Result.Address;
    		String dates_sign[] = Result.Date_Sign;
    		String alink = Result.Addrlink;
    		
    		complete=0;
    		picture=Result.Chart_url[0];
			name= Result.Address;
			description="Last Sold Price:"+Result.data[8]+", 30 Days Overall Change"+Result.data[11];
			link=Result.Addrlink;
    		
    		
        // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Post to Facebook")
               .setPositiveButton("Post Property Details", new DialogInterface.OnClickListener() {
                   @SuppressWarnings("deprecation")
				    public void onClick(DialogInterface dialog, int id) {

                	   String APP_ID = "369170309924050";
           	        //	String APP_ID = getString(R.string.APP_ID);
           	        	final Facebook fb= new Facebook(APP_ID);
           	        	if(fb.isSessionValid())
        	        	{
        	        		Log.v("hello", APP_ID);	
        	        		try {
        						fb.logout(getActivity());
        					} catch (MalformedURLException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					} catch (IOException e) {
        						// TODO Auto-generated catch block
        						e.printStackTrace();
        					}
        	        	}
           	        	else
           	        	{
           	        		fb.authorize(getActivity(), new Facebook.DialogListener(){

								@Override
								public void onComplete(Bundle values) {
									// TODO Auto-generated method stub

								}

								@Override
								public void onFacebookError(FacebookError e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onError(DialogError e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onCancel() {
									// TODO Auto-generated method stub
									
								}
           	        			
           	        		});
           	        		
           	        
           	     		
           	        		Bundle params = new Bundle();
        	        		params.putString("name",name);
        	        		params.putString("caption","Property information from Zillow.com");
        	        		params.putString("description",description);
        	        		params.putString("link",link);
        	        		params.putString("picture",picture);
           	        		
           	        		fb.dialog(getActivity(), "feed", params, new DialogListener() {

								@Override
								public void onComplete(Bundle values) {
									// TODO Auto-generated method stub
		        	        		Log.v("hello.......................", "a");	
									
								}

								@Override
								public void onFacebookError(FacebookError e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onError(DialogError e) {
									// TODO Auto-generated method stub
									
								}

								@Override
								public void onCancel() {
									// TODO Auto-generated method stub
									
								}
           	        			
           	        			
           	        	
           	        		});
           	        		
           	        	

           	        	}
           	        	
           	        }
               })
               .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                	   
                        //post cancel
                	   
						Toast.makeText(getActivity(), "Post Cancelled", Toast.LENGTH_SHORT).show();

                
                	   
                   }
               });

        return builder.create();
    }

}