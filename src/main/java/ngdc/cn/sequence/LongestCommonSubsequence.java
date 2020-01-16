package ngdc.cn.sequence;


import ngdc.cn.Cell;
import ngdc.cn.seqalign.DynamicProgramming;

/**
 * @author Kang
 *最长公共序列
 */
public class LongestCommonSubsequence extends DynamicProgramming {

    public LongestCommonSubsequence(String sequence1, String sequence2) {
        super(sequence1, sequence2);
    }

    public String getLongestCommonSubsequence() {
        if (!isInitialized) {
            initialize();
        }
        if (!tableIsFilledIn) {
            fillIn();
        }

        return (String) getTraceback();
    }

    @Override
    protected void fillInCell(Cell currentCell, Cell cellAbove, Cell cellToLeft,
                              Cell cellAboveLeft) {
        float aboveScore = cellAbove.getScore();
        float leftScore = cellToLeft.getScore();
        float matchScore;
        if (sequence1.charAt(currentCell.getCol() - 1) == sequence2
                .charAt(currentCell.getRow() - 1)) {
            matchScore = cellAboveLeft.getScore() + 1;
        } else {
            matchScore = cellAboveLeft.getScore();
        }
        float cellScore;
        Cell cellPointer;
        if (matchScore >= aboveScore) {
            if (matchScore >= leftScore) {
                // matchScore >= aboveScore and matchScore >= leftScore
                cellScore = matchScore;
                cellPointer = cellAboveLeft;
            } else {
                // leftScore > matchScore >= aboveScore
                cellScore = leftScore;
                cellPointer = cellToLeft;
            }
        } else {
            if (aboveScore >= leftScore) {
                // aboveScore > matchScore and aboveScore >= leftScore
                cellScore = aboveScore;
                cellPointer = cellAbove;
            } else {
                // leftScore > aboveScore > matchScore
                cellScore = leftScore;
                cellPointer = cellToLeft;
            }
        }
        currentCell.setScore(cellScore);
        currentCell.setPrevCell(cellPointer);
    }


    @Override
    protected Cell getInitialPointer(int row, int col) {
        return null;
    }


    @Override
    protected float getInitialScore(int row, int col) {
        return 0;
    }


    @Override
    protected Object getTraceback() {
        StringBuffer lCSBuf = new StringBuffer();
        Cell currentCell = scoreTable[scoreTable.length - 1][scoreTable[0].length - 1];
        while (currentCell.getScore() > 0) {
            Cell prevCell = currentCell.getPrevCell();
            if ((currentCell.getRow() - prevCell.getRow() == 1 && currentCell
                    .getCol()
                    - prevCell.getCol() == 1)
                    && currentCell.getScore() == prevCell.getScore() + 1) {
                lCSBuf.insert(0, sequence1.charAt(currentCell.getCol() - 1));
            }
            currentCell = prevCell;
        }

        return lCSBuf.toString();
    }
}
