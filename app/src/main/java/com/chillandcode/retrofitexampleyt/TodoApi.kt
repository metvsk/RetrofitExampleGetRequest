package com.chillandcode.retrofitexampleyt

import retrofit2.Response
import retrofit2.http.GET

interface TodoApi {
    //interface for api
    //defines all functions we need to access our api
    @GET("/todos")

    suspend //making this function suspend so as to use it in coroutines
    fun getTodos(): Response<List<Todo>>
    /**
    an example of api request with query would look like
    fun getTodo(@Query("apikey")key:String): Response<List<Tod>>
    @POST("/createTodo")
    fun createTodo(todo: Todo):Response<CreateTodoReponse>
    if we have to post it as json then use @Body annotation
    fun createTodo(@Body todo: Todo):Response<CreateTodoReponse>
     */
}