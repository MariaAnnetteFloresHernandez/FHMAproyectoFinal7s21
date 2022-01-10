package mx.edu.tesoem.isc.fhmaproyectofinal7s21;

import android.app.Activity;
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

public class EliminarActivity extends AppCompatActivity {

    EditText txtnombre, txtedad, txtcorreo;
    DatosDTO datosDTO = new DatosDTO();
    Button btneliminar, btncancelar;
    String ideliminar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        Animation titulo = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_derecha);
        TextView title = findViewById(R.id.tituloE);

        title.setAnimation(titulo);

        Bundle datos = getIntent().getExtras();
        String idb = datos.getString("idbuscar");

        txtnombre = findViewById(R.id.txtEnombre);
        txtedad = findViewById(R.id.txtEedad);
        txtcorreo = findViewById(R.id.txtEcorreo);

        btneliminar = findViewById(R.id.btnEliminarE);
        btncancelar = findViewById(R.id.btnCancelarE);

        String[] parametrocondiciones = new String[]{idb};

        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.abrirConexion();
        if(conexionDAO.consultaID(parametrocondiciones)){
            datosDTO = conexionDAO.regresaID();
            txtnombre.setText(datosDTO.getNombre());
            txtedad.setText(String.valueOf(datosDTO.getEdad()));
            txtcorreo.setText(datosDTO.getCorreo());
            ideliminar = String.valueOf(datosDTO.getId());
        }else{
            Toast.makeText(this, "No se puede recupear la información", Toast.LENGTH_SHORT).show();
        }
        conexionDAO.cerrar();

    }

    public void acciones(View v){
        switch (v.getId()){
            case R.id.btnEliminarE: eliminacion();
            break;
            case R.id.btnCancelarE: cancelar();
            break;
        }
    }

    private void eliminacion(){
        String[] condiciones = new String[]{ideliminar};
        ConexionDAO conexionDAO = new ConexionDAO(this);
        conexionDAO.abrirConexion();
        if(conexionDAO.eliminar(condiciones)){
            Toast.makeText(this, "Se elimino correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            intent.putExtra("vista","Eliminar");
            setResult(Activity.RESULT_OK,intent);
            finish();
        }else{
            Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
            btneliminar.setActivated(false);
        }
    }

    private void cancelar(){
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}