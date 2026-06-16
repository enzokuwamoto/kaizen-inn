package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.dao.QuartoRepository;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Quarto;
import fatec.esiii.clienthub.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidarCapacidadeQuarto implements IStrategy {

    @Autowired
    private QuartoRepository quartoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Reserva) {
            Reserva reserva = (Reserva) entidade;
            
            if (reserva.getQuarto() != null && reserva.getQuarto().getId() != null) {
                Optional<Quarto> quartoOpt = quartoRepository.findById(reserva.getQuarto().getId());
                if (quartoOpt.isPresent()) {
                    Quarto quarto = quartoOpt.get();
                    if (reserva.getQtdeAdultos() > quarto.getCapacidadeAdultos()) {
                        return "A quantidade de adultos excede a capacidade do quarto.";
                    }
                    if (reserva.getQtdeCriancas() > quarto.getCapacidadeCriancas()) {
                        return "A quantidade de crianças excede a capacidade do quarto.";
                    }
                } else {
                    return "Quarto informado não encontrado.";
                }
            }
        }
        return null;
    }
}
