package model;

public class FactoryImpl implements AbstractFactory {
    @Override
    public ArrowImpl CreateArrow(double startX, double startY, double endX, double endY) {
        return new ArrowImpl(startX, startY, endX, endY);
    }
}