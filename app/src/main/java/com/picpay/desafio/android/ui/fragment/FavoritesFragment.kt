package com.picpay.desafio.android.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.FragmentFavoritesBinding
import com.picpay.desafio.android.ui.extension.gone
import com.picpay.desafio.android.ui.extension.visible
import com.picpay.desafio.android.ui.utils.FavoriteListAdapter
import com.picpay.desafio.android.ui.viewmodel.UserViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private lateinit var adapter: FavoriteListAdapter

    private val userViewModel by viewModel<UserViewModel>()

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        adapter.clear()
        binding.requireFavorites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            configureFavoritesRecyclerView()

            ivBackToContacts.setOnClickListener {
                it?.findNavController()?.navigate(R.id.action_favoritesFragment_to_contactsFragment)
            }

            requireFavorites()
            userViewModel.favorites().observe(viewLifecycleOwner) {
                it?.let { favorites ->
                    pbLoadingFavorites.gone()
                    adapter.favorites = favorites
                }
            }
        }
    }

    private fun FragmentFavoritesBinding.requireFavorites() {
        pbLoadingFavorites.visible()
        userViewModel.getFavorites()
    }

    private fun FragmentFavoritesBinding.configureFavoritesRecyclerView() {
        adapter = FavoriteListAdapter()
        rvFavoriteContacts.adapter = adapter
        rvFavoriteContacts.layoutManager = GridLayoutManager(requireContext(), 3)
    }

}