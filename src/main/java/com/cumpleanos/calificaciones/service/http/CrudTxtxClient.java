package com.cumpleanos.calificaciones.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "crudTxt-service", url = "http://192.168.112.36:7569/sri")
public interface CrudTxtxClient {

    @GetMapping(value = "/cliente/{ced}/{ident}")
    String nombres(@PathVariable String ced, @PathVariable String ident);
}
