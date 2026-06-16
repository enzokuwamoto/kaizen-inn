package fatec.esiii.clienthub.controller;

import fatec.esiii.clienthub.facade.IFachada;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Pagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentoController {

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
    public ResponseEntity<?> salvar(@RequestBody Pagamento pagamento) {
        String msg = fachada.salvar(pagamento);
        return processarRetorno(msg, pagamento, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Pagamento pagamento) {
        pagamento.setId(id);
        String msg = fachada.alterar(pagamento);
        return processarRetorno(msg, pagamento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(id);
        String msg = fachada.excluir(pagamento);
        return processarRetorno(msg, null, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> consultar() {
        List<EntidadeDominio> resultados = fachada.consultar(new Pagamento());
        List<Pagamento> pagamentos = resultados.stream().map(e -> (Pagamento) e).collect(Collectors.toList());
        return ResponseEntity.ok(pagamentos);
    }
}
