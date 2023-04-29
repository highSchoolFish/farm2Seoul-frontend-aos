package com.farm2seoul_frontend_aos.presentation.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.farm2seoul_frontend_aos.R
import com.farm2seoul_frontend_aos.databinding.InformationDetailBinding

class InformationDetail : AppCompatActivity() {
    private var mBinding: InformationDetailBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = InformationDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }

    private fun initView() {
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }

        binding.toggleButton.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked) {
                when (checkedId) {
                    R.id.garak_button -> {
                        binding.garakView.visibility = View.VISIBLE
                        binding.gangseoView.visibility = View.GONE
                    }

                    R.id.gangseo_button -> {
                        binding.garakView.visibility = View.GONE
                        binding.gangseoView.visibility = View.VISIBLE
                    }
                }
            }

        }
    }
}