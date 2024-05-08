package dds.monedero.model;

import dds.monedero.exceptions.MontoNegativoException;

import java.time.LocalDate;

public class Movimiento {
  private LocalDate fecha;
  // Nota: En ningún lenguaje de programación usen jamás doubles (es decir, números con punto flotante) para modelar dinero en el mundo real.
  // En su lugar siempre usen numeros de precision arbitraria o punto fijo, como BigDecimal en Java y similares
  // De todas formas, NO es necesario modificar ésto como parte de este ejercicio. 

  private MontoMovimiento monto;

  public Movimiento(LocalDate fecha, MontoMovimiento monto) {
    this.fecha = fecha;
    this.monto = monto;
  }

  public double getMonto() {
    return monto.getMonto();
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public boolean fueDepositado(LocalDate fecha) {
    return isDeposito() && esDeLaFecha(fecha);
  }

  public boolean fueExtraido(LocalDate fecha) {
    return isExtraccion() && esDeLaFecha(fecha);
  }

  public boolean esDeLaFecha(LocalDate fecha) {
    return this.fecha.equals(fecha);
  }

  public boolean isDeposito() {
    return monto.esDeposito();
  }

  public boolean isExtraccion() {
    return !monto.esDeposito();
  }

  public double calcularValor(Cuenta cuenta) {
    return monto.getSaldo(cuenta);
  }
}
