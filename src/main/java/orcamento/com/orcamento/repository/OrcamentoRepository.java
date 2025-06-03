package orcamento.com.orcamento.repository;

import orcamento.com.orcamento.orcamentoObject.OrcamentoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface OrcamentoRepository extends JpaRepository<OrcamentoEntity,Long> {

    List<OrcamentoEntity> findByNomeContainingIgnoreCase(String nome);
}
