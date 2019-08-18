package com.example.matechatting.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.matechatting.R
import com.example.matechatting.bean.HomeItemBean
import com.example.matechatting.databinding.ItemHomePersonBinding
import com.example.matechatting.databinding.ItemHomeSearchBinding
import com.example.matechatting.holder.BaseHolder
import com.example.matechatting.holder.HomeItemPersonHolder
import com.example.matechatting.holder.HomeItemSearchHolder
import com.example.matechatting.holder.HomeItemSource

class PagingHomeItemAdapter(
    var callbackPersonButton: () -> Unit,
    var callbackPersonLayout: (Int) -> Unit
) :
    PagedListAdapter<HomeItemBean, BaseHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        when (viewType) {
            ITEM_TYPE_SEARCH -> {
                val binding = DataBindingUtil.inflate<ItemHomeSearchBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_search, parent, false
                )
                return HomeItemSearchHolder(binding)
            }
            else -> {
                val binding = DataBindingUtil.inflate<ItemHomePersonBinding>(
                    LayoutInflater.from(parent.context),
                    R.layout.item_home_person, parent, false
                )
                return HomeItemPersonHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        when (holder) {
            is HomeItemPersonHolder -> {
                getHomeItem(position)?.let {homeItemBean->
                    holder.bind(HomeItemSource(homeItemBean))
                    Log.d("aaa","onBindViewHolder + id:" +homeItemBean.id)
                    holder.getLayout().setOnClickListener { callbackPersonLayout(homeItemBean.id) }
                }
                holder.getButton().setOnClickListener { callbackPersonButton() }

            }
            is HomeItemSearchHolder -> {
                holder.bind()
            }

        }
    }

    private fun getHomeItem(position: Int): HomeItemBean? {
        return getItem(position - 1)
    }

    override fun getItemCount(): Int {
        Log.d("bbb",currentList?.size.toString())
        return super.getItemCount() + 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> ITEM_TYPE_SEARCH
            else -> ITEM_TYPE_PERSON
        }
    }

    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(AdapterDataObserverProxy(observer, HEAD_COUNT))
    }

    companion object {
        private const val ITEM_TYPE_SEARCH = 1
        private const val ITEM_TYPE_PERSON = 2
        private const val HEAD_COUNT = 1
        val diffCallback = object : DiffUtil.ItemCallback<HomeItemBean>() {
            override fun areItemsTheSame(oldItem: HomeItemBean, newItem: HomeItemBean): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: HomeItemBean, newItem: HomeItemBean): Boolean {
                return oldItem.id == newItem.id
            }
        }

    }
}