# Simple-spell-checker
This program is a simple spell-checker. The program should is named Spell and takes two file names as command-line arguments. The program will be invoked from the command line as 
                                  
                                  Spell dictionary.txt fileToCheck.txt

The first file name is the dictionary with correctly spelled words. The second file is the text to be spell-checked. The program should check all words in fileToCheck.txt. Nothing needs to be done for words that are correctly spelled, i.e. those found in the dictionary file. The program should suggest possible correct spellings for words not in the dictionary file by printing to the standard output. The program will perform the following modifications of a misspelled word to handle commonly made mistakes:

  • Letter substitution: iterate over characters in the misspelled word, trying to replace the current character with a different character. For example, in a misspelled word' lat', substituting' c' instead of' l' will produce the word' cat' in a dictionary. There are 25 different characters to try for the current character. Thus, if the length of a word is k characters, the number of modifications to try is 25k.
  
  • Letter omission: try to omit (in turn, one by one) a single character in the misspelled word and check if the resulting new word is in the dictionary. For example, if the misspelled word is' catt', omitting the last character' t' will produce the word' cat' in the dictionary. In this case, there are k modifications to try where k is the number of characters in the word.
  
  • Letter insertion: try to insert a letter in the misspelled word. For example, for' plce', inserting' a' after' l' produces a correctly spelled word' place'. If a word is k characters long, there are 26*(k+1) modifications to try since there are 26 characters to insert and k+1 places (including in the beginning and at the end of the word) to insert a character.
  
  • Letter reversal: Try swapping every pair of adjacent characters. For example, in' paernt', swapping' e' and' r' produces a correctly spelled word' parent'. For a word of length k, there are k - 1 pairs to swap.
