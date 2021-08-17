package com.example.appjogos

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.appjogos.Singleton.Banco
import com.example.appjogos.databinding.FragmentWelcomeBinding

class WelcomeFragment: Fragment(R.layout.fragment_welcome) {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.jogo1Derrotas.text = "Derrotas: " + Banco.usuario!!.jogos[0].derrotas
        binding.jogo1Empates.text = "Empates: " + Banco.usuario!!.jogos[0].empates
        binding.jogo1Vitorias.text = "Vitorias: " + Banco.usuario!!.jogos[0].vitorias

        binding.jogo2Vitorias.text = "Vitorias: " + Banco.usuario!!.jogos[1].vitorias
        binding.jogo2Derrotas.text = "Derrotas: " + Banco.usuario!!.jogos[1].derrotas

        binding.jogo3Vitorias.text = "Vitorias: " + Banco.usuario!!.jogos[2].vitorias
        binding.jogo3Derrotas.text = "Derrotas: " + Banco.usuario!!.jogos[2].derrotas
        binding.jogo3Empates.text = "Empates: " + Banco.usuario!!.jogos[2].empates
    }

}