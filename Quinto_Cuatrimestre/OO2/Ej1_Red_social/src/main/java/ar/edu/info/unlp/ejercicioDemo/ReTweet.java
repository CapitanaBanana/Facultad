package ar.edu.info.unlp.ejercicioDemo;

public class ReTweet extends Tweet {
  public Tweet tweetOrigen;

  public ReTweet(Tweet tweetOrigen) {
    super(tweetOrigen.getUsuario());
    this.tweetOrigen = tweetOrigen;
  }

  public String toString() {
    return ("reTweet del tweet que dice: " + tweetOrigen.toString());
  }
}
