package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.dao.HospedeRepository;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Hospede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidarCPF implements IStrategy {

    @Autowired
    private HospedeRepository hospedeRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Hospede) {
            Hospede hospede = (Hospede) entidade;
            String cpf = hospede.getCpf();
            
            if (cpf == null || cpf.trim().isEmpty()) {
                return "CPF é obrigatório.";
            }

            cpf = cpf.replaceAll("[^0-9]", "");
            if (cpf.length() != 11) {
                return "CPF deve conter 11 dígitos numéricos.";
            }

            if (isCpfInvalido(cpf)) {
                return "CPF inválido (falha na validação do dígito verificador).";
            }
            
            hospede.setCpf(cpf);

            Optional<Hospede> existente = hospedeRepository.findByCpf(cpf);
            if (existente.isPresent()) {
                if (hospede.getId() == null || !hospede.getId().equals(existente.get().getId())) {
                    return "CPF já cadastrado no sistema (RN0202: Unicidade de CPF).";
                }
            }
        }
        return null;
    }

    private boolean isCpfInvalido(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999")) {
            return true;
        }

        try {
            int soma = 0, peso = 10;
            for (int i = 0; i < 9; i++) {
                soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--;
            }
            int r = 11 - (soma % 11);
            char dig10 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            soma = 0;
            peso = 11;
            for (int i = 0; i < 10; i++) {
                soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--;
            }
            r = 11 - (soma % 11);
            char dig11 = (r == 10 || r == 11) ? '0' : (char) (r + 48);

            return (dig10 != cpf.charAt(9)) || (dig11 != cpf.charAt(10));
        } catch (Exception e) {
            return true;
        }
    }
}
