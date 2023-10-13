package uz.hashteam.dummycompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import uz.hashteam.dummycompose.domain.model.Employee

@Database(entities = [Employee::class], version = 1, exportSchema = false)
/*
@TypeConverters(Convertors::class)
*/
abstract class DummyDatabase : RoomDatabase() {

    abstract fun getEmployeeDao(): DummyDao

}
