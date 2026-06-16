package fatec.esiii.clienthub.controller;

import fatec.esiii.clienthub.dao.ReservaRepository;
import fatec.esiii.clienthub.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping("/ocupacao")
    public ResponseEntity<?> relatorioOcupacao() {
        List<Reserva> reservas = reservaRepository.findAll();
        Map<String, Long> ocupacao = new HashMap<>();
        
        long total = reservas.stream()
            .filter(r -> r.getStatus().name().equals("CONFIRMADA") || r.getStatus().name().equals("CHECK_IN"))
            .count();
            
        ocupacao.put("reservasAtivas", total);
        return ResponseEntity.ok(ocupacao);
    }

    @GetMapping("/financeiro")
    public ResponseEntity<?> relatorioFinanceiro() {
        List<Reserva> reservas = reservaRepository.findAll();
        double receitaTotal = reservas.stream()
            .filter(r -> r.getValorTotal() != null)
            .mapToDouble(r -> r.getValorTotal().doubleValue())
            .sum();
            
        Map<String, Double> financeiro = new HashMap<>();
        financeiro.put("receitaBrutaTotal Estimada", receitaTotal);
        return ResponseEntity.ok(financeiro);
    }
}
