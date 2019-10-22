package com.company;

/***
 * Author: Mohlatlego Nakeng
 * Instructions: Build the the puzzle that start with one letter and end with another
 * - Successive entries in the chain must all be real words
 * - And Each word must differ from the previous word with just one letter
 * The words should have the same length
 */

import java.io.*;
import java.util.*;

public class Word_main {
    private final static Character[] letters = { 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z' };

    public static final ArrayList<Character> alLetters = new ArrayList<Character>(
            Arrays.asList(Word_main.letters));



    public static void main(String[] args) throws IOException {
	// write your code here
       // Creating input stream and dictionary scanner
        Scanner scanner = new Scanner(System.in);
        final Word_main wc = new Word_main();
        // words Dictionary from Codekata link :(http://disq.us/url?url=http%3A%2F%2Fcodekata.com%2Fdata%2Fwordlist.txt%3AYN0OWHwgHxUjHyRWDt4MfCYMnrU&cuid=2763579)
        final String file = "words.txt";

      
        System.out.print("You may enter a start name:");
        String start = scanner.next();
        System.out.print("Now Enter another name with the same length as the start name:");
        String end = scanner.next();

  // setting up the length of both words!
        if (start.length() != end.length()) {
            System.out.println("length of both the words should be same");
        }
        //Words has to be same length for FASTER CHAIN PROCESS.
        wc.loadDictionary(file, start.length());
        wc.compute(start, end, 0);

        }
        //READING THE DICTIONARY
    private final ArrayList<String> wordChain = new ArrayList<String>();
    private final Set<String> dictionary = new HashSet<String>();

     //Start processing for the given starting & ending words
     private void compute(final String word, final String end, int level) {

         System.out.println("Chain =" + " "+ (++level ));
         List<String> alnew = this.getNext(word, end);
         while (true) {
             if (this.wordChain.size() > 0) {
                 break;
             }
             System.out.println("Chain =" + " " + (++level ) );
             final List<String> al = new ArrayList<String>(alnew);
             alnew = new ArrayList<String>();

             for (final String newword : al) {
                 // System.out.println("[" + (level) + "]" + newword);
                 alnew.addAll(this.getNext(newword, end));
             }
             // Limiting the Chain levels
             // Break the chain if reached 5th level
             if (level == 5) {
                 break;
             }
         }

         System.out.println("Shortest Word Chain are:");

         for (final String wc : this.wordChain) {
             System.out.println(wc);
         }

     }
    private String getlastword(final String wholeword) {
        final String[] tokens = wholeword.split("_");
        return tokens[tokens.length - 1];

    }

    private List<String> getNext(final String wholeword, final String end) {

        final String word = this.getlastword(wholeword);
        final ArrayList<String> al = new ArrayList<String>();

        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < Word_main.alLetters.size(); j++) {
                final char[] newString = word.toCharArray();
                newString[i] = Word_main.alLetters.get(j);
                final String newword = String.valueOf(newString);
                if (newword.equals(end)) {
                    this.wordChain.add(wholeword + "_" + newword);
                } else if (!newword.equals(word)
                        && !wholeword.startsWith(newword + "_")
                        && !wholeword.contains("_" + newword + "_")
                        && this.dictionary.contains(newword)) {
                    al.add(wholeword + "_" + newword);
                }
            }
        }
        return al;

    }
    private void loadDictionary(final String file, final int length) {

        BufferedReader X = null;
        try {
            X = new BufferedReader(new FileReader(new File(file)));
            String currentline;
            while ((currentline = X.readLine()) != null) {
                if (currentline.length() == length) {
                    this.dictionary.add(currentline.toLowerCase());
                }
            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } finally {
            if (X != null) {
                try {
                    X.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


