package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Pagamento;

public class ValidarDadosPagamento implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Pagamento) {
            Pagamento p = (Pagamento) entidade;

            if (p.getReserva() == null || p.getReserva().getId() == null) {
                return "Reserva é obrigatória para o pagamento.";
            }
            if (p.getFormaPagamento() == null || p.getFormaPagamento().trim().isEmpty()) {
                return "Forma de pagamento é obrigatória.";
            }
            if (p.getValor() == null || p.getValor().doubleValue() <= 0) {
                return "Valor do pagamento é inválido.";
            }
            if (p.getDataOperacao() == null) {
                return "Data da operação é obrigatória.";
            }
        }
        return null;
    }
}
