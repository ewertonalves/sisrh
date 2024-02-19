package com.ewertonalves.sisrh

import com.ewertonalves.sisrh.repository.FuncionarioRepository
import com.ewertonalves.sisrh.service.FuncionarioService
import com.ewertonalves.sisrh.model.Funcionario
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.time.LocalDate

class FuncionarioServiceTest {

    @Mock
    private lateinit var repository: FuncionarioRepository

    @InjectMocks
    private lateinit var service: FuncionarioService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `teste salvar`() {
        val funcionario = Funcionario(
            1,
            "João",
            "123456",
            "12345678901",
            LocalDate.of(1990, 1, 1),
            "joao@example.com",
            "Desenvolvedor",
            LocalDate.of(2022, 1, 1),
            null,
            3000.0
        )
        `when`(repository.existsByCpf(funcionario.cpf)).thenReturn(false)
        `when`(repository.save(funcionario)).thenReturn(funcionario)

        val savedFuncionario = service.salvar(funcionario)

        assertEquals(funcionario, savedFuncionario)
        verify(repository).save(funcionario)
    }

    @Test
    fun `teste salvar com CPF já existente`() {
        val funcionario = Funcionario(
            1,
            "João",
            "123456",
            "12345678901",
            LocalDate.of(1990, 1, 1),
            "joao@example.com",
            "Desenvolvedor",
            LocalDate.of(2022, 1, 1),
            LocalDate.of(2023, 1, 1),
            3000.0
        )
        `when`(repository.existsByCpf(funcionario.cpf)).thenReturn(true)

        assertThrows(IllegalStateException::class.java) {
            service.salvar(funcionario)
        }
    }

    @Test
    fun `teste atualizar funcionario`() {
        val funcionario = Funcionario(
            1,
            "João",
            "123456",
            "12345678901",
            LocalDate.of(1990, 1, 1),
            "joao@example.com",
            "Desenvolvedor",
            LocalDate.of(2022, 1, 1),
            LocalDate.of(2023, 1, 1),
            3000.0
        )
        `when`(repository.save(funcionario)).thenReturn(funcionario)

        val updatedFuncionario = service.atualizarFuncionario(funcionario)

        assertEquals(funcionario, updatedFuncionario)
        verify(repository).save(funcionario)
    }

    @Test
    fun `teste atualizar funcionario com CPF inválido`() {
        val funcionario = Funcionario(
            1,
            "João",
            "123456",
            "12345678901",
            LocalDate.of(1990, 1, 1),
            "joao@example.com",
            "Desenvolvedor",
            LocalDate.of(2022, 1, 1),
            LocalDate.of(2023, 1, 1),
            3000.0
        )
        funcionario.cpf = "12345"

        assertThrows(Exception::class.java) {
            service.atualizarFuncionario(funcionario)
        }
    }

    @Test
    fun `test deletar funcionario`() {
        val id = 1L
        `when`(repository.existsById(id)).thenReturn(true)
        service.deletarFuncionario(id)
        verify(repository).deleteById(id)
    }

    @Test
    fun `test deletar funcionario com ID inexistente`() {
        val id = 1L
        `when`(repository.existsById(id)).thenReturn(false)

        assertThrows(Exception::class.java) {
            service.deletarFuncionario(id)
        }
    }

    @Test
    fun `teste pesquisar CPF`() {
        val cpf = "12345678901"
        val funcionario = Funcionario(
            1,
            "João",
            "123456",
            cpf,
            LocalDate.of(1990, 1, 1),
            "joao@example.com",
            "Desenvolvedor",
            LocalDate.of(2022, 1, 1),
            LocalDate.of(2023, 1, 1),
            3000.0
        )
        `when`(repository.findByCpf(cpf)).thenReturn(funcionario)

        val foundFuncionario = service.pesquisarCpf(cpf)

        assertEquals(funcionario, foundFuncionario)
    }

    @Test
    fun `teste pesquisar com CPF não encontrado`() {
        val cpf = "12345678901"
        `when`(repository.findByCpf(cpf)).thenReturn(null)

        assertThrows(Exception::class.java) {
            service.pesquisarCpf(cpf)
        }
    }

    @Test
    fun `test listar funcionarios`() {
        val funcionarios = listOf(
            Funcionario(
                1,
                "João",
                "123456",
                "12345678901",
                LocalDate.of(1990, 1, 1),
                "joao@example.com",
                "Desenvolvedor",
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2023, 1, 1),
                3000.0
            ),
            Funcionario(
                2,
                "Maria",
                "654321",
                "98765432109",
                LocalDate.of(1995, 5, 10),
                "maria@example.com",
                "Analista",
                LocalDate.of(2021, 6, 15),
                LocalDate.of(2023, 1, 1),
                3500.0
            )
        )
        `when`(repository.findAll()).thenReturn(funcionarios)

        val foundFuncionarios = service.listarFuncionarios()

        assertEquals(funcionarios, foundFuncionarios)
    }
}
