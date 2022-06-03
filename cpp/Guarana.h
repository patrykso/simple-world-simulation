#pragma once
#include "Roslina.h"

#define SYMBOL_GUARANY 'G'
#define SILA_GUARANY 0
#define INICJATYWA_GUARANY 0

class Guarana : public Roslina {
private:
protected:
public:
    Guarana(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual void kolizja(Organizm *innyOrganizm) override;
    virtual string nazwaOrganizmu() override;
};