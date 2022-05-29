package com.app.shared.utility;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class ShippingCostUtility {
	List<Double> Dis30 = Arrays.asList(58.65, 87.00, 114.50, 137.10, 160.80, 181.65, 201.45, 220.30, 238.35, 255.35,
			271.45, 286.55);
	List<Double> Dis60 = Arrays.asList(67.00, 99.40, 130.85, 156.75, 183.80, 207.55, 230.25, 251.85, 272.40, 291.90,
			310.25, 327.50);
	List<Double> Dis90 = Arrays.asList(75.40, 111.90, 147.15, 176.35, 206.65, 233.50, 259.00, 283.35, 306.45, 328.40,
			348.95, 368.40);
	List<Double> Dis120 = Arrays.asList(83.75, 124.30, 163.50, 195.90, 229.70, 259.45, 287.70, 314.85, 340.50, 364.75,
			387.80, 409.40);
	List<Double> Dis150 = Arrays.asList(92.15, 136.70, 179.80, 215.50, 252.60, 285.30, 316.50, 346.30, 374.50, 401.25,
			426.50, 450.35);
	List<Double> Dis180 = Arrays.asList(100.55, 149.20, 196.15, 235.15, 275.60, 311.25, 345.35, 377.75, 408.60, 437.75,
			465.35, 491.25);
	List<Double> Dis210 = Arrays.asList(108.95, 161.55, 212.50, 253.90, 298.60, 337.15, 374.15, 409.30, 442.60, 474.25,
			504.05, 532.15);
	List<Double> Dis240 = Arrays.asList(117.30, 174.05, 228.90, 274.25, 321.55, 363.15, 402.90, 440.75, 476.70, 510.75,
			542.90, 573.15);
	List<Double> Dis270 = Arrays.asList(125.65, 186.45, 245.30, 293.90, 344.55, 389.10, 431.70, 472.20, 510.75, 547.20,
			581.60, 614.05);
	List<Double> Dis300 = Arrays.asList(134.05, 198.85, 261.60, 313.45, 367.50, 415.00, 460.45, 503.70, 544.70, 583.65,
			620.35, 655.05);
	List<Double> Dis330 = Arrays.asList(142.40, 211.30, 277.95, 332.95, 390.45, 441.05, 489.20, 535.20, 578.85, 620.15,
			659.20, 695.95);
	List<Double> Dis360 = Arrays.asList(150.75, 223.80, 294.30, 352.65, 413.45, 466.90, 518.00, 566.70, 612.85, 656.65,
			697.95, 736.90);
	Map<Integer, List<Double>> palletToKmCost = new HashMap<>();

	@PostConstruct
	void initMap() {
	// in memory distance to km shipping costing
		palletToKmCost.put(1, Dis30);
		palletToKmCost.put(2, Dis60);
		palletToKmCost.put(3, Dis90);
		palletToKmCost.put(4, Dis120);
		palletToKmCost.put(5, Dis150);
		palletToKmCost.put(6, Dis180);
		palletToKmCost.put(7, Dis210);
		palletToKmCost.put(8, Dis240);
		palletToKmCost.put(9, Dis270);
		palletToKmCost.put(10, Dis300);
		palletToKmCost.put(11, Dis330);
		palletToKmCost.put(12, Dis360);
	}

	public Double getshippingCost(Double km, Integer pallet) {
		// incase distance is greater than 360 km and number of pallets are more than 12 
		// then default shipping cost is 1000.00
		if (km > 360 || pallet > 12) {
			return 1000.00;
		}
		Integer index = (int) Math.ceil(km / 30);
		List<Double> list = palletToKmCost.get(index);
		Double cost = list.get(pallet-1);
		return cost;
	}
}
