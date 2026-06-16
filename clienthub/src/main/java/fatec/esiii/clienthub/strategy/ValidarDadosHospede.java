package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Hospede;

public class ValidarDadosHospede implements IStrategy {

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Hospede) {
            Hospede hospede = (Hospede) entidade;
            
            if (hospede.getNome() == null || hospede.getNome().trim().isEmpty()) {
                return "Nome é obrigatório.";
            }
            if (hospede.getDataNasc() == null) {
                return "Data de nascimento é obrigatória.";
            }
            if (hospede.getTelefone() == null || hospede.getTelefone().trim().isEmpty()) {
                return "Telefone é obrigatório.";
            }
            if (hospede.getEmail() == null || hospede.getEmail().trim().isEmpty()) {
                return "E-mail é obrigatório.";
            }
            
            if (hospede.getEndereco() == null) {
                return "Endereço é obrigatório.";
            } else {
                if (hospede.getEndereco().getLogradouro() == null || hospede.getEndereco().getLogradouro().trim().isEmpty()) {
                    return "Logradouro é obrigatório.";
                }
                if (hospede.getEndereco().getNumero() == null || hospede.getEndereco().getNumero().trim().isEmpty()) {
                    return "Número do endereço é obrigatório.";
                }
                if (hospede.getEndereco().getCep() == null || hospede.getEndereco().getCep().trim().isEmpty()) {
                    return "CEP é obrigatório.";
                }
                if (hospede.getEndereco().getBairro() == null || hospede.getEndereco().getBairro().trim().isEmpty()) {
                    return "Bairro é obrigatório.";
                }
                if (hospede.getEndereco().getCidade() == null || hospede.getEndereco().getCidade().getDescricao() == null || hospede.getEndereco().getCidade().getDescricao().trim().isEmpty()) {
                    return "Cidade é obrigatória.";
                }
                if (hospede.getEndereco().getCidade().getEstado() == null || hospede.getEndereco().getCidade().getEstado().getDescricao() == null || hospede.getEndereco().getCidade().getEstado().getDescricao().trim().isEmpty()) {
                    return "Estado é obrigatório.";
                }
            }
        } else {
            return "Entidade deve ser um Hóspede para esta validação.";
        }
        
        return null;
    }
}
