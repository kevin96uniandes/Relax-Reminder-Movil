package com.example.relaxreminder.ui.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.example.relaxreminder.R
import com.example.relaxreminder.databinding.FragmentCreateRoutineBinding
import com.example.relaxreminder.ui.adapter.TabsCreateRoutineFragmentAdapter
import com.example.relaxreminder.ui.interfaces.FragmentChangeListener
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayoutMediator
import java.util.Locale


class CreateRoutineFragment : Fragment() {
    private lateinit var binding: FragmentCreateRoutineBinding
    private var fragmentChangeListener: FragmentChangeListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCreateRoutineBinding.inflate(inflater, container, false)

        val adapter = TabsCreateRoutineFragmentAdapter(requireActivity().supportFragmentManager, lifecycle, listOf("Alarmas", "Programaciones"))
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = adapter.tabTitles[position].lowercase(Locale.getDefault())
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }.attach()

        binding.fabPlus.setOnClickListener {
            showBottomDialog()
        }

        binding.btnSave.setOnClickListener {
            showSuccessDialog()
        }

        binding.btnCancel.setOnClickListener {
            backListRoutines(null)
        }

        return binding.root
    }

    private fun showSuccessDialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        val dialogView: View = inflater.inflate(R.layout.dialog_success, null)
        builder.setView(dialogView)

        val animationView = dialogView.findViewById<LottieAnimationView>(R.id.animation_view)
        animationView.setAnimation(R.raw.sucess)

        animationView.playAnimation()
        val tvMsgSuccess = dialogView.findViewById<TextView>(R.id.tv_msg_dialog_success)
        val routineStr = context?.getString(R.string.menu_routine)
        val successfulStr = context?.getString(R.string.create_successfull)
        tvMsgSuccess.text = "$routineStr $successfulStr"
        val dialog: AlertDialog = builder.create()
        val btnAccept = dialogView.findViewById<MaterialButton>(R.id.btn_accept)
        btnAccept.setOnClickListener {
            backListRoutines(dialog)
        }
        dialog.show()
    }

    private fun backListRoutines(dialog: AlertDialog?) {
        dialog?.dismiss()
        cambiarFragmento(ListarActividadesFragment())
    }

    private fun showBottomDialog() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottom_sheet_layout)
        val cancelButton = dialog.findViewById<ImageView>(R.id.cancelButton)

        val addAlarm = dialog.findViewById<LinearLayout>(R.id.layoutVideo)

        //val shortsLayout = dialog.findViewById<LinearLayout>(R.id.layoutShorts)
        //val liveLayout = dialog.findViewById<LinearLayout>(R.id.layoutLive)

        addAlarm.setOnClickListener {
            dialog.dismiss()
            val transaction = this.activity?.supportFragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout, AddAlarmFragment())
            transaction?.disallowAddToBackStack()
            transaction?.commit()
        }

        cancelButton.setOnClickListener { dialog.dismiss() }
        dialog.show()
        dialog.window!!.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.attributes.windowAnimations = R.style.DialogAnimation
        dialog.window!!.setGravity(Gravity.BOTTOM)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentChangeListener) {
            fragmentChangeListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        fragmentChangeListener = null
    }

    private fun cambiarFragmento(fragment: Fragment) {
        val transaction = this.activity?.supportFragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_layout, fragment)
        transaction?.disallowAddToBackStack()
        transaction?.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CreateRoutineFragment()
    }
}