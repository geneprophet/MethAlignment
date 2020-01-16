package ngdc.cn;


/**
 * @author Kang
 *动态规划中表格中的单元格
 */
public class Cell {
    private Cell prevCell;
    private float score;
    private int row;
    private int col;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * @param score
     * the score to set
     */
    public void setScore(float score) {
        this.score = score;
    }

    /**
     * @return the score
     */
    public float getScore() {
        return score;
    }

    /**
     * @param prevCell
     *           the prevCell to set
     */
    public void setPrevCell(Cell prevCell) {
        this.prevCell = prevCell;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * @return the prevCell
     */
    public Cell getPrevCell() {
        return prevCell;
    }

    @Override
    public String toString() {
        return "Cell(" + row + ", " + col + "): score=" + score + ", prevCell="
                + prevCell + "]";
    }
}
