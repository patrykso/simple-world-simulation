#pragma once
#include "Zwierze.h"

#define SYMBOL_OWCY 'O'
#define SILA_OWCY 4
#define INICJATYWA_OWCY 4
#define ZASIEG_RUCHU_OWCY 1

class Owca : public Zwierze {
private:
protected:
public:
    Owca(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual string nazwaOrganizmu() override;
};