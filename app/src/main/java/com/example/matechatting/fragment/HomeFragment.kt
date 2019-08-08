package com.example.matechatting.fragment


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import com.example.matechatting.R
import com.example.matechatting.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var button: Button
    private lateinit var edittext: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        init()
        return binding.root
    }

    private fun initButton() {
        button.setOnClickListener {

        }
    }

    override fun initView() {
        Log.d("aaa","initView")
    }

    override fun initLogin() {
        Log.d("aaa","login")
    }

    override fun initNotLogin() {
        Log.d("aaa","noLogin")
    }

    private fun initEditText() {
//        edittext.addTextChangedListener(object : TextWatcher {
//            override fun afterTextChanged(s: Editable?) {}
//
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                if (s.isNullOrEmpty()){
//                    binding.btSendNews.isEnabled = false
//                    binding.btSendNews.background = getDrawable(R.drawable.bg_botton_invisible)
//                }else{
//                    binding.btSendNews.isEnabled = true
//                    binding.btSendNews.background = getDrawable(R.drawable.bg_botton_send)
//                }
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s.isNullOrEmpty()){
//                    binding.btSendNews.isEnabled = false
//                    binding.btSendNews.background = getDrawable(R.drawable.bg_botton_invisible)
//                }else{
//                    binding.btSendNews.isEnabled = true
//                    binding.btSendNews.background = getDrawable(R.drawable.bg_botton_send)
//                }
//            }
//        })
    }


}
