package com.durandsuppicich.danmsusuarios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("orders")
public interface IOrderClient {

    @GetMapping(value = "/api/orders", params = "cuit")
    List<OrderDto> getByCuit(@RequestParam(name = "cuit") String cuit);

}
