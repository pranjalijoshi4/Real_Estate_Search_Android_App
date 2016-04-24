package com.example.version50;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;
import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class BasicInfo_Fragment extends Fragment {

String name; 
String description;  
String link;   
String sign;
String picture;

	Context thiscontext;
	public BasicInfo_Fragment() {
		// Required empty public constructor
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
        picture=Result.Chart_url[0];
        name= Result.Address;
        description="Last Sold Price: "+Result.data[8]+", 30 Days Overall Change: "+Result.Date_Sign[1]+" "+Result.data[11];
        link=Result.Addrlink;
		// Inflate the layout for this fragment
		thiscontext = container.getContext();
		String val[] = Result.data;
		String head[] = Result.titles;
		String addr = Result.Address;
		String dates_sign[] = Result.Date_Sign;
		String alink = Result.Addrlink;
	
	
		View rootview = inflater.inflate(R.layout.fragment_basic_info_, container, false);
		TextView a = (TextView)	rootview.findViewById(R.id.address);
	

		String link1 = "http://www.zillow.com/corp/Terms.htm";
		String link2="http://www.zillow.com/zestimate/";
        //TextView l1 = (TextView)	rootview.findViewById(R.id.link1);
        TextView l2 = (TextView)	rootview.findViewById(R.id.link2);
        //l1.setText(Html.fromHtml("<a href=\""+ link1 + "\">" + "Terms of Use"+"</a>"));
        l2.setText(Html.fromHtml("<a href=\""+ link2 + "\">" + "What's a Zestimate"+"</a>"));
        //l1.setClickable(true);
        l2.setClickable(true);
        //l1.setMovementMethod (LinkMovementMethod.getInstance());
        l2.setMovementMethod (LinkMovementMethod.getInstance());
		
	    a.setText(Html.fromHtml("<a href=\""+ alink + "\">" + addr+"</a>"));
		a.setClickable(true);
		a.setMovementMethod (LinkMovementMethod.getInstance());
	
	    Button b = (Button) rootview.findViewById(R.id.facebook);
	
		TextView t2 = (TextView)	rootview.findViewById(R.id.textView2);
		TextView t4 = (TextView)	rootview.findViewById(R.id.textView4);
		TextView t6 = (TextView)	rootview.findViewById(R.id.textView6);
		TextView t8 = (TextView)	rootview.findViewById(R.id.textView8);
		TextView t10 = (TextView)	rootview.findViewById(R.id.textView10);
		TextView t12 = (TextView)	rootview.findViewById(R.id.textView12);
		TextView t14 = (TextView)	rootview.findViewById(R.id.textView14);
		TextView t16 = (TextView)	rootview.findViewById(R.id.textView16);
		TextView t18 = (TextView)	rootview.findViewById(R.id.textView18);
		TextView t20 = (TextView)	rootview.findViewById(R.id.textView20);
		TextView t22 = (TextView)	rootview.findViewById(R.id.textView22);
		TextView t24 = (TextView)	rootview.findViewById(R.id.textView24);
		TextView t26 = (TextView)	rootview.findViewById(R.id.textView26);
		TextView t28 = (TextView)	rootview.findViewById(R.id.textView28);
		TextView t30 = (TextView)	rootview.findViewById(R.id.textView30);
		TextView t32 = (TextView)	rootview.findViewById(R.id.textView32);
		TextView t21 = (TextView)	rootview.findViewById(R.id.textView21);
		TextView t27 = (TextView)	rootview.findViewById(R.id.textView27);
		TextView t33 = (TextView)	rootview.findViewById(R.id.textView33);
		/*ImageView imageView1 = (ImageView)	rootview.findViewById(R.id.imageView1);
		ImageView imageView2 = (ImageView)	rootview.findViewById(R.id.imageView2);
		TextView d1 = (TextView)	rootview.findViewById(R.id.datesaof1);
		TextView d2 = (TextView)	rootview.findViewById(R.id.dateasof2);
		*/
		t33.setText(Html.fromHtml("Use is subject to <a href=\""+ link1 + "\">" + "Terms of Use"+"</a>"));
		t33.setClickable(true);
		t33.setMovementMethod (LinkMovementMethod.getInstance());
		t2.setText(val[0]);
	
		t4.setText(val[1]);
	
		t6.setText(val[2]);
		
		t8.setText(val[3]);
		
		t10.setText(val[4]);
	
		t12.setText(val[5]);
	
		t14.setText(val[6]);
	
		t16.setText(val[7]);
	
		t18.setText(val[8]);
	
		t20.setText(val[9]);
	
		String str1 = "Zestimate Property Estimate \nas of "+dates_sign[0];
		t21.setText(str1);
		
		t22.setText(val[10]);
		t24.setText(val[11]);
	
		String str2 = "Rent Zestimate Property Estimate \nas of "+dates_sign[2];
		t27.setText(str2);
		
		t26.setText(val[12]);
	
		t28.setText(val[13]);
	
		t30.setText(val[14]);
	
		t32.setText(val[15]);
		
		
		if(dates_sign[1].equals("+"))
		t24.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.up_g, 0,0, 0);
		else if(dates_sign[1].equals("-"))
			t24.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.down_r,0, 0, 0);

		

		if(dates_sign[3].equals("+"))
			t30.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.up_g,0, 0, 0);
		else if(dates_sign[3].equals("-"))
			t30.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.down_r,0, 0, 0);
		
	
		b.setOnClickListener(new View.OnClickListener() {
	        @SuppressWarnings("deprecation")
			public void onClick(View v) {
	        	

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
	       		        	        		Log.v("complete", "complete");	
	       		        	        		final String postId = values.getString("post_id");
	       		        	        		if(postId != null)
	       		    						Toast.makeText(getActivity(), "Posted Story_ID:  "+postId, Toast.LENGTH_SHORT).show();	
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
	       		       						Toast.makeText(getActivity(), "Post Cancelled", Toast.LENGTH_SHORT).show();

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
	        	
	               builder.show();

	        }
		});
				
		return rootview;
	
	}

}
