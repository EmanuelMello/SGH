package web.sistemagerenciamentohotel.model.filter;

import java.time.LocalDate;
import java.util.Objects;

import web.sistemagerenciamentohotel.model.StatusPago;

public class CheckInFilter {
	private Long codigo;
	private Long codigoQuarto;
	private Long codigoHospede;
	private String dataCheckIn;
	private String dataCheckOut;
	private StatusPago statusPago;
	

	public StatusPago getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(StatusPago statusPago) {
		this.statusPago = statusPago;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigoQuarto() {
		return codigoQuarto;
	}

	public void setCodigoQuarto(Long codigoQuarto) {
		this.codigoQuarto = codigoQuarto;
	}

	public Long getCodigoHospede() {
		return codigoHospede;
	}

	public void setCodigoHospede(Long codigoHospede) {
		this.codigoHospede = codigoHospede;
	}


	public String getDataCheckIn() {
		return dataCheckIn;
	}

	public void setDataCheckIn(String dataCheckIn) {
		this.dataCheckIn = dataCheckIn;
	}

	public String getDataCheckOut() {
		return dataCheckOut;
	}

	public void setDataCheckOut(String dataCheckOut) {
		this.dataCheckOut = dataCheckOut;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo, codigoHospede, codigoQuarto, dataCheckIn, dataCheckOut, statusPago);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckInFilter other = (CheckInFilter) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(codigoHospede, other.codigoHospede)
				&& Objects.equals(codigoQuarto, other.codigoQuarto) && Objects.equals(dataCheckIn, other.dataCheckIn)
				&& Objects.equals(dataCheckOut, other.dataCheckOut) && statusPago == other.statusPago;
	}

	@Override
	public String toString() {
		return "CheckInFilter [codigo=" + codigo + ", codigoQuarto=" + codigoQuarto + ", codigoHospede=" + codigoHospede
				+ ", dataCheckIn=" + dataCheckIn + ", dataCheckOut=" + dataCheckOut + ", statusPago=" + statusPago
				+ "]";
	}


}
