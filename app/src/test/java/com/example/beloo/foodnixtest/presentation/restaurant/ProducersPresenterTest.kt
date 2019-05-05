package com.example.beloo.foodnixtest.presentation.restaurant

import android.location.Location
import com.example.beloo.foodnixtest.data.repository.producer.ProducersRepository
import com.example.beloo.foodnixtest.domain.producer.LocationUseCase
import com.example.beloo.foodnixtest.domain.producer.ProducersListUseCase
import com.example.beloo.foodnixtest.presentation.SubscriptionCache
import com.example.beloo.foodnixtest.presentation.SubscriptionCacheImpl
import com.karumi.dexter.DexterBuilder
import com.karumi.dexter.listener.PermissionGrantedResponse
import dagger.Lazy
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ProducersPresenterTest {

    private lateinit var testable: ProducersContract.Presenter

    @Mock
    lateinit var repository: ProducersRepository
    @Mock
    lateinit var locationUseCase: LocationUseCase
    lateinit var subscriptionCache: SubscriptionCache
    @Mock
    lateinit var postCodeListUseCase: ProducersListUseCase
    @Mock
    lateinit var locationPermission: Lazy<DexterBuilder>
    @Mock
    lateinit var dexter: DexterBuilder
    private val scheduler: Scheduler = Schedulers.trampoline()
    @Mock
    lateinit var location: Location
    @Mock
    lateinit var view: ProducersContract.View

    private val postCode = "se19"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        subscriptionCache = SubscriptionCacheImpl(scheduler)
        testable = ProducersPresenter(
            repository, locationUseCase,
            postCodeListUseCase, locationPermission, subscriptionCache)
        testable.bindView(view)
        `when`(location.latitude).thenReturn(1.0)
        `when`(location.longitude).thenReturn(2.0)
        `when`(locationPermission.get()).thenReturn(dexter)
        `when`(dexter.check()).thenAnswer {
            testable.onPermissionGranted(Mockito.mock(PermissionGrantedResponse::class.java))
        }
        `when`(locationUseCase.getLocation()).thenReturn(Single.just(location))
        `when`(postCodeListUseCase.getAllProducers(1.0, 2.0))
            .thenReturn(Single.just(postCode))
    }

    @Test
    fun findUserLocation_HasPermission_PostcodeSetToView() {
        testable.findUserLocation()
        verify(view).setPostCode(postCode)
    }
}