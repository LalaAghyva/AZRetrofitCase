package com.sirketismi.entities.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class PriceHistory(
  val departure: DepartureX?
) : Parcelable