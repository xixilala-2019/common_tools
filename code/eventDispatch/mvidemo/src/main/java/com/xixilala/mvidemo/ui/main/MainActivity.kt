package com.xixilala.mvidemo.ui.main


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xixilala.mvidemo.R
import com.xixilala.mvidemo.data.apiImp.ApiHelperImpl
import com.xixilala.mvidemo.data.model.User
import com.xixilala.mvidemo.databinding.ActivityMainBinding
import com.xixilala.mvidemo.ui.main.adapter.MainAdapter
import com.xixilala.mvidemo.ui.main.intent.MainIntent
import com.xixilala.mvidemo.ui.main.state.MainState
import com.xixilala.mvidemo.ui.main.viewmodel.MainViewModel
import com.xixilala.mvidemo.ui.main.viewmodel.ViewModelFactory
import com.xixilala.mvidemo.util.RetrofitBuilder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private var adapter = MainAdapter(arrayListOf())

    private lateinit var mainView:  ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val binding = DataBindingUtil.inflate<ActivityMainBinding>(layoutInflater, R.layout.activity_main,null,false)
        mainView = ActivityMainBinding.inflate(layoutInflater)


        setContentView(mainView.root)
        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()




    }

    private fun setupClicks() {
        mainView.buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }


    private fun setupUI() {
        mainView.recyclerView.layoutManager = LinearLayoutManager(this)
        mainView.recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    mainView.recyclerView.context,
                    (mainView.recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        mainView.recyclerView.adapter = adapter
    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun observeViewModel() {
        lifecycleScope.launch {

            mainViewModel.state.collect {
                when (it) {
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        mainView.buttonFetchUser.visibility = View.GONE
                        mainView.progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        mainView.progressBar.visibility = View.GONE
                        mainView.buttonFetchUser.visibility = View.GONE
                        renderList(it.user)
                    }
                    is MainState.Error -> {
                        mainView.progressBar.visibility = View.GONE
                        mainView.buttonFetchUser.visibility = View.VISIBLE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_LONG).show()
                    }
                }
            }

            mainViewModel.textNum.observe(this@MainActivity, object :Observer<String>{
                override fun onChanged(t: String?) {

                }
            })
        }
    }

    private fun renderList(users: List<User>) {
        mainView.recyclerView.visibility = View.VISIBLE
        users.let { listOfUsers -> listOfUsers.let { adapter.addData(it) } }
        adapter.notifyDataSetChanged()
    }
}