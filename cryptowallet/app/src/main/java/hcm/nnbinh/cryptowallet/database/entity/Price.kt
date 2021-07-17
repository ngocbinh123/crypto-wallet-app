package hcm.nnbinh.cryptowallet.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "Price", primaryKeys = ["base","name"])
data class Price(
	@ColumnInfo(name="sell_price")
	val sellPrice: String,
	
	@ColumnInfo(name="icon")
	val icon: String,
	
	@ColumnInfo(name="name")
	val name: String,
	
	@ColumnInfo(name="buy_price")
	val buyPrice: String,
	
	@ColumnInfo(name="counter")
	val counter: String,
	
	@ColumnInfo(name="base")
	val base: String
)