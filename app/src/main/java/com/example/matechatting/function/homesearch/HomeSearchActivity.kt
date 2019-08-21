package com.example.matechatting.function.homesearch

import android.os.Build
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.matechatting.R
import com.example.matechatting.base.BaseActivity
import com.example.matechatting.databinding.ActivitySearchBinding
import com.example.matechatting.listener.EditTextTextChangeListener
import com.example.matechatting.utils.statusbar.StatusBarUtil

class HomeSearchActivity : BaseActivity<ActivitySearchBinding>() {
    private lateinit var back: ImageView
    private lateinit var edit: EditText
    private lateinit var clear: ImageView
    private lateinit var button: ImageView
    private lateinit var frameLayout:FrameLayout

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
        replaceFragment(HomeSearchPopularFragment())
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

    private fun replaceFragment(fragment:Fragment){
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.search_frame,fragment)
        transaction.setCustomAnimations(R.anim.alpha_in,R.anim.alpha_out)
        transaction.commit()
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
