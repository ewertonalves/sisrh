package com.ewertonalves.sisrh

import com.ewertonalves.sisrh.model.Funcionario
import com.ewertonalves.sisrh.service.FuncionarioService
import com.ewertonalves.sisrh.controller.FuncionarioController
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.mockito.Mockito.`when`
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)    
class FuncionarioControllerTest {

    @Mock
    private lateinit var funcionarioService: FuncionarioService

    @InjectMocks
    private lateinit var funcionarioController: FuncionarioController

    @Test
    fun `teste cadastrar funcionario`() {
        val funcionario = Funcionario(
            1L,
            "João Silva",
            "1234567",
            "12345678901",
            LocalDate.of(1990, 1, 1),
            "joao@example.com",
            "Analista",
            LocalDate.of(2022, 1, 1),
            LocalDate.MAX,
            3000.0
        )
        val funcionarioCadastrado = Funcionario(
            1L,
            "João Silva",
            "1234567",
            "12345678901",
            LocalDate.of(1990, 1, 1),
            "joao@example.com",
            "Analista",
            LocalDate.of(2022, 1, 1),
            LocalDate.MAX,
            3000.0
        )
        
        val responseEntityEsperado = ResponseEntity(funcionarioCadastrado, HttpStatus.CREATED)

        `when`(funcionarioService.cadastrarFuncionario(funcionario)).thenReturn(funcionarioCadastrado)

        val responseEntity = funcionarioController.cadastrar(funcionario)

        assertNotNull(responseEntity)
        assertEquals(responseEntityEsperado.statusCode, responseEntity.statusCode)
        assertEquals(responseEntityEsperado.body, responseEntity.body)
    }

    @Test
    fun `teste atualizar funcionario`() {
        val cpf                     = "12345678901"
        val funcionarioAtualizado   = Funcionario(
            1L,
            "João Silva",
            "1234567",
            "12345678901",
            LocalDate.of(1990, 1, 1),
            "joao@example.com",
            "Analista de testes",
            LocalDate.of(2022, 1, 1),
            LocalDate.MAX,
            3000.0
        )

        `when`(funcionarioService.atualizarFuncionario(cpf, funcionarioAtualizado)).thenReturn(funcionarioAtualizado)

        val responseEntity = funcionarioController.atualizar(cpf, funcionarioAtualizado)

        assertNotNull(responseEntity)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(funcionarioAtualizado, responseEntity.body)
    }
    
    @Test
    fun `teste deletar funcionario`() {
        val id = 1L
        val responseEntityEsperado  = ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        val responseEntity          = funcionarioController.deletar(id)

        assertNotNull(responseEntity)
        assertEquals(responseEntityEsperado.statusCode, responseEntity.statusCode)
    }

    @Test
    fun `teste pesquisar funcionario`() {
        val cpf         = "12345678901"
        val funcionario = Funcionario()

        `when`(funcionarioService.buscarFuncionario(cpf)).thenReturn(funcionario)
        val responseEntity = funcionarioController.pesquisar(cpf)
        assertNotNull(responseEntity)
        assertEquals(HttpStatus.OK, responseEntity.statusCode)
        assertEquals(funcionario, responseEntity.body)
    }

}