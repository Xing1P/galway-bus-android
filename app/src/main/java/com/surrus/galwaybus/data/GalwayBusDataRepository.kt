package com.surrus.galwaybus.data

import com.surrus.galwaybus.data.source.GalwayBusDataStoreFactory
import com.surrus.galwaybus.domain.repository.GalwayBusRepository
import com.surrus.galwaybus.model.BusRoute
import com.surrus.galwaybus.model.BusStop
import com.surrus.galwaybus.model.Departure
import com.surrus.galwaybus.model.Location
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Provides an implementation of the [GalwayBusRepository] interface for communicating to and from
 * data sources
 */
class GalwayBusDataRepository @Inject constructor(private val factory: GalwayBusDataStoreFactory): GalwayBusRepository {

    override fun getBusRoutes(): Flowable<List<BusRoute>> {
        return factory.retrieveCacheDataStore().isCached()
                .flatMapPublisher {
                    factory.retrieveDataStore(it).getBusRoutes()
                }
                .flatMap {
                    saveBusRoutes(it).toSingle { it }.toFlowable()
                }
    }

    override fun saveBusRoutes(busRoutes: List<BusRoute>): Completable {
        return factory.retrieveCacheDataStore().saveBusRoutes(busRoutes)
    }

    override fun clearBusRoutes(): Completable {
        return factory.retrieveCacheDataStore().clearBusRoutes()
    }


    override fun getNearestBusStops(location: Location): Flowable<List<BusStop>> {
        return factory.retrieveRemoteDataStore().getNearestBusStops(location)
    }

    override fun getBusStops(routeId: String): Flowable<List<List<BusStop>>> {
        return factory.retrieveRemoteDataStore().getBusStops(routeId)
    }

    override fun getDepartures(stopRef: String): Flowable<List<Departure>> {
        return factory.retrieveRemoteDataStore().getDepartures(stopRef)
    }

}
