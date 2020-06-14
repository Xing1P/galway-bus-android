package com.surrus.galwaybus.common.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BusStop(val stop_id: Int,
                   @SerialName("short_name") val shortName: String,
                   @SerialName("irish_short_name") val irishShortName: String,
                   @SerialName("long_name") val longName: String = "",
                   @SerialName("irish_long_name") val irishLongName: String = "",
                   @SerialName("stop_ref") val stopRef: String = "",
                   val latitude: Double = 0.0,
                   val longitude: Double = 0.0,
                   var times: List<Departure> = emptyList(),
                   val routes: List<String> = emptyList())


