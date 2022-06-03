#pragma once
#include "Zwierze.h"

#define SYMBOL_WILKA 'W'
#define SILA_WILKA 9
#define INICJATYWA_WILKA 5
#define ZASIEG_RUCHU_WILKA 1

class Wilk : public Zwierze {
private:
protected:
public:
    Wilk(Swiat *swiat, Punkt pole, int turaPowstania);
    virtual string nazwaOrganizmu() override;
};