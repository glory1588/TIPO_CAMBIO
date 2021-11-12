package com.api.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TipoCambioOperacion {

	@NotNull(message = "Debes especificar monto")
	@Min(value = 0, message = "El monto mínimo es 0")
	private Double monto;
	
	@NotNull(message = "Debes especificar moneda origen")
	@Pattern(regexp = "PEN|USD", flags = Pattern.Flag.CASE_INSENSITIVE,message = "Debes especificar solo moneda [PEN,USD]")
	private String monedaorigen;
	
	@NotNull(message = "Debes especificar moneda destino")
	@Pattern(regexp = "PEN|USD", flags = Pattern.Flag.CASE_INSENSITIVE,message = "Debes especificar solo moneda [PEN,USD]")
	private String monedadestino;
	
	

	public TipoCambioRespuesta calcularTipoCambio (TipoCambioOperacion tipoCambioOperacion, Double tipoCambio) throws NoSuchMethodException, SecurityException
	{
		
		
		TipoCambioRespuesta tipoCambioRespuesta= new TipoCambioRespuesta();
		Double montoConTipoCambio=0.000;
		String monedaO=tipoCambioOperacion.getMonedaorigen().toUpperCase();
		String monedaD=tipoCambioOperacion.getMonedadestino().toUpperCase();
		
		if(monedaO.equals("PEN") && monedaD.equals("USD") )
		{
			montoConTipoCambio=monto/tipoCambio;
			
		}else if(monedaO.equals("USD") && monedaD.toUpperCase().equals("PEN") )
		{
			
			montoConTipoCambio=monto* tipoCambio;
		}
		
		tipoCambioRespuesta.setMonto(monto);
		tipoCambioRespuesta.setMontocontipodecambio( Math.round(montoConTipoCambio*100.0)/100.0);
		
		tipoCambioRespuesta.setMonedadestino(monedaO);
		tipoCambioRespuesta.setMonedaorigen(monedaD);
		tipoCambioRespuesta.setTipodecambio(tipoCambio);
	
		return tipoCambioRespuesta;
		
	}
	
	
	
	
	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getMonedaorigen() {
		return monedaorigen;
	}

	public void setMonedaorigen(String monedaorigen) {
		this.monedaorigen = monedaorigen;
	}

	public String getMonedadestino() {
		return monedadestino;
	}

	public void setMonedadestino(String monedadestino) {
		this.monedadestino = monedadestino;
	}
	

}
