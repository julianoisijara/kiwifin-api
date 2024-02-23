package com.kiwifin.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name="SUPERVISOR_QUALIDADE", schema = "KIWIFIN")
public class SupervisorQualidade extends Colaborador {
}
