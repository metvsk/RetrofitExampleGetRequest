package com.chillandcode.retrofitexampleyt

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        val api: TodoApi by lazy {
            //lazy is used initialise variables when we actually need them
            Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                //another library used by retrofit inorder to convert json files to and fro
                .build()
                .create(TodoApi::class.java)//Api class with functions passed in
        }
    }

}