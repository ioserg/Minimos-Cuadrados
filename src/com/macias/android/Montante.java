package com.macias.android;

public class Montante {
	
	private double[][] matriz = new double[3][4];
	private double a0, a1, a2;
	
	private final int uno = 1;
	private final int cero = 0;
	
	public Montante(){
		calculoMontante();
	}
	
	public void calculoMontante(){
		
		double pivote = Resultados_Activity.matriz[0][0];
		
		matriz[0][0] = uno;
		matriz[1][0] = cero;
		matriz[2][0] = cero;
		
		for (int i = 1; i < 3; i++) {
			for (int j = 1; j < 4; j++) {
				matriz[0][j] = Resultados_Activity.matriz[0][j];
				matriz[i][j] = op_1(Resultados_Activity.matriz[0][0], Resultados_Activity.matriz[i][j], Resultados_Activity.matriz[i][0], Resultados_Activity.matriz[0][j], pivote);
			}
		}
		
		for (int i = 0; i < 3; i+=2) {
			for (int j = 2; j < 4; j++) {
				matriz[i][j] = op_1(matriz[1][1], matriz[i][j], matriz[i][1], matriz[1][j], pivote);
			}
		}
		
		pivote = matriz[1][1];
		
		matriz[0][1] = cero;
		matriz[1][1] = uno;
		matriz[2][1] = cero;
		
		for (int i = 0; i < 2; i++) {
			matriz[i][3] = op_1(matriz[2][2], matriz[i][3], matriz[i][2], matriz[2][3], pivote);
		}
		
		this.a0 = matriz[0][3] / matriz[2][2];
		this.a1 = matriz[1][3] / matriz[2][2];
		this.a2 = matriz[2][3] / matriz[2][2];
		
		matriz[0][2] = cero;
		matriz[1][2] = cero;
		matriz[2][2] = uno; 
		
	}
	
	public double getA0(){
		return a0;
	}
	
	public double getA1(){
		return a1;
	}
	
	public double getA2(){
		return a2;
	}
	
	public double[][] getMatiz_Montante(){
		return matriz;
	}
	
	public double op_1(double esquina, double num1, double num2, double num3, double pivote){
		double resultado = ((esquina * num1) - (num2 * num3)) / pivote ;
		return resultado;
	}
	
}
