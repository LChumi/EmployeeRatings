package com.cumpleanos.calificaciones.persistence.entities;

import com.cumpleanos.calificaciones.utils.CalificacionEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

@Data
@Document(collection = "calificacion")
@CompoundIndex(name = "fecha_idx", def = "{'fecha':-1}")
public class Calificacion  implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    @CreatedDate
    @Setter(AccessLevel.NONE)
    private Date fecha;

    @Setter(AccessLevel.NONE)
    private LocalTime hora;

    private Cliente cliente;
    private Empleado empleado;
    private String observacion;
    private String calificacion;
    private Short rating;

    public Calificacion() {
        this.hora = LocalTime.now();
    }

    public void registerHour(){
        this.hora= LocalTime.now();
    }
}
