package com.example.beloo.foodnixtest

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.example.beloo.foodnixtest.storage.AppDatabase
import com.example.beloo.foodnixtest.storage.producer.ProducerDao
import com.example.beloo.foodnixtest.storage.producer.ProducerEntity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class EnityReadWriteTest {
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
    fun writeUserAndReadInList() {
        val producer = ProducerEntity(
            12,
            "detail",
            shortDescription = "abc",
            description = "blablabla"
        )
        producerDao.put(producer)
        val todoItem = producerDao.producersStream().blockingFirst().first()
        assertEquals(producer.id, todoItem.id)
    }
}