package dds.monedero.model;

public class Deposito implements MontoMovimiento {
  private double monto;

  public Deposito(double monto){
    this.monto = monto;
  }

  public boolean esDeposito(){
    return true;
  }

  public double getMonto(){
    return monto;
  }

  public double getSaldo(Cuenta cuenta){
    return cuenta.getSaldo() + getMonto();
  }
}
