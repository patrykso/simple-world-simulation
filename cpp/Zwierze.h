#pragma once
#include "Organizm.h"
#include "Punkt.h"

class Zwierze : public Organizm {
private:
protected:
    Organizm *getTypRozmnozonegoOrganizmu(Punkt pole);
public:
    Zwierze(Swiat *swiat, Punkt pole, int turaPowstania, int sila, int inicjatywa, char symbol);
    virtual void akcja(Punkt pole) override;
    virtual void kolizja(Organizm *innyOrganizm) override;
    virtual string nazwaOrganizmu() override;
};
