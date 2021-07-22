package hcm.nnbinh.cryptowallet.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import hcm.nnbinh.cryptowallet.database.CryptoDatabase
import hcm.nnbinh.cryptowallet.database.dao.PriceDao
import hcm.nnbinh.cryptowallet.helper.DataHelper
import hcm.nnbinh.cryptowallet.helper.getLiveDataValue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PricesDaoTest {
	private lateinit var database: CryptoDatabase
	private lateinit var priceDao: PriceDao
	
	@get:Rule
	var instantTaskExecutorRule = InstantTaskExecutorRule()
	
	@Before
	fun setUp() {
		runBlocking {
			val context = InstrumentationRegistry.getInstrumentation().targetContext
			database = Room.inMemoryDatabaseBuilder(context, CryptoDatabase::class.java).build()
			priceDao = database.getPriceDao()
			priceDao.insert(*DataHelper.priceList.toTypedArray())
		}
	}
	
	@After
	fun tearDown() {
		database.close()
	}
	
	@Test
	fun testGetAllPrices() {
		runBlocking {
			val localPrices = priceDao.getPricesFlow("%%").first()
			Assert.assertEquals(localPrices.size, DataHelper.priceList.size)
			
		}
	}
	
	@Test
	fun testGetPricesByWord() {
		runBlocking {
			val actualPrices = priceDao.getPricesFlow("%in%").first()
			Assert.assertEquals(actualPrices.size, 2)
		}
	}
	
	@Test
	fun testGetPriceByBaseAndName() {
		val expectedPrice = DataHelper.priceList.first()
		val actualPrice =
			priceDao.getPriceLive(expectedPrice.base, expectedPrice.name).getLiveDataValue()
		Assert.assertEquals(expectedPrice, actualPrice)
	}
	
	@Test
	fun testDeleteAll() {
		runBlocking {
			priceDao.deleteAll()
			val actualPrices = priceDao.getPricesFlow("%%").first()
			Assert.assertEquals(actualPrices.size, 0)
		}
	}
	
	@Test
	fun testDeleteAndSave() {
		runBlocking {
			val expectedPrices = DataHelper.priceList.subList(0, 1)
			priceDao.deleteAndInsertAll(expectedPrices)
			val actualPrices = priceDao.getPricesFlow("%%").first()
			Assert.assertEquals(actualPrices.size, expectedPrices.size)
		}
	}
}