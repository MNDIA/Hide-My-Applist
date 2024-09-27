package icu.andorid.yeepee.ui.util

import android.widget.Toast
import androidx.annotation.StringRes
import icu.andorid.yeepee.yepApp

fun makeToast(@StringRes resId: Int) {
    Toast.makeText(yepApp, resId, Toast.LENGTH_SHORT).show()
}

fun makeToast(text: CharSequence) {
    Toast.makeText(yepApp, text, Toast.LENGTH_SHORT).show()
}
