package com.christianwestesson.vaxtvakten

import android.content.Context
import androidx.room.*
import java.util.*

class Databasehelper {

    companion object {
        var ctx : Context? = null

        fun getDatabase() : AppDatabase{
            val db = Room.databaseBuilder(
                ctx!!,
                AppDatabase::class.java, "database-name"
            ).allowMainThreadQueries().build()

            return db
        }

        fun checkStart()
        {
            var userdao = getDatabase().userDao()
            var plantList = userdao.getAll()
            if(plantList.size == 0)
            {
               // var roseplant = Plant(uid = 1, waterintervalWeeks = 1)
                //userdao.insertPlant()



            }
        }
    }



}

@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "waterintervalWeeks") val waterintervalWeeks: Int,
    @ColumnInfo(name = "waterintervalDays") val waterintervalDays: Int,
    @ColumnInfo(name = "waterintervalHours") val waterintervalHours: Int,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "wateramount") val wateramount: String?,
    @ColumnInfo(name = "info") val info: String?,
    @ColumnInfo(name = "giveWaterDate") val giveWaterDate: Long?

)

@Dao
interface UserDao {
    @Query("SELECT * FROM plant")
    fun getAll(): List<Plant>

    //  @Query("SELECT * FROM plant WHERE age >60 ")
    //  fun getOldPeople(): List<Plant>

    //  @Query("SELECT * FROM plant WHERE age > :years ORDER BY age DESC")
    //  fun getOlderThanPeople(years: Int): LiveData<List<Plant>>

    @Query("SELECT * FROM plant WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Plant>

    //   @Query("SELECT * FROM plant WHERE first_name LIKE :first AND " +
    //           "last_name LIKE :last LIMIT 1")
    //   fun findByName(first: String, last: String): Plant

    @Insert
    fun insertPlant(vararg users: Plant)

    @Delete
    fun delete(user: Plant)
}

@Database(entities = [Plant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}