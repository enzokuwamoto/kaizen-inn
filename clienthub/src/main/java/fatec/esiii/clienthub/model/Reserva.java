package fatec.esiii.clienthub.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva extends EntidadeDominio {

    @ManyToOne
    @JoinColumn(name = "hospede_id")
    private Hospede hospede;

    @ManyToOne
    @JoinColumn(name = "quarto_id")
    private Quarto quarto;

    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    
    private int qtdeAdultos;
    private int qtdeCriancas;
    
    @Enumerated(EnumType.STRING)
    private StatusReserva status;
    
    private BigDecimal valorTotal;

    public Hospede getHospede() { return hospede; }
    public void setHospede(Hospede hospede) { this.hospede = hospede; }

    public Quarto getQuarto() { return quarto; }
    public void setQuarto(Quarto quarto) { this.quarto = quarto; }

    public LocalDate getDataEntrada() { return dataEntrada; }
    public void setDataEntrada(LocalDate dataEntrada) { this.dataEntrada = dataEntrada; }

    public LocalDate getDataSaida() { return dataSaida; }
    public void setDataSaida(LocalDate dataSaida) { this.dataSaida = dataSaida; }

    public int getQtdeAdultos() { return qtdeAdultos; }
    public void setQtdeAdultos(int qtdeAdultos) { this.qtdeAdultos = qtdeAdultos; }

    public int getQtdeCriancas() { return qtdeCriancas; }
    public void setQtdeCriancas(int qtdeCriancas) { this.qtdeCriancas = qtdeCriancas; }

    public StatusReserva getStatus() { return status; }
    public void setStatus(StatusReserva status) { this.status = status; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }
}
