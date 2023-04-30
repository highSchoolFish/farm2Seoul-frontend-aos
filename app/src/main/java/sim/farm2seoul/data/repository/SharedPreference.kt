package sim.farm2seoul.data.repository

import android.content.Context
import android.content.SharedPreferences

class SharedPreference(context: Context) {
    private val preference: SharedPreferences =
        context.getSharedPreferences("DATA_STORE", Context.MODE_PRIVATE)

    //데이터를 내부 저장소에 저장하기
    fun setPreference(key: String, value: String) {
        preference.edit().putString(key, value).apply()
    }

    //내부 저장소에 저장된 데이터 가져오기
    fun getPreference(key: String): String {
        return preference.getString(key, "").toString()
    }

    //내부 저장소에 저장된 데이터 가져오기
    fun getAllPreference(): Map<String, *> {
        return preference.all
    }

    //데이터를 내부 저장소에 저장하기
    fun deletePreference(key: String) {
        preference.edit().remove(key).apply()
    }
}