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
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "amaryllis", userimgName = "")

                var ampellilja = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Ampellilja", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "ampellilja", userimgName = "")

                var aralia = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Aralia", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "aralia", userimgName = "")

                var aspidistra = Plant(uid = 0, waterintervalWeeks = 2, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Aspidistra", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "aspidistra", userimgName = "")

                var begonia = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Begonia", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "begonia", userimgName = "")

                var benjaminfikus = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Benjaminfikus", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "benjaminfikus", userimgName = "")

                var cyklamen = Plant(uid = 0, waterintervalWeeks = 2, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Cyklamen", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "cyklamen", userimgName = "")

                var flitigaLisa = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Flitiga Lisa", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "flitigalisa", userimgName = "")

                var fredskalla = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Fredskalla", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "fredskalla", userimgName = "")

                var gloxinia = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 10,
                    waterintervalHours = 0, info = "", species = "Gloxinia", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "gloxinia", userimgName = "")

                var hemtrevnad = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Hemtrevnad", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "hemtrevnad", userimgName = "")

                var julstjärna = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Julstjärna", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "julstjärna", userimgName = "")

                var monstera = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Monstera", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "monstera", userimgName = "")

                var paradisträd = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Paradisträd", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "paradisträd", userimgName = "")

                var pelargon = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Pelargon", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "pelargon", userimgName = "")

                var prickblad = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Prickblad", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "prickblad", userimgName = "")

                var silverkalla = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Silverkalla", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "silverkalla", userimgName = "")

                var spjutbräken = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Spjutbräken", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "spjutbräken", userimgName = "")

                var svärmorskudde = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Svärmors kudde", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "svärmorskudde", userimgName = "")

                var svärmorstunga = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Svärmors tunga", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "svärmorstunga", userimgName = "")

                var skvallerreva = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Skvallerreva", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "skvallerreva", userimgName = "")

                var våreld = Plant(uid = 0, waterintervalWeeks = 1, waterintervalDays = 0,
                    waterintervalHours = 0, info = "", species = "Våreld", title = "",
                    wateramount = "Vattna tills jorden är lätt fuktig", giveWaterDate = date, imgName = "våreld", userimgName = "")

                userdao.insertPlant(amaryllis, aralia, ampellilja, aspidistra, begonia, benjaminfikus, cyklamen, flitigaLisa, fredskalla, gloxinia, hemtrevnad,
                julstjärna, monstera, paradisträd, pelargon, prickblad, silverkalla, spjutbräken, svärmorskudde, svärmorstunga, skvallerreva, våreld)
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
    @ColumnInfo(name = "giveWaterDate") val giveWaterDate: Long,
    @ColumnInfo(name = "userimgName") val userimgName: String

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
    @ColumnInfo(name = "giveWaterDate") var giveWaterDate: Long,
    @ColumnInfo(name = "userimgName") val userimgName: String


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
    fun deleteMyPlant(user: MyPlant)

    @Delete
    fun deletePlant(user: Plant)
}

@Database(entities = [Plant::class, MyPlant::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}