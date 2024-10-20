package com.hackathon.teamvoid.presentation.subscription_manager.onboarding_flow

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.RadioGroup
import androidx.navigation.Navigation
import com.hackathon.teamvoid.R
import com.hackathon.teamvoid.data.local.test_stuff.TestData
import com.hackathon.teamvoid.domain.model.Card


class OnboardingFirstFragment : Fragment() {

    private lateinit var radioGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioGroup = view.findViewById(R.id.radio_group)

        val navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.btn_onboarding_first).setOnClickListener {
            if (isRadioButtonSelected()) {
                TestData.cardList.add(Card(img = R.drawable.tbc_student_card, cardName="Subscription ბარათი", "SUBSCRIPTION CARD", "00.00"))
                navController.navigate(R.id.action_onboardingFirstFragment_to_onboardingSecondFragment)
            } else {
                showDialog("Please select an option before proceeding.")
            }
        }


        view.findViewById<ImageButton>(R.id.btn_back_onboarding_second).setOnClickListener {
            navController.popBackStack()
        }
    }

    private fun isRadioButtonSelected(): Boolean {
        return radioGroup.checkedRadioButtonId != -1
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setTitle("Alert")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

}