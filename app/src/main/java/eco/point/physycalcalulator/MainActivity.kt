package eco.point.physycalcalulator

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import eco.point.physycalcalulator.databinding.ActivityMainBinding
import java.lang.Math.sqrt

class MainActivity : AppCompatActivity() {
    private var tableItems = ArrayList<TableItem>()
    private val LEGAL_ACCURACY = arrayOf<Double>(0.5, 0.6, 0.7, 0.8, 0.9, 0.95, 0.98, 0.99)
    val STUDENT_ARRAY = arrayOf(
        doubleArrayOf(1.0, 1.38, 2.0, 3.1, 6.3, 12.7, 31.8, 63.7),
        doubleArrayOf(0.82, 1.06, 1.3, 1.9, 2.9, 4.3, 7.0, 9.9),
        doubleArrayOf(0.77, 0.98, 1.3, 1.6, 2.4, 3.2, 4.5, 5.8),
        doubleArrayOf(0.77, 0.98, 1.3, 1.6, 2.4, 3.2, 4.5, 5.8),
        doubleArrayOf(0.73, 0.92, 1.2, 1.5, 2.0, 2.6, 3.4, 4.0),
        doubleArrayOf(0.72, 0.90, 1.1, 1.4, 1.9, 2.4, 3.1, 3.7),
        doubleArrayOf(0.71, 0.90, 1.1, 1.4, 1.9, 2.4, 3.0, 3.5),
        doubleArrayOf(0.71, 0.90, 1.1, 1.4, 1.9, 2.3, 2.9, 3.4),
        doubleArrayOf(0.70, 0.88, 1.1, 1.4, 1.8, 2.3, 2.8, 3.3),
        doubleArrayOf(0.70, 0.88, 1.1, 1.4, 1.8, 2.2, 2.8, 3.2),
        doubleArrayOf(0.70, 0.87, 1.1, 1.4, 1.8, 2.2, 2.7, 3.1),
        doubleArrayOf(0.70, 0.87, 1.1, 1.4, 1.8, 2.2, 2.7, 3.1),
        doubleArrayOf(0.69, 0.87, 1.1, 1.4, 1.8, 2.2, 2.7, 3.0),
        doubleArrayOf(0.69, 0.87, 1.1, 1.3, 1.8, 2.1, 2.6, 3.0),
        doubleArrayOf(0.69, 0.87, 1.1, 1.3, 1.8, 2.1, 2.6, 2.9),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.6, 2.9),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.6, 2.9),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.6, 2.9),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.5, 2.9),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.5, 2.8),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.5, 2.8),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.5, 2.8),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.5, 2.8),
        doubleArrayOf(0.69, 0.86, 1.1, 1.3, 1.7, 2.1, 2.5, 2.8),
        doubleArrayOf(0.68, 0.86, 1.1, 1.3, 1.7, 2.1, 2.5, 2.8),
        doubleArrayOf(0.68, 0.86, 1.1, 1.3, 1.7, 2.1, 2.5, 2.8),
        doubleArrayOf(0.68, 0.86, 1.1, 1.3, 1.7, 2.0, 2.5, 2.8),
        doubleArrayOf(0.68, 0.86, 1.1, 1.3, 1.7, 2.0, 2.5, 2.8),
        doubleArrayOf(0.68, 0.85, 1.1, 1.3, 1.7, 2.0, 2.5, 2.8))
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var num = 5
        var accuracy: Double = 0.95
        var numWarningStr: String = ""
        var accuracyWarningString = ""
        var linearLayoutManager = LinearLayoutManager(this)
        for (i: Int in 1..num)
            tableItems.add(TableItem())
        var viewAdapter = TableAdapter(tableItems)
        binding.tableRecycle.setHasFixedSize(false)
        binding.tableRecycle.layoutManager = linearLayoutManager
        binding.tableRecycle.adapter = viewAdapter
        binding.accuracyField.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val accuracyStr = binding.accuracyField.text.toString()
                accuracy = 0.95
                accuracyWarningString = ""
                if (!accuracyStr.matches(Regex("(.+)")))
                    accuracyWarningString += "Не установлена точность измерений. " +
                            "Установлено значение по умолчанию $accuracy"
                else
                    if (!LEGAL_ACCURACY.contains(accuracyStr.toDouble()))
                        accuracyWarningString += "Неверная точность измерений. " +
                                "Установлено значение по умолчанию $accuracy"
                    else
                        accuracy = accuracyStr.toDouble()
                binding.accuracyWarningOutField.text = accuracyWarningString
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.numField.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val numStr = binding.numField.text.toString()
                num = 5
                numWarningStr = ""
                if (!numStr.matches(Regex("(.+)")))
                    numWarningStr += "Не установлено количество экспериментов. " +
                            "Установлено значение по умолчанию $num"
                else
                    if (numStr.toInt() < 2 || numStr.toInt() > 30)
                        numWarningStr += "Неверное количество экспериментов. " +
                                "Установлено значение по умолчанию $num"
                    else
                        num = numStr.toInt()
                binding.numWarningOutField.text = numWarningStr
                val tmpTI = ArrayList(viewAdapter.list)
                val startSize = tableItems.size
                if (startSize > num) {
                    tableItems.removeAll(tableItems)
                    for (i: Int in 1..(num))
                        tableItems.add(tmpTI[i])
                }else{
                    for (i: Int in startSize..(num-1))
                        tableItems.add(TableItem())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
        binding.button.setOnClickListener{
            var tmpTI = viewAdapter.list
            var medA = 0.0
            for (item:TableItem in tmpTI)
                medA += item.input
            medA /= num
            var sum = 0.0
            for (i: Int in 0..(num - 1)){
                viewAdapter.list[i].aOut = viewAdapter.list[i].input - medA
                viewAdapter.list[i].sqrAOut = Math.pow(viewAdapter.list[i].aOut, 2.0)
                sum += viewAdapter.list[i].sqrAOut
            }
            val errorCount = STUDENT_ARRAY[num - 2][getAccuracyNum(accuracy)] * sqrt(sum / (num * (num - 1)))
            val errorPercent = errorCount / medA * 100
            binding.answerOutField.text = "Ответ: a = (" + String.format("%.4f", medA) + " +- " + String.format("%.4f", errorCount) +
                        "), ε = " + String.format("%.0f", errorPercent) + "%, при α = " +
                        String.format("%.2f", accuracy)
        }
    }

    private fun getAccuracyNum(accuracy: Double): Int{
        for(i: Int in 0..LEGAL_ACCURACY.size)
            if (LEGAL_ACCURACY[i] == accuracy)
                return i
        return 5
    }
}