package uz.gita.m1nex.presenter

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container

interface AppViewModel<STATE : Any, SIDE_EFFECT : Any> : ContainerHost<STATE, SIDE_EFFECT>, ScreenModel {
    fun <STATE : Any, SIDE_EFFECT : Any> container(
        initialState: STATE,
        settings: Container.Settings = Container.Settings(),
        onCreate: ((state: STATE) -> Unit)? = null
    ) = screenModelScope.container<STATE, SIDE_EFFECT>(initialState, settings, onCreate)
}