package orcamento.com.orcamento.controller;

import jakarta.validation.Valid;
import orcamento.com.orcamento.orcamentoObject.AtualizaOrcamento;
import orcamento.com.orcamento.orcamentoObject.Orcamento;
import orcamento.com.orcamento.orcamentoObject.OrcamentoEntity;
import orcamento.com.orcamento.repository.OrcamentoRepository;
import orcamento.com.orcamento.service.OrcamentoService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.List;

//import static com.sun.beans.introspect.PropertyInfo.Name.description;

@RestController
@RequestMapping(value = "/orcamento")

public class OrcamentoController {

    @Autowired
    OrcamentoRepository repository;

    @Transactional
    @PostMapping

    public ResponseEntity<OrcamentoEntity> cadastroOrcamento(@RequestBody @Valid Orcamento dados) {
        return new ResponseEntity<>(repository.save(new OrcamentoEntity(dados)), HttpStatus.CREATED);
    }

    @Autowired
    OrcamentoService service;
    public ResponseEntity<Page<Orcamento>> listarOrcamento (
            @PageableDefault (size = 2, page = 1, sort = "nome", direction = Sort.Direction.ASC)
            Pageable page){
        return ResponseEntity.ok(service.listarOrcamento(page));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletarOrcamento (@PathVariable long id){
        OrcamentoEntity orcamento = repository.getReferenceById(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/Select")
   // @Query("SELECT o.* FROM Orcamento o WHERE LOWER(o.nome) LIKE LOWER(concat('%', :nome, '%'))")
    public ResponseEntity<Page<Orcamento>> BuscarbyNome (@RequestParam String nome, Pageable pageable ) {
        return ResponseEntity.ok(repository.findByNomeContainingIgnoreCase(nome, pageable).map(Orcamento::new));

    }



    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> orcamentoPorId (@PathVariable Long id){
        OrcamentoEntity orcamento = repository.getReferenceById(id);
        return ResponseEntity.ok(new Orcamento((orcamento)));
    }

    @Transactional
    @PutMapping
    public ResponseEntity<Orcamento> alteraOrcamento(@RequestBody @Valid AtualizaOrcamento dados) {
        OrcamentoEntity orcamento = repository.getReferenceById(dados.id());
        orcamento.AtualizarOrcamento(dados);
        return ResponseEntity.ok(new Orcamento(orcamento));
    }
}
