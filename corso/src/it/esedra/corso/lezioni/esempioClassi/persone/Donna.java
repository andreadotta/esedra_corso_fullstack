package it.esedra.corso.lezioni.esempioClassi.persone;


public class Donna {

  private Persona persona;

  public Donna(String nome, String cognome) {

    persona = new Persona();

//    persona = new Persona();

//    persona.setNome(nome); //chiamo il metodo
//    persona.cognome = cognome; //assegno un valore
    persona.saluta();
  }

}
