package hcm.nnbinh.cryptowallet.base

import android.widget.Toast
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import hcm.nnbinh.cryptowallet.R
import hcm.nnbinh.cryptowallet.objects.Command
import java.net.UnknownHostException

abstract class BaseActivity : AppCompatActivity() {
	fun setupActionBar(toolbar: Toolbar, showHomeAsUp: Boolean = false) {
		setSupportActionBar(toolbar)
		supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
		if (showHomeAsUp) {
			toolbar.setNavigationOnClickListener { onBackPressed() }
		}
	}
	
	@CallSuper
	open fun processCommand(cmd: Command) {
		when (cmd) {
			is Command.ShowError -> handleError(cmd.t)
		}
	}
	
	open fun handleError(t: Throwable) {
		val error = if (t is UnknownHostException) {
			getString(R.string.you_are_offline)
		} else {
			t.message ?: t.toString()
		}
		Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
	}
}