package model;

public class FactoryComp implements AbstractFactory{
    @Override
    public ArrowComp CreateArrow(double startX, double startY, double endX, double endY) {
        return new ArrowComp(startX, startY, endX, endY);
    }
}
