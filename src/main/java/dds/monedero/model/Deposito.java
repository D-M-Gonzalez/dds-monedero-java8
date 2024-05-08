package dds.monedero.model;

import dds.monedero.exceptions.MontoNegativoException;

public class Deposito implements MontoMovimiento {
  private double monto;

  public Deposito(double monto){
    /* El código sigue estando repetido. Podría haberlo solucionado haciendo que
    * la validación este en la creación del movimiento, sin embargo no me parecio
    * correcto que movimiento sea quien tenga la responsabilidad de validar que
    * el monto no puede ser negativo
    */
    if (monto <= 0) {
      throw new MontoNegativoException(monto + ": el monto a ingresar debe ser un valor positivo");
    }
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
