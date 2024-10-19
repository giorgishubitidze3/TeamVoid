package com.hackathon.teamvoid.presentation.subscription_manager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.Navigation
import com.hackathon.teamvoid.R


class SubscriptionManagerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_subscription_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = Navigation.findNavController(view)

        view.findViewById<ImageButton>(R.id.btn_back_sub_manager).setOnClickListener {
            navController.navigate(R.id.action_subscriptionManagerFragment_to_homeFragment)
        }

        view.findViewById<Button>(R.id.btn_manager_activation).setOnClickListener {
            navController.navigate(R.id.action_subscriptionManagerFragment_to_onboardingFirstFragment)
        }
    }

}