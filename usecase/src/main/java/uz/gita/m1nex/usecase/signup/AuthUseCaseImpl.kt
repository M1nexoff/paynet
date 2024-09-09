package uz.gita.m1nex.usecase.signup

import android.util.Log
import kotlinx.coroutines.flow.Flow
import uz.gita.m1nex.core.ResultData
import uz.gita.m1nex.core.asResource
import uz.gita.m1nex.core.flowWithCatch
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignInVerifyRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.entity.data.model.request.SignUpVerifyRequest
import uz.gita.m1nex.entity.data.model.request.TokenRequest
import uz.gita.m1nex.entity.data.model.request.UpdateTokenRequest
import uz.gita.m1nex.entity.data.model.respone.SignInVerifyResponse
import uz.gita.m1nex.entity.data.model.respone.SignUpResponse
import uz.gita.m1nex.entity.data.model.respone.SignUpVerifyResponse
import uz.gita.m1nex.entity.data.model.respone.TokenResponse
import uz.gita.m1nex.entity.data.model.respone.UpdateTokenResponse
import uz.gita.m1nex.entity.repository.AuthRepository
import javax.inject.Inject

internal class AuthUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
) : AuthUseCase {
    init {
        Log.d("TTT", "AuthUseCase: init")
    }
    override fun signUp(signUpRequest: SignUpRequest): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.signUp(signUpRequest)

        emit(result)
    }

    override fun signIn(signUpRequest: SignInRequest): Flow<ResultData<Unit>> = flowWithCatch{
        val result = authRepository.signIn(signUpRequest)
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

}