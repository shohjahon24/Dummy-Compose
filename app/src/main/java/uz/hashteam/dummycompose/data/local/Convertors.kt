package uz.hashteam.dummycompose.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import uz.hashteam.dummycompose.domain.model.Employee

@ProvidedTypeConverter
class Convertors(val moshi: Moshi) {

    @TypeConverter
    fun employeeToString(employee: Employee?): String {
        return moshi.adapter(Employee::class.java).toJson(employee)
    }

    @TypeConverter
    fun stringToEmployee(value: String): Employee? {
        return moshi.adapter(Employee::class.java).fromJson(value)
    }
}
