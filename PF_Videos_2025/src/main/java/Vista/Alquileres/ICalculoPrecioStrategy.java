package Vista.Alquileres;

public interface ICalculoPrecioStrategy {
    double calcularCosto(int diasRentados, double tarifaBase);
}
