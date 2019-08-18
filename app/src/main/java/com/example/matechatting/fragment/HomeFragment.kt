package com.example.matechatting.fragment


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.matechatting.R
import com.example.matechatting.activity.InfoDetailActivity
import com.example.matechatting.activity.LoginActivity
import com.example.matechatting.adapter.PagingHomeItemAdapter
import com.example.matechatting.constvalue.LOGIN_REQUEST_CODE
import com.example.matechatting.databinding.FragmentHomeBinding
import com.example.matechatting.utils.InjectorUtils
import com.example.matechatting.viewmodel.HomeItemViewModel

class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: HomeItemViewModel
    private lateinit var adapter: PagingHomeItemAdapter
    private lateinit var callbackPersonButton: () -> Unit
    private lateinit var callbackPersonLayout: (Int) -> Unit

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        init()
        initRecyclerView()
        setCallbackToAdapter()
        return binding.root
    }

    override fun initView() {
        val factory = InjectorUtils.provideHomeItemViewModelFactory(requireContext())
        viewModel = ViewModelProviders.of(this, factory).get(HomeItemViewModel::class.java)
        recyclerView = binding.homeRecyclerView
    }

    private fun initRecyclerView() {
        initAdapter()
        recyclerView.adapter = adapter
    }

    private fun initAdapter() {
        adapter = PagingHomeItemAdapter(callbackPersonButton,callbackPersonLayout)
        viewModel.getData(requireContext()) {
            it.observe(this, Observer { list ->
                Log.d("aaa", list.toString())
                if (list != null) {
                    adapter.submitList(list)
                }
            })
        }
    }

    override fun onResume() {
        super.onResume()
        init()
        setCallbackToAdapter()
    }

    private fun transferActivity(intent: Intent, requestCode: Int) {
        requireActivity().startActivityForResult(intent, requestCode)
    }

    private fun setCallbackToAdapter() {
        adapter.callbackPersonButton = callbackPersonButton
        adapter.callbackPersonLayout = callbackPersonLayout
    }

    override fun initLogin() {
        callbackPersonButton = {}
        callbackPersonLayout = {
            val intent = Intent(requireActivity(), InfoDetailActivity::class.java)
            intent.putExtra("id",it)
            requireActivity().startActivityForResult(intent,0x999)
        }
    }

    override fun initNotLogin() {
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        callbackPersonButton = {
            transferActivity(intent, LOGIN_REQUEST_CODE)
        }
        callbackPersonLayout = {
            transferActivity(intent, LOGIN_REQUEST_CODE)
        }
    }


}
