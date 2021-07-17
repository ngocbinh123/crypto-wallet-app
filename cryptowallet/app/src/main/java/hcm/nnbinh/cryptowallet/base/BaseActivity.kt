package hcm.nnbinh.cryptowallet.base

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {
	protected abstract fun setupViews()
	protected abstract fun setupObservers()
	
	protected fun setupActionBar(toolbar: Toolbar, showHomeAsUp: Boolean = false) {
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
	}
}