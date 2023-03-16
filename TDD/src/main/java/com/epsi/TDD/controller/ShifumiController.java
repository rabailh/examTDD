package com.epsi.TDD.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/hello")
public class ShifumiController {

	public int win =0;
	public int lose =0;

	public int tie =0;
	public String score ="win : "+ win + " / lose : " + lose + " | tie : " + tie;

	@GetMapping("/game/play/{action}")
	public String start(@PathVariable("action") String action) {
		String status = "perdu";
		String[] choixPossibles = {"pierre", "feuille", "ciseau"};
		Random random = new Random();
		String choixOrdinateur = choixPossibles[random.nextInt(choixPossibles.length)];

		if( action.equals(choixOrdinateur)){
			status = "fait match nul";
			tie++;
		} else if ((action.equals("pierre") && choixOrdinateur.equals("ciseau")) ||
				(action.equals("feuille") && choixOrdinateur.equals("pierre")) ||
				(action.equals("ciseau") && choixOrdinateur.equals("feuille")))
			{
			status = "gagné";
			win++;
		} else {
			lose ++;
		}

		return "Vous avez joué "+ action + ". l'ordinateur à joué "+ action +". Vous avez "+ status +".";
	}

	@PostMapping("/game/restart")
	public String restart() {
		win =0;
		lose =0;
		tie = 0;
		score ="win : "+ win + " / lose : " + lose + " | tie : " + tie;
		return score;
	}

	@GetMapping("/game/score")
	public String getScore() {
		return score;
	}

	@PutMapping("/game/score/{win}/{lose}/{tie}")
	public String setScore(@PathVariable("win") int win,@PathVariable("lose") int lose,@PathVariable("tie") int tie) {
		win =win;
		lose =lose;
		tie = tie;
		score ="win : "+ win + " / lose : " + lose + " | tie : " + tie;
		return score;
	}

}
