package com.durandsuppicich.danmsusuarios.service;

import org.springframework.stereotype.Service;

@Service
public class CreditRiskService implements ICreditRiskService {

    @Override
    public Boolean AfipPositiveReport(String cuit) {
        return true;
    }

    @Override
    public Boolean BcraPositiveReport(String cuit) {
        return true;
    }

    @Override
    public Boolean verazPositiveReport(String cuit) {
        return true;
    }

}
