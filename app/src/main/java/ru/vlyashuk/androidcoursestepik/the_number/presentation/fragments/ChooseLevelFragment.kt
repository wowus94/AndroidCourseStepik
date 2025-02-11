package ru.vlyashuk.androidcoursestepik.the_number.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.vlyashuk.androidcoursestepik.R
import ru.vlyashuk.androidcoursestepik.databinding.FragmentChooseLevelBinding
import ru.vlyashuk.androidcoursestepik.the_number.domain.entity.Level

class ChooseLevelFragment : Fragment() {

    private var _binding: FragmentChooseLevelBinding? = null
    private val binding: FragmentChooseLevelBinding
        get() = _binding ?: throw RuntimeException("FragmentChooseLevelBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChooseLevelBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            testLevelButton.setOnClickListener {
                launchGameFragment(Level.TEST)
            }

            easyLevelButton.setOnClickListener {
                launchGameFragment(Level.EASY)
            }

            normalLevelButton.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }

            hardLevelButton.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
        }
    }

    private fun launchGameFragment(level: Level) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerTheNumber, GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME_GAME_FRAGMENT)
            .commit()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {

        const val NAME = "ChooseLevelFragment"

        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }

    }

}