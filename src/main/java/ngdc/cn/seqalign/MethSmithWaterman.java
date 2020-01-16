package ngdc.cn.seqalign;

import ngdc.cn.Cell;

/**
 * @author Kang
 * local_alignment，DNA+甲基化序列
 */
public class MethSmithWaterman extends MethSequenceAlignment {

    private Cell highScoreCell;

    public MethSmithWaterman(String sequence1, String sequence2) {
        super(sequence1, sequence2);
    }

    public MethSmithWaterman(String sequence1, String sequence2,ScoreMatrix scoreMatrix) {
        super(sequence1, sequence2,scoreMatrix);
    }

    protected void initialize() {
        super.initialize();
        highScoreCell = scoreTable[0][0];
    }
    protected void fillInCell(Cell currentCell, Cell cellAbove, Cell cellToLeft,
                              Cell cellAboveLeft) {
        float rowSpaceScore = cellAbove.getScore() ;
        float colSpaceScore = cellToLeft.getScore();
        float matchOrMismatchScore = cellAboveLeft.getScore();
//TODO:仿射空隙罚分，对当前位置空位的罚分需要根据前一个位置是否也是空位、什么空位来决定
// 判断从上方来的打分
        if (cellAbove.getPrevCell() != null){
            //上方的元素其是否有指针指向其从哪里来
            if (cellAbove.getPrevCell().getCol() == cellAbove.getCol()){
                if ((sequence2.charAt(cellAbove.getRow() - 1) == 'A' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'T' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'C' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'G') &&
                        (sequence2.charAt(currentCell.getRow() - 1) == 'A' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'T' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'C' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'G')){
                    rowSpaceScore += scoreMatrix.ATCG_gapExtend;
                }else if ((sequence2.charAt(cellAbove.getRow() - 1) == 'L' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'M' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'H') &&
                        (sequence2.charAt(currentCell.getRow() - 1) == 'L' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'M' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'H')){
                    rowSpaceScore += scoreMatrix.LMH_gapExtend;
                }else if((sequence2.charAt(cellAbove.getRow() - 1) == 'A' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'T' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'C' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'G') &&
                        (sequence2.charAt(currentCell.getRow() - 1) == 'L' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'M' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'H')) {
                    rowSpaceScore += scoreMatrix.ATCG_LMH_gapExtend;
                }else if ((sequence2.charAt(cellAbove.getRow() - 1) == 'L' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'M' ||
                        sequence2.charAt(cellAbove.getRow() - 1) == 'H') &&
                        (sequence2.charAt(currentCell.getRow() - 1) == 'A' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'T' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'C' ||
                                sequence2.charAt(currentCell.getRow() - 1) == 'G')){
                    rowSpaceScore += scoreMatrix.LMH_ATCG_gapExtend;
                }
            }else {
                    if (sequence2.charAt(currentCell.getRow() - 1) == 'A' ||
                            sequence2.charAt(currentCell.getRow() - 1) == 'T' ||
                            sequence2.charAt(currentCell.getRow() - 1) == 'C' ||
                            sequence2.charAt(currentCell.getRow() - 1) == 'G') {
                        rowSpaceScore += scoreMatrix.ATCG_gap;
                    }
                    if (sequence2.charAt(currentCell.getRow() - 1) == 'L' ||
                            sequence2.charAt(currentCell.getRow() - 1) == 'M' ||
                            sequence2.charAt(currentCell.getRow() - 1) == 'H') {
                        rowSpaceScore += scoreMatrix.LMH_gap;
                    }
            }
        }
//判断从左边来的打分
        if (cellToLeft.getPrevCell() != null){
            if (cellToLeft.getPrevCell().getRow() == cellToLeft.getRow()){
                if ((sequence1.charAt(cellToLeft.getCol() - 1) == 'A' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'T' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'C' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'G') &&
                        (sequence1.charAt(currentCell.getCol() - 1) == 'A' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'T' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'C' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'G')){
                    colSpaceScore += scoreMatrix.ATCG_gapExtend;
                }else if ((sequence1.charAt(cellToLeft.getCol() - 1) == 'L' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'M' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'H') &&
                        (sequence1.charAt(currentCell.getCol() - 1) == 'L' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'M' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'H')){
                    colSpaceScore += scoreMatrix.LMH_gapExtend;
                }else if((sequence1.charAt(cellToLeft.getCol() - 1) == 'L' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'M' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'H') &&
                        (sequence1.charAt(currentCell.getCol() - 1) == 'A' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'T' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'C' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'G')){
                    colSpaceScore += scoreMatrix.LMH_ATCG_gapExtend;
                }else if ((sequence1.charAt(cellToLeft.getCol() - 1) == 'A' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'T' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'C' ||
                        sequence1.charAt(cellToLeft.getCol() - 1) == 'G') &&
                        (sequence1.charAt(currentCell.getCol() - 1) == 'L' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'M' ||
                                sequence1.charAt(currentCell.getCol() - 1) == 'H')){
                    colSpaceScore += scoreMatrix.ATCG_LMH_gapExtend;
                }
            }else {
                    if (sequence1.charAt(currentCell.getCol() - 1) == 'A' ||
                            sequence1.charAt(currentCell.getCol() - 1) == 'T' ||
                            sequence1.charAt(currentCell.getCol() - 1) == 'C' ||
                            sequence1.charAt(currentCell.getCol() - 1) == 'G'){
                        colSpaceScore += scoreMatrix.ATCG_gap;
                    }
                    if (sequence1.charAt(currentCell.getCol() -1) == 'L'||
                            sequence1.charAt(currentCell.getCol() -1) == 'M' ||
                            sequence1.charAt(currentCell.getCol() -1) == 'H' ){
                        colSpaceScore += scoreMatrix.LMH_gap;
                    }
            }
        }
//TODO:无需加else，如果上方元素没有前一个元素，则其应保持初始化的0值，这样rowSpaceScore=cellAbove.getScore() + gap会小于0,在后续判断中也会被忽略


//match/mismatch打分，判断从左上来的打分
        if (sequence2.charAt(currentCell.getRow() - 1) == sequence1.charAt(currentCell.getCol() - 1) &&
                sequence1.charAt(currentCell.getCol() - 1) == 'A') {
            matchOrMismatchScore += scoreMatrix.AAmatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'A' && sequence2.charAt(currentCell.getRow() - 1) == 'T') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'T' && sequence2.charAt(currentCell.getRow() - 1) == 'A'))) {
            matchOrMismatchScore += scoreMatrix.ATmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'A' && sequence2.charAt(currentCell.getRow() - 1) == 'C') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'C' && sequence2.charAt(currentCell.getRow() - 1) == 'A'))) {
            matchOrMismatchScore += scoreMatrix.ACmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'A' && sequence2.charAt(currentCell.getRow() - 1) == 'G') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'G' && sequence2.charAt(currentCell.getRow() - 1) == 'A'))) {
            matchOrMismatchScore += scoreMatrix.AGmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'A' && sequence2.charAt(currentCell.getRow() - 1) == 'L') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'L' && sequence2.charAt(currentCell.getRow() - 1) == 'A'))) {
            matchOrMismatchScore += scoreMatrix.ALmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'A' && sequence2.charAt(currentCell.getRow() - 1) == 'M') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'M' && sequence2.charAt(currentCell.getRow() - 1) == 'A'))) {
            matchOrMismatchScore += scoreMatrix.AMmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'A' && sequence2.charAt(currentCell.getRow() - 1) == 'H') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'H' && sequence2.charAt(currentCell.getRow() - 1) == 'A'))) {
            matchOrMismatchScore += scoreMatrix.AHmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) == sequence1.charAt(currentCell.getCol() - 1) &&
                sequence1.charAt(currentCell.getCol() - 1) == 'T'){
            matchOrMismatchScore += scoreMatrix.TTmatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'T' && sequence2.charAt(currentCell.getRow() - 1) == 'C') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'C' && sequence2.charAt(currentCell.getRow() - 1) == 'T'))) {
            matchOrMismatchScore += scoreMatrix.TCmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'T' && sequence2.charAt(currentCell.getRow() - 1) == 'G') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'G' && sequence2.charAt(currentCell.getRow() - 1) == 'T'))) {
            matchOrMismatchScore += scoreMatrix.TGmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'T' && sequence2.charAt(currentCell.getRow() - 1) == 'L') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'L' && sequence2.charAt(currentCell.getRow() - 1) == 'T'))) {
            matchOrMismatchScore += scoreMatrix.TLmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'T' && sequence2.charAt(currentCell.getRow() - 1) == 'M') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'M' && sequence2.charAt(currentCell.getRow() - 1) == 'T'))) {
            matchOrMismatchScore += scoreMatrix.TMmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'T' && sequence2.charAt(currentCell.getRow() - 1) == 'H') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'H' && sequence2.charAt(currentCell.getRow() - 1) == 'T'))) {
            matchOrMismatchScore += scoreMatrix.THmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) == sequence1.charAt(currentCell.getCol() - 1) &&
                sequence1.charAt(currentCell.getCol() - 1) == 'C') {
            matchOrMismatchScore += scoreMatrix.CCmatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'C' && sequence2.charAt(currentCell.getRow() - 1) == 'G') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'G' && sequence2.charAt(currentCell.getRow() - 1) == 'C'))) {
            matchOrMismatchScore += scoreMatrix.CGmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'C' && sequence2.charAt(currentCell.getRow() - 1) == 'L') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'L' && sequence2.charAt(currentCell.getRow() - 1) == 'C'))) {
            matchOrMismatchScore += scoreMatrix.CLmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'C' && sequence2.charAt(currentCell.getRow() - 1) == 'M') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'M' && sequence2.charAt(currentCell.getRow() - 1) == 'C'))) {
            matchOrMismatchScore += scoreMatrix.CMmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'C' && sequence2.charAt(currentCell.getRow() - 1) == 'H') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'H' && sequence2.charAt(currentCell.getRow() - 1) == 'C'))) {
            matchOrMismatchScore += scoreMatrix.CHmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) == sequence1.charAt(currentCell.getCol() - 1) &&
                sequence1.charAt(currentCell.getCol() - 1) == 'G') {
            matchOrMismatchScore += scoreMatrix.GGmatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'G' && sequence2.charAt(currentCell.getRow() - 1) == 'L') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'L' && sequence2.charAt(currentCell.getRow() - 1) == 'G'))) {
            matchOrMismatchScore += scoreMatrix.GLmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'G' && sequence2.charAt(currentCell.getRow() - 1) == 'M') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'M' && sequence2.charAt(currentCell.getRow() - 1) == 'G'))) {
            matchOrMismatchScore += scoreMatrix.GMmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'G' && sequence2.charAt(currentCell.getRow() - 1) == 'H') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'H' && sequence2.charAt(currentCell.getRow() - 1) == 'G'))) {
            matchOrMismatchScore += scoreMatrix.GHmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) == sequence1.charAt(currentCell.getCol() - 1) &&
                sequence1.charAt(currentCell.getCol() - 1) == 'L') {
            matchOrMismatchScore += scoreMatrix.LLmatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'L' && sequence2.charAt(currentCell.getRow() - 1) == 'M') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'M' && sequence2.charAt(currentCell.getRow() - 1) == 'L'))) {
            matchOrMismatchScore += scoreMatrix.LMmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'L' && sequence2.charAt(currentCell.getRow() - 1) == 'H') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'H' && sequence2.charAt(currentCell.getRow() - 1) == 'L'))) {
            matchOrMismatchScore += scoreMatrix.LHmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) == sequence1.charAt(currentCell.getCol() - 1) &&
                sequence1.charAt(currentCell.getCol() - 1) == 'M') {
            matchOrMismatchScore += scoreMatrix.MMmatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) != sequence1.charAt(currentCell.getCol() - 1) &&
                ((sequence1.charAt(currentCell.getCol() - 1) == 'M' && sequence2.charAt(currentCell.getRow() - 1) == 'H') ||
                        (sequence1.charAt(currentCell.getCol() - 1) == 'H' && sequence2.charAt(currentCell.getRow() - 1) == 'M'))) {
            matchOrMismatchScore += scoreMatrix.MHmismatch;
        }else if (sequence2.charAt(currentCell.getRow() - 1) == sequence1.charAt(currentCell.getCol() - 1) &&
                sequence1.charAt(currentCell.getCol() - 1) == 'H') {
            matchOrMismatchScore += scoreMatrix.HHmatch;
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
        return "[MethSmithWaterman: sequence1=" + sequence1 + ", sequence2="
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
