package ngdc.cn.seqalign;


import ngdc.cn.Cell;

/**
 * @author Kang
 * global_alignment
 */
public class NeedlemanWunsch extends SequenceAlignment {

    public NeedlemanWunsch(String sequence1, String sequence2) {
        super(sequence1, sequence2);
    }

    public NeedlemanWunsch(String sequence1, String sequence2, int match,
                           int mismatch, int gap,int gapExtend) {
        super(sequence1, sequence2, match, mismatch, gap, gapExtend);
    }

    protected void fillInCell(Cell currentCell, Cell cellAbove, Cell cellToLeft,
                              Cell cellAboveLeft) {
        //TODO:实现仿射罚分

        float rowSpaceScore = cellAbove.getScore() ;
        float colSpaceScore = cellToLeft.getScore();
        float matchOrMismatchScore = cellAboveLeft.getScore();

        if (cellAbove.getPrevCell() != null){
            //上方的元素其是否有指针指向其从哪里来
            if (cellAbove.getPrevCell().getCol() == cellAbove.getCol()) {
                rowSpaceScore += gapExtend;
            }else{
                rowSpaceScore += gap;
            }
        }
//左边元素打分
        if (cellToLeft.getPrevCell() != null){
            if (cellToLeft.getPrevCell().getRow() == cellToLeft.getRow()){
                colSpaceScore += gapExtend;
            }else {
                colSpaceScore += gap;
            }
        }
//左上元素打分
        if (sequence2.charAt(currentCell.getRow() - 1) == sequence1
                .charAt(currentCell.getCol() - 1)) {
            matchOrMismatchScore += match;
        } else {
            matchOrMismatchScore += mismatch;
        }

        if (rowSpaceScore >= colSpaceScore) {
            if (matchOrMismatchScore >= rowSpaceScore) {
                currentCell.setScore(matchOrMismatchScore);
                currentCell.setPrevCell(cellAboveLeft);
            } else {
                currentCell.setScore(rowSpaceScore);
                currentCell.setPrevCell(cellAbove);
            }
        } else {
            if (matchOrMismatchScore >= colSpaceScore) {
                currentCell.setScore(matchOrMismatchScore);
                currentCell.setPrevCell(cellAboveLeft);
            } else {
                currentCell.setScore(colSpaceScore);
                currentCell.setPrevCell(cellToLeft);
            }
        }
    }

    @Override
    protected boolean traceBackIsNotDone(Cell currentCell) {
        return currentCell.getPrevCell() != null;
    }

    @Override
    protected Cell getTracebackStartingCell() {
        return scoreTable[scoreTable.length - 1][scoreTable[0].length - 1];
    }


    @Override
    public String toString() {
        return "[NeedlemanWunsch: sequence1=" + sequence1 + ", sequence2="
                + sequence2 + "]";
    }

    protected Cell getInitialPointer(int row, int col) {
        if (row == 0 && col != 0) {
            return scoreTable[row][col - 1];
        } else if (col == 0 && row != 0) {
            return scoreTable[row - 1][col];
        } else {
            return null;
        }
    }
//初始化打分矩阵应该用gap和gapextend
    protected float getInitialScore(int row, int col) {
        if (row == 0 && col != 0) {
            return gap + (col-1) * gapExtend;
        } else if (col == 0 && row != 0) {
            return gap + (row-1) * gapExtend;
        } else {
            //TODO:初始化矩阵为0还是负无穷？全局比对得分有可能为负
            return 0;
        }
    }
}
