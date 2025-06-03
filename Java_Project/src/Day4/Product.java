package Day4;

public class Product implements AutoCloseable {
    private String title;
    private double unitPrice;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public void close() {
        System.out.println("Dispose is invoked....");
    }
}
