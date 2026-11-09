package vn.udn.ute.week08.s2415053122121

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private val D = BigDecimal("5.5")
    private val re = Regex("^\\d+(\\.\\d+)?$")

    private lateinit var etName: EditText
    private lateinit var etQT: EditText
    private lateinit var etCK: EditText
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName)
        etQT = findViewById(R.id.etQT)
        etCK = findViewById(R.id.etCK)
        tvResult = findViewById(R.id.tvResult)
        findViewById<Button>(R.id.btnCheck).setOnClickListener { check() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearAll() }
    }

    private fun parseScore(s: String): BigDecimal? {
        if (!re.matches(s)) return null
        val v = BigDecimal(s)
        return if (v >= BigDecimal.ZERO && v <= BigDecimal.TEN) v else null
    }

    private fun check() {
        etName.error = null; etQT.error = null; etCK.error = null
        tvResult.text = ""
        val name = etName.text.toString().trim()
        val qt = parseScore(etQT.text.toString().trim())
        val ck = parseScore(etCK.text.toString().trim())

        if (name.length !in 1..40) etName.error = "Tên dài 1..40 ký tự"
        if (qt == null) etQT.error = "QT là số từ 0 đến 10, dùng dấu chấm"
        if (ck == null) etCK.error = "CK là số từ 0 đến 10, dùng dấu chấm"
        if (name.length !in 1..40 || qt == null || ck == null) return

        val t = calcT(qt, ck)
        val pass = classify(t, ck)
        tvResult.text = "Học phần: $name\nQT: ${f(qt)}\nCK: ${f(ck)}\nT: ${f(t)}\n" +
                "Kết luận: " + if (pass) "Đạt" else "Chưa đạt"
    }

    private fun calcT(qt: BigDecimal, ck: BigDecimal) =
        qt.multiply(BigDecimal("0.4")).add(ck.multiply(BigDecimal("0.6")))

    private fun classify(t: BigDecimal, ck: BigDecimal) =
        t >= D && ck >= BigDecimal("3")

    private fun f(v: BigDecimal) = v.setScale(2, RoundingMode.HALF_UP).toPlainString()

    private fun clearAll() {
        etName.setText(""); etQT.setText(""); etCK.setText("")
        etName.error = null; etQT.error = null; etCK.error = null
        tvResult.text = ""
    }
}