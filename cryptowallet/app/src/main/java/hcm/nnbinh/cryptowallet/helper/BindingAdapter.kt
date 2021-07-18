package hcm.nnbinh.cryptowallet.helper

import android.annotation.SuppressLint
import android.util.Base64
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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