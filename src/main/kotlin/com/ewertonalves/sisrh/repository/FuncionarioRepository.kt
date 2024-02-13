package com.ewertonalves.sisrh.repository

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import com.ewertonalves.sisrh.model.Funcionario

@Repository
interface FuncionarioRepository: JpaRepository<Funcionario, Long> {
    fun existsByCpf (cpf: String): Boolean
    fun findByCpf   (cpf: String): Funcionario?
}