package ngdc.cn.seqalign;

import ngdc.cn.Cell;
/**
 * 甲基化序列全局比对
 * @author Kang
 */

public class MethNeedlemanWunsch extends MethSequenceAlignment {

    public MethNeedlemanWunsch(String sequence1, String sequence2) {
        super(sequence1, sequence2);
    }
    public MethNeedlemanWunsch(String sequence1, String sequence2,ScoreMatrix scoreMatrix) {
        super(sequence1, sequence2, scoreMatrix);
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
        return "[MethNeedlemanWunsch: sequence1=" + sequence1 + ", sequence2="
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
    //TODO：初始化打分矩阵判断序列是哪一种gap/gapextend,if语句中return必须有else！
    // 解决递归栈溢出,不用递归用矩阵存数据
    protected float getInitialScore(int row, int col) {
        if (row == 0 && col != 0) {
            if(col > 1){
                if((sequence1.charAt(col - 2) == 'A' || sequence1.charAt(col - 2) == 'T' ||
                        sequence1.charAt(col - 2) == 'C' || sequence1.charAt(col - 2) == 'G') &&
                        (sequence1.charAt(col - 1) == 'A' || sequence1.charAt(col - 1) == 'T' ||
                                sequence1.charAt(col - 1) == 'C' || sequence1.charAt(col - 1) == 'G')){
//                    return getInitialScore(row,col-1) + scoreMatrix.ATCG_gapExtend;
                    return scoreTable[row][col-1].getScore() + scoreMatrix.ATCG_gapExtend;
                }else if((sequence1.charAt(col - 2) == 'L' || sequence1.charAt(col - 2) == 'M' ||
                        sequence1.charAt(col - 2) == 'H') &&
                        (sequence1.charAt(col - 1) == 'L' || sequence1.charAt(col - 1) == 'M' ||
                                sequence1.charAt(col - 1) == 'H')){
//                    return getInitialScore(row,col-1) + scoreMatrix.LMH_gapExtend;
                    return scoreTable[row][col-1].getScore() + scoreMatrix.LMH_gapExtend;
                }else if((sequence1.charAt(col - 2) == 'A' || sequence1.charAt(col - 2) == 'T' ||
                        sequence1.charAt(col - 2) == 'C' || sequence1.charAt(col - 2) == 'G') &&
                        (sequence1.charAt(col - 1) == 'L' || sequence1.charAt(col - 1) == 'M' ||
                                sequence1.charAt(col - 1) == 'H')){
//                    return getInitialScore(row,col-1) + scoreMatrix.ATCG_LMH_gapExtend;
                    return scoreTable[row][col-1].getScore() + scoreMatrix.ATCG_LMH_gapExtend;
                }else{
//                    return getInitialScore(row,col-1) + scoreMatrix.LMH_ATCG_gapExtend;
                    return scoreTable[row][col-1].getScore() + scoreMatrix.LMH_ATCG_gapExtend;
                }
            }else if(sequence1.charAt(col - 1) == 'A' || sequence1.charAt(col - 1) == 'T'||
                    sequence1.charAt(col - 1) == 'C' || sequence1.charAt(col - 1) == 'G'){
                return scoreMatrix.ATCG_gap;
            }else {
                return scoreMatrix.LMH_gap;
            }
        } else if (col == 0 && row != 0) {
            if(row > 1){
                if((sequence2.charAt(row - 2) == 'A' || sequence2.charAt(row - 2) == 'T' ||
                        sequence2.charAt(row - 2) == 'C' || sequence2.charAt(row - 2) == 'G') &&
                        (sequence2.charAt(row - 1) == 'A' || sequence2.charAt(row - 1) == 'T' ||
                                sequence2.charAt(row - 1) == 'C' || sequence2.charAt(row - 1) == 'G')){
//                    return getInitialScore(row-1,col) + scoreMatrix.ATCG_gapExtend;
                    return scoreTable[row-1][col].getScore() + scoreMatrix.ATCG_gapExtend;
                }else if((sequence2.charAt(row - 2) == 'L' || sequence2.charAt(row - 2) == 'M' ||
                        sequence2.charAt(row - 2) == 'H') &&
                        (sequence2.charAt(row - 1) == 'L' || sequence2.charAt(row - 1) == 'M' ||
                                sequence2.charAt(row - 1) == 'H')){
//                    return getInitialScore(row-1,col) + scoreMatrix.LMH_gapExtend;
                    return scoreTable[row-1][col].getScore()  + scoreMatrix.LMH_gapExtend;
                }else if((sequence2.charAt(row - 2) == 'A' || sequence2.charAt(row - 2) == 'T' ||
                        sequence2.charAt(row - 2) == 'C' || sequence2.charAt(row - 2) == 'G') &&
                        (sequence2.charAt(row - 1) == 'L' || sequence2.charAt(row - 1) == 'M' ||
                                sequence2.charAt(row - 1) == 'H')){
//                    return getInitialScore(row-1,col) + scoreMatrix.ATCG_LMH_gapExtend;
                    return scoreTable[row-1][col].getScore()  + scoreMatrix.ATCG_LMH_gapExtend;
                }else {
//                    return getInitialScore(row-1,col) + scoreMatrix.LMH_ATCG_gapExtend;
                    return scoreTable[row-1][col].getScore()  + scoreMatrix.LMH_ATCG_gapExtend;
                }
            }else if(sequence1.charAt(row - 1) == 'A' || sequence1.charAt(row - 1) == 'T'||
                    sequence1.charAt(row - 1) == 'C' || sequence1.charAt(row - 1) == 'G'){
                return scoreMatrix.ATCG_gap;
            }else {
                return scoreMatrix.LMH_gap;
            }
        } else {
            return 0;
        }

    }


}
