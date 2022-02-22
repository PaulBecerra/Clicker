package becerra.paul.clicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var count: Int = 0
    lateinit var btnClick: Button
    lateinit var textViewCuenta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClick = findViewById(R.id.btn_clicker)
        textViewCuenta = findViewById(R.id.textViewCount)

        val btnRestar: Button = findViewById(R.id.btnRestar)
        val btnClear: Button = findViewById(R.id.btnClear)

        btnClick.setOnClickListener{
            count++
            textViewCuenta.setText("$count")
        }

        btnRestar.setOnClickListener{
            count--
            textViewCuenta.setText("$count")
        }

        btnClear.setOnClickListener{
            count = 0
            textViewCuenta.setText("$count")
        }
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStart(){
        super.onStart()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT)

    }

    override fun onResume(){
        super.onResume()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT)

    }

    override fun onStop(){
        super.onStop()
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT)

    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onRestart() {
        super.onRestart()
    }

}