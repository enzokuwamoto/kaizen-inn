package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Quarto;

public class ValidarDadosQuarto implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Quarto) {
            Quarto quarto = (Quarto) entidade;
            if (quarto.getNumero() == null || quarto.getNumero().trim().isEmpty()) {
                return "Número do quarto é obrigatório.";
            }
            if (quarto.getTipo() == null) {
                return "Tipo do quarto é obrigatório.";
            }
            if (quarto.getCapacidadeAdultos() < 1) {
                return "Capacidade de adultos deve ser maior ou igual a 1.";
            }
            if (quarto.getCapacidadeCriancas() < 0) {
                return "Capacidade de crianças não pode ser negativa.";
            }
            if (quarto.getStatus() == null) {
                return "Status do quarto é obrigatório.";
            }
            if (quarto.getPrecoBaseDiaria() == null || quarto.getPrecoBaseDiaria().doubleValue() < 0) {
                return "Preço base da diária é obrigatório e não pode ser negativo.";
            }
        }
        return null;
    }
}
