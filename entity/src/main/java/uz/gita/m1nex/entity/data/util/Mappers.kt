package uz.gita.m1nex.entity.data.util

import uz.gita.m1nex.core.data.model.BasicInfo
import uz.gita.m1nex.core.data.model.card.AddCard
import uz.gita.m1nex.core.data.model.sign.SignIn
import uz.gita.m1nex.core.data.model.sign.SignUp
import uz.gita.m1nex.entity.data.model.request.AddCardRequest
import uz.gita.m1nex.entity.data.model.request.SignInRequest
import uz.gita.m1nex.entity.data.model.request.SignUpRequest
import uz.gita.m1nex.entity.data.model.respone.BasicInfoResponse

fun SignIn.toRequest() = SignInRequest(this.phone, this.password)

fun SignUp.toRequest() = SignUpRequest(this.phone, this.password, this.firstName, this.lastName, this.bornDate, this.gender)

fun AddCard.toRequest() = AddCardRequest(this.pan, this.expiredYear, this.expiredMonth, this.type)

fun BasicInfoResponse.toBasicInfo() = BasicInfo(this.firstName, this.genderType, this.age)
