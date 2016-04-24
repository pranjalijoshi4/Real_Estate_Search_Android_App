package com.example.version50;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {

	
	
	private EditText ui_address;
	private EditText ui_city;
	private Spinner ui_state;
	private TextView ui_address_error;
	private TextView ui_city_error;
	private TextView ui_state_error;
	private TextView ui_invalid_url_error;
	private Button ui_search;
	private ImageView image;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findAllViewsById();
		AcceptInput_Onclick();

		image.setOnClickListener(new View.OnClickListener(){
		    public void onClick(View v){
		        Intent intent = new Intent();
		        intent.setAction(Intent.ACTION_VIEW);
		        intent.addCategory(Intent.CATEGORY_BROWSABLE);
		        intent.setData(Uri.parse("http://www.zillow.com/"));
		        startActivity(intent);
		    }
		});
	}
	
	
	
	@SuppressLint("NewApi")
	private void AcceptInput_Onclick() {
		// TODO Auto-generated method stub
		
		
	ui_search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final String address = ui_address.getText().toString();
				final String city = ui_city.getText().toString();
				final String state = ui_state.getSelectedItem().toString();
				 ui_invalid_url_error.setText("");
				if(address.isEmpty() || city.isEmpty() || state.equals("Choose State"))
				{
					if(address.isEmpty())
					{
						ui_address_error.setText("This field is required");
					}
				
					if(city.isEmpty())
					{
						ui_city_error.setText("This field is required");
					}
					
					if(state.equals("Choose State"))
					{
						ui_state_error.setText("This field is required");
					}
					
					if(!(address.isEmpty()))
					{
						ui_address_error.setText("");
					}
				
					if(!(city.isEmpty()))
					{
						ui_city_error.setText("");
					}
					
					if(!(state.equals("Choose State")))
					{
						ui_state_error.setText("");
					}
					
				}
				else
				{
					ui_address_error.setText("");
					ui_city_error.setText("");
					ui_state_error.setText("");
				// TODO Auto-generated method stub
				
				String str = "http://webtech2-env.elasticbeanstalk.com/?address="+address+"&city"+city+"&state="+state;		
				String encodeurl = null;
				String url = null;
				
				try {
					encodeurl =(java.net.URLEncoder.encode(address, "UTF-8").replace("+", "%20"));
					url = "http://webtech2-env.elasticbeanstalk.com/?address="+encodeurl+"&city=";
					encodeurl = (java.net.URLEncoder.encode(city, "UTF-8").replace("+", "%20"));
					url = url+encodeurl+"&state=";
					encodeurl = (java.net.URLEncoder.encode(state, "UTF-8").replace("+", "%20"));
					url = url+encodeurl;
					
					//create object of Downloadclass here
					new DownloadData().execute(url);
					
				
						
				} catch (UnsupportedEncodingException e) {
					
					
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				Log.v(url, url);
				
				
				}
				
			}
		});
	
	}
	
	
	
	

	
	
	private class DownloadData extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... uri) {
			// TODO Auto-generated method stub
			
			 HttpClient httpclient = new DefaultHttpClient();
		        HttpResponse response;
		        String responseString = null;
		        try {
		            response = httpclient.execute(new HttpGet(uri[0]));
		            StatusLine statusLine = response.getStatusLine();
		            if(statusLine.getStatusCode() == HttpStatus.SC_OK){
		                ByteArrayOutputStream out = new ByteArrayOutputStream();
		                response.getEntity().writeTo(out);
		                out.close();
		                responseString = out.toString();
		            } else{
		                //Closes the connection.
		                response.getEntity().getContent().close();
		                throw new IOException(statusLine.getReasonPhrase());
		            }
		        } catch (ClientProtocolException e) {
		            //TODO Handle problems..
		        } catch (IOException e) {
		            //TODO Handle problems..
		        }
		        return responseString;
		}
		
		
		   @Override
		protected void onPostExecute(String result) {
		   
            String str = "  1";
			  
            if(result.equals(str))
			  {
                  ui_invalid_url_error.setText("No exact match found--Verify that the given address are correct.");  
				  ui_invalid_url_error.setTypeface(null, Typeface.BOLD);
			  }
			  else
			  {
				  
				   Intent intent = new Intent(getApplicationContext(), Result.class);
				   intent.putExtra("fname", result);
				   startActivity(intent);
		     }
		   }
		
	 }
	 
	
	
	
	
	

	private void findAllViewsById() {
		// TODO Auto-generated method stub
		ui_address = (EditText) findViewById(R.id.address);
		ui_city=(EditText) findViewById(R.id.city);
		ui_state=(Spinner) findViewById(R.id.state);
		ui_address_error=(TextView) findViewById(R.id.address_error);
		ui_city_error=(TextView) findViewById(R.id.city_error);
		ui_state_error=(TextView) findViewById(R.id.state_error);
		ui_search=(Button) findViewById(R.id.search);
		ui_invalid_url_error=(TextView) findViewById(R.id.invalid_url_error);
		image = (ImageView) findViewById(R.id.imageView2);
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
