

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Scanner;
import java.util.ArrayList;


public class Spell {

    private static Hashtable<String, Boolean> Dictionary;
    private static ArrayList<String> toCheck;

    Spell() {try {
        // Load dictionary words from file into Hashtable
        File temp1 = new File("dictionary.txt");
        Scanner sc = new Scanner(temp1);
        Dictionary = new Hashtable<String, Boolean>();
        //this loop reads entire dictionary and inserts it into our hashtable
        //we remove whitespace convert to lowercase to make it easier to compare later on
        while (sc.hasNext()) {
            Dictionary.put(sc.nextLine().trim().toLowerCase(), true);
        }
        // Load words in fileToCheck.txt
        toCheck = new ArrayList<>();
        temp1 = new File("fileToCheck.txt");
        sc = new Scanner(temp1);
        while (sc.hasNext())
            toCheck.add(sc.nextLine().trim().toLowerCase());
    }
    catch(Exception e){
        System.out.println("File not found");
    }
    }
    Spell(String DictName, String CheckName){try {
        // Load dictionary words from file into Hashtable
        File temp1 = new File(DictName);
        Scanner sc = new Scanner(temp1);
        Dictionary = new Hashtable<String, Boolean>();
        //this loop reads entire dictionary and inserts it into our hashtable
        //we remove whitespace convert to lowercase to make it easier to compare later on
        while (sc.hasNext()) {
            Dictionary.put(sc.nextLine().trim().toLowerCase(), true);
        }
        // Load words in fileToCheck.txt
        toCheck = new ArrayList<>();
        temp1 = new File(CheckName);
        sc = new Scanner(temp1);
        while (sc.hasNext())
            toCheck.add(sc.nextLine().trim().toLowerCase());
    }
    catch(Exception e){
        System.out.println("File not found");
    }
    }


    public static void main(String[] args) throws FileNotFoundException {
        String DictFile = args[0];
        String CheckFile = args[1];
        // init an object of type Spell
        Spell spell = new Spell(DictFile,CheckFile);
        // add your code here
        for(int i = 0; i<toCheck.size(); i++){
            checkSpelling(toCheck.get(i));
        }
    }

    // this function check if the dictionary is loaded or not
    public static Hashtable<String, Boolean> getDictionary(){
        return Dictionary;
    }

    // This function takes a String word as an argument to check if the word exists in the dictionary.
    // If the word exists, it will print it with a message "Correct Spelling:" to the console.
    // Else it will call the suggestCorrections function to provide the correct word from the words given in the dictionary file.
    public static boolean checkSpelling(String word){
        word = word.toLowerCase();
        if(Dictionary.containsKey(word)) {   //this means our dictionary contained the word
            System.out.println(word+ ": Correct Spelling");
            return true;
        }
        else{    //this means the dictionary did not contain our wanted word
            suggestCorrections(word);
            return false;
        }
    }

    // This function takes a String input word as argument.
    // It starts by printing the message word: Incorrect Spelling to the console.
    // The function also uses four different methods (correctSpellingWithSubstitution,
    // correctSpellingWithOmission, correctSpellingWithInsertion, correctSpellingWithReversal)
    // to generate possible corrected spellings for the input word.
    // The function then returns the suggestions list which contains the possible corrected spellings.
    public static boolean suggestCorrections(String word) {
        word = word.toLowerCase();
        System.out.println(word+ ": Incorrect Spelling");
        System.out.print(word + " =>");
        // we create an arraylist which collects all the corrections
        // from the four functions
        ArrayList<String> suggestions = new ArrayList<>();
        String s = correctSpellingSubstitution(word);
        if(s!=null)suggestions.add(s);
        s = correctSpellingWithOmission(word);
        if(s!=null) suggestions.add(s);
        ArrayList<String> temp = correctSpellingWithInsertion(word);
        for(int i = 0; i<temp.size();i++)
            suggestions.add(temp.get(i));
        s = correctSpellingWithReversal(word);
        if(s!=null) suggestions.add(s);
        //we print our corrections if they exist and return true
        if(!suggestions.isEmpty()) {
            for (int i = 0; i < suggestions.size() - 1; i++) {
                System.out.print(" "+ suggestions.get(i)+ ",");
            }
            System.out.println(" "+suggestions.get(suggestions.size()-1));
            return true;
        }
        // this means no corrections were found
        else return false;
    }

    // This function takes in a string word and tries to correct the spelling by substituting letters and
    // check if the resulting new word is in the dictionary.
    static String correctSpellingSubstitution(String word) {
        //this loop runs such that it checks every permutation by replacing
        //characters in the string by every character from a to z
        for(int i = 0; i<word.length(); i++){
            for(int j = 0; j<26; j++) {
                String temp = word.substring(0, i) + ((char) 'a' +j) +word.substring(i + 1);
                if (Dictionary.containsKey(temp)) return temp;
            }
        }
        //the code reaching this part means no suggestion was found
        return null;
    }

    // This function tries to omit (in turn, one by one) a single character in the misspelled word
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithOmission(String word) {
        //this loop runs such that it iterates over every char in the string
        // and omits it one by one
        for(int i = 0; i<word.length(); i++){
            String temp = word.substring(0,i)+ word.substring(i+1);
            if(Dictionary.containsKey(temp)) return temp;
        }
        return null;
    }

    // This function tries to insert a letter in the misspelled word
    // and check if the resulting new word is in the dictionary.
    static ArrayList<String> correctSpellingWithInsertion(String word) {
        ArrayList<String> corrections = new ArrayList<>();
        //we have made such a nested loop that it inserts every alphabet
        //in spots 0 to k+i to check if it is in the dictionary
        for(int i = 0;i<=word.length() ;i++){
            for(int j = 0; j<26; j++){
                String temp = word.substring(0,i)+((char)('a'+j))+word.substring(i);
                if(Dictionary.containsKey(temp)) corrections.add(temp);
            }
        }
        //if no corrections are found, the arraylist will be empty
        return corrections;
    }

    // This function tries swapping every pair of adjacent characters
    // and check if the resulting new word is in the dictionary.
    static String correctSpellingWithReversal(String word) {
        //this for loop creates every permutation of strings produced
        // by swapping pairs in the given word and checks if they exist in dictionary
        for(int i = 0; i<word.length()-1; i++){
            String temp = word.substring(0,i)+ word.charAt(i+1)+ word.charAt(i)+word.substring(i+2);
            if(Dictionary.containsKey(temp))
                return temp;
        }
        //this means no swapped pair gave a word in the dictionary
        return null;
    }

}