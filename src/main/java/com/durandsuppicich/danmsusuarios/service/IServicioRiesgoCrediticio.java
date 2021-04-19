package com.durandsuppicich.danmsusuarios.service;

public interface IServicioRiesgoCrediticio {
    
    Boolean reporteAFIPPositivo(String cuit);
    Boolean resporteBCRAPositivo(String cuit);
    Boolean reporteVerazPositivo(String cuit);
    
}
