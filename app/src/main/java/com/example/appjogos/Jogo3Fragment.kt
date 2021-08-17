package com.example.appjogos

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.appjogos.Singleton.Banco
import com.example.appjogos.databinding.FragmentJogo3Binding
import kotlin.random.Random

class Jogo3Fragment(): Fragment(R.layout.fragment_jogo3) {

    private var _binding: FragmentJogo3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentJogo3Binding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    var usuario: TextView? = null
    var pontosA: TextView? = null
    var pontosB:TextView? = null
    var pontosJogador:TextView? = null
    var pontosBot:TextView? = null
    var ivA1:ImageView? = null
    var ivA2:ImageView? = null
    var ivA3:ImageView? = null
    var ivA4:ImageView? = null
    var ivA5:ImageView? = null
    var ivA6:ImageView? = null
    var ivA7:ImageView? = null
    var ivA8:ImageView? = null
    var ivB1:ImageView? = null
    var ivB2:ImageView? = null
    var ivB3:ImageView? = null
    var ivB4:ImageView? = null
    var ivB5:ImageView? = null
    var ivB6:ImageView? = null
    var ivB7:ImageView? = null
    var ivB8:ImageView? = null
    var listaA: ArrayList<ImageView?> = ArrayList()
    var listaB: ArrayList<ImageView?> = ArrayList()
    var pa = 0
    var pb = 0
    var contador = 2
    var contadorB = 2
    var vinteeum = 0
    var vinteumdois:Int = 0
    var winner = 0
    var r = Random
    var pontoJ = 0
    var pontoB:Int = 0

