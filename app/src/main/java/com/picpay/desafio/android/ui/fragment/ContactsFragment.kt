package com.picpay.desafio.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentContactsBinding
import com.picpay.desafio.android.ui.extension.gone
import com.picpay.desafio.android.ui.extension.visible
import com.picpay.desafio.android.ui.utils.UserListAdapter
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : Fragment() {

    private lateinit var adapter: UserListAdapter

    private val userViewModel by viewModel<UserViewModel>()

    private var _binding: FragmentContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        adapter.clear()
        binding.requireUsers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            configureUsersRecyclerView()

            fabFavorites.setOnClickListener {
                it?.findNavController()?.navigate(R.id.action_contactsFragment_to_favoritesFragment)
            }

            userViewModel.users().observe(viewLifecycleOwner) {
                it?.let { users ->
                    pbLoadingContacts.gone()
                    adapter.users = users

                    if (users.isEmpty())
                        showTryAgainSnackbar()
                }
            }
        }
    }

    private fun FragmentContactsBinding.showTryAgainSnackbar() {
        Snackbar.make(this.root, R.string.error, Snackbar.LENGTH_INDEFINITE)
            .setMaxInlineActionWidth(256)
            .setAction(R.string.try_again) { requireUsers() }
            .show()
    }

    private fun FragmentContactsBinding.requireUsers() {
        pbLoadingContacts.visible()
        userViewModel.getContacts()
    }

    private fun FragmentContactsBinding.configureUsersRecyclerView() {
        adapter = UserListAdapter(onIsFavoriteChangeListener = { user, isFavorite ->
            if (isFavorite)
                userViewModel.saveFavorite(user)
            else
                userViewModel.removeFavorite(user.id)
        })
        rvContacts.adapter = adapter
        rvContacts.layoutManager = LinearLayoutManager(requireContext())
    }

}