#include <cstdlib>
#include "Punkt.h"

Punkt::Punkt() {
    x = 0;
    y = 0;
};

Punkt::Punkt(int x, int y) {
    this->x = x;
    this->y = y;
}

void Punkt::setX(int x) {
    this->x = x;
}

void Punkt::setY(int y) {
    this->y = y;
}

int Punkt::getX() {
    return x;
}

int Punkt::getY() {
    return y;
}