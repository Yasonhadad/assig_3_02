package assig3_2;

public class Judge extends Thread {
	private GamePlay gameplay;

	public Judge(GamePlay gameplay) {
		this.gameplay = gameplay;
	}

	public void run() {
		while (!Thread.interrupted()) {
			gameplay.makeCoinAvail(true);
			try {
				Thread.sleep(500); // Coin is available for half a second
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

			gameplay.makeCoinAvail(false);
			try {
				Thread.sleep(1000); // Coin is not available for 1 second
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}
	}
}
