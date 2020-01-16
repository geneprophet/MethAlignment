package ngdc.cn.seqalign;

import ngdc.cn.Cell;

/**
 * @author Kang
 * local_alignment
 */
public class SmithWaterman extends SequenceAlignment {

    private Cell highScoreCell;

    public SmithWaterman(String sequence1, String sequence2) {
        super(sequence1, sequence2);
    }

    public SmithWaterman(String sequence1, String sequence2, int match,
                         int mismatch, int gap,int gapExtend) {
        super(sequence1, sequence2, match, mismatch, gap, gapExtend);
    }

    protected void initialize() {
        super.initialize();
        highScoreCell = scoreTable[0][0];
    }

    protected void fillInCell(Cell currentCell, Cell cellAbove, Cell cellToLeft,
                              Cell cellAboveLeft) {

        float rowSpaceScore = cellAbove.getScore();
        float colSpaceScore = cellToLeft.getScore();
        float matchOrMismatchScore = cellAboveLeft.getScore();

        if (cellAbove.getPrevCell() != null){
            //上方的元素其是否有指针指向其从哪里来,

            if (cellAbove.getPrevCell().getCol() == cellAbove.getCol()) {
                rowSpaceScore += gapExtend;
            }else{
                rowSpaceScore += gap;
            }
        }
        //TODO:是否加else？如果上方元素没有前一个元素，则其应保持初始化的0值，这样rowSpaceScore=cellAbove.getScore() + gap会小于0,在后续判断中也会被忽略


        if (cellToLeft.getPrevCell() != null){
            if (cellToLeft.getPrevCell().getRow() == cellToLeft.getRow()){
                colSpaceScore += gapExtend;
            }else {
                colSpaceScore += gap;
            }
        }


        if (sequence2.charAt(currentCell.getRow() - 1) == sequence1
                .charAt(currentCell.getCol() - 1)) {
            matchOrMismatchScore += match;
        } else {
            matchOrMismatchScore += mismatch;
        }
        if (rowSpaceScore >= colSpaceScore) {
            if (matchOrMismatchScore >= rowSpaceScore) {
                if (matchOrMismatchScore > 0) {
                    currentCell.setScore(matchOrMismatchScore);
                    currentCell.setPrevCell(cellAboveLeft);
                }
            } else {
                if (rowSpaceScore > 0) {
                    currentCell.setScore(rowSpaceScore);
                    currentCell.setPrevCell(cellAbove);
                }
            }
        } else {
            if (matchOrMismatchScore >= colSpaceScore) {
                if (matchOrMismatchScore > 0) {
                    currentCell.setScore(matchOrMismatchScore);
                    currentCell.setPrevCell(cellAboveLeft);
                }
            } else {
                if (colSpaceScore > 0) {
                    currentCell.setScore(colSpaceScore);
                    currentCell.setPrevCell(cellToLeft);
                }
            }
        }
        if (currentCell.getScore() > highScoreCell.getScore()) {
            highScoreCell = currentCell;
        }
    }


    @Override
    public String toString() {
        return "[SmithWaterman: sequence1=" + sequence1 + ", sequence2="
                + sequence2 + "]";
    }

    @Override
    protected boolean traceBackIsNotDone(Cell currentCell) {
        return currentCell.getScore() != 0;
    }

    @Override
    public Cell getTracebackStartingCell() {
        return highScoreCell;
    }

    @Override
    protected Cell getInitialPointer(int row, int col) {
        return null;
    }

    @Override
    protected float getInitialScore(int row, int col) {
        return 0;
    }
}
