package com.riceplant.jokelib;

import java.util.Random;

public class JokeClass {

    // Jokes were found on https://bestlifeonline.com/dad-jokes-so-bad-theyre-actually-hilarious/

    private static String[] mJokes = {
            "Today, my son asked \"Can I have a book mark?\" and I burst into tears. 11 years old and he still doesn't know my name is Brian.",
            "What rock group has four men that don't sing? Mount Rushmore.",
            "When I was a kid, my mother told me I could be anyone I wanted to be. Turns out, identity theft is a crime.",
            "A guy goes to his doctor because he can see into the future. The doctor asks him, \"How long have you suffered from that condition?\" The guy tells him, \"Since next Monday.\"",
            "What do sprinters eat before a race? Nothing, they fast!",
    };

    public static String getJoke() {
        return mJokes[new Random().nextInt(mJokes.length)];
    }
}
