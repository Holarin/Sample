package model;

public interface AbstractFactory {
    AbstractProductArrow CreateArrow(double startX, double startY, double endX, double endY);
}
