package Day4;

public class Stack implements Cloneable {
    private int size;
    private int[] sArr;

    public Stack(int s) {
        this.size = s;
        this.sArr = new int[size];
    }

    @Override
    protected Stack clone() {
        Stack replica = new Stack(this.size);
        System.arraycopy(this.sArr, 0, replica.sArr, 0, this.size);
        return replica;
    }
}