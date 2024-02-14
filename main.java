package assig3_2;

public class main {

	public static void main(String[] args) {

		GamePlay game = new GamePlay();
		Gamer gamer1 = new Gamer(game);
		Gamer gamer2 = new Gamer(game);
		Judge judge = new Judge(game);
		gamer1.setName("gemar 1");
		gamer2.setName("gemar 2");


        judge.start(); // Start the judge thread
        gamer1.start(); // Start the first gamer thread
        gamer2.start(); // Start the second gamer thread

        try {
			gamer1.join(); // Wait for the first gamer to finish
	        gamer2.join(); // Wait for the second gamer to finish

		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
        judge.interrupt(); // Interrupt the judge as the game is over

        int score1 = gamer1.getScore();
        int score2 = gamer2.getScore();

        if (score1 > score2) {
            System.out.println("Gamer 1 wins with score: " + score1);
        } else if (score2 > score1) {
            System.out.println("Gamer 2 wins with score: " + score2);
        } else {
            System.out.println("It's a tie!");
        }
	}

}
