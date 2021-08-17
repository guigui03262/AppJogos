package com.example.appjogos

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appjogos.Singleton.Banco
import com.example.appjogos.databinding.FragmentJogo2Binding
import kotlin.random.Random

class Jogo2Fragment: Fragment(R.layout.fragment_jogo2) {

    private var _binding: FragmentJogo2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentJogo2Binding.inflate(inflater, container, false)
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
        binding.btnCoroa.setOnClickListener {
            binding.tvResult.text = joga(0)
        }
        binding.btnCara.setOnClickListener {
            binding.tvResult.text = joga(1)
        }
    }

    fun joga(num: Int): String {
        binding.ivComp.visibility = View.VISIBLE
        val comp = Random.nextInt(2)
        var end: String = ""
        Log.d("Jogo comp: ",comp.toString())
        when(comp){
            0 -> binding.ivComp.setImageResource(R.drawable.coroa)
            1 -> binding.ivComp.setImageResource(R.drawable.cara)
        }
        if(comp == 0 && num == 1 || comp == 1 && num == 0){
            end = "Perdeu"
            binding.tvResult.setTextColor(Color.RED)
            Banco.usuario!!.jogos[1].derrotas += 1
        }
        else{
            end = "Ganhou"
            binding.tvResult.setTextColor(Color.GREEN)
            Banco.usuario!!.jogos[1].vitorias += 1
        }
        return end
    }

}