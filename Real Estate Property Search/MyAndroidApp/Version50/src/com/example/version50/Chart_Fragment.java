package com.example.version50;



import java.io.InputStream;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * A simple {@link Fragment} subclass.
 * 
 */
public class Chart_Fragment extends Fragment implements ViewFactory {
	 ImageSwitcher is;
		
	
	 
	
		Button prev, next;
		int count =0;
        Context thiscontext;
        String puturl;
        TextSwitcher textSwitcher;
		  
	public Chart_Fragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		String text = Result.Address;
		thiscontext  = container.getContext();
		View rootview = inflater.inflate(R.layout.fragment_chart_, container, false);
		TextView add = (TextView)	rootview.findViewById(R.id.addr);
		add.setText(text);
		String link1 = "http://www.zillow.com/corp/Terms.htm";
		String link2="http://www.zillow.com/zestimate/";
		TextView l1 = (TextView)	rootview.findViewById(R.id.textView33);
		TextView l2 = (TextView)	rootview.findViewById(R.id.link2);
        l2.setText(Html.fromHtml("<a href=\""+ link2 + "\">" + "What's a Zestimate"+"</a>"));
        l2.setClickable(true);
        l2.setMovementMethod (LinkMovementMethod.getInstance());
		

        l1.setText(Html.fromHtml("Use is subject to <a href=\""+ link1 + "\">" + "Terms of Use"+"</a>"));
        l1.setClickable(true);
        l1.setMovementMethod (LinkMovementMethod.getInstance());


        prev = (Button)rootview.findViewById(R.id.button1);
        next = (Button)rootview.findViewById(R.id.button2);
	
		 
		 textSwitcher = (TextSwitcher) rootview.findViewById(R.id.textswitcher);
			textSwitcher.setFactory(new ViewFactory() {
				@Override
				public View makeView() {
					TextView textView = new TextView(thiscontext);
					textView.setTextSize(17);
					return textView;
				}
			});
			textSwitcher.setInAnimation(thiscontext, android.R.anim . slide_in_left);
			textSwitcher.setOutAnimation(thiscontext, android.R.anim.slide_out_right);
			 
			is = (ImageSwitcher)rootview.findViewById(R.id.imageSwitcher1);
			
		
			is.setFactory(new ViewFactory() {
				@Override
				public View makeView() {
					ImageView imageView = new ImageView(thiscontext);
					imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
					imageView.setLayoutParams(new ImageSwitcher.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
					return imageView;
				}
			});
			is.setInAnimation(thiscontext, android.R.anim . slide_in_left);
			is.setOutAnimation(thiscontext, android.R.anim.slide_out_right);

	           final	String[] arr = new String[]{"Historical Zestimate for the past 1 year",
				"Historical Zestimate for the past 5 years",
				"Historical Zestimate for the past 10 years"};
			
			
		 
                final String urls[] = Result.Chart_url;

                urls[0] = Result.Chart_url[0]; 
                urls[1]=Result.Chart_url[1]; 
                urls[2]=Result.Chart_url[2]; 
                puturl=urls[0];
		

		      textSwitcher.setText(arr[count]);
		      new DownloadData().execute();
		
		
		
		
prev.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					if(count>0)
					{
						count--;
						try{
							puturl=urls[count];
							 textSwitcher.setText(arr[count]);
							new DownloadData().execute();
				        }
						catch(Exception e)
						{
							e.printStackTrace();
						}
						
					}
					else	if(count == 0)
					{
						count=2;
						puturl=urls[count];
						 textSwitcher.setText(arr[count]);
						new DownloadData().execute();
					}
					
			}
		});
		next.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(count<urls.length)
				{
					count++;
									
					try{
						puturl=urls[count];
						 textSwitcher.setText(arr[count]);
						new DownloadData().execute();
					}
				catch(Exception e)
				{
					e.printStackTrace();
				}
					
					
				}
				
				if(count == 3)
				{
					count=0;
					puturl=urls[count];
					 textSwitcher.setText(arr[count]);
					new DownloadData().execute();
				}
				
			}
		});
		
		return rootview;
	}


	private class DownloadData extends AsyncTask<String, Void, String> {

		Drawable drawable;    
	    @Override
	    public String doInBackground(String... args){

	        drawable = LoadImageFromWeb(puturl);
	        return null; // here you can pass any string on response as on error or on success

	    }

	    public void onPostExecute(String result){

	        if(drawable!=null){

	            is.setImageDrawable(drawable);

	        }

	    }

}
	
	
	
	private Drawable LoadImageFromWeb(String url){
	      try{
	          InputStream is = (InputStream) new URL(url).getContent();
	          Drawable d = Drawable.createFromStream(is, "src name");
	          return d;
	      }catch (Exception e) {
	          System.out.println("Exc="+e);
	          return null;
	      }
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		return null ;
	}

}
