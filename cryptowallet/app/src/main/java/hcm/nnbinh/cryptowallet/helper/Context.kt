package hcm.nnbinh.cryptowallet.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest.Builder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun Context.networkAvailableFlow(): Flow<Boolean> = callbackFlow {
	val callback = object : NetworkCallback() {
		override fun onAvailable(network: Network) {
			this@callbackFlow.trySend(true).isSuccess
		}
		
		override fun onLost(network: Network) {
			this@callbackFlow.trySend(false).isSuccess
		}
	}
	val manager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	manager.registerNetworkCallback(Builder().run {
		addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
		build()
	}, callback)
	awaitClose { manager.unregisterNetworkCallback(callback) }
}

fun Context.isOnline(): Boolean {
	val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
	return cm.isDefaultNetworkActive
}