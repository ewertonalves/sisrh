package com.ewertonalves.sisrh.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import com.ewertonalves.sisrh.repository.FuncionarioRepository
import com.ewertonalves.sisrh.model.Funcionario

@Service
class FuncionarioService(@Autowired private val repository: FuncionarioRepository) {

    fun salvar(funcionario: Funcionario): Funcionario {
        require(validarCPF(funcionario.cpf)) { "CPF inválido" }
    
        if (repository.existsByCpf(funcionario.cpf)) {
            throw IllegalStateException("CPF já foi cadastrado")
        }
        return repository.save(funcionario)
    }

    fun atualizarFuncionario(funcionario: Funcionario): Funcionario {
        if (!validarCPF(funcionario.cpf)) {
            throw Exception("CPF inválido")
        }
        return repository.save(funcionario)
    }

    fun deletarFuncionario(id: Long) {
        if (!repository.existsById(id)) {
            throw Exception("Funcionário não encontrado com o ID: $id")
        }
        repository.deleteById(id)
    }

    fun pesquisarCpf(cpf: String): Funcionario {
        val funcionario = repository.findByCpf(cpf) ?: throw Exception("Funcionário não encontrado com o CPF: $cpf")
        return funcionario
    }

    fun listarFuncionarios(): List<Funcionario> {
        return repository.findAll()
    }

    private fun validarCPF(cpf: String): Boolean {
        return cpf.matches(Regex("\\d{11}"))
    }
}
