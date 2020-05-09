package com.prongbang.looperviewpager

import android.os.Parcelable
import androidx.annotation.ColorRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Gallery(
		var id: Int,
		var name: String,
		var image: String,
		@ColorRes var backgroundColor: Int
) : Parcelable