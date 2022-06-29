package com.dkkim.anew.Fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dkkim.anew.Activity.LoginActivity
import com.dkkim.anew.Activity.SelectDeviceActivity
import com.dkkim.anew.Model.SettingInfo
import com.dkkim.anew.R
import com.dkkim.anew.RecyclerView.SettingItemAdapter
import com.dkkim.anew.Util.MySharedPreferences
import com.dkkim.anew.databinding.FragmentSettingBinding
import com.google.firebase.auth.FirebaseAuth


class SettingFragment : Fragment(), SettingItemAdapter.OnItemClickListener {
    lateinit var binding: FragmentSettingBinding // FragmentSetting 바인딩
    private var firebaseAuth = FirebaseAuth.getInstance() // FirebaseAuth 인스턴스 초기화

    // 설정에 들어갈 메뉴들
    private val settingList = arrayListOf(
        SettingInfo("개인정보 설정"),
        SettingInfo("푸시알람 설정"),
        SettingInfo("비밀번호 재설정"),
        SettingInfo("블루투스 연결"),
        SettingInfo("Q & A"),
        SettingInfo("로그아웃")
    )

    // 리사이클러뷰 어댑터
    private val settingItemAdapter = SettingItemAdapter(settingList)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(inflater, container, false)

        binding.settingRecyclerView.apply {
            // 리니어 레이아웃 형식으로
            layoutManager = LinearLayoutManager(context)
            // 어댑터 설정
            adapter = settingItemAdapter
        }
        // 리사이클러뷰 아이템 클릭 이벤트
        settingItemAdapter.setOnItemClickListener(this)

        // 프래그먼트에선 return 문이 코드 마지막에 와야 함
        return binding.root
    }

    // 각 프래그먼트로 변경하는 코드
    override fun onItemClick(view: View, data: SettingInfo, position: Int) {
        when (data.name) {
            "개인정보 설정" -> {
                val bundle = Bundle()
                bundle.putString("uid", "사용자uid") // 번들에 key, value 넣고 전달
                replaceFragment(SettingUserInfoFragment(), bundle)
            }
            "푸시알람 설정" -> {
                val bundle = Bundle()
                bundle.putString("uid", "사용자uid") // 번들에 key, value 넣고 전달
                replaceFragment(SettingPushFragment(), bundle)
            }
            "식단관리 모드 설정" -> {
                val bundle = Bundle()
                bundle.putString("uid", "사용자uid") // 번들에 key, value 넣고 전달
                replaceFragment(SettingDietModeFragment(), bundle)
            }
            "비밀번호 재설정" -> {
                val bundle = Bundle()
                bundle.putString("uid", "사용자uid")
                replaceFragment(PasswordFragment(), bundle)
            }
            "블루투스 연결" -> {
                val bundle = Bundle()
                bundle.putString("uid", "사용자uid")
                val intent = Intent(getActivity(), SelectDeviceActivity::class.java) //SelectDeviceActivity로 화면 전환
                startActivity(intent)
            }
            "Q & A" -> {
                val bundle = Bundle()
                bundle.putString("uid", "사용자uid")
                replaceFragment(SettingQnaFragment(), bundle)
            }
            "로그아웃" -> {
                Logout()
            }
        }
    }

    private fun Logout() {
        firebaseAuth.signOut() // 로그아웃
        val intent = Intent(getActivity(), LoginActivity::class.java) // LoginActivity로 화면 전환
        startActivity(intent)
        Toast.makeText(getActivity(), "로그아웃", Toast.LENGTH_SHORT)
            .show()

        MySharedPreferences.clearUser(requireContext())

    }

    // 프래그먼트 변경 함수 (스택에 쌓이는 -> 뒤로가기 시 전 프래그먼트 뜸)
    private fun replaceFragment(fragment: Fragment, bundle: Bundle) {
        fragment.arguments = bundle
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.main_frame, fragment)
            .addToBackStack(null)
            .commit()
    }
}