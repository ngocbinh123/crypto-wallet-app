package hcm.nnbinh.cryptowallet.screens.price_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import hcm.nnbinh.cryptowallet.base.BaseFragment
import hcm.nnbinh.cryptowallet.databinding.FragmentPriceDetailBinding
import hcm.nnbinh.cryptowallet.screens.main.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class PriceDetailFragment : BaseFragment() {
	private val args: PriceDetailFragmentArgs by navArgs()
	private val detailVM: PriceDetailVM by viewModel {
		parametersOf(args.base, args.name)
	}
	private lateinit var binding: FragmentPriceDetailBinding
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentPriceDetailBinding.inflate(inflater, container, false)
		binding.viewModel = detailVM
		binding.lifecycleOwner = this
		return binding.root
	}
	
	override fun setupViews() {
		(requireActivity() as MainActivity).setupActionBar(binding.toolbar, true)
	}
	
	override fun setupObservers() {
	}
}