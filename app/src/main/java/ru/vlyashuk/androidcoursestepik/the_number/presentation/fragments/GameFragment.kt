package ru.vlyashuk.androidcoursestepik.the_number.presentation.fragments

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.databinding.FragmentGameBinding
import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.GameResult
import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.Level
import ru.vlyashuk.androidcoursestepik.the_number.presentation.GameViewModel
import ru.vlyashuk.androidcoursestepik.the_number.presentation.GameViewModelFactory

class GameFragment : Fragment() {

    private lateinit var level: Level
    private val viewModelFactory by lazy {
        GameViewModelFactory(level, requireActivity().application)
    }
    private val gameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val optionsTextView by lazy {
        mutableListOf<TextView>().apply {
            add(binding.optionOneTextView)
            add(binding.optionTwoTextView)
            add(binding.optionThreeTextView)
            add(binding.optionFourTextView)
            add(binding.optionFiveTextView)
            add(binding.optionSixTextView)
        }
    }

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setClickListenersToOptions()
    }

    private fun setClickListenersToOptions() {
        for (optionTextView in optionsTextView) {
            optionTextView.setOnClickListener {
                gameViewModel.chooseAnswer(optionTextView.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel() {
        with(gameViewModel) {
            question.observe(viewLifecycleOwner) {
                binding.apply {
                    it.sum.toString().also { sumTextView.text = it }
                    it.visibleNumber.toString().also { leftNumberTextView.text = it }
                }
                for (i in 0 until optionsTextView.size) {
                    it.options[i].toString().also { optionsTextView[i].text = it }
                }
            }
            percentOfRightAnswers.observe(viewLifecycleOwner) {
                binding.progressBar.setProgress(it, true)
            }
            enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
                binding.answersProgressTextView.setTextColor(getColorByState(it))
            }
            enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
                val color = getColorByState(it)
                binding.progressBar.progressTintList = ColorStateList.valueOf(color)
            }
            formattedTime.observe(viewLifecycleOwner) {
                binding.timerTextView.text = it
            }
            minPercent.observe(viewLifecycleOwner) {
                binding.progressBar.secondaryProgress = it
            }
            gameResult.observe(viewLifecycleOwner) {
                launchGameFinishedFragment(it)
            }
            progressAnswers.observe(viewLifecycleOwner) {
                binding.answersProgressTextView.text = it.toString()
            }
        }
    }

    private fun getColorByState(state: Boolean): Int {
        val colorResId = if (state) {
            android.R.color.holo_green_dark
        } else {
            android.R.color.holo_red_dark
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerTheNumber, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        level = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.TIRAMISU) {
            requireArguments().getParcelable(KEY_LEVEL, Level::class.java)
        } else {
            requireArguments().getParcelable(KEY_LEVEL) as? Level
        } ?: throw RuntimeException("Level is null")
    }

    companion object {

        const val NAME_GAME_FRAGMENT = "GameFragment"
        private const val KEY_LEVEL = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }

    }
}