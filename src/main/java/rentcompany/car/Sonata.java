package rentcompany.car;

import rentcompany.Car;

public class Sonata extends Car {
    private static final double DISTANCE_PER_LITER = 10;

    private final double tripDistance;

    public Sonata(double tripDistance) {
        this.tripDistance = tripDistance;
    }

    @Override
    protected double getDistancePerLiter() {
        return DISTANCE_PER_LITER;
    }

    @Override
    public double getTripDistance() {
        return tripDistance;
    }

    @Override
    public String getName() {
        return "Sonata";
    }
}
