package fatec.esiii.clienthub.controller;

import fatec.esiii.clienthub.facade.IFachada;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private IFachada fachada;

    private ResponseEntity<?> processarRetorno(String msgErro, Object data, HttpStatus statusSucesso) {
        if (msgErro == null) {
            return ResponseEntity.status(statusSucesso).body(data);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("erro", msgErro);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Reserva reserva) {
        String msg = fachada.salvar(reserva);
        return processarRetorno(msg, reserva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Reserva reserva) {
        reserva.setId(id);
        String msg = fachada.alterar(reserva);
        return processarRetorno(msg, reserva, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Reserva reserva = new Reserva();
        reserva.setId(id);
        String msg = fachada.excluir(reserva);
        return processarRetorno(msg, null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> consultar() {
        List<EntidadeDominio> resultados = fachada.consultar(new Reserva());
        List<Reserva> reservas = resultados.stream().map(e -> (Reserva) e).collect(Collectors.toList());
        return ResponseEntity.ok(reservas);
    }
}
