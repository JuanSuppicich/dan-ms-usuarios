package com.durandsuppicich.danmsusuarios.service;

import org.springframework.stereotype.Service;

@Service
public class ServicioRiesgoCrediticio implements IServicioRiesgoCrediticio {

    @Override
    public Boolean reporteAFIPPositivo(String cuit) {
        return true;
    }

    @Override
    public Boolean resporteBCRAPositivo(String cuit) {
        return true;
    }

    @Override
    public Boolean reporteVerazPositivo(String cuit) {
        return true;
    }
    
}