    constructor(parcel: Parcel) : this() {
        pa = parcel.readInt()
        pb = parcel.readInt()
        contador = parcel.readInt()
        contadorB = parcel.readInt()
        vinteeum = parcel.readInt()
        vinteumdois = parcel.readInt()
        winner = parcel.readInt()
        pontoJ = parcel.readInt()
        pontoB = parcel.readInt()
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pontosA = binding.pontosA
        pontosB = binding.pontosB
        pontosJogador = binding.pontosJogador
        pontosBot = binding.pontosBot
        pontoJ = Banco.usuario!!.jogos[2].vitorias
        pontoB = Banco.usuario!!.jogos[2].derrotas
        binding.pontosJogador.setText(pontoJ.toString())
        binding.pontosBot.setText(pontoB.toString())
        definirImagens()
        encherListas()
        inicioGame()

        binding.btNovoJogo.setOnClickListener {
            listaA.clear()
            listaB.clear()
            when (winner) {
                0 -> {
                }
                1 -> pontoJ += 1
                2 -> pontoB += 1
            }
            winner = 0
            pa = 0
            pb = 0
            contador = 2
            contadorB = 2
            vinteeum = 0
            vinteumdois = 0
            removerImagem()
            inicioGame()
            pontosA!!.text = pa.toString()
            binding.pontosB.setText(pb.toString())
            binding.pontosJogador.setText(pontoJ.toString())
            binding.pontosBot.setText(pontoB.toString())
        }

        if (vinteeum == 3 && vinteumdois == 3) {
            pa = 21
            Toast.makeText(activity, "DRAW.", Toast.LENGTH_SHORT).show()
            Banco.usuario!!.jogos[2].empates += 1
        }
        if (vinteeum == 3) {
            winner = 1
            pontosA!!.text = 21.toString()
            Toast.makeText(activity, "Players Wins.", Toast.LENGTH_SHORT).show()
            Banco.usuario!!.jogos[2].vitorias += 1
        } else {
            pontosA!!.text = pa.toString()
        }
        if (vinteumdois == 3) {
            winner = 2
            binding.pontosB.setText(21.toString())
            Toast.makeText(activity, "BOT Wins.", Toast.LENGTH_SHORT).show()
            Banco.usuario!!.jogos[2].derrotas += 1
        } else {
            binding.pontosB.setText(pb.toString())
        }

        binding.btPedir.setOnClickListener {
            if (pa < 21 && winner == 0) {
                if (contador < listaA.size) {
                    sortearCartaA(listaA[contador])
                    pontosA!!.text = pa.toString()
                    contador++
                }
            }
            if (pa == 21) {
                winner = 1
                Toast.makeText(activity, "Players Wins.", Toast.LENGTH_SHORT).show()
                Banco.usuario!!.jogos[2].vitorias += 1
            }
            if (pa > 21) {
                Toast.makeText(activity, "BOT Wins.", Toast.LENGTH_SHORT).show()
                winner = 2
                Banco.usuario!!.jogos[2].derrotas += 1
            }
        }

        binding.btParar.setOnClickListener {
            while (pb <= pa && pa < 21 && winner == 0) {
                if (contadorB < listaB.size) {
                    sortearCartaB(listaB[contadorB])
                    pontosB!!.text = pb.toString()
                    contadorB++
                }
            }
            if (pb == 21 || pb > pa && pb <= 21 || pa > 21) {
                winner = 2
                Toast.makeText(activity, "BOT Wins.", Toast.LENGTH_SHORT).show()
            } else {
                winner = 1
                Toast.makeText(activity, "Player Wins.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun sortearCartaA(img: ImageView?) {
        val i: Int = r.nextInt(13) + 1
        when (i) {
            1 -> {
                img!!.setImageResource(R.drawable.spade1)
                pa += 1
                vinteeum += 1
            }
            2 -> {
                img!!.setImageResource(R.drawable.spade2)
                pa += 2
            }
            3 -> {
                img!!.setImageResource(R.drawable.spade3)
                pa += 3
            }
            4 -> {
                img!!.setImageResource(R.drawable.spade4)
                pa += 4
            }
            5 -> {
                img!!.setImageResource(R.drawable.spade5)
                pa += 5
            }
            6 -> {
                img!!.setImageResource(R.drawable.spade6)
                pa += 6
            }
            7 -> {
                img!!.setImageResource(R.drawable.spade7)
                pa += 7
            }
            8 -> {
                img!!.setImageResource(R.drawable.spade8)
                pa += 8
            }
            9 -> {
                img!!.setImageResource(R.drawable.spade9)
                pa += 9
            }
            10 -> {
                img!!.setImageResource(R.drawable.spade10)
                pa += 10
            }
            11 -> {
                img!!.setImageResource(R.drawable.spade11)
                pa += 10
                vinteeum = 2
            }
            12 -> {
                img!!.setImageResource(R.drawable.spade12)
                pa += 10
                vinteeum = 2
            }
            13 -> {
                img!!.setImageResource(R.drawable.spade13)
                pa += 10
                vinteeum = 2
            }
        }
    }

    fun sortearCartaB(img: ImageView?) {
        val i: Int = r.nextInt(13) + 1
        when (i) {
            1 -> {
                img!!.setImageResource(R.drawable.spade1)
                pb += 1
                vinteumdois += 1
            }
            2 -> {
                img!!.setImageResource(R.drawable.spade2)
                pb += 2
            }
            3 -> {
                img!!.setImageResource(R.drawable.spade3)
                pb += 3
            }
            4 -> {
                img!!.setImageResource(R.drawable.spade4)
                pb += 4
            }
            5 -> {
                img!!.setImageResource(R.drawable.spade5)
                pb += 5
            }
            6 -> {
                img!!.setImageResource(R.drawable.spade6)
                pb += 6
            }
            7 -> {
                img!!.setImageResource(R.drawable.spade7)
                pb += 7
            }
            8 -> {
                img!!.setImageResource(R.drawable.spade8)
                pb += 8
            }
            9 -> {
                img!!.setImageResource(R.drawable.spade9)
                pb += 9
            }
            10 -> {
                img!!.setImageResource(R.drawable.spade10)
                pb += 10
            }
            11 -> {
                img!!.setImageResource(R.drawable.spade11)
                pb += 10
                vinteumdois = 2
            }
            12 -> {
                img!!.setImageResource(R.drawable.spade12)
                pb += 10
                vinteumdois = 2
            }
            13 -> {
                img!!.setImageResource(R.drawable.spade13)
                pb += 10
                vinteumdois = 2
            }
        }
    }

    fun definirImagens() {
        ivA1 = binding.ivA1
        ivA2 = binding.ivA2
        ivA3 = binding.ivA3
        ivA4 = binding.ivA4
        ivA5 = binding.ivA5
        ivA6 = binding.ivA6
        ivA7 = binding.ivA7
        ivA8 = binding.ivA8
        ivB1 = binding.ivB1
        ivB2 = binding.ivB2
        ivB3 = binding.ivB3
        ivB4 = binding.ivB4
        ivB5 = binding.ivB5
        ivB6 = binding.ivB6
        ivB7 = binding.ivB7
        ivB8 = binding.ivB8
    }

    fun encherListas() {
        listaA.add(ivA1)
        listaA.add(ivA2)
        listaB.add(ivB1)
        listaB.add(ivB2)
        listaA.add(ivA3)
        listaA.add(ivA4)
        listaB.add(ivB3)
        listaB.add(ivB4)
        listaA.add(ivA5)
        listaA.add(ivA6)
        listaB.add(ivB5)
        listaB.add(ivB6)
        listaA.add(ivA7)
        listaA.add(ivA8)
        listaB.add(ivB7)
        listaB.add(ivB8)
    }

    fun inicioGame() {
        encherListas()
        sortearCartaA(listaA[0])
        sortearCartaA(listaA[1])
        sortearCartaB(listaB[0])
        sortearCartaB(listaB[1])
    }

    fun removerImagem() {
        binding.ivA1.setImageResource(0)
        binding.ivA2.setImageResource(0)
        binding.ivA3.setImageResource(0)
        binding.ivA4.setImageResource(0)
        binding.ivA5.setImageResource(0)
        binding.ivA6.setImageResource(0)
        binding.ivA7.setImageResource(0)
        binding.ivA8.setImageResource(0)
        binding.ivB1.setImageResource(0)
        binding.ivB2.setImageResource(0)
        binding.ivB3.setImageResource(0)
        binding.ivB4.setImageResource(0)
        binding.ivB5.setImageResource(0)
        binding.ivB6.setImageResource(0)
        binding.ivB7.setImageResource(0)
        binding.ivB8.setImageResource(0)
    }

    fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(pa)
        parcel.writeInt(pb)
        parcel.writeInt(contador)
        parcel.writeInt(contadorB)
        parcel.writeInt(vinteeum)
        parcel.writeInt(vinteumdois)
        parcel.writeInt(winner)
        parcel.writeInt(pontoJ)
        parcel.writeInt(pontoB)
    }

    fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Jogo3Fragment> {
        override fun createFromParcel(parcel: Parcel): Jogo3Fragment {
            return Jogo3Fragment(parcel)
        }

        override fun newArray(size: Int): Array<Jogo3Fragment?> {
            return arrayOfNulls(size)
        }
    }

}