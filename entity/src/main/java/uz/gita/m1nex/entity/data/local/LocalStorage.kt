package uz.gita.m1nex.entity.data.local

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.gita.m1nex.core.helper.SharedPreference
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
internal class LocalStorage @Inject constructor(@ApplicationContext context: Context) : SharedPreference(context) {
    var isVerified by booleans(false)
    var isFirstRun: Boolean by booleans(true)
    var isSignIn: Boolean by booleans(false)
    var token: String by strings("")
    var transferToken: String by strings("")
    var refreshToken: String by strings("")
    var accessToken: String by strings("")

    var password: String by strings()
    var phone: String by strings()

    var totalBalance: Int by ints(0)
    var firstName: String by strings()
    var genderType: Int by ints(0)
    var age: Int by ints(0)
    var cards: String by strings()


//    var launchData : LaunchData by objects(LaunchData::class.java,LaunchData.FIRST_TIME)

}