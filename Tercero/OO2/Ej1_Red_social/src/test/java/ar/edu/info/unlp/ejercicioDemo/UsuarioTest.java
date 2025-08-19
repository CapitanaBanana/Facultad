package ar.edu.info.unlp.ejercicioDemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Aca escribiremos los test de unidad para cada clase
 * 
 */
public class UsuarioTest {

	Usuario usr1, usr2;

	Tweet tweet1, tweet2;

	@BeforeEach
	void setUp() throws Exception {
		usr1 = new Usuario("NadieNuncaNada");
		usr2 = new Usuario("CapitanaBanana");
		tweet1 = new TweetBase("Este es un tweet", "CapitanaBanana");
		tweet2 = new TweetBase("Este es otro tweet", "NadieNuncaNada");
	}

	@Test
	public void testTwittear() {
		usr1.twittear("Este es un tweet");
		usr2.twittear("");
		assertEquals("[tweet que dice Este es un tweet]", usr1.getTweets().toString());
		assertEquals("[]", usr2.getTweets().toString());
	}

	@Test
	public void testReTwittear() {
		usr1.twittear("Este es un tweet");
		usr1.retwittear(tweet1);
		assertEquals("[tweet que dice Este es un tweet, reTweet del tweet que dice: tweet que dice Este es un tweet]",
				usr1.getTweets().toString());
	}

	@Test
	public void testBorrarTweets() {
		usr1.twittear("Este es un tweet");
		usr1.retwittear(tweet1);
		assertEquals("[tweet que dice Este es un tweet, reTweet del tweet que dice: tweet que dice Este es un tweet]",
				usr1.getTweets().toString());
		usr1.borrarTweets();
		assertEquals("[]", usr1.getTweets().toString());
	}

	@Test
	public void testBorrarTweetsDe() {
		usr1.twittear("Este es un tweet");
		usr1.retwittear(tweet1);
		usr1.borrarReTweetsDe("CapitanaBanana");
		assertEquals("[tweet que dice Este es un tweet]", usr1.getTweets().toString());

	}
}
