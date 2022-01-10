package mx.edu.tesoem.isc.fhmaproyectofinal7s21;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import mx.edu.tesoem.isc.fhmaproyectofinal7s21.DAO.ConexionDAO;
import mx.edu.tesoem.isc.fhmaproyectofinal7s21.DTO.DatosDTO;

import java.util.ArrayList;
import java.util.List;

public class ListarActivity extends AppCompatActivity {

    Button btnLregresa, btnLcancelar;
    GridView gvdatos;
    List<DatosDTO> lista = new ArrayList<DatosDTO>();
    ArrayList<String> listagrid = new ArrayList<String>();
    String seleccionid ="0";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        Animation titulo = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_derecha);
        TextView title = findViewById(R.id.tituloL);

        title.setAnimation(titulo);

        btnLregresa = findViewById(R.id.btnLregresar);
        btnLcancelar = findViewById(R.id.btnLcancelar);
        gvdatos = findViewById(R.id.gvDatos);

        CargarDatos();

        gvdatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                seleccionid = listagrid.get(i);
                Toast.makeText(ListarActivity.this, "Dato seleccionado: " + seleccionid, Toast.LENGTH_SHORT).show();
            }
        });

        btnLcancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

        btnLregresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("vista", "Listar");
                intent.putExtra("idregresa", seleccionid);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }

    private void CargarDatos(){
        ConexionDAO conexion = new ConexionDAO(this);
        conexion.abrirConexion();
        if(conexion.cargartodos()){
            lista = conexion.getListado();
            CargarGrid();
        }else Toast.makeText(this, "No se cargaron los datos correctamente", Toast.LENGTH_SHORT).show();
        conexion.cerrar();
    }

    private void CargarGrid(){

        DatosDTO datosDTO;
        ArrayAdapter<String> adaptador;
        listagrid.add("ID");
        listagrid.add("NOMBRE");
        for(int a = 0; a<lista.size(); a++){
            datosDTO = new DatosDTO();
            datosDTO = lista.get(a);
            listagrid.add(String.valueOf(datosDTO.getId()));
            listagrid.add(datosDTO.getNombre());
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listagrid);
        gvdatos.setAdapter(adaptador);
    }
}