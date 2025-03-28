package com.cumpleanos.calificaciones.service.implementation;

import com.cumpleanos.calificaciones.service.exception.HttpResponseHandler;
import com.cumpleanos.calificaciones.service.http.CrudTxtxClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CrudTxtClientServiceImpl {

    private final CrudTxtxClient client;

    public String getCliente(String ced, String ident) {
        return HttpResponseHandler.handle(() -> client.nombres(ced, ident),
                "Error al obtener los datos del cliente con CI: " + ced);
    }
}
