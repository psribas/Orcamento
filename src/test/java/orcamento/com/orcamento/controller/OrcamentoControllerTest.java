package orcamento.com.orcamento.controller;

import orcamento.com.orcamento.orcamentoObject.*;
import orcamento.com.orcamento.repository.OrcamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.List;

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
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        dados = new Orcamento("Nikita", Categoria.RECEITA,1500, Status.PENDENTE, LocalDate.now());
    }

    private OrcamentoEntity mockOrcamentoPersistido(long id){
        OrcamentoEntity orcamentoPersistido = new OrcamentoEntity(dados);
        orcamentoPersistido.setId(id);

        when(repository.save(any(OrcamentoEntity.class))).thenReturn(orcamentoPersistido);
        when(repository.getReferenceById(id)).thenReturn(orcamentoPersistido);

        return orcamentoPersistido;
    }

    private Page<OrcamentoEntity> mockPaginacao () {
        Pageable pageable = Pageable.unpaged();
        OrcamentoEntity orcamento = mockOrcamentoPersistido(1L);
        PageImpl<OrcamentoEntity> page = new PageImpl<>(List.of(orcamento));
        when(repository.findAll(pageable)).thenReturn(page);
        return page;
    }
    @Test
    @DisplayName("Deve retornar o status 201 created")
    void cadastroOrcamento() {
        mockOrcamentoPersistido(1L);
        ResponseEntity<OrcamentoEntity> response = controler.cadastroOrcamento(dados);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED,response.getStatusCode());
    }

    @Test
    @DisplayName("Deve retorna a validade do corpo do Orcamento cadastrado")
    void cadastroValidarCorpo(){

        mockOrcamentoPersistido(1L);
        ResponseEntity<OrcamentoEntity> response = controler.cadastroOrcamento(dados);

        OrcamentoEntity orcamento = response.getBody();

        assertEquals("Nikita", orcamento.getNome());
        assertEquals(Categoria.RECEITA, orcamento.getCategoria());
        assertEquals(1500, orcamento.getValor());
        assertEquals(Status.PENDENTE, orcamento.getStatus());
    }

    @Test
    @DisplayName("Deve retornar uma lista paginada de Orcamento")
    void listaOrcamento() {
        Page<OrcamentoEntity> page = mockPaginacao();

        ResponseEntity<Page<Orcamento>> response = controler.listaOrcamento(Pageable.unpaged());

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(page.getTotalElements(), response.getBody().getTotalElements());
    }

    @Test
    @DisplayName("Deve excluir um orçamento com sucesso.")
    void alteraOrcamento() {
        mockOrcamentoPersistido(1L);

        AtualizaOrcamento dadosAtulizados = new AtualizaOrcamento(
                1L, 300, Status.DEBITADO,  LocalDate.now());

        ResponseEntity<Orcamento> response = controler.alteraOrcamento(dadosAtulizados);

        assertNotNull(response.getBody());
        assertEquals(dadosAtulizados.dataalteracao(), response.getBody().dataalteracao());
        assertEquals( dadosAtulizados.valor(), response.getBody().valor());
        assertEquals(dadosAtulizados.status(), response.getBody().status());
    }

    @Test
    @DisplayName("Deve retornar Orcamento por Nome")
    void orcamentoByNome(){
        Pageable pageable = Pageable.unpaged();
        Page page = mockPaginacao();

        when(repository.findByNomeContainingIgnoreCase(dados.nome(),pageable)).thenReturn(page);

        ResponseEntity<Page<Orcamento>> response = controler.BuscarbyNome(dados.nome(),pageable);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(page.getTotalElements(),response.getBody().getTotalElements());
    }

    @Test
    @DisplayName("Deve retornar Orcamento por ID")
    void orcamentoPorId() {
        mockOrcamentoPersistido(1L);

        ResponseEntity<Orcamento> response = controler.orcamentoPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

    }

    @Test
    @DisplayName("DEve atualizar um orcamento com sucesso")
    void deletarOrcamento() {
        mockOrcamentoPersistido(1L);

        ResponseEntity<Void> response = controler.deletarOrcamento(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
       // assertNotNull(response.getBody());
    }
}