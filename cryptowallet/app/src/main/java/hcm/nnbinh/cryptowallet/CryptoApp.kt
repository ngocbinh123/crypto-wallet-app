package hcm.nnbinh.cryptowallet

import android.app.Application
import com.facebook.stetho.Stetho
import hcm.nnbinh.cryptowallet.di.apiModule
import hcm.nnbinh.cryptowallet.di.databaseModule
import hcm.nnbinh.cryptowallet.di.repoModule
import hcm.nnbinh.cryptowallet.di.retrofitModule
import hcm.nnbinh.cryptowallet.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class CryptoApp : Application() {
	override fun onCreate() {
		super.onCreate()
		if (BuildConfig.DEBUG) {
			Stetho.initializeWithDefaults(this)
		}
		setupKoin()
	}
	
	private fun setupKoin() {
		startKoin {
			androidLogger(Level.DEBUG)
			androidContext(this@CryptoApp)
			modules(
				listOf(
					viewModelModule,
					retrofitModule,
					apiModule,
					repoModule,
					databaseModule
				)
			)
		}
	}
}