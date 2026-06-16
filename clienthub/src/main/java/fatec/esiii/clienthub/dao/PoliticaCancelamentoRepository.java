package fatec.esiii.clienthub.dao;

import fatec.esiii.clienthub.model.PoliticaCancelamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticaCancelamentoRepository extends JpaRepository<PoliticaCancelamento, Long> {
}
