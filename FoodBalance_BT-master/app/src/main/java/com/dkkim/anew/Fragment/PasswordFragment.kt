package com.dkkim.anew.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.dkkim.anew.Model.UserAccount
import com.dkkim.anew.R
import com.dkkim.anew.databinding.FragmentPasswordBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase

class PasswordFragment : Fragment() {

    lateinit var binding: FragmentPasswordBinding

    var user = UserAccount(Firebase.auth.currentUser?.uid, null, null)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentPasswordBinding.inflate(inflater, container, false)

        binding.userPasswordBtn.setOnClickListener {

            val changePwd: String = binding.changePassword.text.toString()

            updatePasswordInfo(changePwd)
        }

        binding.backBtn.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }

    private fun updatePasswordInfo(
        changePwd: String,
    ) {
        val mDatabase = FirebaseDatabase.getInstance().getReference("UserAccount")

        mDatabase.child(Firebase.auth.currentUser?.uid.toString())
            .addValueEventListener(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    for (snapshotChild in snapshot.children) {

                        val pwd1 = snapshotChild.child("pwd1").getValue().toString()
                        val latePwd = binding.latePassword.text.toString()
                        val changePwd2 = binding.changePassword2.text.toString()

                        val updateUserPwd = mapOf<String, Any>(
                            "pwd1" to changePwd
                        )

                        if ((latePwd == pwd1) && (changePwd == changePwd2)) {

                            // ?????? ????????? ?????? ????????????
                            mDatabase.child(user.uid.toString())
                                .child("account")
                                .updateChildren(updateUserPwd)

                            Toast.makeText(context, "??????????????? ?????????????????????", Toast.LENGTH_SHORT).show()

                            parentFragmentManager
                                .beginTransaction()
                                .replace(R.id.main_frame, SettingFragment())
                                .addToBackStack(null)
                                .commit()

                        } else if ((latePwd != pwd1) && (changePwd == changePwd2)) {
                            Toast.makeText(context, "?????? ??????????????? ?????? ?????????????????????", Toast.LENGTH_SHORT)
                                .show()
                        } else if ((changePwd != changePwd2) && (latePwd == pwd1)) {
                            Toast.makeText(context, "?????? ??????????????? ????????? ??????????????? ????????????", Toast.LENGTH_SHORT)
                                .show()
                        } else if ((changePwd != changePwd2) && (latePwd != pwd1)) {
                            Toast.makeText(context, "?????? ??????????????????", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    //Log.w("Error", "Failed to read value.", error.toException())
                }

            })


    }

}