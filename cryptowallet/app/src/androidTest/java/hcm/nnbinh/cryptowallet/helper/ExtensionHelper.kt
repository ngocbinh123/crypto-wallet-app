package hcm.nnbinh.cryptowallet.helper

import androidx.lifecycle.LiveData
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@Throws(InterruptedException::class)
fun <T> LiveData<T>.getLiveDataValue(): T {
	val data = arrayOfNulls<Any>(1)
	val latch = CountDownLatch(1)
	this.observeForever { o ->
		data[0] = o
		latch.countDown()
	}
	latch.await(2, TimeUnit.SECONDS)
	
	@Suppress("UNCHECKED_CAST")
	return data[0] as T
}
