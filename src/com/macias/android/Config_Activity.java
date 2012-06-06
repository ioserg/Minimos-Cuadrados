package com.macias.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Config_Activity extends Activity implements OnCheckedChangeListener{
	
	private Spinner lista;
	private ToggleButton manual;
	private Button continuar;
	private EditText valor_m;
	private String[] valores;
	private ArrayAdapter<String> adapter;
	private int pars_valor;
	static int pars_m;
	private boolean manual_;
	private Intent activity_manual;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Toast.makeText(getApplicationContext(), "Por: Luis Alberto Macias Angulo", Toast.LENGTH_LONG).show();
        
        pars_m = 0;
        
        lista = (Spinner) findViewById(R.id.spinner);
        manual = (ToggleButton) findViewById(R.id.toggleManual);
        valor_m = (EditText) findViewById(R.id.et_M);
        continuar = (Button) findViewById(R.id.btnContinuar);
        
        valores = new String[14];
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, valores);
        
        int v = 2;
        for (int i = 0; i < valores.length; i++) {
			valores[i] = v+"";
			v++;
		}
        
        lista.setAdapter(adapter);
        lista.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				pars_valor = Integer.parseInt(lista.getSelectedItem().toString());				
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        manual.setOnCheckedChangeListener(this);
        
        continuar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				activity_manual = new Intent(getApplicationContext(), Insercion_Activity.class);
				activity_manual.putExtra("CANTIDAD", pars_valor);
				
				if(manual.isChecked() && pars_m != 0){
					startActivity(activity_manual);
					Config_Activity.this.finish();
				}
				else if (pars_m == 0)
					dialogo();
			}
		});
        
        
        valor_m.addTextChangedListener(new TextWatcher() {
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(valor_m.getText().toString().equals(""))
					pars_m = 0;
				else if(valor_m.getText().toString().contains("."))
					dialogo();
				else
					pars_m = Integer.parseInt(valor_m.getText().toString());
			}
		});
        
    }

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.toggleManual:
			manual_ = manual.isChecked();
			Log.d("MACIAS", "Seleccionado toogle Manual ->"+manual_);
			break;
		}
	}
	
	public void dialogo(){
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Tipo de valor incorrecto.");
		dialogo.setMessage(Html.fromHtml("El valor de <strong>m</strong> debe ser entero positivo. (m > 0)"));
		dialogo.setPositiveButton("Corregir", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (valor_m.getText().toString().startsWith(".") && valor_m.getText().toString().trim().length() != 0)
					valor_m.setText(valor_m.getText().toString().substring(1));
				else if(valor_m.getText().toString().trim().length() > 0)
					valor_m.setText(valor_m.getText().toString().substring(0, valor_m.getText().toString().indexOf(".")));
			}
		});
		dialogo.show();
	}
	
}