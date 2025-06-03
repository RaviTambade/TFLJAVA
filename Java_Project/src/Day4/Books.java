package Day4;



public class Books {
    private String[] titles;

    public Books() {
        titles = new String[5];
        titles[0] = "You Can Win";
        titles[1] = "Ignited Minds";
        titles[2] = "Rich Dad, Poor Dad";
        titles[3] = "Let Us C";
        titles[4] = "Effective C++";
    }

    public String getTitle(int index) {
        return titles[index];
    }

    public void setTitle(int index, String title) {
        titles[index] = title;
    }
}
