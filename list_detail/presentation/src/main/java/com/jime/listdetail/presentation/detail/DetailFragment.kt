package com.jime.listdetail.presentation.detail

import android.content.DialogInterface
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.jime.listdetail.domain.Resource
import com.jime.listdetail.domain.error.Error
import com.jime.listdetail.domain.model.OompaLoompaDetail
import com.jime.listdetail.presentation.R
import com.jime.listdetail.presentation.databinding.FragmentDetailBinding
import com.jime.listdetail.presentation.detail.viewmodel.OompaLoompaDetailViewModel
import com.jime.listdetail.presentation.util.getStringId
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: OompaLoompaDetailViewModel by viewModels()
    private val arguments: DetailFragmentArgs by navArgs()
    private var oompaLoompaId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        oompaLoompaId = arguments.argId
        viewModel.getOompaLoompaById(oompaLoompaId)
        viewModel.oompaLoompa.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Success -> {
                    updateOompaLoompaInfo(result.data)
                    showDetail()
                }
                is Resource.Failure -> {
                    showError(result.error)
                }
            }
        }
        viewModel.progressVisible.observe(viewLifecycleOwner) {
            updateProgressVisibility(it)
        }
    }

    private fun showError(error: Error) {
        val dialog = AlertDialog.Builder(requireContext())
            .setMessage(getString(error.getStringId()))
            .setPositiveButton(
                R.string.retry
            ) { _, _ -> viewModel.getOompaLoompaById(oompaLoompaId) }
        dialog.show()
    }

    private fun showDetail() {
        binding.detailView.visibility = View.VISIBLE
    }

    private fun updateProgressVisibility(visible: Boolean) {
        binding.progress.visibility = if (visible) View.VISIBLE else View.GONE
    }

    private fun updateOompaLoompaInfo(oompaLoompa: OompaLoompaDetail) {
        binding.apply {
            profilePic.load(oompaLoompa.image)
            name.text = oompaLoompa.name
            lastName.text = oompaLoompa.lastName
            email.text = oompaLoompa.email
            gender.text = getString(oompaLoompa.gender.getStringId())
            country.text = oompaLoompa.country
            profession.text = getString(oompaLoompa.profession.getStringId())
            description.text = Html.fromHtml(oompaLoompa.description, Html.FROM_HTML_MODE_COMPACT)
            favoriteSong.text = oompaLoompa.favoriteSong
            age.text = oompaLoompa.age.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}