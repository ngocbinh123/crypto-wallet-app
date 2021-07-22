package hcm.nnbinh.cryptowallet

import android.app.Application
import com.facebook.stetho.Stetho
import hcm.nnbinh.cryptowallet.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level.DEBUG
import org.koin.core.logger.Level.NONE

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
			androidLogger(if (BuildConfig.DEBUG) DEBUG else NONE)
			androidContext(this@CryptoApp)
			modules(appModules)
		}
	}
}