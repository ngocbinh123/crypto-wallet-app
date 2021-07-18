package hcm.nnbinh.cryptowallet.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseVH<T>(v: View) : RecyclerView.ViewHolder(v) {
	abstract fun onBind(item: T)
}