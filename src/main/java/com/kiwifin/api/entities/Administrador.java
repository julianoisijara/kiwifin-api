package com.kiwifin.api.entities;


import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ADMINISTRADOR", schema = "KIWIFIN")
public class Administrador extends Colaborador {
}
