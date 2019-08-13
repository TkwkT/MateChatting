package com.example.matechatting.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.matechatting.holder.BaseHolder
import com.example.matechatting.holder.BaseSource

abstract class BaseRecyclerAdapter<B : ViewDataBinding, D : Any, HD : BaseHolder, S : BaseSource> :
    RecyclerView.Adapter<HD>() {
    protected val dataList = ArrayList<D>()
    protected lateinit var binding: B


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HD {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            getLayoutId(), parent, false
        )

        return onCreate(binding)
    }

    override fun onBindViewHolder(holder: HD, position: Int) {
        onBind(holder, position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    abstract fun onCreate(binding: B):HD

    abstract fun onBind(holder: HD, position: Int)

    abstract fun getItem(position: Int): S

    abstract fun getLayoutId(): Int

}