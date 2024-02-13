package com.ewertonalves.sisrh.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import com.ewertonalves.sisrh.service.FuncionarioService
import com.ewertonalves.sisrh.model.Funcionario

@RestController
@RequestMapping("/api/v1/sisrh/funcionario")
class FuncionarioController(@Autowired private val service: FuncionarioService) {

    @PostMapping("/cadastrar")
    fun cadastrar(@RequestBody funcionario: Funcionario): ResponseEntity<Any> {
        return try {
            val cadastrar = service.cadastrarFuncionario(funcionario)
            ResponseEntity(cadastrar, HttpStatus.CREATED)
        } catch(e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/{cpf}")
    fun atualizar(@PathVariable cpf: String, @RequestBody funcionario: Funcionario): ResponseEntity<Any> {
        return try {
            val atualizar = service.atualizarFuncionario(cpf, funcionario)
            ResponseEntity(atualizar, HttpStatus.OK)
        }
        catch(e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Long): ResponseEntity<Any> {
        return try {
            val deletar = service.deletarFuncionario(id)
            ResponseEntity(deletar, HttpStatus.NO_CONTENT)
        }
        catch(e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/{cpf}")
    fun pesquisar(@PathVariable cpf: String): ResponseEntity<Any> {
        return try {
            val pesquisar = service.buscarFuncionario(cpf)
            ResponseEntity(pesquisar, HttpStatus.OK)
        }
        catch(e: Exception) {
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }
}