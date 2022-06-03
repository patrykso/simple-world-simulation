#pragma once
#include "Organizm.h"

class Roslina : public Organizm {
private:
protected:
    Organizm *getTypRozmnozonegoOrganizmu(Punkt pole);
public:
    Roslina(Swiat *swiat, Punkt pole, int turaPowstania, int sila, int inicjatywa, char symbol);
    virtual void akcja(Punkt pole) override;
    virtual void kolizja(Organizm *innyOrganizm) override;
    virtual string nazwaOrganizmu() override;
};
