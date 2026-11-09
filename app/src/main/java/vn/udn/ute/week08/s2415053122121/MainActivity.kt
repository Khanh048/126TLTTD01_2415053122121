package vn.udn.ute.week08.s2415053122121

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private val D = BigDecimal("5.5")
    private val re = Regex("^\\d+(\\.\\d+)?$")

    private lateinit var etName :EditText
    private lateinit var etQT :EditText
    private lateinit var etCK :EditText
    private lateinit var tvResult :EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}