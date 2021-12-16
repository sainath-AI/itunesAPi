package com.masai.sainath.itunesapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.masai.sainath.itunesapi.viewmodels.AppViewModel
import com.masai.sainath.itunesapi.adapter.ItunesAdapter
import com.masai.sainath.itunesapi.databinding.ActivityMainBinding
import com.masai.sainath.itunesapi.remote.Status
import com.masai.sainath.itunesapi.remote.db.ItunesDbTable
import com.masai.sainath.itunesapi.remote.responses.ResultModel

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: AppViewModel by viewModels()
    lateinit var itunesAdapter: ItunesAdapter
    val resultList = mutableListOf<ResultModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView();
        binding.etEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                binding.etEditText.clearFocus()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                loadApi(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    fun loadApi(query: String) {
        viewModel.getDataFromAPI(query).observe(this, Observer {
            when (it.status) {
                Status.ERROR -> {
                    viewModel.getDataFromDb()
                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    resultList.clear()
                    it.data?.resultModels.let {
                        if (it != null) {
                            resultList.addAll(it)
                            itunesAdapter.notifyDataSetChanged()
                        }
                    }
                    CoroutineScope(Dispatchers.Default).launch {
                        insertDataToDb(it.data!!.resultModels)
                    }
                }
            }
        })
        viewModel.getDataFromAPI(query)
    }

    private fun insertDataToDb(resultModels: List<ResultModel>) {
        viewModel.deleteDataFromDb()
        resultModels.forEach {
            val itunesDb = ItunesDbTable(it.artistName, it.artworkUrl100)
            viewModel.insertDataInDb(itunesDb)
        }
    }

    private fun setRecyclerView() {
        itunesAdapter = ItunesAdapter(resultList)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = itunesAdapter
        }
    }
}