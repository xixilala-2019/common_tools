package com.xixilala.ssq.main.ui

import android.Manifest
import android.app.Activity
import android.app.ActivityManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.HORIZONTAL
import com.bilibili.magicasakura.utils.ThemeUtils
import com.meituan.robust.Patch
import com.meituan.robust.PatchExecutor
import com.meituan.robust.RobustCallBack
import com.qw.soul.permission.SoulPermission
import com.qw.soul.permission.bean.Permission
import com.qw.soul.permission.callbcak.CheckRequestPermissionListener
import com.xixilala.ssq.R
import com.xixilala.ssq.databinding.ActivityMainBinding
import com.xixilala.ssq.main.ui.adapter.MainDltAdapter
import com.xixilala.ssq.main.ui.adapter.MainSsqAdapter
import com.xixilala.ssq.mvi.ssq.QueryIntent
import com.xixilala.ssq.mvi.ssq.QueryState
import com.xixilala.ssq.patch.PatchManipulateImp
import com.xixilala.ssq.util.ThemeHelper
import com.xixilala.ssq.vm.MainViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class MainActivity : AppCompatActivity() {
//    www.cwl.gov.cn/cwl_admin/kjxx/findDrawNotice?name=ssq&issueCount=50
//    www.cwl.gov.cn/cwl_admin/kjxx/findKjxx/forIssue?name=ssq&code=2021034
//    Referer http://www.cwl.gov.cn/kjxx/ssq/
//    https://webapi.sporttery.cn/gateway/lottery/getHistoryPageListV1.qry?gameNo=85&provinceId=0&pageSize=30&isVerify=1&pageNo=1


    private lateinit var viewModel: MainViewModel
    private lateinit var mainView: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        mainView = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainView.root)

        setupViewModel()
        setupView()
        setObserver()
        querySsqData()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        window.statusBarColor = ThemeUtils.getColorById(
            this,
            R.color.theme_color_primary
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.change_lottery, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ssq -> {
                setMyTitle(R.string.ssq)
                querySsqData()
            }
            R.id.dlt -> {
                setMyTitle(R.string.dlt)
                queryDataIntent(QueryIntent.queryDltList)
            }
            R.id.night-> {
//                getWindow().setWindowAnimations(android.R.anim.fade_out)
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                ChangeThemeDialog().show(supportFragmentManager,"changeTheme")
                val dialog = CardPickerDialog();
                dialog.setClickListener { currentTheme ->
                    ThemeHelper.setTheme(this@MainActivity, currentTheme)
                    ThemeUtils.refreshUI(this@MainActivity, object : ThemeUtils.ExtraRefreshable{
                        override fun refreshGlobal(activity: Activity?) {
                            if (Build.VERSION.SDK_INT >= 21) {
                                val context= activity as AppCompatActivity
                                val taskDescription = ActivityManager.TaskDescription(
                                    null,
                                    null,
                                    ThemeUtils.getThemeAttrColor(
                                        context,
                                        R.attr.colorPrimary
                                    )
                                )
                                context.setTaskDescription(taskDescription)
                                context.window.statusBarColor = ThemeUtils.getColorById(
                                    context,
                                    R.color.theme_color_primary
                                )
                            }
                        }

                        override fun refreshSpecificView(view: View?) {

                        }
                    })
                }
                dialog.show(supportFragmentManager,"themeChoose")
            }
            R.id.loadPatch -> {
                SoulPermission.getInstance().checkAndRequestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, object : CheckRequestPermissionListener{
                    override fun onPermissionOk(permission: Permission?) {
                        PatchExecutor(applicationContext, PatchManipulateImp(), object : RobustCallBack{
                            override fun onPatchApplied(result: Boolean, patch: Patch?) {
                                Log.e("---robust---","-----------onPatchApplied")
                            }

                            override fun onPatchListFetched(
                                result: Boolean,
                                isNet: Boolean,
                                patches: MutableList<Patch>?
                            ) {
                                Log.e("---robust---","-----------onPatchListFetched")
                            }

                            override fun onPatchFetched(
                                result: Boolean,
                                isNet: Boolean,
                                patch: Patch?
                            ) {
                                Log.e("---robust---","-----------onPatchFetched")
                            }

                            override fun logNotify(log: String?, where: String?) {
                                Log.e("---robust---","-----------logNotify")
                            }

                            override fun exceptionNotify(throwable: Throwable?, where: String?) {
                                Log.e("---robust---","-----------exceptionNotify")
                            }

                        }).start()
                    }

                    override fun onPermissionDenied(permission: Permission?) {

                    }
                })
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun querySsqData() {
        queryDataIntent(QueryIntent.querySsqList)
    }

    private fun queryDataIntent(intent: QueryIntent) {
        viewModel.viewModelScope.launch {
            viewModel.userIntent.send(intent)
        }
    }

    private fun setObserver() {
        viewModel.viewModelScope.launch {
            viewModel.state.collect(object : FlowCollector<QueryState> {
                override suspend fun emit(value: QueryState) {
                    when (value) {
                        is QueryState.Loading -> {
                            mainView.loading.visibility = View.VISIBLE
                        }
                        is QueryState.SsqSuccess -> {
                            mainView.loading.visibility = View.GONE
                            mainView.rvData.visibility = View.VISIBLE
                            mainView.rvData.adapter = MainSsqAdapter(value.list)
                        }
                        is QueryState.DltSuccess -> {
                            mainView.loading.visibility = View.GONE
                            mainView.rvData.visibility = View.VISIBLE
                            mainView.rvData.adapter = MainDltAdapter(value.list)
                        }
                        is QueryState.Error -> {
                            mainView.loading.visibility = View.GONE
                            Toast.makeText(applicationContext, "出错了", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }

    private fun setupView() {

        setSupportActionBar(mainView.toolbar)
        setMyTitle(R.string.ssq)
        mainView.rvData.addItemDecoration(DividerItemDecoration(this, HORIZONTAL))


    }

    private fun setupViewModel() {
        viewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModel::class.java)
    }

    private fun setMyTitle(@StringRes title:Int) {
        mainView.toolbar.setTitle(resources.getString(title))
    }
}