package rps.bll.game;

import java.util.Random;

/**
 * The various move options in the game
 *
 * @author smsj
 */
public enum Move {
    Rock,
    Paper,
    Scissor,;

    private static final Random PRNG = new Random();

    public static Move randomMove()  {
        Move[] move = values();
        return move[PRNG.nextInt(move.length)];
    }
}
