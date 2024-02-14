package assig3_2;

import java.util.concurrent.ThreadLocalRandom;

public class GamePlay {
	// Indicates whether the coin is available for flipping.
	private boolean coinAvailable = false;

	// Counts the number of rounds that have been played.
	private int roundsCounter = 1;

	// Makes the coin available or unavailable for flipping.
	public synchronized void makeCoinAvail(boolean val) {
		coinAvailable = val;
		// If the coin is now available, notify a waiting thread.
		if (coinAvailable) {
			notifyAll(); // Could consider lock.notifyAll() if multiple threads need to be woken up.
		}	
	}

	// Flips the coin if it's available. Waits if the coin is not available.
	public synchronized boolean flipCoin() {
		// Wait until the coin is available.
		while (!coinAvailable) {
			System.out.println(Thread.currentThread().getName() + " is waiting for coin");
			try {
				wait();
			} catch (InterruptedException e) {
				// Proper handling of InterruptedException by re-interrupting the thread.
				Thread.currentThread().interrupt();
			}
		}
		// Mark the coin as unavailable to ensure only one thread can flip it at a time.
		coinAvailable = false;

		// Randomly determine the outcome of the flip, 0 for tails and 1 for heads.
		int flipResult = ThreadLocalRandom.current().nextInt(2);
		roundsCounter++; // Increment the round counter with each flip.

		// Print the flipping result for debugging.
		System.out.println(Thread.currentThread().getName() + " is flipping coin. Result: "
				+ (flipResult == 1 ? "Heads" : "Tails"));

		// Return true for heads and false for tails.
		return flipResult == 1 ? true : false;
	}

	// Returns the number of rounds that have been played so far.
	public int getNumOfRounds() {
		return roundsCounter;
	}
}
