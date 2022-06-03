#pragma once
#include "Zwierze.h"

#define SYMBOL_ANTYLOPY 'A'
#define SILA_ANTYLOPY 4
#define INICJATYWA_ANTYLOPY 4
#define ZASIEG_RUCHU_ANTYLOPY 2

class Antylopa : public Zwierze {
private:
protected:
public:
    Antylopa(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual void akcja(Punkt pole) override;
    virtual void kolizja(Organizm *innyOrganizm) override;
    bool czyAntylopaMozeUciec();
    virtual string nazwaOrganizmu() override;
};