package becerra.paul.clicker

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var count: Int = 0
    lateinit var btnClicker: Button
    lateinit var textViewCuenta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClicker = findViewById(R.id.btn_clicker)
        textViewCuenta = findViewById(R.id.textViewCount)

        val btnDecrease: Button = findViewById(R.id.btnRestar)
        val btnClear: Button = findViewById(R.id.btnClear)

        btnClicker.setOnClickListener{
            count++
            textViewCuenta.setText("$count")
        }

        btnDecrease.setOnClickListener{
            count--
            textViewCuenta.setText("$count")
        }

        btnClear.setOnClickListener{
            clear()
        }
    }

    fun clear(){
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                        count = 0
                        textViewCuenta.setText("$count")
                    })
                setNegativeButton(R.string.cancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User cancelled the dialog
                    })
            }
            builder?.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)

            // Create the AlertDialog
            builder.create()
        }
        alertDialog?.show()
    }

    override fun onStop(){
        super.onStop()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        val edit = sharedPref.edit()
        edit.putInt("key_count",count)
        edit.apply()
    }

    override fun onStart() {
        super.onStart()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        count = sharedPref.getInt("key_count", 0)
        textViewCuenta.setText("$count")
    }
}