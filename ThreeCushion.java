package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * Class that models the game of three-cushion billiards.
 * 
 * @author Issmale Bekri
 */
public class ThreeCushion {

	/**
	 * variable for the inning
	 */
	private int inning;
	/**
	 * current Ball
	 */
	private BallType givenBall;
	/**
	 * ' current player
	 */
	private PlayerPosition shotPlayer;
	/**
	 * boolean to keep track of the break shot
	 */
	private boolean breakShot;
	/**
	 * boolean to keep track if the shot has started
	 */
	private boolean shotStarted;
	/**
	 * boolean to keep track if the inning has started
	 */
	private boolean inningStarted;
	/**
	 * boolean to keep track if the player has committed a foul
	 */
	private boolean foulCommitted;
	/**
	 * boolean to keep track if cueStickStrike was in play
	 */
	private boolean cueStickStrike;
	/**
	 * boolean to keep track if the game has ended or not
	 */
	private boolean gameOver;
	/**
	 * boolean to keep track if the lagWinner was called or not
	 */
	private boolean lagWinner;
	/**
	 * boolean to keep track if end shot was called or not
	 */
	private boolean endShot;
	/**
	 * boolean to see if the shot is valid or not
	 */
	private boolean validShot;
	/**
	 * integer to count the player A's score
	 */
	private int playerAScore;
	/**
	 * integer to count the player B's score
	 */
	private int playerBScore;
	/**
	 * integer to count the ball impact on the cushion
	 */
	private int cueBallImpactCushion;
	/**
	 * used to keep track of which ball has been stricken in BallStrike
	 */
	private BallType strickenBall;
	/**
	 * boolean to keep track if red ball has been stricken or not
	 */
	private boolean ballRedStrike;
	/**
	 * boolean to keep track if yellow ball has been stricken or not
	 */
	private boolean ballYellowStrike;
	/**
	 * boolean to keep track if the shot was a bank shot or not
	 */
	private boolean bankShot;
	/**
	 * boolean to keep track if red ball has been stricken or not
	 */
	private boolean ballWhiteStrike;
	/**
	 * integer to keep track of the winning points
	 */
	private int winningPoints;
	/**
	 * variable to keep track of the other player while the current player(shot
	 * player) is playing
	 * 
	 */
	private PlayerPosition otherPlayer;
	/**
	 * variable to keep track of the other ball while the cueBall (given Ball) is in
	 * play
	 */
	private BallType otherBall;
	/**
	 * the cueBall assigned at first in lagWinnerChooses
	 */
	private BallType assignedBall;
	/**
	 * the player who wins the lag is saved in this variable
	 */
	private PlayerPosition winnerOfLag;
	/**
	 * boolean to see what the initial self break is so we can change it after
	 */
	private boolean initialSelfBreak;
	/**
	 * integer to see how many times cueStickStrike is called before endSht
	 */
	private int cueStickStrikeVariable;
	/**
	 * boolean to see if the shot ends on the cushion impact and not stricken any
	 * balls.
	 */
	private boolean endedOnCushion;

	// TODO: EVERTHING ELSE GOES HERE
	// Note that this code will not compile until you have put in stubs for all
	// the required methods.

	// The method below is provided for you and you should not modify it.
	// The compile errors will go away after you have written stubs for the
	// rest of the API methods.

	/**
	 * Creates a new game of three-cushion billiards with a given lag winner and the
	 * predetermined number of points required to win the game.
	 * 
	 * @param lagWinner   is the winner of the players of who is going to start the
	 *                    game
	 * @param pointsToWin maximum points for PLAYER_A or PLAYER_B to score and to
	 *                    win the game.
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {

		inning = 1;
		foulCommitted = false;
		shotStarted = false;
		gameOver = false;
		this.lagWinner = false;
		endShot = false;
		validShot = true;
		cueBallImpactCushion = 0;
		bankShot = false;
		winningPoints = pointsToWin;
		winnerOfLag = lagWinner;
		cueStickStrikeVariable = 0;
		endedOnCushion = false;

		if (winnerOfLag == PLAYER_A) {

			otherPlayer = PLAYER_B;
		}

		else {

			otherPlayer = PLAYER_A;
		}
	}

	/**
	 * 
	 * @return Gets the number of points scored by Player B.
	 */
	public int getPlayerBScore() {

		return playerBScore;
	}

