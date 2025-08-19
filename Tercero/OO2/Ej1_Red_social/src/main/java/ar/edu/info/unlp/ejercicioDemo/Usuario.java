package ar.edu.info.unlp.ejercicioDemo;

import java.util.ArrayList;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Usuario {
  private String screenName;
  private ArrayList<Tweet> tweets;

  public Usuario(String screenName) {
    tweets = new ArrayList<Tweet>();
    this.screenName = screenName;
  }

  public void twittear(String texto) {
    if (Tweet.verificarLongitud(texto))
      tweets.add(new TweetBase(texto, screenName));
  }

  public void retwittear(Tweet tweetBase) {
    tweets.add(new ReTweet(tweetBase));
  }

  public void borrarTweets() {
    tweets.clear();
  }

  public String getScreenName() {
    return this.screenName;
  }

  public ArrayList<Tweet> getTweets() {
    return this.tweets;
  }

  public void borrarReTweetsDe(String screenName) {
    tweets.removeAll(
        this.tweets.stream().filter(tweet -> tweet.getUsuario().equals(screenName)).collect(Collectors.toList()));
  }

  public String toString() {
    return this.screenName;
  }
}
