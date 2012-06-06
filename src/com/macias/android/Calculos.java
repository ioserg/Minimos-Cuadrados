package com.macias.android;

import java.lang.Math;

public class Calculos {
	
	private double[] x;
	private double[] fx;
	
	private double Ex, Efx, Ex2, Ex3, Ex4, Efx_x, Efx_x2, Efx2;
	
	private double[][] matriz = new double[3][4];
	
	public Calculos(double[] x, double[] fx){
		this.x = x;
		this.fx = fx;
		calcular();

	}
	
	public void calcular(){
		double Ex = 0, Efx = 0, Ex2 = 0, Ex3 = 0, Ex4 = 0, Efx_x = 0, Efx_x2 = 0, Efx2 = 0;
		for (int i = 0; i < x.length && x.length == fx.length; i++) {
			Ex += x[i];
			Efx += fx[i];
			Ex2 += Math.pow(x[i], 2);
			Ex3 += Math.pow(x[i], 3);
			Ex4 += Math.pow(x[i], 4);
			Efx_x += fx[i] * x[i];
			Efx_x2 += fx[i] * Math.pow(x[i], 2);
			Efx2 += Math.pow(fx[i], 2);
		}
		this.Ex = Ex;
		this.Efx = Efx;
		this.Ex2 = Ex2;
		this.Ex3 = Ex3;
		this.Ex4 = Ex4;
		this.Efx_x = Efx_x;
		this.Efx_x2 = Efx_x2;
		this.Efx2 = Efx2;
	}
	
	public double getEx(){
		return Ex;
	}
	
	public double getEfx(){
		return Efx;
	}
	
	public double getEx2(){
		return Ex2;
	}
	
	public double getEx3(){
		return Ex3;
	}
	
	public double getEx4(){
		return Ex4;
	}
	
	public double getEfx_x(){
		return Efx_x;
	}
	
	public double getEfx_x2(){
		return Efx_x2;
	}
	
	public double getEfx2(){
		return Efx2;
	}
	
	public double[][] getMatriz(){
				
		matriz[0][0] = Config_Activity.pars_m;
		matriz[0][1] = this.Ex;
		matriz[0][2] = this.Ex2;
		matriz[0][3] = this.Efx;
		
		matriz[1][0] = this.Ex;
		matriz[1][1] = this.Ex2;
		matriz[1][2] = this.Ex3;
		matriz[1][3] = this.Efx_x;
		
		matriz[2][0] = this.Ex2;
		matriz[2][1] = this.Ex3;
		matriz[2][2] = this.Ex4;
		matriz[2][3] = this.Efx_x2;
				
		return matriz;
	}
	
	
}
