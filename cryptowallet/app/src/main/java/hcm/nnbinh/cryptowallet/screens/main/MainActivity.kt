package hcm.nnbinh.cryptowallet.screens.main

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import hcm.nnbinh.cryptowallet.R
import hcm.nnbinh.cryptowallet.base.BaseActivity
import hcm.nnbinh.cryptowallet.databinding.ActivityMainBinding
import hcm.nnbinh.cryptowallet.helper.networkAvailableFlow
import hcm.nnbinh.cryptowallet.objects.Command.ShowError
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {
	private val mainVM: MainVM by viewModel()
	private lateinit var binding: ActivityMainBinding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.lifecycleOwner = this
		setupViews()
		setupObservers()
		lifecycleScope.launchWhenResumed {
			networkAvailableFlow().collect {
				mainVM.onNetworkStatusChange(it)
			}
		}
		mainVM.startGetRemotePriceList()
	}
	
	override fun setupViews() {
		setSupportActionBar(binding.toolbar)
	}
	
	override fun setupObservers() {
		mainVM.getCommand().observe(this) { cmd ->
			when(cmd) {
				is ShowError -> Toast.makeText(this, cmd.e.message ?: cmd.e.toString(), Toast.LENGTH_SHORT).show()
			}
		}
	}
}