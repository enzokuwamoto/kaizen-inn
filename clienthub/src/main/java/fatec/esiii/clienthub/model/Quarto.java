package fatec.esiii.clienthub.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "quartos")
public class Quarto extends EntidadeDominio {

    private String numero;
    private String nome;
    
    @Enumerated(EnumType.STRING)
    private TipoQuarto tipo;
    
    private int capacidadeAdultos;
    private int capacidadeCriancas;
    
    @Enumerated(EnumType.STRING)
    private StatusQuarto status;
    
    private BigDecimal precoBaseDiaria;

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    
    public TipoQuarto getTipo() { return tipo; }
    public void setTipo(TipoQuarto tipo) { this.tipo = tipo; }
    
    public int getCapacidadeAdultos() { return capacidadeAdultos; }
    public void setCapacidadeAdultos(int capacidadeAdultos) { this.capacidadeAdultos = capacidadeAdultos; }
    
    public int getCapacidadeCriancas() { return capacidadeCriancas; }
    public void setCapacidadeCriancas(int capacidadeCriancas) { this.capacidadeCriancas = capacidadeCriancas; }
    
    public StatusQuarto getStatus() { return status; }
    public void setStatus(StatusQuarto status) { this.status = status; }
    
    public BigDecimal getPrecoBaseDiaria() { return precoBaseDiaria; }
    public void setPrecoBaseDiaria(BigDecimal precoBaseDiaria) { this.precoBaseDiaria = precoBaseDiaria; }
}
