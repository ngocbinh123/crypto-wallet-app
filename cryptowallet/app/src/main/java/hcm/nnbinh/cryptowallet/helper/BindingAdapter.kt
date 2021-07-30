package hcm.nnbinh.cryptowallet.helper

import android.annotation.SuppressLint
import android.util.Base64
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import hcm.nnbinh.cryptowallet.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@BindingAdapter("iconUrl")
fun showRemoteIcon(v: ImageView, iconUrl: String? = null) {
	if (!iconUrl.isNullOrEmpty()) {
		Glide.with(v.context)
			.load(iconUrl)
			.thumbnail(0.1f)
			.diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
			.fitCenter()
			.into(v)
	}
}

@SuppressLint("SetTextI18n")
@BindingAdapter("price")
fun showPrice(v: TextView, price: String? = null) {
	if (price != null) {
		v.text = "$${price}"
	}
}

@BindingAdapter("isBookmark")
fun activeBookmark(v: ImageView, isBookmark: Boolean? = null) {
	if (isBookmark == true) {
		v.setImageResource(R.drawable.ic_bookmark_active)
	} else {
		v.setImageResource(R.drawable.ic_bookmark_deactive)
	}
}