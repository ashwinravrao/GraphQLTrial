package com.ashwinrao.graphqltrial.util

import android.content.Context
import android.util.TypedValue

object UnitConversion {

    fun dpToPx(context: Context, dp: Float): Int = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        context.resources.displayMetrics
    ).toInt()

}