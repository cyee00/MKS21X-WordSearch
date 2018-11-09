public class WordSearch{
    private char[][]data;

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
      String ans = "";
      for (int i=0;i<data.length;i++){
        for (int n=0;n<data[i].length-1;n++){
          ans=ans+data[i][n]+" ";
        }
        ans+=data[i][data[i].length-1];
        ans+="\n";
      }
      ans=ans.substring(0,ans.length()-1);
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
}
