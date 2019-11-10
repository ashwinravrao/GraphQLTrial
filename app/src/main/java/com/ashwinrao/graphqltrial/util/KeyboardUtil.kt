package com.ashwinrao.graphqltrial.util

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager
import org.jetbrains.annotations.NotNull

object KeyboardUtil {

    fun detectKeyboard(@NotNull root: View): Boolean {
        val r = Rect()
        root.getWindowVisibleDisplayFrame(r)
        val screenHeight = root.rootView.height
        val keypadHeight = screenHeight - r.bottom
        return keypadHeight > screenHeight * 0.15
    }

    fun hideKeyboard(context: Context, view: View) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}