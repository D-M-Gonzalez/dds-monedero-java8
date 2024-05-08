package dds.monedero.model;

import dds.monedero.exceptions.MaximaCantidadDepositosException;
import dds.monedero.exceptions.MaximoExtraccionDiarioException;
import dds.monedero.exceptions.MontoNegativoException;
import dds.monedero.exceptions.SaldoMenorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MonederoTest {
  private Cuenta cuenta;

  @BeforeEach
  void init() {
    cuenta = new Cuenta();
  }

  @Test
  @DisplayName("Es posible poner $1500 en una cuenta vacía")
  void Poner() {
    cuenta.poner(1500);
    assertEquals(1500.0,cuenta.getSaldo());
  }

  @Test
  @DisplayName("No es posible poner montos negativos")
  void PonerMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.poner(-1500));
  }

  @Test
  @DisplayName("Es posible realizar múltiples depósitos consecutivos")
  void TresDepositos() {
    cuenta.poner(1500);
    cuenta.poner(456);
    cuenta.poner(1900);

    assertEquals(3856.0,cuenta.getSaldo());
  }

  @Test
  @DisplayName("No es posible superar la máxima cantidad de depositos diarios")
  void MasDeTresDepositos() {
    assertThrows(MaximaCantidadDepositosException.class, () -> {
      cuenta.poner(1500);
      cuenta.poner(456);
      cuenta.poner(1900);
      cuenta.poner(245);
    });
  }

  @Test
  @DisplayName("No es posible extraer más que el saldo disponible")
  void ExtraerMasQueElSaldo() {
    assertThrows(SaldoMenorException.class, () -> {
      cuenta.setSaldo(90);
      cuenta.sacar(1001);
    });
  }

  @Test
  @DisplayName("No es posible extraer más que el límite diario")
  void ExtraerMasDe1000() {
    assertThrows(MaximoExtraccionDiarioException.class, () -> {
      cuenta.setSaldo(5000);
      cuenta.sacar(1001);
    });
  }

  @Test
  @DisplayName("No es posible extraer un monto negativo")
  void ExtraerMontoNegativo() {
    assertThrows(MontoNegativoException.class, () -> cuenta.sacar(-500));
  }

  @Test
  @DisplayName("El movimiento es un depósito")
  void EsUnDeposito() {
    cuenta.poner(1500);
    List<Movimiento> movimientoList = cuenta.getMovimientos();

    assertTrue(movimientoList.get(0).isDeposito());
  }

  @Test
  @DisplayName("El movimiento no es un depósito")
  void NoEsUnDeposito() {
    cuenta.setSaldo(5000);
    cuenta.sacar(500);
    List<Movimiento> movimientoList = cuenta.getMovimientos();

    assertFalse(movimientoList.get(0).isDeposito());
  }

  @Test
  @DisplayName("El movimiento no es una extracción")
  void NoEsUnaExtraccion() {
    cuenta.poner(1500);
    List<Movimiento> movimientoList = cuenta.getMovimientos();

    assertFalse(movimientoList.get(0).isExtraccion());
  }

  @Test
  @DisplayName("El movimiento es una extracción")
  void EsUnaExtraccion() {
    cuenta.setSaldo(5000);
    cuenta.sacar(500);
    List<Movimiento> movimientoList = cuenta.getMovimientos();

    assertTrue(movimientoList.get(0).isExtraccion());
  }

  @Test
  @DisplayName("El movimiento fue de la fecha de hoy")
  void FueDeHoy() {
    cuenta.poner(1500);
    List<Movimiento> movimientoList = cuenta.getMovimientos();

    assertTrue(movimientoList.get(0).esDeLaFecha(LocalDate.now()));
  }

  @Test
  @DisplayName("El movimiento fue depositado hoy")
  void FueDepositadoHoy() {
    cuenta.poner(1500);
    List<Movimiento> movimientoList = cuenta.getMovimientos();

    assertTrue(movimientoList.get(0).fueDepositado(LocalDate.now()));
  }

  @Test
  @DisplayName("El movimiento fue extraido hoy")
  void FueExtraidoHoy() {
    cuenta.setSaldo(5000);
    cuenta.sacar(500);
    List<Movimiento> movimientoList = cuenta.getMovimientos();

    assertTrue(movimientoList.get(0).fueExtraido(LocalDate.now()));
  }

}