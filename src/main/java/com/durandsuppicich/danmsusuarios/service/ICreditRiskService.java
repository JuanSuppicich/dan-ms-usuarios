package com.durandsuppicich.danmsusuarios.service;

public interface ICreditRiskService {

    Boolean AfipPositiveReport(String cuit);

    Boolean BcraPositiveReport(String cuit);

    Boolean verazPositiveReport(String cuit);

}
