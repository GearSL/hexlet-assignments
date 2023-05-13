package exercise;

// BEGIN
class Flat implements Home {
    private final double area;
    private final double balconyArea;
    int floor;
    Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }
    @Override
    public double getArea() {
        return area + balconyArea;
    }
    @Override
    public int compareTo(Home another) {
        return Double.compare(getArea(), another.getArea());
    }

    public String toString() {
        return String.format("Квартира площадью %.1f метров на %d этаже", getArea(), this.floor);
    }
}
// END
