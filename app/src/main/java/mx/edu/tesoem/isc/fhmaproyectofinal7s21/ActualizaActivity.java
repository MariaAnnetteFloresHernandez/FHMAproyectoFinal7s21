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
import mx.edu.tesoem.isc.fhmaproyectofinal7s21.DAO.ConexionDAO;
import mx.edu.tesoem.isc.fhmaproyectofinal7s21.DTO.DatosDTO;
import mx.edu.tesoem.isc.fhmaproyectofinal7s21.DAO.DatosHelper.tabladatos;



public class ActualizaActivity extends AppCompatActivity {

    EditText txtnombre, txtedad, txtcorreo;
    DatosDTO datosDTO = new DatosDTO();
    String idactualiza = "";
    Button btnactualiza, btncancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza);

        Animation titulo = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_derecha);
        TextView title = findViewById(R.id.tituloA);

        title.setAnimation(titulo);

        Bundle datos = getIntent().getExtras();
        String idb = datos.getString("idbuscar");

        txtnombre = findViewById(R.id.txtAnombre);
        txtedad = findViewById(R.id.txtAedad);
        txtcorreo = findViewById(R.id.txtAcorreo);

        btnactualiza = findViewById(R.id.btnAguardar);
        btncancelar = findViewById(R.id.btnAcancelar);

        String[] parametrocondiciones = new String[]{idb};

        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.abrirConexion();
        if(conexionDAO.consultaID(parametrocondiciones)){
            datosDTO = conexionDAO.regresaID();
            txtnombre.setText(datosDTO.getNombre());
            txtedad.setText(String.valueOf(datosDTO.getEdad()));
            txtcorreo.setText(datosDTO.getCorreo());
            idactualiza = String.valueOf(datosDTO.getId());
        }else{
            Toast.makeText(this, "No se puede recupear la información", Toast.LENGTH_SHORT).show();
        }
        conexionDAO.cerrar();
    }

    public void acciones(View v){
        switch (v.getId()){
            case R.id.btnAguardar: btnguarda();
            break;
            case R.id.btnAcancelar: btncancela();
            break;
        }
    }

    private void btnguarda(){
        String[] condiciones = new String[]{idactualiza};
        ContentValues contentValues = new ContentValues();
        contentValues.put(tabladatos.TABLA_NOMBRE,txtnombre.getText().toString());
        contentValues.put(tabladatos.TABLA_EDAD,Integer.valueOf(txtedad.getText().toString()));
        contentValues.put(tabladatos.TABLA_CORREO,txtcorreo.getText().toString());

        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.abrirConexion();
        if (conexionDAO.actualiza(contentValues,condiciones)){
            Toast.makeText(this, "Se actualizaron los datos...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("vista","Actualizar");
            setResult(Activity.RESULT_OK,intent);
            finish();
        }else{
            Toast.makeText(this, "Error al actualizar...", Toast.LENGTH_SHORT).show();
            btnactualiza.setActivated(false);
        }
    }

    private void btncancela(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }


}