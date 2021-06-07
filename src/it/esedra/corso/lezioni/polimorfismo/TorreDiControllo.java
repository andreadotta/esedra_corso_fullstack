package it.esedra.corso.lezioni.polimorfismo;


public class TorreDiControllo {

  public void autorizzaAtterraggio(Volante v) {
    v.atterra();
  }

  public void autorizzaDecollo(Volante v) {
    v.decolla();
  }

}
