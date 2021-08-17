package com.example.appjogos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appjogos.Singleton.Banco
import com.example.appjogos.Singleton.Usuario
import com.example.appjogos.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            var username = binding.usuarioEditText.text.toString()
            var senha = binding.senhaEditText.text.toString()
            var user = Usuario(username, senha)
            var index = 0

            for (i in Banco.usuarios){
                if (Banco.usuarios[index].username == user.username){
                    Banco.usuario = Banco.usuarios[index]
                    val action = HomeFragmentDirections.actionHomeFragmentToWelcomeFragment()
                    findNavController().navigate(action)
                }else{
                    Toast.makeText(activity,"Usuario nãoencontrado!", Toast.LENGTH_SHORT).show()
                    index += 1
                }

            }

        }

        binding.registerButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToLoginFragment()
            findNavController().navigate(action)
        }
    }
}