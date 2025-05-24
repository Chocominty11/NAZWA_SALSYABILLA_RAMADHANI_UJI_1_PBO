// Abstract class Vehicle menunjukkan konsep abstraction
abstract class Vehicle {
    public abstract void startEngine(); // method abstract
}

// Interface Electric menunjukkan kemampuan tambahan
interface Electric {
    void chargeBattery();
}

// Class Car mewarisi Vehicle dan mengimplementasikan Electric
class Car extends Vehicle implements Electric {
    @Override
    public void startEngine() {
        System.out.println("Car engine started with keyless system.");
    }

    @Override
    public void chargeBattery() {
        System.out.println("Car battery is charging...");
    }
}

// Class Motorcycle mewarisi Vehicle
class Motorcycle extends Vehicle {
    @Override
    public void startEngine() {
        System.out.println("Motorcycle engine started with kick starter.");
    }
}

// Class utama untuk mendemonstrasikan polymorphism dan method
public class Kendaraan {
    public static void main(String[] args) {
        // Polymorphism: referensi Vehicle bisa menunjuk ke Car atau Motorcycle
        Vehicle v1 = new Car();
        Vehicle v2 = new Motorcycle();

        v1.startEngine(); // Memanggil startEngine() milik Car
        v2.startEngine(); // Memanggil startEngine() milik Motorcycle

        // Pemanggilan method dari interface Electric
        Electric eCar = new Car();
        eCar.chargeBattery(); // Mengakses chargeBattery dari interface
    }
}
