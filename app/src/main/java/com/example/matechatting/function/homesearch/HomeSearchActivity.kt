package com.example.matechatting.function.homesearch

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.example.matechatting.ALBUM_REQUEST_CODE
import com.example.matechatting.LOGIN_REQUEST_CODE
import com.example.matechatting.R
import com.example.matechatting.base.BaseActivity
import com.example.matechatting.base.BaseFragment
import com.example.matechatting.bean.SearchBean
import com.example.matechatting.databinding.ActivitySearchBinding
import com.example.matechatting.listener.EditTextTextChangeListener
import com.example.matechatting.utils.InjectorUtils
import com.example.matechatting.utils.ToastUtil
import com.example.matechatting.utils.statusbar.StatusBarUtil

class HomeSearchActivity : BaseActivity<ActivitySearchBinding>() {
    private lateinit var back: ImageView
    private lateinit var edit: EditText
    private lateinit var clear: ImageView
    private lateinit var button: ImageView
    private lateinit var frameLayout: FrameLayout
    lateinit var viewModel: HomeSearchViewModel
    var resultArray = MutableLiveData<List<SearchBean.Payload.MyArray.Map>>()
    var key = ""
    var callback: (List<SearchBean.Payload.MyArray.Map>) -> Unit = { it ->
        if (it.isEmpty()) {
            ToastUtil().setToast(this, "当前关键字无搜索结果")
        } else {
            replaceFragment(ResultFragment())
            resultArray.value = it
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        StatusBarUtil.setRootViewFitsSystemWindows(this, true)
        StatusBarUtil.setStatusBarDarkTheme(this, true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarUtil.setStatusBarColor(this, this.getColor(R.color.bg_ffffff))
        }
        canSlideFinish(true)
        initView()
        initEdit()
        initClear()
        initViewModel()
        initButton()
        replaceFragment(HomeSearchPopularFragment())
    }

    fun getData(){
        viewModel.getResult(key,1,20,callback)
    }

    private fun initViewModel() {
        val factory = InjectorUtils.provideHomeSearchViewModelFactory(this)
        viewModel = ViewModelProviders.of(this, factory).get(HomeSearchViewModel::class.java)
    }

    private fun initView() {
        binding.apply {
            back = searchBack
            edit = searchEditText
            clear = searchClearImage
            button = searchButton
            frameLayout = searchFrame
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.search_frame, fragment)
        transaction.setCustomAnimations(R.anim.alpha_in, R.anim.alpha_out)
        transaction.commit()
    }

    private fun initButton(){
        button.setOnClickListener {
            val str = edit.text.toString().trim()
            if (str.isEmpty()){
                ToastUtil().setToast(this,"关键字不能为空或空格")
            }else{
                getData()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == LOGIN_REQUEST_CODE && data != null) {
            getLoginState()
        }
    }

    private fun getLoginState() {
        val sp = getSharedPreferences("loginInfo", Context.MODE_PRIVATE)
        BaseFragment.isLogin = sp.getBoolean("isLogin", false)
        val account = sp.getString("account", "")
        BaseFragment.account = account ?: ""
    }

    private fun initEdit() {
        edit.addTextChangedListener(
            EditTextTextChangeListener({
                if (it.isNotEmpty()) {
                    clear.visibility = View.VISIBLE
                } else {
                    clear.visibility = View.GONE
                }
            })
        )
    }

    private fun initClear() {
        val emptyString = SpannableStringBuilder("")
        clear.setOnClickListener {
            edit.text = emptyString
        }
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_search
    }


}
