package com.example.alarm

import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import com.example.alarm.databinding.FragmentAddAlarmBinding
import kotlinx.coroutines.launch
import java.util.*

class AddAlarmFragment : BaseFragment<FragmentAddAlarmBinding>(
    FragmentAddAlarmBinding::inflate){

    private var ringTonUri: Uri = "".toUri()
    private lateinit var  day: Day
    private lateinit var alarmViewModel: AlarmViewModel


    private val resultLauncher: ActivityResultLauncher<Uri?> = registerForActivityResult(
        RingtoneActivityContract()
    ) {
        it?.let {
            binding.alarmTitle.text = getRingtoneTitle(it)
            ringTonUri = it
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView() {
        binding.alarmTitle.text = getString(R.string.alarm_empty)
        binding.timePicker.hour = Calendar.HOUR_OF_DAY
        binding.timePicker.minute =Calendar.MINUTE
    }

    // 알람음 제목 가져오기
    private fun getRingtoneTitle(uri: Uri): String = RingtoneManager.getRingtone(context, uri).getTitle(context)

    // Listener 초기화
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initListener() {
        // 알람음 선택
        binding.alarmTitle.setOnClickListener {
            resultLauncher.launch(ringTonUri)
        }

        // 취소 버튼 클리
        binding.buttonCancel.setOnClickListener {
        }

        //확인 버튼 클릭
        binding.buttonCheck.setOnClickListener {

            lifecycleScope.launch() {
                if (isValidationCheck()) {
                    Toast.makeText(context, "성공", Toast.LENGTH_SHORT).show()


                } else
                    Toast.makeText(context, "실패", Toast.LENGTH_SHORT).show()

            }

        }
    }

    private fun isValidationCheck(): Boolean {
        return when {
            !day.sun || !day.mon || !day.tue || !day.wed || !day.thu || !day.fri || !day .sat -> {
                Toast.makeText(context, "요일을 선택해주세요", Toast.LENGTH_SHORT).show()
                false

            }

            ringTonUri == Uri.EMPTY -> {
                Toast.makeText(context, "알람음을 선택해주세요.", Toast.LENGTH_SHORT).show()
                false
            }
            else -> {
                true
            }
        }
    }

}