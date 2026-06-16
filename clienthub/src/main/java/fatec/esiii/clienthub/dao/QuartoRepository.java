package fatec.esiii.clienthub.dao;

import fatec.esiii.clienthub.model.Quarto;
import fatec.esiii.clienthub.model.StatusQuarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Long> {
    List<Quarto> findByStatus(StatusQuarto status);
}
