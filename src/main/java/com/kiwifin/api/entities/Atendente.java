package com.kiwifin.api.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="ATENDENTE", schema = "KIWIFIN")
public class Atendente extends Colaborador implements Serializable {
}
