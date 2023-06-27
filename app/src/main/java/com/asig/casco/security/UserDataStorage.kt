package com.asig.casco.security

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.map

class UserDataStorage(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_data")
        val TOKEN = stringPreferencesKey("stored_token")
        val EMAIL = stringPreferencesKey("stored_email")
    }

    val getToken: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[TOKEN]
        }

    val getEmail: Flow<String?> = context.dataStore.data // Add this block
        .map { preferences ->
            preferences[EMAIL]
        }

    suspend fun saveToken(name: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = name
        }
    }

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[EMAIL] = email
        }
    }

    fun isKeyStored(key: Preferences.Key<String>): Flow<Boolean>  =
        context.dataStore.data.map {
                preference -> !(preference[key] == null || preference[key] == "")
        }

}