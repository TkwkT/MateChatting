package com.example.matechatting.utils

import android.content.Context
import com.example.matechatting.database.AppDatabase
import com.example.matechatting.repository.*
import com.example.matechatting.viewmodel.*

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

    fun provideBindPhoneViewModelFactory(context: Context): BindPhoneViewModelFactory {
        return BindPhoneViewModelFactory(BindPhoneRepository())
    }

    fun provideChangePasswordByTokenViewModelFactory(context: Context): ChangePasswordByTokenViewModelFactory {
        return ChangePasswordByTokenViewModelFactory(ChangePasswordByTokenRepository())
    }

    fun provideForgetPasswordViewModelFactory(context: Context): ForgetPasswordViewModelFactory {
        return ForgetPasswordViewModelFactory(ForgetPasswordRepository())
    }

    fun provideResetPassViewModelFactory(context: Context): ResetPassViewModelFactory {
        return ResetPassViewModelFactory(ResetPassRepository())
    }

    fun provideClipViewModelFactory(context: Context): ClipViewModelFactory {
        return ClipViewModelFactory(ClipRepository())
    }

    fun getHomeItemRepository(context: Context): HomeItemRepository {
        return HomeItemRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).homeItemDao()
        )
    }

    fun provideHomeItemViewModelFactory(context: Context): HomeItemViewModelFactory {
        return HomeItemViewModelFactory(getHomeItemRepository(context))
    }

    fun provideInfoDetailViewModelFactory(context: Context): InfoDetailViewModelFactory {
        return InfoDetailViewModelFactory(InfoDetailRepository())
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