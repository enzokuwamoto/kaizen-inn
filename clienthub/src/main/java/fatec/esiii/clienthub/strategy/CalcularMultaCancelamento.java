package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.dao.ReservaRepository;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Pagamento;
import fatec.esiii.clienthub.model.Reserva;
import fatec.esiii.clienthub.model.StatusPagamento;
import fatec.esiii.clienthub.model.StatusReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class CalcularMultaCancelamento implements IStrategy {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Reserva) {
            Reserva reserva = (Reserva) entidade;
            
            if (reserva.getStatus() == StatusReserva.CANCELADA && reserva.getId() != null) {
                Optional<Reserva> resOpt = reservaRepository.findById(reserva.getId());
                if (resOpt.isPresent()) {
                    Reserva rBanco = resOpt.get();
                    long horasAteEntrada = ChronoUnit.HOURS.between(LocalDateTime.now(), rBanco.getDataEntrada().atStartOfDay());

                    if (horasAteEntrada < 48) {
                        System.out.println("LOG: Cancelamento com menos de 48h. Aplicando multa de 50%. (Apenas informativo via log nesta POC)");
                    } else {
                        System.out.println("LOG: Cancelamento sem multa (antecedência >= 48h).");
                    }
                }
            }
        }
        return null;
    }
}