	/**
	 * 
	 * @return Gets the number of points scored by Player A.
	 */
	public int getPlayerAScore() {

		return playerAScore;
	}

	/**
	 * 
	 * @return Returns true if the game is over
	 */
	public boolean isGameOver() {

		return gameOver;
	}

	/**
	 * 
	 * @return Returns true if the shooting player has taken their first shot of the
	 *         inning.
	 */
	public boolean isInningStarted() {

		return inningStarted;
	}

	/**
	 * 
	 * @return Gets the current player.
	 */
	public PlayerPosition getInningPlayer() {

		return shotPlayer;
	}

	/**
	 * 
	 * @param selfBreak the player that won the lagWinner will decide to break or
	 *                  not
	 * @param cueBall   gives the cueBall that is in play
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {

		givenBall = cueBall;
		breakShot = selfBreak;
		lagWinner = true;
		assignedBall = cueBall;

		if (selfBreak == true) {

			shotPlayer = winnerOfLag;
			givenBall = assignedBall;

			if (assignedBall == WHITE) {
				otherBall = YELLOW;
			}

			else {
				otherBall = WHITE;

			}

		}

		else if (selfBreak == false) {

			shotPlayer = otherPlayer;

			if (assignedBall == WHITE) {
				otherBall = YELLOW;
			}

			else {
				otherBall = WHITE;
			}

			givenBall = otherBall;
		}

		if (selfBreak == true) {

			initialSelfBreak = true;
			shotPlayer = winnerOfLag;
		}

		else {

			initialSelfBreak = false;
		}

	}

	/**
	 * 
	 * @return Gets the cue ball of the current player.
	 */
	public BallType getCueBall() {

		return givenBall;
	}

	/**
	 * 
	 * @return Returns true if and only if this is the break shot
	 */
	public boolean isBreakShot() {

		return breakShot;

	}

	/**
	 * 
	 * @param ball Indicates the cue stick has struck the given ball.
	 */
	public void cueStickStrike(BallType ball) {

		cueStickStrikeVariable += 1;
		if (gameOver == false) {

			if (shotStarted == true) {

				foulCommitted = true;
				foul();
			}

			else {

				if (ball == RED) {

					foulCommitted = true;
					foul();

				} else if (ball != givenBall) {

					foulCommitted = true;
					foul();

				}

				else {

					shotStarted = true;
					inningStarted = true;
					cueStickStrike = true;

				}
			}
		}
	}

	/**
	 * 
	 * @return Returns true if a shot has been taken
	 */
	public boolean isShotStarted() {

		return shotStarted;
	}

