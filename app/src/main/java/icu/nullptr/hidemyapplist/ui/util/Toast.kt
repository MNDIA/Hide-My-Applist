package icu.nullptr.fgol.ui.util

import android.widget.Toast
import androidx.annotation.StringRes
import icu.nullptr.fgol.ypwApp

fun makeToast(@StringRes resId: Int) {
    Toast.makeText(ypwApp, resId, Toast.LENGTH_SHORT).show()
}

fun makeToast(text: CharSequence) {
    Toast.makeText(ypwApp, text, Toast.LENGTH_SHORT).show()
}
