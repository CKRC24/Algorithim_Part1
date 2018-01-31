import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grids;                  // 2D-array to store isOpen status(default: false)
    private WeightedQuickUnionUF unionUF;       // union-find object
    private WeightedQuickUnionUF isFullUF;      // union-find for isFull
    private int gridSize;                       // grid size (n)
    private int num_grids;                      // total number of grids (n*2 + 2)
    private int num_open_sites = 0;             // store number of open sites

    // Constructor, create n-by-n grid, with all sites blocked
    public Percolation(int n){
        // check grid size
        if (n <= 0)
            throw new IllegalArgumentException("Grid Size <= 0");

        // add grid and initialize
        this.gridSize = n;
        this.num_grids = (int) Math.pow(n, 2);
        this.grids = new boolean[this.gridSize][this.gridSize];

        // create union-find object, size = num_grids + 2 (virtual top & bot)
        this.unionUF = new WeightedQuickUnionUF(num_grids + 2);

        // isFull union-find object size = num_grids + 1(only virtual top)
        this.isFullUF = new WeightedQuickUnionUF(num_grids + 1);
    }

    // check range of row, col
    private void checkRange(int row, int col){
        if ((row < 1 || row > this.gridSize) || (col < 1 || col > this.gridSize))
            throw new IllegalArgumentException("row or col number out of bounds");
    }

    // turn (row, col) into arraylist index
    private int toArrayIndex(int row, int col){
        return this.gridSize * (row-1) + (col-1);
    }

    // check neighbors & connect
    private void checkNeighbors(int row, int col){
        int uf_index = toArrayIndex(row, col) + 1;

        // get neighbor indexs(top, bot, left, right)
        if (row-1 >= 1 && isOpen(row-1, col)) {
            int top_index = toArrayIndex(row-1, col) + 1;
            this.unionUF.union(uf_index, top_index);
            this.isFullUF.union(uf_index, top_index);
        }
        if (row+1 <= this.gridSize && isOpen(row+1, col)) {
            int bot_index = toArrayIndex(row+1, col) + 1;
            this.unionUF.union(uf_index, bot_index);
            this.isFullUF.union(uf_index, bot_index);
        }
        if (col-1 >= 1 && isOpen(row, col-1)) {
            int left_index = toArrayIndex(row, col-1) + 1;
            this.unionUF.union(uf_index, left_index);
            this.isFullUF.union(uf_index, left_index);
        }
        if (col+1 <= this.gridSize && isOpen(row, col+1)) {
            int right_index = toArrayIndex(row, col+1) + 1;
            this.unionUF.union(uf_index, right_index);
            this.isFullUF.union(uf_index, right_index);
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col){
        checkRange(row, col);
        if (!isOpen(row, col)) {
            // open site
            this.grids[row-1][col-1] = true;
            this.num_open_sites++;
            int uf_index = toArrayIndex(row, col) + 1;

            // if site is in the top/bot row, connect to virtual top/bot
            if (row == 1) {
                this.unionUF.union(0, uf_index);
                this.isFullUF.union(0, uf_index);
            }
            if (row == this.gridSize)
                this.unionUF.union(this.num_grids+1, uf_index);

            // connect to open neighbors
            checkNeighbors(row, col);
        }
    }

    // check if site(row, col) is open
    public boolean isOpen(int row, int col){
        checkRange(row, col);
        return this.grids[row-1][col-1];
    }

    // check if site(row, col) is full
    public boolean isFull(int row, int col){
        checkRange(row, col);
        if (isOpen(row, col)) {
            int uf_index = toArrayIndex(row, col) + 1;
            return this.isFullUF.connected(uf_index, 0);
        }
        else{
            return false;
        }
    }

    // return number of open sites
    public int numberOfOpenSites(){
        return this.num_open_sites;
    }

    // judge wheter the system percolates
    public boolean percolates(){
        return this.unionUF.connected(0, this.num_grids+1);
    }

}
