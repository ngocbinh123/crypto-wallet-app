package hcm.nnbinh.cryptowallet.screens.main

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import hcm.nnbinh.cryptowallet.R
import hcm.nnbinh.cryptowallet.base.BaseActivity
import hcm.nnbinh.cryptowallet.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
	private lateinit var binding: ActivityMainBinding
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
		binding.lifecycleOwner = this
	}
}