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
      ans+="\nWords: ";
      for (int i=0;i<wordsAdded.size()-1;i++){
        ans=ans+wordsAdded.get(i)+", ";
      }
      if (wordsAdded.size()>0){
        ans=ans+wordsAdded.get(wordsAdded.size()-1);
      }
      ans=ans+" (seed: " + seed + ")";
      return ans;
    }



//Update your wordSearch with the methods discussed in class:

//Two Constructors:

public WordSearch( int rows, int cols, String fileName) throws FileNotFoundException{
  randgen = new Random();
  seed = Math.abs(randgen.nextInt()%10000);
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
  addAllWords();
}
public WordSearch( int rows, int cols, String fileName, int randSeed) throws FileNotFoundException{
  seed = Math.abs(randSeed%10000);
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
  addAllWords();
}
  //  Both will read in the word text file, then run addAllWords(). Do not fill in random letters after.

//Two Methods
private boolean addWord( int row, int col, String word, int rowIncrement, int colIncrement){
  boolean modify = false;
  word = word.toUpperCase();
  if (rowIncrement==0&&colIncrement==0){
    return false;
  }
  for (int i=0;i<word.length();i++){
    if (word.charAt(i)==data[row+(rowIncrement*i)][col+(colIncrement*i)]||
    data[row+(rowIncrement*i)][col+(colIncrement*i)]=='_'){
      modify = true;
    }
  }
  if (modify){
    for (int i=0;i<word.length();i++){
      data[row+(rowIncrement*i)][col+(colIncrement*i)]=word.charAt(i);
    }
  }
  return false;
}
/**Attempts to add a given word to the specified position of the WordGrid.
  *The word is added in the direction rowIncrement,colIncrement

  *Words must have a corresponding letter to match any letters that it overlaps.

  *
  *@param word is any text to be added to the word grid.
  *@param row is the vertical locaiton of where you want the word to start.
  *@param col is the horizontal location of where you want the word to start.

  *@param rowIncrement is -1,0, or 1 and represents the displacement of each letter in the row direction
  *@param colIncrement is -1,0, or 1 and represents the displacement of each letter in the col direction

  *@return true when: the word is added successfully.
  *        false when: the word doesn't fit, OR  rowchange and colchange are both 0,
  *        OR there are overlapping letters that do not match
  */
    /*-when colIncrement and rowIncrement are both 0, return false.
    -when you successfully add a word, move the word from wordsToAdd to wordsAdded, then return true.
    -return false otherwise. */
    /*[rowIncrement,colIncrement] examples:

        *[-1,1] would add up and the right because (row -1 each time, col + 1 each time)

        *[ 1,0] would add downwards because (row+1), with no col change

        *[ 0,-1] would add towards the left because (col - 1), with no row change

        */
private boolean addAllWords() {
  int tries = 1000;
  while (tries>0){//stops trying to add any words when tries reaches 0, i.e. 999 tries
    randgen = new Random(seed);
    String word = wordsToAdd.get(randgen.nextInt()%wordsToAdd.size());
    int rowIncrement = randgen.nextInt()%2;
    int colIncrement = randgen.nextInt()%2;
    for (int i=0;i<100;i++){ //run out of positional tries at 100, so move on to the next word
      int row=Math.abs(randgen.nextInt()%data.length);
      int col=Math.abs(randgen.nextInt()%data[0].length);
      if (addWord(row,col,word,rowIncrement,colIncrement)){
        i=100; //word added successfully, so move on to the next word
      }
    }
    if (wordsToAdd.isEmpty()){//all words added, so stop
      return true;
    } else {tries--;}//else keep going
  }
  return true;
}
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
    private void fill(){
      randgen=new Random();
      String alphabet="QWERTYUIOPASDFGHJKLZXCVBNM";
      for (int i=0;i<data.length;i++){
        for (int n=0;i<data[i].length;n++){
          if (data[i][n]=='_'){
            data[i][n]=alphabet.charAt(Math.abs(randgen.nextInt()%26));
          }
        }
      }
    }
    public static void main(String[]args){
      try{

      }catch(IllegalArgumentException e){

      }
    }
}
