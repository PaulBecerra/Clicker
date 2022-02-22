package becerra.paul.clicker

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var count: Int = 0
    lateinit var countName: String
    lateinit var btnClicker: Button
    lateinit var textViewCuenta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClicker = findViewById(R.id.btn_clicker)
        textViewCuenta = findViewById(R.id.textViewCount)

        val btnDecrease: Button = findViewById(R.id.btnRestar)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnSave: Button = findViewById(R.id.btn_save)

        val editText_CountName: EditText = findViewById(R.id.EditText_CountName)
        btnSave.setOnClickListener{
            val input = editText_CountName.text.toString()

            if (input.isNullOrEmpty()) {
                warning()
                return@setOnClickListener
            }

            countName = input
            textViewCuenta.setText("$count $countName")
        }

        btnClicker.setOnClickListener{
            count++
            textViewCuenta.setText("$count $countName")
        }

        btnDecrease.setOnClickListener{
            count--
            textViewCuenta.setText("$count $countName")
        }

        btnClear.setOnClickListener{
            clear()
        }
    }

    fun warning(){
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                    })
            }
            builder?.setMessage(R.string.dialog_inputEmpty)
                .setTitle(R.string.dialog_title)

            // Create the AlertDialog
            builder.create()
        }
        alertDialog?.show()
    }

    fun clear(){
        val alertDialog: AlertDialog? = this?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.ok,
                    DialogInterface.OnClickListener { dialog, id ->
                        // User clicked OK button
                        count = 0
                        textViewCuenta.setText("$count $countName")
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
        edit.putString("key_countName",countName)
        edit.apply()
    }

    override fun onStart() {
        super.onStart()
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE)
        count = sharedPref.getInt("key_count", 0)
        countName = sharedPref.getString("key_countName","clicks").toString()
        textViewCuenta.setText("$count $countName")
    }
}