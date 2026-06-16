package fatec.esiii.clienthub.controller;

import fatec.esiii.clienthub.facade.IFachada;
import fatec.esiii.clienthub.model.EntidadeDominio;
import fatec.esiii.clienthub.model.Hospede;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {

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
    public ResponseEntity<?> salvar(@RequestBody Hospede hospede) {
        String msg = fachada.salvar(hospede);
        return processarRetorno(msg, hospede, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody Hospede hospede) {
        hospede.setId(id);
        String msg = fachada.alterar(hospede);
        return processarRetorno(msg, hospede, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Hospede hospede = new Hospede();
        hospede.setId(id);
        String msg = fachada.excluir(hospede);
        if (msg == null) {
            Map<String, String> response = new HashMap<>();
            response.put("mensagem", "Hóspede inativado com sucesso");
            return ResponseEntity.ok(response);
        } else {
            return processarRetorno(msg, null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Hospede>> consultar(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nome) {
        
        Hospede hospedeConsulta = new Hospede();
        hospedeConsulta.setId(id);
        hospedeConsulta.setCpf(cpf);
        hospedeConsulta.setNome(nome);
        
        List<EntidadeDominio> resultados = fachada.consultar(hospedeConsulta);
        List<Hospede> hospedes = resultados.stream().map(e -> (Hospede) e).collect(Collectors.toList());
        return ResponseEntity.ok(hospedes);
    }
}
