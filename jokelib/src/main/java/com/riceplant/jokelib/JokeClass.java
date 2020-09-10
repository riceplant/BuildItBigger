package com.riceplant.jokelib;

import java.util.Random;

public class JokeClass {

    private static String[] mJokes = {
            "There are 10 types of people in the world: those who understand binary, and those who don't.",
            "How many programmers does it take to change a light bulb?\nNone. It's a hardware problem.",
            "A SEO couple had twins. For the first time they were happy with duplicate content.",
            "Why is it that programmers always confuse Halloween with Christmas?\nBecause 31 OCT = 25 DEC.",
            "Why do they call it hyper text?\nToo much JAVA.",
            "Why was the JavaScript developer sad?\nBecause he didn't Node how to Express himself.",
            "In order to understand recursion you must first understand recursion.",
            "Why do Java developers wear glasses? Because they can't C#",
            "What do you call 8 hobbits?\nA hobbyte.",
            "Why did the developer go broke?\nBecause he used up all his cache.",
            "Why did the geek add body { padding-top: 1000px; } to his Facebook profile?\nHe wanted to keep a low profile.",
            "An SEO expert walks into a bar, bars, pub, tavern, public house, Irish pub, drinks, beer, alcohol.",
            "I would tell you a UDP joke, but you might not get it.",
            "8 bytes walk into a bar, the bartenders asks \"What will it be?\"\nOne of them says, \"Make us a double.\"",
            "Two bytes meet. The first byte asks, \"Are you ill?\"\nThe second byte replies, \"No, just feeling a bit off.\"",
            "These two strings walk into a bar and sit down. The bartender says, \"So what'll it be?\"\n" +
                    "The first string says, \"I think I'll have a beer quag fulk boorg jdk^CjfdLk jk3s d#f67howe%^U r89nvy~~owmc63^Dz x.xvcu\"\n" +
                    "\"Please excuse my friend,\" the second string says, \"He isn't null-terminated.\"",
            "\"Knock, knock. Who's there?\"\nvery long pause...\n\"Java.\"",
            "If you put a million monkeys on a million keyboards, one of them will eventually write a Java program. The rest of them will write Perl programs.",
            "There's a band called 1023MB. They haven't had any gigs yet.",
            "There are only two hard things in computer science: cache invalidation, naming things, and off-by-one errors."
    };

    public JokeClass() {
        // Empty constructor.
    }

    /**
     * Get a joke.
     *
     * @return a random joke
     */
    public static String getJoke() {
        return mJokes[new Random().nextInt(mJokes.length)];
    }
}
