package com.picpay.desafio.android.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.utils.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: UserListAdapter

    private val userViewModel by viewModel<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        with(binding) {
            configureUserListRecyclerView()

            requireUsers()

            userViewModel.users().observe(this@MainActivity) {
                it?.let { userList ->
                    if (userList.isEmpty()) {
                        val message = getString(R.string.error)

                        pbLoadingContacts.visibility = View.GONE
                        rvContacts.visibility = View.GONE

                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                        // TODO: alterar para snackBar com botão "tentar novamente" e bater o
                        //  requireUsers() de novo no clique
                    } else {
                        pbLoadingContacts.visibility = View.GONE

                        adapter.users = userList
                    }
                }
            }
        }
    }

    private fun ActivityMainBinding.requireUsers() {
        userViewModel.getContacts()
        pbLoadingContacts.visibility = View.VISIBLE
    }

    private fun ActivityMainBinding.configureUserListRecyclerView() {
        adapter = UserListAdapter()
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(this@MainActivity)
    }

}
