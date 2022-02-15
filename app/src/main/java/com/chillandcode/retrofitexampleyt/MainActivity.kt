package com.chillandcode.retrofitexampleyt

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.chillandcode.retrofitexampleyt.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter
    private val TAG = "606060"
    //*** MVVM architecture not used... main focus only on retrofit basic idea

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        CoroutineScope(Dispatchers.IO).launch {
            var response: Response<List<Todo>>? = null
            binding.progressBar.isVisible = true//to show if we are loading
            try {
                response = RetrofitInstance.api.getTodos()
            } catch (e: IOException) {
                Log.d(TAG, "You might not have internet connections")
            } catch (e: HttpException) {
                Log.d(TAG, "Http Exception : Unexpected response")
            }
            withContext(Dispatchers.Main) {
                response?.let {
                    if (it.isSuccessful && it.body() != null) {
                        todoAdapter.todosList = response.body()!!//if successful add response values to the adapter to present in UI
                    } else {
                        Log.d(TAG, "Api Response not successful !!")
                    }
                    binding.progressBar.isVisible=false//hide progress bar
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerView.apply {
            todoAdapter = TodoAdapter()
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}