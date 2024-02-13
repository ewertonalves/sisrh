package com.ewertonalves.sisrh.service

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.ewertonalves.sisrh.repository.FuncionarioRepository
import com.ewertonalves.sisrh.model.Funcionario

@Service
class FuncionarioService(@Autowired private val repository: FuncionarioRepository){

    fun cadastrarFuncionario(funcionario: Funcionario): Funcionario {
        if (!validarCPF(funcionario.cpf)) {
            throw Exception("CPF deve ter exatamente 11 dígitos")
        }
        return repository.save(funcionario)
    }

    fun atualizarFuncionario(cpf: String, funcionario: Funcionario): Funcionario {
        if (!validarCPF(cpf)) {
            throw Exception("CPF inválido: $cpf")
        }
    
        if (!repository.existsByCpf(cpf)) {
            throw Exception("Funcionário não encontrado com o CPF: $cpf")
        }
        
        funcionario.cpf = cpf
        return repository.save(funcionario)
    }

    fun deletarFuncionario(id: Long){
        return repository.deleteById(id)
    }

    fun buscarFuncionario(cpf: String): Funcionario {
        val funcionario = repository.findByCpf(cpf) ?: 
            throw Exception("Funcionário não encontrado com o cpf: $cpf")
        return funcionario
    }

    private fun validarCPF(cpf: String): Boolean {
        return cpf.matches(Regex("\\d{11}"))
    }
}