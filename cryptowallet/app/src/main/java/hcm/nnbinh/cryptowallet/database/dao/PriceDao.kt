package hcm.nnbinh.cryptowallet.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hcm.nnbinh.cryptowallet.database.entity.Price

@Dao
interface PriceDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg item: Price)
	
	@Query("DELETE FROM Price")
	fun deleteAll()
	
	@Query("SELECT * FROM PRICE")
	fun getAllLive() :LiveData<List<Price>>
}