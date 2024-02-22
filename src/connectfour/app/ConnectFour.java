package connectfour.app;

import connectfour.model.ImplGame;
import connectfour.view.ConsoleGameView;
import connectfour.view.GameView;

public class ConnectFour {

	public static void main(String[] args) {

		// Cr�ation du jeu
		// On instancie une vue, � laquelle on fournit un mod�le (MVC)
		GameView gameView = new ConsoleGameView(new ImplGame());

		// Lancement du syst�me de jeu
		gameView.play();

		// Fin de l'application
		System.exit(1);

	}

}
