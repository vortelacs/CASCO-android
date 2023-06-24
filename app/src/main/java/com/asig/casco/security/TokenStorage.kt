package com.asig.casco.security

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.map

class TokenStorage(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "stored_token")
        val TOKEN = stringPreferencesKey("stored_token")
    }

    val getToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN]
        }

    suspend fun saveData(name: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = name
        }
    }

    fun isKeyStored(key: Preferences.Key<String>): Flow<Boolean>  =
        context.dataStore.data.map {
                preference -> !(preference[key] == null || preference[key] == "")
        }

}