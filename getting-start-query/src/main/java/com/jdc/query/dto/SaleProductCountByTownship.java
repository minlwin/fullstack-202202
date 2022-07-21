package com.jdc.query.dto;

public record SaleProductCountByTownship(
		int townshipId, 
		String township,
		int productId,
		String product,
		int price,
		long count
		) {

}
