package com.example.dcardhomework.vvm.homesearch

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.example.dcardhomework.Result
import com.example.dcardhomework.data.SearchResponse
import com.example.dcardhomework.data.source.RemoteDataSource
import com.example.dcardhomework.databinding.FragmentHomeSearchBinding
import com.example.dcardhomework.util.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class HomeSearchFragment : Fragment() {
    lateinit var binding: FragmentHomeSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeSearchBinding.inflate(layoutInflater)
//        ss
        //aa
        // Create a Coroutine scope using a job to be able to cancel when needed
        val job = Job()
        // the Coroutine runs using the Main (UI) dispatcher
        val coroutineScope = CoroutineScope(job + Dispatchers.Main)

        val searchResponse = MutableLiveData<SearchResponse?>()


        binding.progressBar.visibility = View.INVISIBLE


        binding.edtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.progressBar.visibility = View.VISIBLE
                coroutineScope.launch {
                    when (val result = RemoteDataSource.getInfo(s.toString())) {
                        is Result.Success ->
                            searchResponse.value = result.data
                        else -> {
                            Logger.i("getRFIDInfo RESULT=>$result")
                        }
                    }

                    binding.progressBar.visibility = View.INVISIBLE
                    binding.rvResult.adapter = HomeSearchAdapter()
                    (binding.rvResult.adapter as HomeSearchAdapter).submitList(searchResponse.value?.items)
                }


            }
        })

        return binding.root
    }


}