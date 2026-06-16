package fatec.esiii.clienthub.dao;

import fatec.esiii.clienthub.model.Hospede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long> {
    Optional<Hospede> findByCpf(String cpf);
    List<Hospede> findByNomeContainingIgnoreCase(String nome);
}
