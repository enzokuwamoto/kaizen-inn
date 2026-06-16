package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Reserva;

import java.time.temporal.ChronoUnit;

public class ValidarMinimoDiariasFeriado implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Reserva) {
            Reserva reserva = (Reserva) entidade;
            
            if (reserva.getDataEntrada() != null && reserva.getDataSaida() != null) {
                long diarias = ChronoUnit.DAYS.between(reserva.getDataEntrada(), reserva.getDataSaida());

                int mes = reserva.getDataEntrada().getMonthValue();
                int dia = reserva.getDataEntrada().getDayOfMonth();
                
                boolean isFeriadoProlongado = (mes == 12 && dia >= 24) || (mes == 2 && dia >= 15); // Ex: Natal/Carnaval
                
                if (isFeriadoProlongado && diarias < 2) {
                    return "Em feriados prolongados, a reserva deve possuir no mínimo 2 diárias.";
                }
            }
        }
        return null;
    }
}
