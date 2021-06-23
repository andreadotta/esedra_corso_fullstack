package it.esedra.corso.esercitazioneB.matteocirri;

public class CapacitaSuperata extends Exception{
	
		public CapacitaSuperata() {
			super("Problema con la capacita");
		}
		
		@Override
		public String toString() {
			return getMessage() + ": capacit� superata";
		}
		
		public CapacitaSuperata(Automobile automobile) {
			super("Capacit� superata per " + automobile.getTarga());
		}
}
