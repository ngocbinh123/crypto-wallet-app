package hcm.nnbinh.cryptowallet.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import hcm.nnbinh.cryptowallet.database.entity.Price
import kotlinx.coroutines.flow.Flow

@Dao
interface PriceDao {
	@Insert(onConflict = OnConflictStrategy.REPLACE)
	fun insert(vararg item: Price)
	
	@Query("DELETE FROM Price")
	fun deleteAll()
	
	@Query("SELECT * FROM PRICE WHERE name LIKE :query")
	fun getPricesFlow(query: String): Flow<List<Price>>
	
	@Query("SELECT * FROM PRICE WHERE base = :base AND name = :name")
	fun getPriceLive(base: String, name: String): LiveData<Price?>
	
	@Transaction
	fun deleteAndInsertAll(remoteList: List<Price>) {
		deleteAll()
		insert(*remoteList.toTypedArray())
	}
	
	@Query("UPDATE Price SET is_bookmark = :isBookmark WHERE base = :base AND name = :name")
	fun updateBookMark(base: String, name: String, isBookmark: Boolean)
}