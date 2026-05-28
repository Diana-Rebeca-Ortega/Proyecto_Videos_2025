
package Vista.Alquileres;
//para dias festivos y descuentos 
public class PrecioPromocionalStrategy implements ICalculoPrecioStrategy {
    @Override
    public double calcularCosto(int diasRentados, double tarifaBase) {
        double costoNormal = diasRentados * tarifaBase;
        return costoNormal * 0.85; // Aplica un 15% de descuento
    }
}
