package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Reserva;
import fatec.esiii.clienthub.model.StatusReserva;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ValidarDataCheckIn implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Reserva) {
            Reserva reserva = (Reserva) entidade;
            
            // Só validamos a data se o status estiver mudando para CHECK_IN
            if (reserva.getStatus() == StatusReserva.CHECK_IN) {
                if (reserva.getDataEntrada() != null) {
                    LocalDate dataCheckin = null;
                    if (reserva.getDataCheckinReal() != null) {
                        dataCheckin = reserva.getDataCheckinReal().toLocalDate();
                    } else {
                        dataCheckin = LocalDate.now();
                    }
                    
                    if (!dataCheckin.isEqual(reserva.getDataEntrada())) {
                        return "A data real de Check-in informada (" + dataCheckin + ") deve ser exatamente o dia agendado para a entrada (" + reserva.getDataEntrada() + ").";
                    }
                }
            }
        }
        return null;
    }
}
