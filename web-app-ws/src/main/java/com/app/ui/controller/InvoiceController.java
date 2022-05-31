package com.app.ui.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rest.DistanceMatrixRestClient;
import com.app.shared.utility.ShippingCostUtility;
import com.ui.model.request.InvoiceModel;
import com.ui.model.request.MaskModel;
import com.ui.model.response.InvoiceRest;

@RestController
@RequestMapping("invoice")
public class InvoiceController {
	@Autowired
	ShippingCostUtility shippingCostUtility;

	DistanceMatrixRestClient client = new DistanceMatrixRestClient();
	static Integer perBoxCost = 25;
	static String origin = "Von Rollstrasse 10,4600 Olten";

	@PostMapping(path = "/getBillingDetail")
	// this API is used for calculation of bill
	public InvoiceRest getBillingDetail(@RequestBody InvoiceModel invoiceModel) {
		// calculate per box cost 
		Double blackBoxCost = 0.00;
		Integer blackPallet = 0;
		if (invoiceModel.getBlack() != null && invoiceModel.getBlack().getBoxes() > 0) {
			blackBoxCost = getBoxCost(invoiceModel.getBlack());
			blackPallet = getPalletCount(invoiceModel.getBlack());
		}
		// calculate per box cost 
		Double blueBoxCost = 0.00;
		Integer bluePallet = 0;
		if (invoiceModel.getBlue() != null && invoiceModel.getBlue().getBoxes() > 0) {
			blueBoxCost = getBoxCost(invoiceModel.getBlue());
			bluePallet = getPalletCount(invoiceModel.getBlue());
		}
		// calculate per box cost 
		Double pinkBoxCost = 0.00;
		Integer pinkPallet = 0;
		if (invoiceModel.getPink() != null && invoiceModel.getPink().getBoxes() > 0) {
			pinkBoxCost = getBoxCost(invoiceModel.getPink());
			pinkPallet = getPalletCount(invoiceModel.getPink());
		}
		// calculate per box cost 
		Double yellowBoxCost = 0.00;
		Integer yellowPallet = 0;
		if (invoiceModel.getYellow() != null && invoiceModel.getYellow().getBoxes() > 0) {
			yellowBoxCost = getBoxCost(invoiceModel.getYellow());
			yellowPallet = getPalletCount(invoiceModel.getYellow());
		}
		Integer totalPallets = blackPallet + bluePallet + pinkPallet + yellowPallet;

		Double totalBoxesCost = Double.sum(Double.sum(blueBoxCost, blackBoxCost),
				Double.sum(pinkBoxCost, yellowBoxCost));

		String destination = invoiceModel.getDestination();
		Double blueBoxshippingCost = 0.00;
		Double blackBoxshippingCost = 0.00;
		Double pinkBoxshippingCost = 0.00;
		Double yellowBoxshippingCost = 0.00;
		Double totalShippingCost = 0.00;
		if (destination != null) {
			String distanceResponse = client.getDistance(origin, destination);
			Long distanceInMeter = parseDistnceResponse(distanceResponse);
			Double distanceInKm = getDistanceInKm(distanceInMeter);
			if (bluePallet > 0) {
				blueBoxshippingCost = shippingCostUtility.getshippingCost(distanceInKm, bluePallet);
			}
			if (blackPallet > 0) {
				blackBoxshippingCost = shippingCostUtility.getshippingCost(distanceInKm, blackPallet);
			}
			if (pinkPallet > 0) {
				pinkBoxshippingCost = shippingCostUtility.getshippingCost(distanceInKm, pinkPallet);
			}
			if (yellowPallet > 0) {
				yellowBoxshippingCost = shippingCostUtility.getshippingCost(distanceInKm, yellowPallet);
			}
			
			// find the total palletes shipping cost
			if (totalPallets > 0) {
				totalShippingCost = shippingCostUtility.getshippingCost(distanceInKm, totalPallets);
			}
//			totalShippingCost = Double.sum(Double.sum(blueBoxshippingCost, blackBoxshippingCost),
//					Double.sum(pinkBoxshippingCost, yellowBoxshippingCost));
			System.out.println("total distance in km " + distanceInKm);
			System.out.println("total shipping Cost " + totalShippingCost);
		}
		// prepare the response object here
		InvoiceRest response = new InvoiceRest();
		response.setBlackCost(blackBoxCost);
		response.setBlueCost(blueBoxCost);
		response.setPinkCost(pinkBoxCost);
		response.setYellowCost(yellowBoxCost);
		response.setProductCost(totalBoxesCost);
		response.setTotalCost(totalBoxesCost + totalShippingCost);

		response.setBlackShippingCost(blackBoxshippingCost);
		response.setBlueShippingCost(blueBoxshippingCost);
		response.setPinkShippingCost(pinkBoxshippingCost);
		response.setYellowShippingCost(yellowBoxshippingCost);

		response.setShippingCost(totalShippingCost);
		return response;
	}
// convert the string  response received from google api to json object and retrive the Km information
	private Long parseDistnceResponse(String distanceResponse) {
		JSONObject jsonObject = new JSONObject(distanceResponse);
		if (jsonObject != null && jsonObject.getJSONArray("rows").length() > 0) {
			JSONObject rows = (JSONObject) jsonObject.getJSONArray("rows").get(0);
			JSONArray elements = rows.getJSONArray("elements");
			if (elements != null && elements.length() > 0) {
				JSONObject ele = (JSONObject) elements.get(0);
				JSONObject distance = (JSONObject) ele.get("distance");
				Long meter = Long.parseLong(String.valueOf(distance.get("value")));
				return meter;
			}
		}
		return 0l;
	}

	// calculates requred pallet
	private int getPalletCount(MaskModel maskObj) {
		Double boxes = maskObj.getBoxes();
		return (int) Math.ceil(boxes / 800.00);
	}

	// calulate the cost per box
	private Double getBoxCost(MaskModel maskObj) {
		Double boxes = maskObj.getBoxes();

		Double boxCostDouble = (Double) (perBoxCost * boxes);
		return boxCostDouble;
	}
	// get the distance in Km  from meter
	private Double getDistanceInKm(long meter) {
		if (meter <= 999) {
			return 1.0;
		}
		Double km = Math.ceil(meter / 1000);
		return km;
	}
}
