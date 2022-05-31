package com.app.rest;

import org.springframework.web.client.RestTemplate;

public class DistanceMatrixRestClient {

	// google distance matrix api declared here
	private String distanceMatrixApi = new String(
			"https://maps.googleapis.com/maps/api/distancematrix/json?origins=%s&destinations=%s&key=AIzaSyCSyv2CMB3kLGTb8o8hCTyBDqj0AjDvh3Y");

// this method is used to find distance between origin and destination the acctual response structure would look similar to json present at bottom
	public String getDistance(String origins, String destinations) {
		RestTemplate restTemplate = new RestTemplate();
		String url = String.format(distanceMatrixApi, origins, destinations);

		String responseEntity = restTemplate.getForObject(url, String.class);
		System.out.println(responseEntity);
		return responseEntity;
	}

}
//
//{
//	   "destination_addresses" : [ "San Francisco, CA, USA" ],
//	   "origin_addresses" : [ "Seattle, WA, USA" ],
//	   "rows" : [
//	      {
//	         "elements" : [
//	            {
//	               "distance" : {
//	                  "text" : "1,300 km",
//	                  "value" : 1299780
//	               },
//	               "duration" : {
//	                  "text" : "12 hours 48 mins",
//	                  "value" : 46077
//	               },
//	               "status" : "OK"
//	            }
//	         ]
//	      }
//	   ],
//	   "status" : "OK"
//	}