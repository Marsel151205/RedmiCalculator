package com.example.calculatorredmi

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.calculatorredmi.databinding.FragmentCalculatorBinding
import com.google.android.material.button.MaterialButton

class CalculatorFragment : Fragment() {

    private lateinit var binding: FragmentCalculatorBinding
    private var firstNum: Int? = null
    private var secondNum: Int? = null
    private var operation: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(layoutInflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickForButtons()
    }

    private fun clickForButtons() {
        with(binding) {
            btnOne.setOnClickListener { addingNumbers(btnOne) }
            btnTwo.setOnClickListener { addingNumbers(btnTwo) }
            btnThree.setOnClickListener { addingNumbers(btnThree) }
            btnFour.setOnClickListener { addingNumbers(btnFour) }
            btnFive.setOnClickListener { addingNumbers(btnFive) }
            btnSix.setOnClickListener { addingNumbers(btnSix) }
            btnSeven.setOnClickListener { addingNumbers(btnSeven) }
            btnEight.setOnClickListener { addingNumbers(btnEight) }
            btnNine.setOnClickListener { addingNumbers(btnNine) }
            btnZero.setOnClickListener { addingNumbers(btnZero) }
            btnC.setOnClickListener { tvResult.text = "0" }
            btnPlus.setOnClickListener { operations(btnPlus.text.toString()) }
            btnMinus.setOnClickListener { operations(btnMinus.text.toString()) }
            btnMultiplication.setOnClickListener { operations(btnMultiplication.text.toString()) }
            btnDivision.setOnClickListener { operations(btnDivision.text.toString()) }
            btnEquals.setOnClickListener { showResult() }
            btnDelete.setOnClickListener {
                if (tvResult.text != "0") tvResult.text = tvResult.text.dropLast(1)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun addingNumbers(btn: MaterialButton) {
        if (binding.tvResult.text == "0") {
            binding.tvResult.text = btn.text
        } else binding.tvResult.text = binding.tvResult.text.toString() + btn.text.toString()
    }

    private fun operations(oper: String) {
        firstNum = binding.tvResult.text.toString().toInt()
        binding.tvResult.text = "0"
        operation = oper
    }

    private fun showResult() {
        secondNum = binding.tvResult.text.toString().toInt()
        when (operation) {
            "/" -> {
                if (secondNum != 0) {
                    binding.tvResult.text = (firstNum?.div(secondNum!!)).toString()
                } else Toast.makeText(
                    requireContext(),
                    "На ноль делить нельзя!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            "*" -> binding.tvResult.text = (firstNum?.times(secondNum!!)).toString()
            "+" -> binding.tvResult.text = (firstNum?.plus(secondNum!!)).toString()
            "-" -> binding.tvResult.text = (firstNum?.minus(secondNum!!)).toString()
        }
    }
}