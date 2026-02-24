package orcamento.com.orcamento.service;

import orcamento.com.orcamento.orcamentoObject.Orcamento;
import orcamento.com.orcamento.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrcamentoService {

    @Autowired
    OrcamentoRepository repository;

    public Page<Orcamento> listarOrcamento (Pageable page){
        return repository.findAll(page).map(Orcamento::new);
    }
}
