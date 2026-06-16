package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Pagamento;
import fatec.esiii.clienthub.model.StatusPagamento;

public class CalcularEstorno implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Pagamento) {
            Pagamento pgto = (Pagamento) entidade;
            
            if (pgto.getStatus() == StatusPagamento.ESTORNADO) {
                System.out.println("LOG: Processando cálculo de estorno.");
            }
        }
        return null;
    }
}
