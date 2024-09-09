package uz.gita.m1nex.paynet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.m1nex.paynet.app.screen.home.HomeDirectionImpl
import uz.gita.m1nex.paynet.app.screen.password.PasswordDirectionImpl
import uz.gita.m1nex.paynet.app.screen.signin.SignInDirectionImpl
import uz.gita.m1nex.paynet.app.screen.verify.VerifyDirectionImpl
import uz.gita.m1nex.paynet.app.screen.signup.SignUpDirectionImpl
import uz.gita.m1nex.paynet.app.screen.splash.SplashDirectionImpl
import uz.gita.m1nex.presenter.screenmodel.home.HomeContract
import uz.gita.m1nex.presenter.screenmodel.password.PasswordContract
import uz.gita.m1nex.presenter.screenmodel.signin.SignInContract
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.presenter.screenmodel.splash.SplashContract
import uz.gita.m1nex.presenter.screenmodel.verify.VerifyContract

@Module
@InstallIn(SingletonComponent::class)
interface DirectionModule {
    @Binds
    fun splashDirection(impl: SplashDirectionImpl): SplashContract.Direction

    @Binds
    fun signUpDirection(impl: SignUpDirectionImpl): SignUpContract.Direction

    @Binds
    fun signInDirection(impl: SignInDirectionImpl): SignInContract.Direction

    @Binds
    fun verifyDirection(impl: VerifyDirectionImpl): VerifyContract.Direction

    @Binds
    fun passwordDirection(impl: PasswordDirectionImpl): PasswordContract.Direction

    @Binds
    fun homeDirection(impl: HomeDirectionImpl): HomeContract.Direction
}