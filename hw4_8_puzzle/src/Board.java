import java.util.Iterator;

public class Board {
    // define parameters

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks){

    }

    // board dimension
    public int dimension(){
        return 0;
    }

    // number of blocks out of place
    public int hamming(){
        return 0;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan(){

    }

    // is this board the goal board
    public boolean isGoal(){

    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin(){

    }

    // does this board equal another
    public boolean equals(Object y){}

    // all neighboring boards
    public Iterable<Board> neighbors(){}

    // string representation of this board
    public String toString(){}

    // test
    public static void main(String[] args) {

    }
}
