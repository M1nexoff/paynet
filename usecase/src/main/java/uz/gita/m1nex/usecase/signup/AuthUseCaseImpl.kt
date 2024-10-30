package uz.gita.m1nex.usecase.signup

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.data.model.sign.SignIn
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.entity.repository.AuthRepository
import javax.inject.Inject

internal class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : AuthUseCase {
    init {
        Log.d("TTT", "AuthUseCase: init")
    }
    override fun signUp(signUp: SignUp): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.signUp(signUp)

        emit(result)
    }

    override fun signIn(signUp: SignIn): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.signIn(signUp)
        emit(result)
    }

    override fun signUpVerify(code: String): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.signUpVerify(code)
        emit(result)
    }

    override fun signInVerify(code: String): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.signInVerify(code)
        emit(result)
    }

    override fun signUpResend(): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.signUpResend()
        emit(result)
    }

    override fun signInResend(): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.signInResend()
        emit(result)
    }

    override fun updateToken(): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.updateToken()
        emit(result)
    }

    override fun logout(): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.logout()
        emit(result)


    }

}