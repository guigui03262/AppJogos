package com.example.appjogos

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appjogos.Singleton.Banco
import com.example.appjogos.databinding.FragmentJogo1Binding
import kotlin.random.Random

class Jogo1Fragment: Fragment(R.layout.fragment_jogo1) {

    private var _binding: FragmentJogo1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJogo1Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ivComp.visibility = View.GONE
        binding.btnPapel.setOnClickListener {
            binding.tvResult.text = joga(0)
        }
        binding.btnPedra.setOnClickListener {
            binding.tvResult.text = joga(1)
        }
        binding.btnTesoura.setOnClickListener {
            binding.tvResult.text = joga(2)
        }
    }

    fun joga(num: Int): String {
        binding.ivComp.visibility = View.VISIBLE
        val comp = Random.nextInt(3)
        var end: String = ""
        Log.d("Jogo comp: ",comp.toString())
        when(comp){
            0 -> binding.ivComp.setImageResource(R.drawable.papel)
            1 -> binding.ivComp.setImageResource(R.drawable.pedra)
            2 -> binding.ivComp.setImageResource(R.drawable.tesoura)
        }
        if(comp == num){
            end = "Empatou"
            binding.tvResult.setTextColor(Color.GRAY)
            Banco.usuario!!.jogos[0].empates += 1
        }
        else if((comp == 0 && num == 1) || (comp == 1 && num == 2) || (comp == 2 && num == 0)){
            end = "Perdeu"
            binding.tvResult.setTextColor(Color.RED)
            Banco.usuario!!.jogos[0].derrotas += 1
        }
        else{
            end = "Ganhou"
            binding.tvResult.setTextColor(Color.GREEN)
            Banco.usuario!!.jogos[0].vitorias += 1
        }
        return end
    }

}