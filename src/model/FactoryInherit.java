package model;

public class FactoryInherit implements AbstractFactory {
    @Override
    public ArrowInherit CreateArrow(double startX, double startY, double endX, double endY) {
        return new ArrowInherit(startX, startY, endX, endY);
    }
}
