// Interface Taxable
interface Taxable {
    double calculateTax();
}

// Abstract class Product
abstract class Product {
    protected String name;
    protected double price;
    protected int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Abstract method untuk menghitung total harga sebelum pajak
    public abstract double calculateTotalBeforeTax();

    // Method untuk menampilkan detail produk
    public void displayProduct() {
        System.out.println(quantity + "x " + name + " @Rp" + price);
    }
}

// Subclass Food
class Food extends Product {
    private boolean isVegetarian;

    public Food(String name, double price, int quantity, boolean isVegetarian) {
        super(name, price, quantity);
        this.isVegetarian = isVegetarian;
    }

    @Override
    public double calculateTotalBeforeTax() {
        return price * quantity;
    }

    @Override
    public void displayProduct() {
        super.displayProduct();
        System.out.println("  (Makanan" + (isVegetarian ? " Vegetarian" : "") + ")");
    }
}

// Subclass Beverage (mengimplementasikan Taxable)
class Beverage extends Product implements Taxable {
    private boolean isCold; // Tambahan atribut untuk minuman dingin/panas

    public Beverage(String name, double price, int quantity, boolean isCold) {
        super(name, price, quantity);
        this.isCold = isCold;
    }

    @Override
    public double calculateTotalBeforeTax() {
        return price * quantity;
    }

    @Override
    public double calculateTax() {
        // Pajak 5% untuk semua minuman (tanpa alkohol)
        return calculateTotalBeforeTax() * 0.05;
    }

    @Override
    public void displayProduct() {
        super.displayProduct();
        System.out.println("  (Minuman " + (isCold ? "Dingin" : "Panas") + ")");
    }
}

// Class Kasir untuk menghitung total belanja
class Cashier {
    public void checkout(Product[] products) {
        double subtotal = 0;
        double totalTax = 0;
        
        System.out.println("=== STRUK BELANJA ===");
        
        for (Product product : products) {
            product.displayProduct();
            subtotal += product.calculateTotalBeforeTax();
            
            // Polimorfisme: hanya produk yang implement Taxable akan diproses
            if (product instanceof Taxable) {
                totalTax += ((Taxable) product).calculateTax();
            }
        }
        
        double total = subtotal + totalTax;
        
        System.out.println("\nRINCIAN PEMBAYARAN:");
        System.out.printf("Subtotal: Rp%,.2f%n", subtotal);
        System.out.printf("Pajak:    Rp%,.2f%n", totalTax);
        System.out.printf("TOTAL:    Rp%,.2f%n", total);
        System.out.println("=====================");
    }
}

// Class utama untuk demonstrasi
public class Kasir {
    public static void main(String[] args) {
        // Membuat array produk
        Product[] products = {
            new Food("Nasi Goreng", 25000, 2, false),
            new Beverage("Es Teh", 8000, 3, true),
            new Food("Salad", 35000, 1, true),
            new Beverage("Kopi Panas", 15000, 2, false)
        };
        
        // Proses checkout
        Cashier cashier = new Cashier();
        cashier.checkout(products);
    }
}