package dds.monedero.model;

import dds.monedero.exceptions.MontoNegativoException;

public class Extraccion implements MontoMovimiento {
  private double monto;

  public Extraccion(double monto){
    if (monto <= 0) {
      throw new MontoNegativoException(monto + ": el monto a ingresar debe ser un valor positivo");
    }
    this.monto = monto;
  }

  public boolean esDeposito(){
    return false;
  }

  public double getMonto(){
    return monto;
  }

  public double getSaldo(){
    return -getMonto();
  }
}
