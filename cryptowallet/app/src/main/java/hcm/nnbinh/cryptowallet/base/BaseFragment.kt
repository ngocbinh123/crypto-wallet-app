package hcm.nnbinh.cryptowallet.base

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import hcm.nnbinh.cryptowallet.objects.Command

abstract class BaseFragment : Fragment() {
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupViews()
		setupObservers()
	}
	
	@CallSuper
	open fun processCommand(cmd: Command) {
		(requireActivity() as BaseActivity).processCommand(cmd)
	}
	
	protected abstract fun setupViews()
	protected abstract fun setupObservers()
}