	/**
	 * Indicates that all balls have stopped motion.
	 */
	public void endShot() {
		endShot = true;

		if ((gameOver == false) && (cueStickStrike == true) && (shotStarted == true)) {

			shotStarted = false;

			if (initialSelfBreak == true) {

				breakShot = false;
			}

			else {

				breakShot = true;
			}

			if (cueBallImpactCushion >= 3 && givenBall == WHITE) {

				if (ballRedStrike == true && ballYellowStrike == true) {

					validShot = true;
					foulCommitted = false;
				}

			}

			else if (cueBallImpactCushion >= 3 && givenBall == YELLOW) {

				if (ballRedStrike == true && ballWhiteStrike == true) {

					validShot = true;
					foulCommitted = false;
				}
			}

			else {

				validShot = false;
				if (foulCommitted == false) {

					inning += 1;
				}
			}
		}

		if (bankShot == true) {
			if ((ballRedStrike == true) && (ballYellowStrike == false && ballWhiteStrike == false)) {

				bankShot = false;
			}

			else {
				bankShot = true;
			}
		}

		else {

		}

		if (cueBallImpactCushion >= 3 && foulCommitted == false) {
			if ((shotPlayer == winnerOfLag) && (givenBall == assignedBall)) {
				if ((shotPlayer == PLAYER_A) && (givenBall == WHITE)) {
					if ((ballRedStrike == true) && (ballYellowStrike == true)) {
						if (endedOnCushion == false) {
							playerAScore += 1;
						}
					}

				} else if ((shotPlayer == PLAYER_A) && (givenBall == YELLOW)) {

					if ((ballRedStrike == true) && (ballWhiteStrike == true)) {

						if (endedOnCushion == false) {
							playerAScore += 1;
						}
					}
				}

				else if ((shotPlayer == PLAYER_B) && (givenBall == WHITE)) {

					if ((ballRedStrike == true) && (ballYellowStrike == true)) {

						if (endedOnCushion == false) {
							playerBScore += 1;
						}
					}
				}

				else if ((shotPlayer == PLAYER_B) && (givenBall == YELLOW)) {

					if ((ballRedStrike == true) && (ballWhiteStrike == true)) {

						if (endedOnCushion == false) {
							playerBScore += 1;
						}
					}
				}

			} else if ((shotPlayer != winnerOfLag) && (givenBall != assignedBall)) {

				if ((shotPlayer == PLAYER_A) && (givenBall == WHITE)) {

					if ((ballRedStrike == true) && (ballYellowStrike == true)) {

						if (endedOnCushion == false) {
							playerAScore += 1;
						}
					}

				} else if ((shotPlayer == PLAYER_A) && (givenBall == YELLOW)) {

					if ((ballRedStrike == true) && (ballWhiteStrike == true)) {

						if (endedOnCushion == false) {
							playerAScore += 1;
						}
					}
				}

				else if ((shotPlayer == PLAYER_B) && (givenBall == WHITE)) {

					if ((ballRedStrike == true) && (ballYellowStrike == true)) {

						if (endedOnCushion == false) {
							playerBScore += 1;
						}
					}
				}

				else if ((shotPlayer == PLAYER_B) && (givenBall == YELLOW)) {

					if ((ballRedStrike == true) && (ballWhiteStrike == true)) {

						if (endedOnCushion == false) {
							playerBScore += 1;
						}
					}
				}

			}

		}

		if (validShot != true && endShot == true) {

			if ((shotPlayer == winnerOfLag) && (givenBall == assignedBall)) {

				if (winnerOfLag == PLAYER_A) {

					shotPlayer = PLAYER_B;
				} else {

					shotPlayer = PLAYER_A;
				}
				if (assignedBall == WHITE) {

					givenBall = YELLOW;
					otherBall = WHITE;
				} else {

					givenBall = WHITE;
					otherBall = YELLOW;
				}

				shotStarted = false;
				inningStarted = false;
			}
		}

		if (lagWinner == false) {

			inning = 1;
		}

		if (playerBScore >= winningPoints) {

			inningStarted = false;
			shotStarted = false;
			gameOver = true;
		}

		else if (playerAScore >= winningPoints) {
			inningStarted = false;
			shotStarted = false;
			gameOver = true;

		}

		else {
			gameOver = false;
		}

		if (cueStickStrikeVariable != 1) {

			if (shotPlayer == PLAYER_A) {

				shotPlayer = PLAYER_B;
			}

			else {
				shotPlayer = PLAYER_A;
			}

			if (givenBall == WHITE) {

				givenBall = YELLOW;
			}

			else {

				givenBall = WHITE;
			}
		}

		cueBallImpactCushion = 0;
		cueStickStrikeVariable = 0;
		ballRedStrike = false;
		ballYellowStrike = false;
		ballWhiteStrike = false;
		foulCommitted = false;
		endedOnCushion = false;

	}

	/**
	 * A foul immediately ends the player's inning, even if the current shot has not
	 * yet ended.
	 */
	public void foul() {

		if ((gameOver == false) && (breakShot == false || breakShot == true)) {

			foulCommitted = true;
			validShot = false;
			inningStarted = false;

			if (shotPlayer == PLAYER_A) {

				shotPlayer = PLAYER_B;
				otherPlayer = PLAYER_A;
			}

			else {
				shotPlayer = PLAYER_A;
				otherPlayer = PLAYER_B;
			}

			if (validShot == false && lagWinner == true && foulCommitted == true) {

				inning += 1;
			}

			else {

			}

			if (givenBall == WHITE) {

				givenBall = YELLOW;
				otherBall = WHITE;
			}

			else {

				givenBall = WHITE;
				otherBall = YELLOW;
			}

		}

	}

