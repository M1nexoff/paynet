package uz.gita.m1nex.presenter.di

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.hilt.ScreenModelKey
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import uz.gita.m1nex.presenter.screenmodel.addcard.AddCardScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.card.CardScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.history.HistoryScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.home.HomeScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.home.tab.main.MainScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.password.PasswordScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.profile.ProfileScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.signin.SignInScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.signup.SignUpScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.splash.SplashScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.transfer.TransferScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.transfer.card.TransferCardScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.transfer.verify.TransferVerifyScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.update.CardUpdateScreenModelImpl
import uz.gita.m1nex.presenter.screenmodel.user.UserDataScreenModelImpl
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

    @Binds
    @IntoMap
    @ScreenModelKey(ProfileScreenModelImpl::class)
    fun bindProfileScreenModelImpl(model: ProfileScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(AddCardScreenModelImpl::class)
    fun bindAddCardScreenModelImpl(model: AddCardScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(CardScreenModelImpl::class)
    fun bindCardScreenModelImpl(model: CardScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(TransferScreenModelImpl::class)
    fun bindTransferScreenModelImpl(model: TransferScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(TransferCardScreenModelImpl::class)
    fun bindTransferCardScreenModelImpl(model: TransferCardScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(TransferVerifyScreenModelImpl::class)
    fun bindTransferVerifyScreenModelImpl(model: TransferVerifyScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(HistoryScreenModelImpl::class)
    fun bindHistoryScreenModelImpl(model: HistoryScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(UserDataScreenModelImpl::class)
    fun bindUserDataScreenModelImpl(model: UserDataScreenModelImpl): ScreenModel

    @Binds
    @IntoMap
    @ScreenModelKey(CardUpdateScreenModelImpl::class)
    fun bindCardUpdateScreenModelImpl(model: CardUpdateScreenModelImpl): ScreenModel
}
