package assig3_2;

public class Gamer extends Thread {
    // Counter for successful flips ("heads").
    private int goodFlipsCounter = 0;
    
    // Reference to the GamePlay object to interact with the game.
    private GamePlay gameplay;

    // Constructor that sets the GamePlay object.
    public Gamer(GamePlay gameplay) {
        this.gameplay = gameplay;
    }

    // The run method that's called when the thread starts.
    public void run() {
        // Starts the game playing process.
        play();
    }

    // Main game logic for the Gamer thread.
    public void play() {
        // Continues playing until the thread is interrupted or the maximum number of rounds is reached.
        while (!Thread.interrupted() && gameplay.getNumOfRounds() < 10) {
            // Attempts to flip the coin and increments the counter if the flip is successful (heads).
            if (gameplay.flipCoin()) {
                goodFlipsCounter++;
            }

            // Pauses the thread for 1 second between flips to simulate time taken for each turn.
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Returns the total number of successful flips ("heads") made by the gamer.
    public int getScore() {
        return goodFlipsCounter;
    }
}
