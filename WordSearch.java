import java.util.*; //random, scanner, arraylist
import java.io.*; //file, filenotfoundexception
public class WordSearch{
    private char[][]data;

    //the random seed used to produce this WordSearch
    private int seed=-1;

    //a random Object to unify your random calls
    private Random randgen;

    //all words from a text file get added to wordsToAdd, indicating that they have not yet been added
    private ArrayList<String>wordsToAdd;

    //all words that were successfully added get moved into wordsAdded.
    private ArrayList<String>wordsAdded;

    /**Initialize the grid to the size specified
     *and fill all of the positions with '_'
     *@param row is the starting height of the WordSearch
     *@param col is the starting width of the WordSearch
     */
    public WordSearch(int rows,int cols){
      data = new char[rows][cols];
      for (int i=0;i<rows;i++){
        for (int n=0;n<cols;n++){
          data[i][n]='_';
        }
      }
    }

    /**Set all values in the WordSearch to underscores'_'*/
    private void clear(){
      for (int i=0;i<data.length;i++){
        for (int n=0;n<data[i].length;n++){
          data[i][n]='_';
        }
      }
    }

    /**Each row is a new line, there is a space between each letter
     *@return a String with each character separated by spaces, and rows
     *separated by newlines.
     */
    public String toString(){
      String ans = "|";
      for (int i=0;i<data.length;i++){
        for (int n=0;n<data[i].length-1;n++){
          ans=ans+data[i][n]+" ";
        }
        ans+=data[i][data[i].length-1];
        ans+="|\n|";
      }
      ans=ans.substring(0,ans.length()-2);
      ans+="\nWords: ";/*
      for (int i=0;i<wordsAdded.size()-1;i++){
        ans+=wordsAdded.get(i);
      }
      if (wordsAdded.size()>0){
        ans=ans+wordsAdded.get(wordsAdded.size()-1);
      }*/
      return ans;
    }


    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from left to right, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     * or there are overlapping letters that do not match, then false is returned
     * and the board is NOT modified.
     */
    public boolean addWordHorizontal(String word,int row, int col){
      boolean modify = true;
      if (col+word.length()>data[row].length){
        return false;
      }
      for (int i=0;i<word.length();i++){
        if (word.charAt(i)==data[row][col+i]||data[row][col+i]=='_'){
          modify=true;
        } else {
          return false;
        }
      }
      if (modify){
        for (int i=0;i<word.length();i++){
          data[row][col+i]=word.charAt(i);
        }
      }
      return true;
    }

   /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top to bottom, must fit on the WordGrid, and must
     *have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     *and the board is NOT modified.
     */
    public boolean addWordVertical(String word,int row, int col){
      boolean modify = true;
      if (row+word.length()-2>data.length){
        return false;
      }
      for (int i=0;i<word.length();i++){
        if (word.charAt(i)==data[row+i][col]||data[row+i][col]=='_'){
          modify=true;
        } else {
          return false;
        }
      }
      if (modify){
        for (int i=0;i<word.length();i++){
          data[row+i][col]=word.charAt(i);
        }
      }
      return true;
    }
    /**Attempts to add a given word to the specified position of the WordGrid.
     *The word is added from top left to bottom right, must fit on the WordGrid,
     *and must have a corresponding letter to match any letters that it overlaps.
     *
     *@param word is any text to be added to the word grid.
     *@param row is the vertical locaiton of where you want the word to start.
     *@param col is the horizontal location of where you want the word to start.
     *@return true when the word is added successfully. When the word doesn't fit,
     *or there are overlapping letters that do not match, then false is returned.
     */
    public boolean addWordDiagonal(String word,int row, int col){
      boolean modify = true;
      if (row+word.length()-2>data.length||col+word.length()>data[row].length){
        return false;
      }
      for (int i=0;i<word.length();i++){
        if (word.charAt(i)==data[row+i][col+i]||data[row+i][col+i]=='_'){
          modify=true;
        } else {
          return false;
        }
      }
      if (modify){
        for (int i=0;i<word.length();i++){
          data[row+i][col+i]=word.charAt(i);
        }
      }
      return true;
    }
//Update your wordSearch with the methods discussed in class:

//Two Constructors:

public WordSearch( int rows, int cols, String fileName) throws FileNotFoundException{
  seed = (int)(Math.random()*10000);
  File f = new File(fileName);
  Scanner in = new Scanner(f);
  while(in.hasNext()){
        String word = in.next();
        wordsToAdd.add(word);
  }
  data = new char[rows][cols];
  for (int i=0;i<rows;i++){
    for (int n=0;n<cols;n++){
      data[i][n]='_';
    }
  }
  //addAllWords();
}
public WordSearch( int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
  seed = randSeed;
  File f = new File(fileName);
  Scanner in = new Scanner(f);
  while(in.hasNext()){
        String word = in.next();
        wordsToAdd.add(word);
  }
  data = new char[rows][cols];
  for (int i=0;i<rows;i++){
    for (int n=0;n<cols;n++){
      data[i][n]='_';
    }
  }
  //addAllWords();
}
  //  Both will read in the word text file, then run addAllWords(). Do not fill in random letters after.

//Two Methods
//private boolean addWord( int r, int c, String word, int rowIncrement, int colIncrement)
    /*-when colIncrement and rowIncrement are both 0, return false.
    -when you successfully add a word, move the word from wordsToAdd to wordsAdded, then return true.
    -return false otherwise. */

//private boolean addAllWords() {}
      /*-Attempt to add all of the words from the wordsToAdd list using the following algorithm:
    1-Choose a random word, and a random direction (rowIncrement/colIncrement)
    2-Trying to add that word to different starting positions until:
        --you successfully add the word,
        --or you run out of positional tries*
      Each positional try only changes the position of the start, not the direction or word.
      *(Experiment with how many positional tries, maybe 100 is enough, maybe 1000.)
    3-Repeat this process until you added all of the words, or you tried to add unsuccessfully too many times* in a row. (experiment how many is sufficient to add most of the words)

    4-OPTIONAL
    Optimize your addAllWords such that:
    -You do not try to add words to positions that would not fit the word on the board.
    -Use the rowIncrement/colIncrement to decide what the range of valid row/col should be.
    e.g.
    A five letter word that is meant to be added across to the right should not START in the last 4 columns of the board    */
}
