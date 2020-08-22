package iv.nakonechnyi.giftenor.database

import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import iv.nakonechnyi.giftenor.TestHelper
import iv.nakonechnyi.giftenor.data.GifInfo
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@SmallTest
@RunWith(AndroidJUnit4::class)
class TenorDaoTest {

    private lateinit var db: TenorDatabase
    private lateinit var dao: TenorDao
    private lateinit var expectedList: List<GifInfo>

    @Before
    fun before(){
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            TenorDatabase::class.java
        ).build()
        dao = db.tenorDao()
        expectedList = TestHelper.getListOfTestedGifInfo()
    }

    @Test
    fun whenInsertListOfGifInfoThenReadTheSameOne() = runBlocking {

        val expectedCount = expectedList.size

        dao.insertAll(expectedList)

        val dataSource = dao.getAll().create() as LimitOffsetDataSource
        val actualCount = dataSource.countItems()
        val actualList = dataSource.loadRange(0, actualCount)

        assertEquals(expectedCount, actualCount)
        assertTrue(TestHelper.compareCollections(actualList, expectedList))
    }

    @Test
    fun whenDeleteOneThenCountOfItemsIncrementedAndRemovedItemIsRight() = runBlocking {

        val expectedSizeBefore = expectedList.size
        val expectedSizeAfter = expectedSizeBefore - 1

        val removedItem = expectedList[0]

        dao.insertAll(expectedList)

        val dataSourceBefore = dao.getAll().create() as LimitOffsetDataSource
        val actualCountBefore = dataSourceBefore.countItems()

        dao.remove(removedItem)

        val expectedListAfter = expectedList.subList(1, expectedSizeBefore)

        val dataSourceAfter = dao.getAll().create() as LimitOffsetDataSource
        val actualCountAfter = dataSourceAfter.countItems()
        val actualListAfter = dataSourceAfter.loadRange(0, actualCountAfter)

        assertEquals(expectedSizeBefore, actualCountBefore)
        assertEquals(expectedSizeAfter, actualCountAfter)
        assertTrue(TestHelper.compareCollections(expectedListAfter, actualListAfter))

    }

    @Test
    fun after(){
        db.close()
    }

}