package com.mhebrawi.s181663

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mhebrawi.s181663.databinding.FragmentPlayingGameLandswordsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PlayingGameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PlayingGameFragment : Fragment() {

    private lateinit var binding: FragmentPlayingGameLandswordsBinding


    var lives: Int = 5
    var scores: Int = 0
    var points: Int = 0
    var handler = Handler()


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_playing_game_landswords, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPlayingGameLandswordsBinding.bind(view)
        setLife(lives)
        setScore(scores)
        updateLifeScore()
        binding.textViewShowTheWord.text = getRandomLandName()
        binding.textViewShowTheWord.text = hiddenLandsNameWord()

        binding.buClickToSendTheLetter.setOnClickListener {
            game()
        }

    }

    override fun onStart() {
        super.onStart()
        spin()
    }


    private fun spinAgain() {
        binding.apply {
            buClickToSendTheLetter.visibility = View.GONE
            EditTextWritTheWord.visibility = View.GONE
            EditTextWritTheWord.setText("")
            buClickToStartSpining.visibility = View.VISIBLE
        }
    }

    @SuppressLint("WrongConstant")
    private fun spin() {
        binding.apply {
            buClickToStartSpining.setOnClickListener {
                buClickToStartSpining.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                spinWheelFun()
                spinlogic()
                if (getSpinner() in 0..2) {
                    val x = Runnable {
                        progressBar.visibility = View.GONE
                        buClickToSendTheLetter.visibility = View.VISIBLE
                        textViewShowTheWord.visibility = View.VISIBLE
                        EditTextWritTheWord.visibility = View.VISIBLE
                        buClickToStartSpining.visibility = View.GONE
                        Toast.makeText(activity, getSpinnerResult(), 0).show()
                    }
                    handler.postDelayed(x, 1000)
                } else if (getSpinner() == 3) {
                    progressBar.visibility = View.VISIBLE
                    Toast.makeText(activity, getSpinnerResult() + " prøv igen ", 0).show()
                    buClickToStartSpining.visibility = View.VISIBLE
                    textViewShowTheWord.visibility = View.GONE
                    EditTextWritTheWord.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun spinlogic() {

        binding.apply {
            when {
                getSpinner() == 0 -> {
                    extra1000(scores)
                    scores = getScore()
                    binding.playerScore.text = "score$scores"
                    buClickToSendTheLetter.setOnClickListener {
                        game()
                    }
                }
                getSpinner() == 1 -> {
                    extraLife(lives)
                    lives = updateLives()
                    binding.playerLives.text = "Lives $lives"
                    buClickToSendTheLetter.setOnClickListener {
                        game()
                    }
                }
                getSpinner() == 2 -> {
                    resetScore(scores)
                    scores = getScore()
                    binding.playerScore.text = "Score$scores"
                    buClickToSendTheLetter.setOnClickListener {
                        game()
                    }
                }
                getSpinner() == 3 -> {
                    minusLife(lives)
                    lives = updateLives()
                    binding.playerLives.text = "lives $lives"
                    buClickToStartSpining.visibility = View.VISIBLE


                }
                lives <= 0 -> {
                    Toast.makeText(activity, "Du har tabte", Toast.LENGTH_LONG).show()
                    gamWonOver()

                }
            }


        }
    }


    @SuppressLint("SetTextI18n")
     private fun updateLifeScore() {
        binding.apply {
            playerLives.text = "Lives : ${updateLives()}"
            playerScore.text = "Score : ${getScore()}"
        }
    }


    @SuppressLint("WrongConstant", "SetTextI18n")
   private fun game() {
        binding.apply {
            if (EditTextWritTheWord.text.trim().toString().isNotEmpty()) {
                if (findLetterAsGusset(EditTextWritTheWord.text.toString())) {
                    Toast.makeText(activity, getMessage(), 1).show()
                    points = getCounter() * 1000
                    scores += points
                    updateScore()
                    textViewShowTheWord.text = updateTheHiddenWord()
                    playerScore.text = "Score  : " + getScore() + "DKK"
                    resetCounter()
                    if (!isWon())
                        Toast.makeText(activity, " Du Har vundet", Toast.LENGTH_LONG).show()
                    else {
                        spinAgain()
                    }
                } else if (!findLetterAsGusset(EditTextWritTheWord.text.toString()) && lives == 1) {
                    minusLife(lives)
                    lives = updateLives()
                    binding.playerLives.text = "Lives : $lives"
                    buClickToStartSpining.visibility = View.VISIBLE
                    Toast.makeText(activity, getSpinnerResult(), 0).show()
                    buClickToSendTheLetter.visibility = View.GONE
                    EditTextWritTheWord.visibility = View.GONE
                    Toast.makeText(activity, "Du har tabte", Toast.LENGTH_LONG).show()
                    gamWonOver()


                } else if (!findLetterAsGusset(EditTextWritTheWord.text.toString())) {
                    minusLife(lives)
                    lives = updateLives()
                    binding.playerLives.text = "Lives$lives"
                    buClickToStartSpining.visibility = View.VISIBLE
                    spinAgain()
                } else if (isWon() && getScore() < 0) {
                    TODO() // her jeg skal implemntere at man vinder
                }
            }
        }
    }


    private fun gamWonOver() {


        binding.apply {

            buClickToStartSpining.visibility = View.GONE
            textViewShowTheWord.visibility = View.GONE
            EditTextWritTheWord.visibility = View.GONE
            val builder = AlertDialog.Builder(requireContext())
            with(builder) {
                setTitle("   ")
                setPositiveButton("spil igen") { dialog, which ->
                    requireActivity().onBackPressed()

                }
                setNegativeButton("luk spilet") { dialog, which ->


                    Log.d("Main", "Negative button clicked")
                }
                show()
            }

        }
    }
}

