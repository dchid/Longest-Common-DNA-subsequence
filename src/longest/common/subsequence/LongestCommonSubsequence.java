/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longest.common.subsequence;

/**
 *
 * @author dnchidester
 */
public class LongestCommonSubsequence {
    
    static char x10[] = LongestCommonSubsequence.makeDNA(10);
    static char y10[] = LongestCommonSubsequence.makeDNA(10);
    
    static char x20[] = LongestCommonSubsequence.makeDNA(20);
    static char y20[] = LongestCommonSubsequence.makeDNA(20);
    
    static char x100[] = LongestCommonSubsequence.makeDNA(100);
    static char y100[] = LongestCommonSubsequence.makeDNA(100);
    
    static char x1000[] = LongestCommonSubsequence.makeDNA(1000);
    static char y1000[] = LongestCommonSubsequence.makeDNA(1000);

    static char x10000[] = LongestCommonSubsequence.makeDNA(10000);
    static char y10000[] = LongestCommonSubsequence.makeDNA(10000);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LongestCommonSubsequence.printCharArray(x10);
        System.out.println("\n");
        LongestCommonSubsequence.printCharArray(y10);
        System.out.println("\n");
        lcs(x10, y10);
    }

    static void lcs(char x[], char y[]) {
        
        //length of char arrays
        int m = x.length;
        int n = y.length;
        
        int tableC[][] = new int[m+1][n+1];
        char tableB[][] = new char[m][n];
        
        //setting c values to 0
        for (int i = 0; i < m; i++){
            //i is the row
            tableC[i][0] = 0;
        }
        for (int j = 0; j < n; j++){
            //j is the collumn
            tableC[0][j] = 0;
        }
        
        //setting table values
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (x[i] == y[j]){
                    tableC[i+1][j+1] = tableC[i][j] + 1;
                    tableB[i][j] = 'D';
                }
                else if (tableC[i][j+1] >= tableC[i+1][j]){
                    tableC[i+1][j+1] = tableC[i][j+1];
                    tableB[i][j] = 'U';
                }
                else {
                    tableC[i+1][j+1] = tableC[i+1][j];
                    tableB[i][j] = 'R';
                }
            }
        }
        //uncomment for print statement testing
        
        for (int i = 0; i < m+1; i++){
            for (int j = 0; j < n+1; j++){
                System.out.print("" + tableC[i][j]);
            }
            System.out.println("");
        }
        
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                System.out.print("" + tableB[i][j]);
            }
            System.out.println("");
        }
        
        System.out.println("");
        
        //avoid array out of bounds by using largest array
        if (m >= n){
            printLCS(m-1, n-1, tableB, x);
        }
        else printLCS(m-1, n-1, tableB, y);
    }
    
    static void printLCS(int i, int j, char[][] b, char[] x){
        //returns when finished 
        if (i==-1 || j==-1){
        return;
        //arrow equals up, right, or diagonal
        }
        switch (b[i][j]) {
            //diagonal arrow
            case 'D': 
                printLCS((i-1), (j-1), b, x);
                System.out.print(x[i]);
                break;
            //up arrow
            case 'U':
                printLCS((i-1), j, b, x);
                break;
            //right arrow
            default:
                printLCS(i, (j-1), b, x);
                break;
        }
    }

    //generates a random sequence of DNA nitrogen bases
    //A = Adenine G = Gaunine T = Thymine C = Cytosine
    static char[] makeDNA(int length) {
        char dnaSequence[] = new char[length];
        for (int index = 0; index < length; index++) {
            //generate a radom integer and use mod 4 to get the int between 0 and 3
            int base = ((int) (100 * Math.random())) % 4;
            //switch statement creates nitrogen bases
            
            switch (base) {
                case 0:
                    dnaSequence[index] = 'A';
                    break;
                case 1:
                    dnaSequence[index] = 'G';
                    break;
                case 2:
                    dnaSequence[index] = 'T';
                    break;
                case 3:
                    dnaSequence[index] = 'C';
                    break;
                default:
                    System.out.println("Error: broken switch statement");
            }
        }
        return dnaSequence;
    }
    
    static void printCharArray(char[] array){
        for (int i =0; i < array.length; i++) System.out.print(array[i]);
    }

}
