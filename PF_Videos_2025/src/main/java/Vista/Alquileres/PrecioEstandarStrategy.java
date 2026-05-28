package Vista.Alquileres;
 
public class PrecioEstandarStrategy implements ICalculoPrecioStrategy{
    @Override
    public double calcularCosto(int diasRentados, double tarifaBase) {
        // Cálculo normal: días por el precio base de la película
        return diasRentados * tarifaBase;
    }
}
