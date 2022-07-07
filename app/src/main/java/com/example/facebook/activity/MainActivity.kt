package com.example.facebook.activity

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.facebook.R
import com.example.facebook.databinding.ActivityMainBinding
import com.example.facebook.util.BaseActivity
import com.example.facebook.viewmodels.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityViewModel>() {

    private fun loginValidation() {
        if (dataBinding.phEmailEdt.text.toString().isEmpty()) {
            dataBinding.tilPhnOrEmail.error = "Please Enter Email or Phone Number"
        } else if (dataBinding.etPassword.text.toString().isEmpty()) {
            dataBinding.tilPassword.error = "Please Enter Password"
        } else {
            dataBinding.tilPhnOrEmail.isErrorEnabled = false
            dataBinding.tilPassword.isErrorEnabled = false
            viewModel.loginBtnClicked(
                dataBinding.phEmailEdt.text.toString(),
                dataBinding.etPassword.text.toString()
            )
        }

    }

    override fun setupViews() {
        dataBinding.loginVM = viewModel
        dataBinding.loginBtn.setOnClickListener {
            loginValidation()
        }
        lifecycleScope.launchWhenResumed {
            viewModel.statusEvent.collectLatest { status ->
                if (status) {
                    startActivity(Intent(this@MainActivity, HomeActivity::class.java))
                } else {
                    Toast.makeText(this@MainActivity, "Invalid User details", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        }
    }

    override fun getViewModel() = MainActivityViewModel::class.java

    override fun getResourceId(): Int = R.layout.activity_main
}