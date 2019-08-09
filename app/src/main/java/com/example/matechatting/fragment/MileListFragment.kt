package com.example.matechatting.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import com.example.matechatting.activity.MainActivity
import com.example.matechatting.activity.MyOnTouchListener
import com.example.matechatting.R
import com.example.matechatting.databinding.FragmentMileListBinding
import com.example.matechatting.myview.SideView
import com.example.matechatting.utils.statusbar.StatusBarUtil

class MileListFragment : BaseFragment() {
    private lateinit var binding: FragmentMileListBinding
    private lateinit var toolbar: Toolbar
    private lateinit var sideView: SideView
    private var changing = false
    private val myOnTouchListener = object : MyOnTouchListener {
        override fun onTouch(isScroll: Boolean) {
            if (isScroll && !changing) {
                changing = true
                sideView.visibility = View.GONE
//                sideView.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.side_view_gone)
            } else if (!isScroll) {
                changing = false
                sideView.visibility = View.VISIBLE
                sideView.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.side_view_show)
            }
        }
    }

    override fun initView() {
        toolbar = binding.mileListToolbar
        sideView = binding.mileListSideview
        (requireActivity() as? MainActivity)?.registerMyOnTouchListener(myOnTouchListener)
        initSideView()
    }

    override fun initLogin() {
        Log.d("aaa","Login")
    }

    override fun initNotLogin() {
        Log.d("aaa","noLogin")
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mile_list, container, false)
        StatusBarUtil.setStatusBarDarkTheme(requireActivity(), true)
        init()
//        initSideView()

        return binding.root
    }

    private fun initSideView() {
        sideView.setOnTouchingLetterChangedListener(object : SideView.Companion.OnTouchingLetterChangedListener {
            override fun onTouchingLetterChanged(str: String) {
                Log.d("aaa", str)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        (requireActivity() as? MainActivity)?.unregisterMyOnTouchListener(myOnTouchListener)
    }

}

