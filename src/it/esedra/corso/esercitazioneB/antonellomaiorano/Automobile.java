package it.esedra.corso.esercitazioneB.antonellomaiorano;

public class Automobile {
	private String targa;
	private Serbatoio serbatoio;
	
	
	private Automobile() {
		
	}
	
	public Automobile(String targa, int capacitąSerbatoio) {
		this.targa = targa;
		this.serbatoio = capacitąSerbatoio;
	}
	
	public void setCapacitą(String targa) {
		this.targa = targa;
	}
	public String getTarga() {
		return targa;
	}
	public void setSerbatoio(Serbatoio serbatoio) {
		this.serbatoio = serbatoio;
	}
	public Serbatoio getSerbatoio() {
		return serbatoio;
	}
}
