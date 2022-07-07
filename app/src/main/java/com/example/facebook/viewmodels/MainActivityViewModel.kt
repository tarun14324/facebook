package com.example.facebook.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.viewModelScope
import com.example.facebook.api.RetrofitInstance
import com.example.facebook.dataclasses.LoginRequest
import com.example.facebook.util.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivityViewModel : BaseViewModel() {
    private val loginEventChannel = Channel<Unit>()
    val loginEvent = loginEventChannel.receiveAsFlow()
    private val status = Channel<Boolean>()
    val statusEvent = status.receiveAsFlow()

    //    private val forgotPasswordEventChannel = Channel<Unit>()
//    val forgotPasswordEvent = forgotPasswordEventChannel.receiveAsFlow()
//
//    private val createAccountEventChannel = Channel<Unit>()
//    val createAccountEvent = createAccountEventChannel.receiveAsFlow()
//
    fun loginBtnClicked(name: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val Response = RetrofitInstance().apiService.signin(LoginRequest(name, password))
            Log.e("TAG", "loginBtnClicked: ${Response.message()}")
            if (Response.isSuccessful) {
                status.send(true)
            } else {
                status.send(false)

            }



        }
    }
//
//    fun login(name:String,password:String) {
//
//
//        // startActivity(Intent(this, HomeActivity::class.java))
//    }
//
//    fun forgotPasswordBtnClicked() {
//        viewModelScope.launch {
//            forgotPasswordEventChannel.send(Unit)
//        }
//    }
//
//    fun createAccBtnClicked() {
//        viewModelScope.launch {
//            createAccountEventChannel.send(Unit)
//        }
//    }
}
