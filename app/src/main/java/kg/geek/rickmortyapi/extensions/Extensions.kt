package kg.geek.rickmortyapi.extensions

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import kg.geek.rickmortyapi.R

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.load(uri: String) {
    Glide.with(this)
        .load(uri)
        .centerCrop()
        .placeholder(R.drawable.ic_progress_avatar)
        .into(this)
}

var View.visible: Boolean
    get() = visibility == View.GONE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }
