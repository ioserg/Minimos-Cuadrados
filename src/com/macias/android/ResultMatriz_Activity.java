package com.macias.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class ResultMatriz_Activity extends Activity implements OnClickListener {

	private SeekBar barra;
	private TextView precision, a0,a1,a2;
	private Button regresar,principal;
	private Bundle parametros;
	private Intent next;
	
	private Montante montante;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultados_matriz);
		
		montante = new Montante();
		parametros = getIntent().getExtras();
		
		barra = (SeekBar) findViewById(R.id.Barra2);
		precision = (TextView) findViewById(R.id.tvPrecision2);
		a0 = (TextView) findViewById(R.id.tvA0);
		a1 = (TextView) findViewById(R.id.tvA1);
		a2 = (TextView) findViewById(R.id.tvA2);
		regresar = (Button) findViewById(R.id.btnRegresar);
		principal = (Button) findViewById(R.id.btnPrincipal);
		
		barra.setProgress(parametros.getInt("PROGRESS"));
		precision.setText("Precisión: "+(parametros.getInt("PROGRESS"))/10+" decimales.");
		
		barra.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				int p = progress/10;
				precision.setText((p == 1) ? "Precisión: 1 decimal.":"Precisión: "+p+" decimales.");
				a0.setText(changeBar(p, montante.getA0()));
				a1.setText(changeBar(p, montante.getA1()));
				a2.setText(changeBar(p, montante.getA2()));				
			}
		});
		
		a0.setText(changeBar(parametros.getInt("PROGRESS")/10, montante.getA0()));
		a1.setText(changeBar(parametros.getInt("PROGRESS")/10, montante.getA1()));
		a2.setText(changeBar(parametros.getInt("PROGRESS")/10, montante.getA2()));
		
		regresar.setOnClickListener(this);
		principal.setOnClickListener(this);
		
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnRegresar:
			this.finish();
			break;
		case R.id.btnPrincipal:
			next = new Intent(getApplicationContext(), Config_Activity.class);
			this.finish();
			startActivity(next);
			break;
		}
	}
	
	public String changeBar(int p, double r){
		String res = String.format("%."+p+"f", r);
		return res;
	}
	
}
