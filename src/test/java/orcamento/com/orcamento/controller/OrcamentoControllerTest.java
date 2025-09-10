package orcamento.com.orcamento.controller;

import orcamento.com.orcamento.orcamentoObject.Categoria;
import orcamento.com.orcamento.orcamentoObject.Orcamento;
import orcamento.com.orcamento.orcamentoObject.OrcamentoEntity;
import orcamento.com.orcamento.orcamentoObject.Status;
import orcamento.com.orcamento.repository.OrcamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class OrcamentoControllerTest {


    @Mock
    OrcamentoRepository repository;

    @InjectMocks
    OrcamentoController controler;

    Orcamento dados;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dados = new Orcamento("Nikita", Categoria.RECEITA,1500, Status.PENDENTE);
    }

    @Test
    @DisplayName("Deve retornar o status 201 created")
    void cadastroOrcamento() {

        OrcamentoEntity orcamentoTest = new OrcamentoEntity(dados);
        orcamentoTest.setId(1);

        when(repository.save(any(OrcamentoEntity.class))).thenReturn(orcamentoTest);

        ResponseEntity<OrcamentoEntity> response = controler.cadastroOrcamento(dados);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    @DisplayName("Deve retorna a validade do corpo do Orcamento cadastrado")
    void cadastroValidarCorpo(){

        OrcamentoEntity orcamentoTest = new OrcamentoEntity(dados);
        orcamentoTest.setId(1);

        when(repository.save(any(OrcamentoEntity.class))).thenReturn(orcamentoTest);

        ResponseEntity<OrcamentoEntity> response = controler.cadastroOrcamento(dados);

        OrcamentoEntity orcamento = response.getBody();

        assertEquals("Nikita", orcamento.getNome());
        assertEquals(Categoria.RECEITA, orcamento.getCategoria());
        assertEquals(1500, orcamento.getValor());
        assertEquals(Status.PENDENTE, orcamento.getStatus());
    }

    @Test
    void listaOrcamento() {
    }

    @Test
    void deletarOrcamento() {
    }

    @Test
    void buscarbyNome() {
    }

    @Test
    void orcamentoPorId() {
    }

    @Test
    void alteraOrcamento() {
    }
}