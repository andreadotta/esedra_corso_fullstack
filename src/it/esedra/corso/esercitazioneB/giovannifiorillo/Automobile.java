package it.esedra.corso.esercitazioneB.giovannifiorillo;

public class Automobile extends Esercizio1 {
	public String targa ; 

	private Automobile(String targa, int capacitaSerbatoio) {
	
}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}
}