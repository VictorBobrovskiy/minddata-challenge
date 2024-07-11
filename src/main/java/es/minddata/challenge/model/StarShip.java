package es.minddata.challenge.model;

public abstract class StarShip {
    private String name;
    private transient Position position;
    private Engine engine;
    private int maxSpeed;
    private int weight;
    private int size;
    private byte shieldStrength;

    public abstract void move(int speed, Direction direction);
}