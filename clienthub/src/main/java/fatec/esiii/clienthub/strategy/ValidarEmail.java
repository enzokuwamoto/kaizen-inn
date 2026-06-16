package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Hospede;

import java.util.regex.Pattern;

public class ValidarEmail implements IStrategy {

    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Hospede) {
            Hospede hospede = (Hospede) entidade;
            String email = hospede.getEmail();
            
            if (email != null && !email.trim().isEmpty()) {
                if (!Pattern.compile(EMAIL_PATTERN).matcher(email).matches()) {
                    return "E-mail em formato inválido.";
                }
            }
        }
        return null;
    }
}
