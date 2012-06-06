package com.macias.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Resultados_Activity extends Activity{
	
	private Bundle parametros;
	private SeekBar barra;
	private TextView precision, sum1, sum2, sum3, sum4, sum5, sum6, sum7, sum8;
	private Button mas_result;
	private Intent next;
	
	private Calculos calculos;
	static double[][] matriz;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultados);
		
		matriz = new double[3][4];
		parametros = getIntent().getExtras();
		next = new Intent(getApplicationContext(), ResultMatriz_Activity.class);
		calculos = new Calculos(parametros.getDoubleArray("x"), parametros.getDoubleArray("fx"));
		
		matriz = calculos.getMatriz();
		
		
		barra = (SeekBar) findViewById(R.id.Barra);
		precision = (TextView) findViewById(R.id.tvPrecision);
		sum1 = (TextView) findViewById(R.id.tvSum1);
		sum2 = (TextView) findViewById(R.id.tvSum2);
		sum3 = (TextView) findViewById(R.id.tvSum3);
		sum4 = (TextView) findViewById(R.id.tvSum4);
		sum5 = (TextView) findViewById(R.id.tvSum5);
		sum6 = (TextView) findViewById(R.id.tvSum6);
		sum7 = (TextView) findViewById(R.id.tvSum7);
		sum8 = (TextView) findViewById(R.id.tvSum8);
		mas_result = (Button) findViewById(R.id.btnMas);
		
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
				Log.d("BARRA", progress+" - "+fromUser);
				int p = progress/10;
				precision.setText((p == 1) ? "Precisión: 1 decimal.":"Precisión: "+p+" decimales.");
				sum1.setText(changeBar(p, calculos.getEx()));
				sum2.setText(changeBar(p, calculos.getEfx()));
				sum3.setText(changeBar(p,calculos.getEx2()));
				sum4.setText(changeBar(p, calculos.getEx3()));
				sum5.setText(changeBar(p, calculos.getEx4()));
				sum6.setText(changeBar(p, calculos.getEfx_x()));
				sum7.setText(changeBar(p, calculos.getEfx_x2()));
				sum8.setText(changeBar(p, calculos.getEfx2()));
			}
		});
		
		mas_result.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				next.putExtra("M", parametros.getInt("m"));
				next.putExtra("PROGRESS", barra.getProgress());
				startActivity(next);
			}
		});
		
		precision.setText("Precisión: 0 decimales.");
		sum1.setText(num_Int(calculos.getEx()));
		sum2.setText(num_Int(calculos.getEfx()));
		sum3.setText(num_Int(calculos.getEx2()));
		sum4.setText(num_Int(calculos.getEx3()));
		sum5.setText(num_Int(calculos.getEx4()));
		sum6.setText(num_Int(calculos.getEfx_x()));
		sum7.setText(num_Int(calculos.getEfx_x2()));
		sum8.setText(num_Int(calculos.getEfx2()));
		
		
		
	}
	
	public String num_Int(double d){
		String res = String.format("%.0f", d);
		return res;
	}
	
	public String changeBar(int p, double r){
		String res = String.format("%."+p+"f", r);
		return res;
	}
	
}
