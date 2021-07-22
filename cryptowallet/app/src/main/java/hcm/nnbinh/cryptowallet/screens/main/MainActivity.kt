package hcm.nnbinh.cryptowallet.screens.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import hcm.nnbinh.cryptowallet.R
import hcm.nnbinh.cryptowallet.base.BaseActivity
import hcm.nnbinh.cryptowallet.databinding.ActivityMainBinding
import hcm.nnbinh.cryptowallet.helper.isOnline
import hcm.nnbinh.cryptowallet.helper.networkAvailableFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

@ExperimentalCoroutinesApi
class MainActivity : BaseActivity() {
	private val mainVM: MainVM by viewModel()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.lifecycleOwner = this
		
		lifecycleScope.launchWhenResumed {
			networkAvailableFlow().collect { isOnline ->
				mainVM.startOrStopGetPriceListSchedule(isOnline)
			}
		}
		
		mainVM.getCommand().observe(this) { cmd -> processCommand(cmd)}
	}
	
	override fun onRestart() {
		super.onRestart()
		mainVM.startOrStopGetPriceListSchedule(this.isOnline())
	}
	
	override fun onStop() {
		mainVM.startOrStopGetPriceListSchedule(false)
		super.onStop()
	}
}