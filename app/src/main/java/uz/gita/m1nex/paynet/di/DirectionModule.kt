package uz.gita.m1nex.paynet.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.m1nex.paynet.app.screen.addcard.AddCardDirectionImpl
import uz.gita.m1nex.paynet.app.screen.card.CardDirectionImpl
import uz.gita.m1nex.paynet.app.screen.home.HomeDirectionImpl
import uz.gita.m1nex.paynet.app.screen.home.tab.main.MainDirectionImpl
import uz.gita.m1nex.paynet.app.screen.home.tab.transfer.TransferDirectionImpl
import uz.gita.m1nex.paynet.app.screen.password.PasswordDirectionImpl
import uz.gita.m1nex.paynet.app.screen.profile.ProfileDirectionImpl
import uz.gita.m1nex.paynet.app.screen.signin.SignInDirectionImpl
import uz.gita.m1nex.paynet.app.screen.verify.VerifyDirectionImpl
import uz.gita.m1nex.paynet.app.screen.signup.SignUpDirectionImpl
import uz.gita.m1nex.paynet.app.screen.splash.SplashDirectionImpl
import uz.gita.m1nex.paynet.app.screen.transfer.card.TransferCardDirectionImpl
import uz.gita.m1nex.paynet.app.screen.transfer.verify.TransferVerifyDirectionImpl
import uz.gita.m1nex.paynet.app.screen.update.CardUpdateDirectionImpl
import uz.gita.m1nex.paynet.app.screen.user.UserDataDirectionImpl
import uz.gita.m1nex.presenter.screenmodel.addcard.AddCardContract
import uz.gita.m1nex.presenter.screenmodel.card.CardContract
import uz.gita.m1nex.presenter.screenmodel.home.HomeContract
import uz.gita.m1nex.presenter.screenmodel.home.tab.main.MainContract
import uz.gita.m1nex.presenter.screenmodel.password.PasswordContract
import uz.gita.m1nex.presenter.screenmodel.profile.ProfileContract
import uz.gita.m1nex.presenter.screenmodel.signin.SignInContract
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpContract
import uz.gita.m1nex.presenter.screenmodel.splash.SplashContract
import uz.gita.m1nex.presenter.screenmodel.transfer.TransferContract
import uz.gita.m1nex.presenter.screenmodel.transfer.card.TransferCardContract
import uz.gita.m1nex.presenter.screenmodel.transfer.verify.TransferVerifyContract
import uz.gita.m1nex.presenter.screenmodel.update.CardUpdateContract
import uz.gita.m1nex.presenter.screenmodel.user.UserDataContract
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

    @Binds
    fun mainDirection(impl: MainDirectionImpl): MainContract.Direction

    @Binds
    fun profileDirection(impl: ProfileDirectionImpl): ProfileContract.Direction

    @Binds
    fun addCardDirection(impl: AddCardDirectionImpl): AddCardContract.Direction

    @Binds
    fun cardDirection(impl: CardDirectionImpl): CardContract.Direction

    @Binds
    fun transferDirection(impl: TransferDirectionImpl): TransferContract.Direction

    @Binds
    fun transferCardDirection(impl: TransferCardDirectionImpl): TransferCardContract.Direction

    @Binds
    fun transferVerifyDirection(impl: TransferVerifyDirectionImpl): TransferVerifyContract.Direction

    @Binds
    fun userDataDirection(impl: UserDataDirectionImpl): UserDataContract.Direction

    @Binds
    fun cardUpdateDirection(impl: CardUpdateDirectionImpl): CardUpdateContract.Direction
}