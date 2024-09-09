package uz.gita.m1nex.presenter.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import uz.gita.m1nex.presenter.screenmodel.home.HomeScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.home.tab.main.MainScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.password.PasswordScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.signin.SignInScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.splash.SplashScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.verify.VerifyScreenModelImpl

@Module
@InstallIn(ActivityComponent::class)
internal interface ScreenModelModule {

    @Binds
    @IntoMap
    @ScreenModelKey(SplashScreenModelImpl::class)
    fun bindSplashScreenModelImpl(model: SplashScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(SignUpScreenModelImpl::class)
    fun bindSignUpScreenModelImpl(model: SignUpScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(SignInScreenModelImpl::class)
    fun bindSignInScreenModelImpl(model: SignInScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(VerifyScreenModelImpl::class)
    fun bindVerifyScreenModelImpl(model: VerifyScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(PasswordScreenModelImpl::class)
    fun bindPasswordScreenModelImpl(model: PasswordScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(HomeScreenModelImpl::class)
    fun bindHomeScreenModelImpl(model: HomeScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(MainScreenModelImpl::class)
    fun bindMainScreenModelImpl(model: MainScreenModelImpl): ScreenModel

}
