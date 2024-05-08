package dds.monedero.model;

public class Extraccion implements MontoMovimiento {
  private double monto;

  public Extraccion(double monto){
    this.monto = monto;
  }

  public boolean esDeposito(){
    return false;
  }

  public double getMonto(){
    return monto;
  }

  public double getSaldo(Cuenta cuenta){
    return cuenta.getSaldo() - getMonto();
  }
}
