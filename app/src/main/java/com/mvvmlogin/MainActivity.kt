package com.mvvmlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvvmlogin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Variáveis
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Eventos
        binding.buttonLogin.setOnClickListener(this)

        // Observadores
        createObservers()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login){
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            viewModel.doLogin(email, password)
        }
    }

    private fun createObservers() {
        viewModel.welcome().observe(this, Observer {
            binding.textWelcome.text = it
        })
        viewModel.login().observe(this, Observer {
            if (it){
                Toast.makeText(this, "SUCESSO!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "FALHA!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}