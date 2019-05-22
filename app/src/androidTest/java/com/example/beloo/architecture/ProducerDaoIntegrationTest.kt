package com.example.beloo.architecture

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.beloo.architecture.storage.AppDatabase
import com.example.beloo.architecture.storage.image.ImageEntity
import com.example.beloo.architecture.storage.producer.CompleteProducerEntity
import com.example.beloo.architecture.storage.producer.ProducerDao
import com.example.beloo.architecture.storage.producer.ProducerEntity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ProducerDaoIntegrationTest {
    private lateinit var producerDao: ProducerDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().context
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).build()
        producerDao = db.producerDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun write_and_read_same_composite_producer() {
        val producer = ProducerEntity(
            12,
            "detail",
            shortDescription = "abc",
            description = "blablabla"
        )
        val image = ImageEntity(
            "image/somewhere",
            1,
            12
        )
        val completeProducer = CompleteProducerEntity(
            producer,
            listOf(image)
        )
        producerDao.put(listOf(completeProducer)).subscribe()
        val actual = producerDao.producersStream().blockingFirst().first()
        assertEquals(completeProducer, actual)
    }
}