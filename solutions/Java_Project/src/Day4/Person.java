package Day4;

public  class Person {
    public static final double PI = 3.14;
    private static Person instance = null;

    private int id;
    private String firstName;
    private String lastName;

   

    public Person() {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public static Person getInstance() {
        if (instance == null) {
            instance = new Person();
        }
        return instance;
    }

    public void display(String... subjects) {
        for (String subject : subjects) {
            System.out.println(subject);
        }
    }

    public void swap(int[] nums) {
        int temp = nums[0];
        nums[0] = nums[1];
        nums[1] = temp;
    }

    public void calculate(int radius, double[] results) {
        results[0] = PI * radius * radius; // Area
        results[1] = 2 * PI * radius;      // Circumference
    }
}