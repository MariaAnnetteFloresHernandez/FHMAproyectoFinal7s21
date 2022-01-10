package mx.edu.tesoem.isc.fhmaproyectofinal7s21;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mx.edu.tesoem.isc.fhmaproyectofinal7s21.DAO.DatosHelper.tabladatos;
import mx.edu.tesoem.isc.fhmaproyectofinal7s21.DAO.ConexionDAO;

public class RegistrarActivity extends AppCompatActivity {

    EditText txtnombre, txtedad, txtcorreo;
    Button btnguardar, btncancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        Animation titulo = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_derecha);
        TextView title = findViewById(R.id.tituloR);

        title.setAnimation(titulo);

        txtnombre = findViewById(R.id.txtnombre);
        txtedad = findViewById(R.id.txtedad);
        txtcorreo = findViewById(R.id.txtcorreo);

        btnguardar = findViewById(R.id.btnRguardar);
        btncancelar = findViewById(R.id.btnRcancelar);

        btnguardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("vista", "Registrar");
                guardar();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        btncancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    private void guardar(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(tabladatos.TABLA_NOMBRE,txtnombre.getText().toString());
        contentValues.put(tabladatos.TABLA_EDAD,Integer.valueOf(txtedad.getText().toString()));
        contentValues.put(tabladatos.TABLA_CORREO,txtcorreo.getText().toString());

        ConexionDAO conexion = new ConexionDAO(this);
        conexion.abrirConexion();
        if(conexion.insertar(contentValues)){
            Toast.makeText(this, "Se registro correctamente", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
        }
        conexion.cerrar();
    }
}