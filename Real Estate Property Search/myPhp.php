
<?php
header('Access-Control-Allow-Origin: *');
header('Access-Control-Allow-Methods: GET, POST');
?>
  <?php    
   
	$address = $_GET['address'];
	$city = $_GET['city'];
	$state = $_GET['state'];
       $citystate = $city.", ".$state;	
       $data = array('zws-id'=>'X1-ZWz1dxnwibm80b_9zlkj',
      'address'=>$address,
      'citystatezip'=>$citystate,
      'rentzestimate'=>'true');
	$halfurl = http_build_query($data) ;
	$url = 'http://www.zillow.com/webservice/GetDeepSearchResults.htm?'.$halfurl;
	$xml=simplexml_load_file($url);
            
if(isset($xml)){
	//print_r($xml);
	if($xml->message->code[0] != 0)
	{
		$errormsg = 1;
		echo $errormsg;
	}
	else
	{
		date_default_timezone_set('America/Los_Angeles'); 	
		$headerstreet = (string) $xml->response->results->result->address->street;
		$headerszip = (string) $xml->response->results->result->address->zipcode;
		$headercity = (string) $xml->response->results->result->address->city;
		$headerstate = (string) $xml->response->results->result->address->state;
		$headerlink = (string)$xml->response->results->result->links->homedetails;	
        	$latitude = (string)$xml->response->results->result->address->latitude;	
        	$longitude = (string)$xml->response->results->result->address->longitude;	
	        $useCode = (string) $xml->response->results->result->useCode;
	        $lastSoldPrice = number_format((double)$xml->response->results->result->lastSoldPrice,2);
	        $yearbuilt = (string)$xml->response->results->result->yearBuilt;
	      
	        $d3 = (string)$xml->response->results->result->lastSoldDate;
	        $date3=date_create_from_format("m/d/Y",$d3);
	        if($date3 != false)
            		$lastSoldDate = date_format($date3,"j-M-Y");
        	else
            		$lastSoldDate = "";
        
        	$lotsize=number_format((double)$xml->response->results->result->lotSizeSqFt,2); 
        
        
	        $d1 =  (string)$xml->response->results->result->zestimate->{'last-updated'};
	        $date1=date_create_from_format("m/d/Y",$d1); 
	        if($date1 != false)
	            $estimatelastupdate = date_format($date1,"j-M-Y");
	        else
	            $estimatelastupdate="";
	    
           
	        $estimateamount=number_format((double)$xml->response->results->result->zestimate->amount,2);
	        $finishedarea = number_format((double)$xml->response->results->result->finishedSqFt ,2);
	        
	     
	        $down = "http://cs-server.usc.edu:45678/hw/hw6/down_r.gif";
	        $up = "http://cs-server.usc.edu:45678/hw/hw6/up_g.gif";
	        
    
	        $e1 = $xml->response->results->result->zestimate->valueChange;
	        if (substr(strval($e1), 0, 1) == "-")
	        {
	            $estimatevaluechange = "-";
	              settype($e1, "integer"); $e1=str_replace('-','',$e1); 
	              $estval = number_format($e1,2);
	        }
	        else
	        {
	            $estimatevaluechange = "+";
	            $estval = number_format((double)$e1,2);
	        }
    
	        $bathrooms=(string)$xml->response->results->result->bathrooms;
	        $valrangelow= number_format((double)$xml->response->results->result->zestimate->valuationRange->low,2);        
	        $valrangehigh= number_format((double)$xml->response->results->result->zestimate->valuationRange->high,2);
	        $bedrooms =(string)$xml->response->results->result->bedrooms;
	
	     
	        $d2 = (string)$xml->response->results->result->rentzestimate->{'last-updated'};
	        $date2=date_create_from_format("m/d/Y",$d2);
	        if($date2 != false)
	            $rentestimate = date_format($date2,"j-M-Y");
	        else
	            $rentestimate="";
	      
	        $rentestamt = number_format((double)$xml->response->results->result->rentzestimate->amount,2);
	        $taxyear = (string)$xml->response->results->result->taxAssessmentYear;
	        $rentestvalrangelow = number_format((double)$xml->response->results->result->rentzestimate->valuationRange->low,2);
	        $rentestvalrangehigh = number_format((double)$xml->response->results->result->rentzestimate->valuationRange->high,2); 
	        
        	 $e2=$xml->response->results->result->rentzestimate->valueChange;
		 	if (substr(strval($e2), 0, 1) == "-")
          		{
            			$rentestimatevaluechange = "-";
			        settype($e2, "integer"); $e2=str_replace('-','',$e2); 
			        $rentestval = number_format($e2,2);
          		}
        		else
        		{
            			$rentestimatevaluechange = "+";
            			$rentestval = number_format((double)$e2,2);
        		}
        
        
        		$taxasses = number_format((double)$xml->response->results->result->taxAssessment,2);
        		$zpid=$xml->response->results->result->zpid;
        
        
    			$urlimagexml = 'http://www.zillow.com/webservice/GetUpdatedPropertyDetails.htm?zws-id=X1-ZWz1dxnwibm80b_9zlkj&zpid='.$zpid;
        
           		$urlimagexml = 'http://www.zillow.com/webservice/GetUpdatedPropertyDetails.htm?zws-id=X1-ZWz1dxnwibm80b_9zlkj&zpid='.$zpid;
        		$xmlimage=simplexml_load_file($urlimagexml);
        		$imagelink=(string)$xmlimage->response->links->photoGallery;
        		$image1link=(string)$xmlimage->response->images->image->url;
     
        		$urlchart1 = 'http://www.zillow.com/webservice/GetChart.htm?zws-id=X1-ZWz1dxnwibm80b_9zlkj&unit-type=percent&zpid='.$zpid.'&width=600&height=300'.'&chartDuration=1year';
       
        		$urlchart1xml=simplexml_load_file($urlchart1);
        		$urlchart1link=(string)$urlchart1xml->response->url;
      
        
         		$urlchart2 = 'http://www.zillow.com/webservice/GetChart.htm?zws-id=X1-ZWz1dxnwibm80b_9zlkj&unit-type=percent&zpid='.$zpid.'&width=600&height=300'.'&chartDuration=5years';
        
        
        		$urlchart2xml=simplexml_load_file($urlchart2);
        		$urlchart2link=(string)$urlchart2xml->response->url;
      
        
            		$urlchart3 = 'http://www.zillow.com/webservice/GetChart.htm?zws-id=X1-ZWz1dxnwibm80b_9zlkj&unit-type=percent&zpid='.$zpid.'&width=600&height=300'.'&chartDuration=10years';
        
        
        		$urlchart3xml=simplexml_load_file($urlchart3);
		 	$urlchart3link=(string)$urlchart3xml->response->url;
    
        		$arr1 = array('result'=> 
                     [
                      'homedetails' => $headerlink,
                      'street' => $headerstreet,    
                      'city' => $headercity, 
                      'state'=> $headerstate, 
                      'zipcode' => $headerszip, 
                      'latitute' => $latitude, 
                      'longitude'=> $longitude, 
                      'useCode'=> $useCode, 
                      'lastSoldPrice' => $lastSoldPrice, 
                      'yearBuilt' => $yearbuilt, 
                      'lastSoldDate'=> $lastSoldDate, 
                      'lotSizeSqFt' => $lotsize, 
                      'estimatedLastUpdate'=>$estimatelastupdate, 
                      'estimateAmount' => $estimateamount, 
                      'finishedSqFt'=>$finishedarea, 
                      'estimateValueChangeSign' => $estimatevaluechange, 
                      'imgn' => $down, 
                      'imgp' => $up, 
                      'estimateValueChange' =>  $estval,
                      'bathrooms' => $bathrooms,          
                      'estimateValuationRangeLow'=>$valrangelow,
                      'estimateValuationRangeHigh'=>$valrangehigh,
                      'bedrooms'=>$bedrooms,
                      'restimateLastUpdate'=> $rentestimate,
                      'restimateAmount'=>$rentestamt,
                      'taxAssessmentYear'=> $taxyear,
                      'restimateValueChangeSign'=>$rentestimatevaluechange,
                      'restimateValueChange'=> $rentestval,
                      'taxAssessment'=> $taxasses,
                      'restimateValuationRangeLow'=> $rentestvalrangelow,
                      'restimateValuationRangeHigh'=>$rentestvalrangehigh
                     ], 
                      'images' => 
                      [
                          'photoGallery'=>$imagelink,
                          'image1'=>$image1link
                      ],
                      'chart'=>
                      [
                          '1year'=> ['url'=>$urlchart1link],
                          '5years'=>['url'=>$urlchart2link],
                          '10years'=>['url'=>$urlchart3link]
                      ]
                     );
    
     
        	$jsondata =  json_encode($arr1,  JSON_PRETTY_PRINT);
        	echo $jsondata;
	}
}
?>