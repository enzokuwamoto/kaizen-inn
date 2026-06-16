package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.dao.PagamentoRepository;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Pagamento;
import fatec.esiii.clienthub.model.Reserva;
import fatec.esiii.clienthub.model.StatusPagamento;
import fatec.esiii.clienthub.model.StatusReserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidarConfirmacaoCondicionadaPgto implements IStrategy {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Reserva) {
            Reserva reserva = (Reserva) entidade;

            if (reserva.getStatus() == StatusReserva.CONFIRMADA) {
                if (reserva.getId() == null) {
                    return "RN0222: Reserva nova não pode nascer como CONFIRMADA sem pagamento prévio.";
                }
                
                List<Pagamento> pagamentos = pagamentoRepository.findByReservaId(reserva.getId());
                boolean hasAprovado = pagamentos.stream()
                        .anyMatch(p -> p.getStatus() == StatusPagamento.APROVADO);
                        
                if (!hasAprovado) {
                    return "RN0222: A reserva não pode ser confirmada pois não possui pagamento aprovado.";
                }
            }
        }
        return null;
    }
}
