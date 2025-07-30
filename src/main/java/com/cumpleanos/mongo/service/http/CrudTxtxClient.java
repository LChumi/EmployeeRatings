package com.cumpleanos.mongo.service.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "crudTxt-service", url = "http://192.168.112.36:7569")
public interface CrudTxtxClient {

    @GetMapping(value = "/sri/cliente/{ced}/{ident}")
    ResponseEntity<String> nombres(@PathVariable String ced, @PathVariable String ident);
}
