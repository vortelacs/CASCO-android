package com.asig.casco.screens.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.asig.casco.api.interfaces.InsurerApi
import com.asig.casco.model.Insurer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/*
fun sendRequestgetById(
    id: String,
    insurerState: MutableState<Insurer>
) {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://localhost:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val id = "12"
    val api = retrofit.create(InsurerApi::class.java)
    val call: Insurer = api.getInsurerById("dba81157-b9d7-41c6-987d-30a4911ff301",id);
*/
/*

    call!!.enqueue(object: Callback<Insurer?> {
        override fun onResponse(call: Call<Insurer?>, response: Response<Insurer?>) {
            if(response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                insurerState.value = response.body()!!.copy()
            }
        }
*//*


        override fun onFailure(call: Call<Insurer?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })

}

fun sendRequestgetAll(
    insurerList: MutableList<Insurer>,
    ctx: Context
) {

    val retrofit = Retrofit.Builder()
        .baseUrl("http://10.202.0.155:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    val api = retrofit.create(InsurerApi::class.java)
//    val call : Call<ArrayList<Insurer>> = api.getInsurers("dba81157-b9d7-41c6-987d-30a4911ff301")

*/
/*    call!!.enqueue(object : Callback<ArrayList<Insurer>?> {
        override fun onResponse(
            call: Call<ArrayList<Insurer>?>,
            response: Response<ArrayList<Insurer>?>
        ) {
            // on below line we are checking if response is successful.
            if (response.isSuccessful) {
                // on below line we are creating a new list
                var lst: ArrayList<Insurer> = ArrayList()

                // on below line we are passing
                // our response to our list
                lst = response.body()!!

                // on below line we are passing
                // data from lst to course list.
                for (i in 0 until lst.size) {
                    // on below line we are adding data to course list.
                    insurerList.add(lst[i].copy())
                }
            }
        }*//*

        override fun onFailure(call: Call<ArrayList<Insurer>?>, t: Throwable) {
            // displaying an error message in toast
            Toast.makeText(ctx,"Fail to get the data..", Toast.LENGTH_SHORT)
                .show()
        }
    })


}*/