	/**
	 * 
	 * @param ball Indicates the player's cue ball has struck the given ball.
	 */
	public void cueBallStrike(BallType ball) {

		strickenBall = ball;
		endShot = false;

		if ((shotPlayer == winnerOfLag) && (givenBall == assignedBall)) {

			if (breakShot == true && inning == 1) {

				if (ball != RED || cueBallImpactCushion != 0) {

					foulCommitted = true;
					foul();
				}

				else {

					breakShot = false;
					ballRedStrike = true;
				}

			}

			if (givenBall == WHITE && (strickenBall == RED || strickenBall == YELLOW || strickenBall == WHITE)) {

				inningStarted = true;
				if (strickenBall == RED) {

					ballRedStrike = true;
				}

				else if (strickenBall == YELLOW) {

					ballYellowStrike = true;
				}

				else if (strickenBall == WHITE) {

					ballWhiteStrike = true;
				}
			}

			else if (givenBall == YELLOW && (strickenBall == RED || strickenBall == YELLOW || strickenBall == WHITE)) {

				inningStarted = true;

				if (strickenBall == RED) {

					ballRedStrike = true;
				}

				else if (strickenBall == YELLOW) {

					ballYellowStrike = true;
				}

				else if (strickenBall == WHITE) {

					ballWhiteStrike = true;
				}
			}

		}

		else if ((shotPlayer != winnerOfLag) && (givenBall != assignedBall)) {

			if (givenBall == WHITE && (strickenBall == RED || strickenBall == YELLOW || strickenBall == WHITE)) {

				inningStarted = true;
				if (strickenBall == RED) {

					ballRedStrike = true;
				}

				else if (strickenBall == YELLOW) {

					ballYellowStrike = true;
				}

				else if (strickenBall == WHITE) {

					ballWhiteStrike = true;
				}
			}

			else if (givenBall == YELLOW && (strickenBall == RED || strickenBall == YELLOW || strickenBall == WHITE)) {

				inningStarted = true;
				if (strickenBall == RED) {

					ballRedStrike = true;
				}

				else if (strickenBall == YELLOW) {

					ballYellowStrike = true;
				}

				else if (strickenBall == WHITE) {

					ballWhiteStrike = true;
				}
			}

		}

	}

	/**
	 * Indicates the given ball has impacted the given cushion.
	 */
	public void cueBallImpactCushion() {

		cueBallImpactCushion += 1;

		if (givenBall == WHITE) {

			if ((cueBallImpactCushion >= 3) && (ballRedStrike == false) && (ballYellowStrike == false)) {

				bankShot = true;

			} else if ((cueBallImpactCushion == 1 || cueBallImpactCushion == 2)
					&& (ballRedStrike == true || ballYellowStrike == true)) {

				bankShot = false;
			}

		}

		else if (givenBall == YELLOW) {

			if ((cueBallImpactCushion >= 3) && (ballRedStrike == false) && (ballWhiteStrike == false)) {

				bankShot = true;

			}

			else if ((cueBallImpactCushion == 1 || cueBallImpactCushion == 2)
					&& (ballRedStrike == true || ballWhiteStrike == true)) {

				bankShot = false;
			}

			if (ballRedStrike == true && ballYellowStrike == true && givenBall == WHITE) {

				endedOnCushion = true;
			}

			else if (ballRedStrike == true && ballWhiteStrike == true && givenBall == YELLOW) {

				endedOnCushion = true;
			}

		}

	}

	/**
	 * 
	 * @return Returns true if and only if the most recently completed shot was a
	 *         bank shot.
	 */
	public boolean isBankShot() {

		return bankShot;
	}

	/**
	 * 
	 * @return Gets the inning number.
	 */
	public int getInning() {

		return inning;
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * . <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}

}
