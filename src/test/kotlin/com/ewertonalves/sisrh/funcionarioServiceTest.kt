package com.ewertonalves.sisrh

import com.ewertonalves.sisrh.model.Funcionario
import com.ewertonalves.sisrh.repository.FuncionarioRepository
import com.ewertonalves.sisrh.service.FuncionarioService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.Mockito.mock
import java.time.LocalDate

class FuncionarioServiceTest {

    private lateinit var funcionarioRepository: FuncionarioRepository
    private lateinit var funcionarioService:    FuncionarioService

    @BeforeEach
    fun setup() {
        funcionarioRepository   = mock(FuncionarioRepository::class.java)
        funcionarioService      = FuncionarioService(funcionarioRepository)
    }

    @Test
    fun `cadastrar funcionario com CPF valido`() {
        val funcionario = Funcionario(
            id              = 1L,
            nome            = "Fulano",
            rg              = "123456",
            cpf             = "12345678901",
            dtNascimento    = LocalDate.now(),
            emailCorp       = "fulano@example.com",
            funcao          = "Analista",
            dtAdmissao      = LocalDate.now(),
            dtDemissao      = LocalDate.now(),
            salario         = 1000.0
        )

        `when`(funcionarioRepository.save(any(Funcionario::class.java))).thenReturn(funcionario)

        val result = funcionarioService.cadastrarFuncionario(funcionario)

        Assertions.assertEquals(funcionario, result)
    }

    @Test
    fun `cadastrar funcionario deve lançar exceção para CPF inválido`() {
        val funcionario = Funcionario(
            id              = 1L,
            nome            = "Fulano",
            rg              = "123456",
            cpf             = "123",
            dtNascimento    = LocalDate.now(),
            emailCorp       = "fulano@example.com",
            funcao          = "Analista",
            dtAdmissao      = LocalDate.now(),
            dtDemissao      = LocalDate.now(),
            salario         = 1000.0
        )

        Assertions.assertThrows(Exception::class.java) {
            funcionarioService.cadastrarFuncionario(funcionario)
        }
    }

    @Test
    fun `atualizar funcionario deve lançar exceção para CPF inválido`() {
        val funcionario = Funcionario(
            id              = 1L,
            nome            = "Fulano",
            rg              = "123456",
            cpf             = "123",
            dtNascimento    = LocalDate.now(),
            emailCorp       = "fulano@example.com",
            funcao          = "Analista de testes",
            dtAdmissao      = LocalDate.now(),
            dtDemissao      = LocalDate.now(),
            salario         = 1000.0
        )

        Assertions.assertThrows(Exception::class.java) {
            funcionarioService.atualizarFuncionario("123", funcionario)
        }
    }

    @Test
    fun `atualizar funcionario deve lançar exceção para funcionario não encontrado`() {
        val funcionario = Funcionario(
            id              = 1L,
            nome            = "Fulano",
            rg              = "12345678901",
            cpf             = "123",
            dtNascimento    = LocalDate.now(),
            emailCorp       = "fulano@example.com",
            funcao          = "Analista de testes",
            dtAdmissao      = LocalDate.now(),
            dtDemissao      = LocalDate.now(),
            salario         = 1000.0
        )

        `when`(funcionarioRepository.existsByCpf("12345678901")).thenReturn(false)

        Assertions.assertThrows(Exception::class.java) {
            funcionarioService.atualizarFuncionario("12345678901", funcionario)
        }
    }

    @Test
    fun `atualizar funcionario com sucesso`() {
        val cpf = "12345678901"
        val funcionario = Funcionario(
            id              = 1L,
            nome            = "Fulano",
            rg              = "12345678901",
            cpf             = "123",
            dtNascimento    = LocalDate.now(),
            emailCorp       = "fulano@example.com",
            funcao          = "Analista de testes",
            dtAdmissao      = LocalDate.now(),
            dtDemissao      = LocalDate.now(),
            salario         = 1000.0
        )
    
        `when`(funcionarioRepository.existsByCpf(cpf)).thenReturn(true)
        `when`(funcionarioRepository.save(funcionario)).thenReturn(funcionario)
    
        val funcionarioAtualizado = funcionarioService.atualizarFuncionario(cpf, funcionario)
    
        Assertions.assertEquals(cpf, funcionarioAtualizado.cpf)
    }

    @Test
    fun `apagar um funcionario`(){
        val id = 1L

        Assertions.assertDoesNotThrow {
            funcionarioService.deletarFuncionario(id)
        }
    }

    @Test
    fun `buscar funcionario existente`() {
        val cpf = "12345678901"
        val funcionario = Funcionario(
            id              = 1L,
            nome            = "Fulano",
            rg              = "123456",
            cpf             = "123",
            dtNascimento    = LocalDate.now(),
            emailCorp       = "fulano@example.com",
            funcao          = "Analista",
            dtAdmissao      = LocalDate.now(),
            dtDemissao      = LocalDate.now(),
            salario         = 1000.0
        )
        `when`(funcionarioRepository.findByCpf(cpf)).thenReturn(funcionario)

        val result = funcionarioService.buscarFuncionario(cpf)

        Assertions.assertEquals(funcionario, result)
    }

    @Test
    fun `buscar funcionario inexistente`() {
        val cpf = "12345678901"
        `when`(funcionarioRepository.findByCpf(cpf)).thenReturn(null)

        Assertions.assertThrows(Exception::class.java) {
            funcionarioService.buscarFuncionario(cpf)
        }
    }
}
