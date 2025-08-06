public class Date {
    private int day;
    private int month;
    private int year;

    public setDay(int d){
        this.day=d;
    }
    public int getDay(){
        return this.day;
    }
    
    public setMonth(int m){
        this.month=m;
    }
    public int getMonth(){
        return this.month;
    }

    
    public setYear(int y){
        this.year=y;
    }
    public int getYear(){
        return this.year;
    }

     
    public Date(){
        this.day=27;
        this.month=8;
        this.year=2024;

    }
    public Date(int d, int m, int y){
        this.day=d;
        this.month=m;
        this.year=y;
    }

}
