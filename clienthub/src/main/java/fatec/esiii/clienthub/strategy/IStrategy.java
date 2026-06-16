package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;

public interface IStrategy {
    String processar(EntidadeDominio entidade);
}
