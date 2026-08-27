package com.example.examenparcialcm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val etNombre = findViewById<EditText>(R.id.etNombre)
        val chkSegundoNombre = findViewById<CheckBox>(R.id.chkSegundoNombre)
        val etSegundoNombre = findViewById<EditText>(R.id.etSegundoNombre)
        val etApellidos = findViewById<EditText>(R.id.etApellidos)
        val etEdad = findViewById<EditText>(R.id.etEdad)
        val spArea = findViewById<Spinner>(R.id.spArea)
        val spCargo = findViewById<Spinner>(R.id.spCargo)
        val btnTerminar = findViewById<Button>(R.id.btnTerminar)


        chkSegundoNombre.setOnCheckedChangeListener { _, isChecked ->
            etSegundoNombre.isEnabled = isChecked
            if (!isChecked) {
                etSegundoNombre.text.clear()
            }
        }


        val bdAreasCargos = mapOf(
            "Sistemas" to listOf("Desarrollador", "Analista", "Soporte"),
            "Recursos Humanos" to listOf("Reclutador", "Asistente de RRHH"),
            "Ventas" to listOf("Vendedor", "Cajero")
        )

        val listaAreas = bdAreasCargos.keys.toList()


        val areaAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaAreas)
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spArea.adapter = areaAdapter

        spArea.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val areaSeleccionada = listaAreas[position]
                val listaCargos = bdAreasCargos[areaSeleccionada] ?: emptyList()

                val cargoAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, listaCargos)
                cargoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spCargo.adapter = cargoAdapter
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

            btnTerminar.setOnClickListener {
            val nombre = etNombre.text.toString()
            val apellidos = etApellidos.text.toString()


            if (nombre.isEmpty() || apellidos.isEmpty() || etCodigo.text.toString().isEmpty()) {
                Toast.makeText(this, "Complete los datos obligatorios", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("EXTRA_NOMBRE", nombre)
                putExtra("EXTRA_APELLIDOS", apellidos)
            }
            startActivity(intent)
        }
    }
}