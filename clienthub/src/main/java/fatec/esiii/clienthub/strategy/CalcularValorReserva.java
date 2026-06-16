package fatec.esiii.clienthub.strategy;

import fatec.esiii.clienthub.dao.QuartoRepository;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Quarto;
import fatec.esiii.clienthub.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Component
public class CalcularValorReserva implements IStrategy {

    @Autowired
    private QuartoRepository quartoRepository;

    @Override
    public String processar(EntidadeDominio entidade) {
        if (entidade instanceof Reserva) {
            Reserva reserva = (Reserva) entidade;
            
            if (reserva.getQuarto() != null && reserva.getQuarto().getId() != null) {
                Optional<Quarto> qOpt = quartoRepository.findById(reserva.getQuarto().getId());
                if (qOpt.isPresent()) {
                    Quarto quarto = qOpt.get();
                    
                    long diarias = ChronoUnit.DAYS.between(reserva.getDataEntrada(), reserva.getDataSaida());
                    if (diarias < 1) diarias = 1;
                    
                    BigDecimal precoBase = quarto.getPrecoBaseDiaria();
                    BigDecimal valorBaseTotal = precoBase.multiply(new BigDecimal(diarias));
                    
                    // RN0241: Crianças até 5 anos não pagam. 
                    // Na nossa modelagem simplificada, o preço base do quarto cobre a capacidade total
                    // Se houvesse custo extra por adulto, calcularíamos aqui. Vamos assumir que a reserva pega o valor do quarto cheio.
                    
                    // RN0242: Promoções aplicáveis
                    // Para simplificar, assumimos que não há promoção ativa acoplada diretamente à reserva aqui, 
                    // ou poderíamos buscar do BD a promoção e aplicar desconto.
                    
                    reserva.setValorTotal(valorBaseTotal);
                }
            }
        }
        return null;
    }
}
