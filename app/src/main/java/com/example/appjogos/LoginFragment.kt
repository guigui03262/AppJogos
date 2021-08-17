package com.example.appjogos

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appjogos.Singleton.Banco
import com.example.appjogos.Singleton.Jogos
import com.example.appjogos.Singleton.Usuario
import com.example.appjogos.databinding.FragmentLoginBinding

class LoginFragment: Fragment(R.layout.fragment_login) {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.confirmButton.setOnClickListener {
            var jogo1 = Jogos("jogo1", 0, 0, 0)
            var jogo2 = Jogos("jogo2", 0, 0, 0)
            var jogo3 = Jogos("jogo3", 0, 0, 0)
            var jogos = ArrayList<Jogos>()
            jogos.add(jogo1)
            jogos.add(jogo2)
            jogos.add(jogo3)
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            var usuario = Usuario(username, password)
            usuario.jogos.addAll(jogos)
            var aux = 1
            var index = 0

            for (i in Banco.usuarios){
                if (Banco.usuarios[index].username == usuario.username){
                    aux = 0
                    break
                }
                index += 1
            }

            if(aux == 0){
                Toast.makeText(activity, "Usuario já cadastrado", Toast.LENGTH_SHORT).show()
            } else if (username.isEmpty() || password.isEmpty()){
                Toast.makeText(activity, "Campos não podm estar em branco", Toast.LENGTH_SHORT).show()
            }else{
                Banco.usuarios.add(usuario)
                val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment2()
                findNavController().navigate(action)
            }

        }
    }
}