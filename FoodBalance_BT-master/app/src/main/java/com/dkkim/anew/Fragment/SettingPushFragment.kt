package com.dkkim.anew.Fragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dkkim.anew.R
import com.dkkim.anew.databinding.FragmentSettingPushBinding
import kotlinx.android.synthetic.main.fragment_setting_push.*
import java.security.KeyStore
import java.util.*

class SettingPushFragment : Fragment() {
    lateinit var binding: FragmentSettingPushBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSettingPushBinding.inflate(inflater, container, false)

        binding.pushSwitch.isChecked = true

        binding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.pushSetBtn.setOnClickListener {
            val isChecked = binding.pushSwitch.isChecked // true:알림허용, false:알림비허용

            parentFragmentManager
                .beginTransaction()
                .replace(R.id.main_frame, SettingFragment())
                .addToBackStack(null)
                .commit()
        }

        binding.pushSwitch.setOnClickListener {
            val isChecked = binding.pushSwitch.isChecked

            if (!isChecked) {
                breakfast.visibility = View.INVISIBLE
                lunch.visibility = View.INVISIBLE
                dinner.visibility = View.INVISIBLE
            }
            else {
                breakfast.visibility = View.VISIBLE
                lunch.visibility = View.VISIBLE
                dinner.visibility = View.VISIBLE
            }
        }

        binding.br.setOnClickListener {
            val cal = Calendar.getInstance()
            var timeBr = ""
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeBr = "${hourOfDay}시 ${minute}분"
                br.text = timeBr
            }
            TimePickerDialog(context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true).show()
        }

        binding.lun.setOnClickListener {
            val cal = Calendar.getInstance()
            var timeLun = ""
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeLun = "${hourOfDay}시 ${minute}분"
                lun.text = timeLun
            }
            TimePickerDialog(context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true).show()
        }

        binding.din.setOnClickListener {
            val cal = Calendar.getInstance()
            var timeDin = ""
            val timeSetListener = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                timeDin = "${hourOfDay}시 ${minute}분"
                din.text = timeDin
            }
            TimePickerDialog(context,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true).show()
        }

        return binding.root
    }
}