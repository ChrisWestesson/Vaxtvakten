package com.christianwestesson.vaxtvakten

import android.content.Context
import android.util.Log
import androidx.room.*
import java.util.*


class Databasehelper {



    companion object {
        var ctx : Context? = null
        val model = MyPlantViewModel()


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
            Log.i("VAXTVAKTENDEBUG", "Innan: ${userdao.getAll().toString()}")
            var date = Calendar.getInstance().timeInMillis

            var plantList = userdao.getAll()


            if(plantList.size == 0)
            {

                var amaryllis = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Amaryllis", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "amaryllis")

                var ampellilja = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Ampellilja", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "ampellilja")

                var aralia = Plant(uid = 0, waterintervalWeeks = 0, waterintervalDays = 3,
                    waterintervalHours = 12, info = "", species = "Aralia", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "aralia")


                userdao.insertPlant(amaryllis, aralia, ampellilja)
            }

            Log.i("VAXTVAKTENDEBUG", "Efter: ${userdao.getAll().toString()}")
        }
    }



}

@Entity
data class Plant(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "waterintervalWeeks") val waterintervalWeeks: Int,
    @ColumnInfo(name = "waterintervalDays") val waterintervalDays: Int,
    @ColumnInfo(name = "waterintervalHours") val waterintervalHours: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "wateramount") val wateramount: String,
    @ColumnInfo(name = "info") val info: String,
    @ColumnInfo(name = "imgName") val imgName: String,
    @ColumnInfo(name = "giveWaterDate") val giveWaterDate: Long

)

@Entity
data class MyPlant(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "waterintervalWeeks") val waterintervalWeeks: Int,
    @ColumnInfo(name = "waterintervalDays") val waterintervalDays: Int,
    @ColumnInfo(name = "waterintervalHours") val waterintervalHours: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "species") val species: String,
    @ColumnInfo(name = "wateramount") val wateramount: String,
    @ColumnInfo(name = "info") val info: String,
    @ColumnInfo(name = "imgName") val imgName: String,
    @ColumnInfo(name = "giveWaterDate") var giveWaterDate: Long

)

@Dao
interface UserDao {
    @Query("SELECT * FROM plant")
    fun getAll(): List<Plant>

   // @Query("SELECT * FROM myplant")
   // fun getAllMyPlants(): List<MyPlant>

    @Query("SELECT * FROM myplant ORDER BY giveWaterDate")
    fun getAllMyPlants(): List<MyPlant>


    @Update
    fun updateWaterDate(plant: MyPlant)

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

    @Insert
    fun insertMyPlant(vararg users: MyPlant)

    @Delete
    fun delete(user: Plant)
}

@Database(entities = [Plant::class, MyPlant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}