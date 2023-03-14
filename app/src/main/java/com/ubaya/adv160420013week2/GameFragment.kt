package com.ubaya.adv160420013week2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val btnBack = view.findViewById<Button>(R.id.btnBack)
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmit)
        val txtTurn = view.findViewById<TextView>(R.id.txtTurn)
        val txtAnswer = view.findViewById<EditText>(R.id.txtAnswer)
        var playerScore: Int = 0
        var num1 = (0..100).random()
        var num2 = (0..100).random()
        view.findViewById<TextView>(R.id.txtNum1).text = num1.toString()
        view.findViewById<TextView>(R.id.txtNum2).text = num2.toString()

        btnSubmit.setOnClickListener{
            if(txtAnswer.text.toString() == ""){
                Snackbar.make(view, "You should give your answer", Snackbar.LENGTH_SHORT).show()
            } else {
                var hasil = num1 + num2
                var answer = txtAnswer.text.toString().toInt()
                if(hasil.toInt() == answer){
                    num1 = (0..100).random()
                    num2 = (0..100).random()
                    view.findViewById<TextView>(R.id.txtNum1).text = num1.toString()
                    view.findViewById<TextView>(R.id.txtNum2).text = num2.toString()
                    playerScore += 1
                } else {
                    val action = GameFragmentDirections.actionGameFragmentToResultFragment(playerScore)
                    Navigation.findNavController(it).navigate(action)
                }
            }
        }

//        btnBack.setOnClickListener {
//            val action = GameFragmentDirections.actionMainFragment()
//            Navigation.findNavController(it).navigate(action)
//        }
        arguments?.let {
            val playerName =
                GameFragmentArgs.fromBundle(requireArguments()).playerName
            txtTurn.text = "$playerName's Turn"
            playerScore = GameFragmentArgs.fromBundle(requireArguments()).playerScore
        }

    }
}