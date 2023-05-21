package exercise;

// BEGIN
class Circle {
    Point point;
    int radius;
    double PI = 3.1415926535;

    public Circle(Point point, int radius) {
        this.point = point;
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Error, radius must be bigger than 0.");
        }
        return (radius * radius) * PI;
    }
}
// END
