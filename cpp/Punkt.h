#pragma once

class Punkt {
private:
    int x, y;
public:
    Punkt();
    Punkt(int x, int y);
    void setX(int x);
    void setY(int y);
    int getX();
    int getY();
};
