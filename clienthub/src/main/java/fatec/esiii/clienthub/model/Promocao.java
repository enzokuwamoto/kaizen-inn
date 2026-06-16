package fatec.esiii.clienthub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "promocoes")
public class Promocao extends EntidadeDominio {

    private String nome;
    private BigDecimal descontoPercentual;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String regraAplicacao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public BigDecimal getDescontoPercentual() { return descontoPercentual; }
    public void setDescontoPercentual(BigDecimal descontoPercentual) { this.descontoPercentual = descontoPercentual; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public String getRegraAplicacao() { return regraAplicacao; }
    public void setRegraAplicacao(String regraAplicacao) { this.regraAplicacao = regraAplicacao; }
}
