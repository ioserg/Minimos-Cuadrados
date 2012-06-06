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
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class Insercion_Activity extends Activity implements OnFocusChangeListener, TextWatcher {
	
	private Bundle parametros;
	private TableLayout tabla;
	private TableRow fila;
	private TextView texto;
	private EditText campo_1, campo_2;
	private Button continuar;
	private double[] x, fx;
	private View view;
	
	private Intent next;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.insercion);
		
		next = new Intent(this, Resultados_Activity.class);
				
		parametros = getIntent().getExtras();
		tabla = (TableLayout) findViewById(R.id.table);
		
		x = new double[parametros.getInt("CANTIDAD")];
		fx = new double[parametros.getInt("CANTIDAD")];
		
		continuar = (Button) findViewById(R.id.btnCalcular);
		
		for (int i = 0; i < parametros.getInt("CANTIDAD"); i++) {
			
			fila = new TableRow(getApplicationContext());
			texto = new TextView(getApplicationContext());
			campo_1 = new EditText(getApplicationContext());
			campo_2 = new EditText(getApplicationContext());
			
			fila.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.MATCH_PARENT, 3.0f));
			texto.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT));
			campo_1.setLayoutParams(new LayoutParams(1, LayoutParams.MATCH_PARENT));
			campo_2.setLayoutParams(new LayoutParams(2, LayoutParams.MATCH_PARENT));
			
			texto.setText(Html.fromHtml("x<sub>"+i+"</sub>"));
			campo_1.setHint(Html.fromHtml("x<sub>"+i+"</sub>"));
			campo_2.setHint(Html.fromHtml("f(x)<sub>"+i+"</sub>"));
			
			texto.setGravity(0x11);
			campo_1.setGravity(0x11);
			campo_2.setGravity(0x11);
			
			campo_1.setInputType(0x00002002);
			campo_2.setInputType(0x00002002);
			
			campo_1.setLines(1);
			campo_2.setLines(1);
			
			campo_1.setId(i);
			if(i == 0)
				campo_2.setId(99);
			else
				campo_2.setId(i*100);
			
			campo_1.setOnFocusChangeListener(this);
			campo_2.setOnFocusChangeListener(this);
			
			campo_1.addTextChangedListener(this);
			campo_2.addTextChangedListener(this);
			
			fila.addView(texto);
			fila.addView(campo_1);
			fila.addView(campo_2);
			tabla.addView(fila);
			
			x[i] = 0;
			fx[i] = 0;
		}
		
		continuar.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				validar(v);
			}
		});
		
	}

	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		
		if (view.getId()<= 14) {
			
			x[view.getId()] = valoresCorrectos(s);
			Log.d("EDITABLE_M", "x["+view.getId()+"] ="+x[view.getId()]);
			
		} else if(view.getId() == 99) {
			
			fx[0] = valoresCorrectos(s);
			Log.d("EDITABLE_M", "fx[0] ="+fx[0]);
			
		} else {
			
			int pos = (this.view.getId()) / 100;
			fx[pos] = valoresCorrectos(s);
			Log.d("EDITABLE_M", "fx["+pos+"] ="+fx[pos]);
		}
	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		
	}

	public void onFocusChange(View v, boolean hasFocus) {
		// TODO Auto-generated method stub
		view = v;
		Log.d("MACIAS", view.getId()+"");
	}
	
	public void validar(View v){
		boolean activity = true;
		for (int i = 0; i < x.length; i++) {
			if(x[i] == 0 || fx[i] == 0){
				dialogo();
				activity = false;
				break;
			}
		}
		if(activity && v.getId() == R.id.btnCalcular){
			next.putExtra("x", x);
			next.putExtra("fx", fx);
			startActivity(next);
		}
	}
	
	public void dialogo(){
		AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
		dialogo.setTitle("Datos sin valor.");
		dialogo.setMessage("Hay espacios sin asignarle un valor. ¿Desea continuar? Los espacios sin valor se tomarán con el valor de 0.");
		dialogo.setPositiveButton("Calcular", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				next.putExtra("x", x);
				next.putExtra("fx", fx);
				startActivity(next);
			}
		});
		dialogo.setNegativeButton("Asignar", null);
		dialogo.show();
	}
	
	public double valoresCorrectos(Editable s){
		double v;
		TextView tv = (TextView) view;
		if(s.toString().startsWith(".")){
			tv.setText("");
			v = 0.0;
			return v;
		}else{
			v = (s.toString().equals("")) ? 0.0:Double.parseDouble(s.toString());
			return v;
		}
	}

	
}
