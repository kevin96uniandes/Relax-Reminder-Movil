package com.example.relaxreminder.ui.fragments

import android.R
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.example.relaxreminder.databinding.FragmentAddAlarmBinding
import com.example.relaxreminder.ui.adapter.SuggestionsAdapter
import com.google.android.material.button.MaterialButton


class AddAlarmFragment : Fragment() {

    private lateinit var binding: FragmentAddAlarmBinding
    val activities = arrayOf("Cantar", "Baailar", "Leer", "Caminar", "Reir", "Saltar", "Dormir", "Jugar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activity = requireNotNull(this.activity)
        activity.title = "Agregar alarma"
        binding = FragmentAddAlarmBinding.inflate(inflater, container, false)
        val autoCompleteTextView = binding.autoComplete
        val adapter = ArrayAdapter<String>(requireContext(), android.R.layout.simple_dropdown_item_1line, activities)
        autoCompleteTextView.setAdapter(adapter)

        binding.btnCancel.setOnClickListener {
            goToCreateRoutine()
        }

        binding.btnSave.setOnClickListener {
            showSuccessDialog()
        }
        return binding.root
    }

    private fun showSuccessDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(com.example.relaxreminder.R.layout.dialog_success, null)
        builder.setView(dialogView)

        val animationView = dialogView.findViewById<LottieAnimationView>(com.example.relaxreminder.R.id.animation_view)
        animationView.setAnimation(com.example.relaxreminder.R.raw.sucess)

        animationView.playAnimation()
        val tvMsgSuccess = dialogView.findViewById<TextView>(com.example.relaxreminder.R.id.tv_msg_dialog_success)
        val alarmStr = context?.getString(com.example.relaxreminder.R.string.alarm)
        val successfulStr = context?.getString(com.example.relaxreminder.R.string.create_successfull)
        tvMsgSuccess.text = "$alarmStr $successfulStr"
        val dialog: AlertDialog = builder.create()
        val btnAccept = dialogView.findViewById<MaterialButton>(com.example.relaxreminder.R.id.btn_accept)
        btnAccept.setOnClickListener {
            dialog.dismiss()
            goToCreateRoutine()
        }
        dialog.show()
    }

    fun goToCreateRoutine() {
        val transaction = this.activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(com.example.relaxreminder.R.id.frame_layout, CreateRoutineFragment())
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AddAlarmFragment()
    }
}