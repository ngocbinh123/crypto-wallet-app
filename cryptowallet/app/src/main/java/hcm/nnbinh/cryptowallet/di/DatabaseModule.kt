package hcm.nnbinh.cryptowallet.di

import android.app.Application
import androidx.room.Room
import hcm.nnbinh.cryptowallet.database.CryptoDatabase
import org.koin.dsl.module

val databaseModule = module {
	single {
		cryptoDatabase(get())
	}
}

private fun cryptoDatabase(app: Application): CryptoDatabase {
	return Room.databaseBuilder(app, CryptoDatabase::class.java, "CryptoDatabase")
		.build()
}