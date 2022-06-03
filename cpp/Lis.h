#pragma once
#include "Zwierze.h"

#define SYMBOL_LISA 'L';
#define SILA_LISA 3;
#define INICJATYWA_LISA 7
#define ZASIEG_RUCHU_LISA 1

class Lis : public Zwierze {
private:
protected:
public:
    virtual void akcja(Punkt pole) override;
    Lis(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual string nazwaOrganizmu() override;
};