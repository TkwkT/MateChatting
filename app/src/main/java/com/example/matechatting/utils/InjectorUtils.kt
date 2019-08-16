package com.example.matechatting.utils

import android.content.Context
import com.example.matechatting.database.AppDatabase
import com.example.matechatting.repository.LoginRepository
import com.example.matechatting.viewmodel.LoginViewModelFactory

object InjectorUtils {

    fun getLoginRepository(context: Context): LoginRepository {
        return LoginRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).loginDao()
        )
    }

    fun provideLoginViewModelFactory(context: Context): LoginViewModelFactory {
        val repository = getLoginRepository(context)
        return LoginViewModelFactory(repository)
    }
//
//    fun getDetailRepository(context: Context): DetailRepository {
//        return DetailRepository.getInstance(
//            AppDatabase.getInstance(context.applicationContext).detailDao()
//        )
//    }
//
//    fun provideInformationDetailViewModelFactory(context: Context): InformationDetailViewModelFactory {
//        val repository = getDetailRepository(context)
//        return InformationDetailViewModelFactory(repository)
//    }
//
//    fun getCollectionDetailRepository(context: Context): CollectionDetailRepository {
//        return CollectionDetailRepository.getInstance(
//            AppDatabase.getInstance(context.applicationContext).collectionDetailDao()
//        )
//    }
//
//    fun provideCollectionDetailViewModelFactory(context: Context): CollectionDetailViewModelFactory {
//        val repository = getCollectionDetailRepository(context)
//        return CollectionDetailViewModelFactory(repository)
//    }
//
//    fun getDetailActivityRepository(context: Context): DetailActivityRepository {
//        return DetailActivityRepository.getInstance(
//            AppDatabase.getInstance(context.applicationContext).collectionDetailDao()
//        )
//    }
//
//    fun provideDetailActivityViewModelFactory(context: Context): DetailActivityViewModelFactory {
//        val repository = getDetailActivityRepository(context)
//        return DetailActivityViewModelFactory(repository)
//    }
}