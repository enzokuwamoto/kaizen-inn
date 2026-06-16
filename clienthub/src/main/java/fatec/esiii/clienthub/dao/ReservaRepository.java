package fatec.esiii.clienthub.dao;

import fatec.esiii.clienthub.model.Reserva;
import fatec.esiii.clienthub.model.StatusReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByHospedeId(Long hospedeId);
    List<Reserva> findByQuartoId(Long quartoId);
    List<Reserva> findByStatus(StatusReserva status);
    List<Reserva> findByDataEntradaBetween(LocalDate start, LocalDate end);
}
