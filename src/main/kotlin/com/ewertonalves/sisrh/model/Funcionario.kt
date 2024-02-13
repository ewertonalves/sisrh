package com.ewertonalves.sisrh.model

import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate

@Entity
@Table(name = "Funcionario")
data class Funcionario(
   
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:             Long,
    var nome:           String,
    var rg:             String,
    var cpf:            String,
    var dtNascimento:   LocalDate,
    var emailCorp:      String,
    var funcao:         String,
    var dtAdmissao:     LocalDate,
    var dtDemissao:     LocalDate,
    var salario:        Double
)