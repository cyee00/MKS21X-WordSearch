public class Driver
{
  public static void main(String[] args)
  {
    System.out.println("testing Constructor");
    WordSearch testCase = new WordSearch(3, 5);
    System.out.println("should print out 3 by 5 array with underscores");
    System.out.println(testCase.toString());/*
    System.out.println("\n\n\n");
    System.out.println("Testing addWordHorizontal");
    testCase.addWordHorizontal("hey", 0, 0);
    System.out.println("top row should say hey");
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    testCase.addWordHorizontal("yeh", 0, 0);
    System.out.println("should not contain yeh because of overlap");
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    System.out.println("testing addWordVertical");
    testCase.addWordHorizontal("yoyoyo", 0, 1);
    System.out.println("second column should not say yoyoyo because it doesn't fit");
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    testCase.addWordVertical("joe", 0, 4);
    System.out.println("should not print greetings because of capacity");
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    System.out.println("should have yeh on third column");
    testCase.addWordVertical("yeh", 0, 2);
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    System.out.println("should clear board");
    testCase.clear();
    System.out.println(testCase.toString());
    System.out.println("\n\n\n");
    System.out.println("should have yeh diagonally across board");
    testCase.addWordDiagonal("yeh", 3, 0);
    System.out.println(testCase.toString());*/
  }
}
