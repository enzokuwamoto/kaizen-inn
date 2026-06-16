package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Reserva;

public class ValidarDadosReserva implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Reserva) {
            Reserva reserva = (Reserva) entidade;
            
            if (reserva.getHospede() == null || reserva.getHospede().getId() == null) {
                return "Hóspede é obrigatório para a reserva.";
            }
            if (reserva.getQuarto() == null || reserva.getQuarto().getId() == null) {
                return "Quarto é obrigatório para a reserva.";
            }
            if (reserva.getDataEntrada() == null) {
                return "Data de entrada é obrigatória.";
            }
            if (reserva.getDataSaida() == null) {
                return "Data de saída é obrigatória.";
            }
            if (reserva.getQtdeAdultos() < 1) {
                return "A reserva deve ter pelo menos 1 adulto.";
            }
            if (reserva.getQtdeCriancas() < 0) {
                return "Quantidade de crianças não pode ser negativa.";
            }
            if (reserva.getStatus() == null) {
                return "Status da reserva é obrigatório.";
            }
        }
        return null;
    }
}
