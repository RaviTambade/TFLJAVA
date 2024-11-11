package Day1;

public class Complex {
    private int real;
    private int imag;

    // Constructors
    public Complex() {
        this.real = 0;
        this.imag = 0;
    }

    public Complex(int r, int i) {
        this.real = r;
        this.imag = i;
    }

    // Getter and Setter for real and imaginary parts
    public int getReal() {
        return this.real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public int getImag() {
        return this.imag;
    }

    public void setImag(int imag) {
        this.imag = imag;
    }

    // Method to add two complex numbers
    public Complex add(Complex other) {
        return new Complex(this.real + other.real, this.imag + other.imag);
    }
}
