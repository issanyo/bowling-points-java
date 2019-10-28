package bowling;

public class Game {

    public static final int ROLLS = 20;
    public static final int PINS = 10;

    private GameStatus gameStatus;
    private boolean gameFinished;

    public Game(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.gameFinished = false;
    }

    public void roll(final int points) {
        gameStatus.addRoll(points);

        if (isLastTurn() && gameStatus.isStrike()) {
            gameStatus.setBonusRolls(2);
        }
        if (isLastTurn() && gameStatus.isSpare()) {
            gameStatus.setBonusRolls(1);
        }
    }

    public void nextRound() {
        if (!isGameFinished()) {
            gameStatus.nextRound();

            if (rollsFinished()) {
                gameFinished = true;
            }
        }
    }

    public boolean hasMoreRolls() {
        if (rollsFinished()) {
            return false;
        }
        return gameStatus.getCurrentTurnPoints() < PINS || gameStatus.getRolls() > ROLLS;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    private boolean isLastTurn() {
        return gameStatus.getRolls() >= ROLLS - 2;
    }

    private boolean rollsFinished() {
        return gameStatus.getRolls() >= gameStatus.getBonusRolls() + ROLLS;
    }
}
