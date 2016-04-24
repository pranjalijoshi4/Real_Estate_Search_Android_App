package com.example.version50;



import org.json.JSONException;
import org.json.JSONObject;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class Result extends Activity  {
	 ActionBar.Tab basic_Tab, chart_Tab;
     Fragment infoFragmentTab = new BasicInfo_Fragment();
     Fragment chartFragmentTab = new Chart_Fragment();
	    
	static String data[] = new String[20];
	static String titles[];  
	static String Address;
	static String Date_Sign[];
	static String Addrlink;
	static String Chart_url[];
	static  String jsondataresult;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Intent intent = getIntent();
	    jsondataresult = intent.getStringExtra("fname");
		
	    JSONObject reader;
		try {
			reader = new JSONObject(jsondataresult);
			JSONObject result  = reader.getJSONObject("result");
			JSONObject images  = reader.getJSONObject("images");
			JSONObject chart  = reader.getJSONObject("chart");
			JSONObject url_chart1  = chart.getJSONObject("1year");
			JSONObject url_chart2  = chart.getJSONObject("5years");
			JSONObject url_chart3  = chart.getJSONObject("10years");

			Chart_url = new String[]{url_chart1.getString("url"),
					 url_chart2.getString("url"),
					 url_chart3.getString("url")};
			 
			 Address = result.getString("street")+", "+
					 result.getString("city")+", "+
					 result.getString("state")+"-"+
					 result.getString("zipcode");
			
			 Date_Sign = new String[]{ 
			 result.getString("estimatedLastUpdate"),
			 result.getString("estimateValueChangeSign"),
			 result.getString("restimateLastUpdate"),
			 result.getString("restimateValueChangeSign")};
			 
			 Addrlink = result.getString("homedetails");
			
			
			 if(result.getString("useCode").isEmpty())
			 {
				 data[0] = "N/A"; 
			 }
			 else
			 {
				 data[0]=result.getString("useCode");
			 }
			 if( result.getString("yearBuilt").isEmpty())
			 {
				data[1]="N/A";
			 }
			 else
			 {
				data[1] = result.getString("yearBuilt");
			 }
			 

			 if(result.getString("lotSizeSqFt").equals("0.00"))
					data[2] = "N/A"; 
			 else
					data[2] = result.getString("lotSizeSqFt")+" sq.ft."; 
			 
			 if(result.getString("finishedSqFt").equals("0.00"))
				 	data[3] ="N/A";
			 else
				 	data[3] = result.getString("finishedSqFt")+ " sq. ft.";			 
			 
			 if(result.getString("bathrooms").isEmpty())
					 data[4]="N/A";
			 else
				 data[4] = result.getString("bathrooms");
			 
			
				 
			 if(result.getString("bedrooms").isEmpty())
					 data[5] = "N/A";
			 else
				 data[5] = result.getString("bedrooms");
					 
			
			 if(result.getString("taxAssessmentYear").isEmpty())
					data[6] = "N/A";
			 else
				 data[6] = result.getString("taxAssessmentYear");
			
			 if(result.getString("taxAssessment").equals("0.00"))
					data[7] = "N/A";
			 else
				 	data[7]="$"+result.getString("taxAssessment");
			 
			 if(result.getString("lastSoldPrice").equals("0.00"))
				 	data[8] = "N/A";
			 else
				    data[8] ="$"+result.getString("lastSoldPrice");			
		
			 if(result.getString("lastSoldDate").isEmpty())
				 	data[9] = "N/A";
			 else
				 data[9]  =result.getString("lastSoldDate");
				
			 if(result.getString("estimateAmount").equals("0.00"))
					 data[10] = "N/A";
			 else
				     data[10] = "$" + result.getString("estimateAmount");
					 
			
			 if(result.getString("estimateValueChange").equals("0.00"))
				 	data[11] = "N/A";
			 else
				 	data[11] = "$" + result.getString("estimateValueChange");
			 
			 String str1,str2,str3,str4;
			 if(result.getString("estimateValuationRangeLow").equals("0.00"))
					 str1 = "N/A";
			 else
				 	 str1 = "$" +result.getString("estimateValuationRangeLow");
					 
			 
			 
			 if(result.getString("estimateValuationRangeHigh").equals("0.00"))
					 str2 = "N/A";	 
			 else
				 	 str2 = "$" + result.getString("estimateValuationRangeHigh");
			 
			 data[12] = str1+"-"+str2;
			 
			 if(result.getString("restimateAmount").equals("0.00"))
					 data[13]="N/A";		 
			 else
				 	data[13] = "$" +result.getString("restimateAmount");
			 
			
			
			 if(result.getString("restimateValueChange").equals("0.00"))
				 	data[14] = "N/A";
			 else
				 data[14] = "$" +result.getString("restimateValueChange");
			 
			 if(result.getString("restimateValuationRangeLow").equals("0.00"))
				 str3 ="N/A";
			 else
				 str3 = "$" +result.getString("restimateValuationRangeLow");
			 
			 if(result.getString("restimateValuationRangeHigh").equals("0.00"))
				 str4 ="N/A";
			else
				 str4="$"+result.getString("restimateValuationRangeHigh");
			
			data[15] = str3 +"-"+str4;
			 
		
			titles = new String[] {"Property Type", "Year Built", "Lot Size", "Finished Area", "Bathrooms", "Bedrooms", "Tax Assessment Year", "Tax Assessment", "Last Sold Price", "Last Sold Date", "Zestimate Property Estimate", "30 Days Overall Change", "All Time Property Range", "Rent Zestimate", "30 Days Rent Change", "All Time Rent Range" };
							
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  
		 //  Toast(homedetails+".."+city);
		 
	  
		
	// Asking for the default ActionBar element that our platform supports.
	 ActionBar actionBar = getActionBar();
       
     // Screen handling while hiding ActionBar icon.
     actionBar.setDisplayShowHomeEnabled(false);

     // Screen handling while hiding Actionbar title.
     actionBar.setDisplayShowTitleEnabled(false);

     // Creating ActionBar tabs.
     actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

     // Setting custom tab icons.
     basic_Tab = actionBar.newTab().setText("Basic Info");
     chart_Tab = actionBar.newTab().setText("Historical ZEstimate");
  
      
     // Setting tab listeners.
     basic_Tab.setTabListener(new TabListener(infoFragmentTab));
     chart_Tab.setTabListener(new TabListener(chartFragmentTab));
   //basic_Tab.setTabListener(new com.example.propertysearchzillow.TabListener(fragment))
     
     // Adding tabs to the ActionBar.
     actionBar.addTab(basic_Tab);
     actionBar.addTab(chart_Tab);
		
	}

}