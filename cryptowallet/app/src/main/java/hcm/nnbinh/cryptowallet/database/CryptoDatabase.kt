package hcm.nnbinh.cryptowallet.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hcm.nnbinh.cryptowallet.database.dao.PriceDao
import hcm.nnbinh.cryptowallet.database.entity.Price

@Database(
	entities = [Price::class],
	version = 1, exportSchema = false
)
abstract class CryptoDatabase : RoomDatabase() {
	abstract fun getPriceDao(): PriceDao
}