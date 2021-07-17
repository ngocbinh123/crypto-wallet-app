package hcm.nnbinh.cryptowallet.api

import hcm.nnbinh.cryptowallet.api.response.PriceListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
	@GET("/api/v3/price/all_prices_for_mobile")
	suspend fun getAllPrices(
		@Query("counter_currency") counterCurrency: String = "USD"
	): PriceListResponse
}