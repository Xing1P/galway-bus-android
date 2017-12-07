package com.surrus.galwaybus.data.repository

import com.surrus.galwaybus.model.BusRoute
import com.surrus.galwaybus.model.BusStop
import com.surrus.galwaybus.model.Departure
import com.surrus.galwaybus.model.Location

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface GalwayBusDataStore {
    fun clearBusRoutes(): Completable
    fun saveBusRoutes(bufferoos: List<BusRoute>): Completable
    fun getBusRoutes(): Flowable<List<BusRoute>>
    fun isCached(): Single<Boolean>


    fun getNearestBusStops(location: Location): Flowable<List<BusStop>>
    fun getBusStops(routeId: String): Flowable<List<List<BusStop>>>
    fun getDepartures(stopRef: String): Flowable<List<Departure>>
}