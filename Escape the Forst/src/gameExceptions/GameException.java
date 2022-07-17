/**
 * 
 */
package gameExceptions;

/**
 * @author chris
 * @version 1.0, ITEC 3860, 5-28-22
 * 
 * This class represents a custom exception for the game
 *
 */
public class GameException extends Exception{
	public GameException() {}
	public GameException (String message) {super(message);}
}
