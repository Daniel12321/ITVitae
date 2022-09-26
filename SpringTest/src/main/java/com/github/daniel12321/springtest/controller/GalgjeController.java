package com.github.daniel12321.springtest.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/galgje")
public class GalgjeController {

    private static final int MAX_GUESSES = 5;

    private String word;
    private List<Character> letters;
    private int guessesLeft;

    @GetMapping
    public String getStatus() {
        if (!this.letters.contains('_'))
            return "Congrats, you got the word!";

        if (this.guessesLeft <= 0)
            return "Sorry, you are out of guesses! The word was '" + this.word + "' Better luck next time.";

        StringBuilder word = new StringBuilder();
        for (Character c : this.letters)
            word.append(c);

        return word + "\nYou have " + this.guessesLeft + " guesses left!";
    }

    @PostMapping
    public String guess(@RequestParam(value = "guess") String guess) {
        if (guess.equalsIgnoreCase(this.word)) {
            for (int i = 0; i < this.word.length(); i++)
                this.letters.set(i, this.word.charAt(i));
        }

        char c = guess.charAt(0);
        boolean correct = false;

        for (int i = 0; i < this.word.length(); i++) {
            if (this.word.charAt(i) == c) {
                this.letters.set(i, c);
                correct = true;
            }
        }

        if (!correct)
            this.guessesLeft--;

        return "Je hebt '" + c + "' geraden. Dit was " + (correct ? "" : "niet ") + "correct!";
    }

    @PostMapping("/start")
    public String setWord(@RequestParam(value="word") String word) {
        System.out.println("New word: " + word);

        this.word = word;
        this.letters = new ArrayList<>(word.length());
        this.guessesLeft = MAX_GUESSES;

        for (int i = 0; i < word.length(); i++)
            letters.add('_');

        return "Starting a new game with the word '" + word + "'!";
    }
}
