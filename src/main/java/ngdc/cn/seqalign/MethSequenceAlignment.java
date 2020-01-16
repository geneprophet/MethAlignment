package ngdc.cn.seqalign;

import ngdc.cn.Cell;
/**
 * @author Kang
 * 继承自动态规划，DNA+甲基化序列的比对
 */
public abstract class MethSequenceAlignment extends DynamicProgramming {
/*
    protected int match;
    protected int mismatch;
    protected int gap;
    protected int gapExtend;
    protected int cmatch;
    protected int methmatch;
    protected int methmismatch;
    protected int methgap;
    protected int methgapExtend;
    protected int mdmismatch;
*/

    protected String[] alignments;
    protected ScoreMatrix scoreMatrix;
    public MethSequenceAlignment(String sequence1, String sequence2) {
        this(sequence1, sequence2,new ScoreMatrix());
    }

    public MethSequenceAlignment(String sequence1, String sequence2,ScoreMatrix scoreMatrix) {
        super(sequence1, sequence2);
        this.scoreMatrix = scoreMatrix;
/*        this.match = match;
        this.mismatch = mismatch;
        this.gap = gap;
        this.gapExtend = gapExtend;
        this.cmatch = cmatch;
        this.methmatch = methmatch;
        this.methmismatch = methmismatch;
        this.methgap = methgap;
        this.methgapExtend = methgapExtend;
        this.mdmismatch = mdmismatch;
        */
    }

    protected Object getTraceback() {
        StringBuffer align1Buf = new StringBuffer();
        StringBuffer align2Buf = new StringBuffer();
        Cell currentCell = getTracebackStartingCell();
        System.out.println("Score: " + currentCell.getScore());
        System.out.println("Sequence1_End: " + currentCell.getCol());
        System.out.println("Sequence2_End: " + currentCell.getRow());
        while (traceBackIsNotDone(currentCell)) {
            if (currentCell.getRow() - currentCell.getPrevCell().getRow() == 1) {
                align2Buf.insert(0, sequence2.charAt(currentCell.getRow() - 1));
            } else {
                align2Buf.insert(0, '-');
            }
            if (currentCell.getCol() - currentCell.getPrevCell().getCol() == 1) {
                align1Buf.insert(0, sequence1.charAt(currentCell.getCol() - 1));
            } else {
                align1Buf.insert(0, '-');
            }
            currentCell = currentCell.getPrevCell();
        }
        System.out.println("Sequence1_Start: " + currentCell.getCol());
        System.out.println("Sequence2_Start: " + currentCell.getRow());

        String[] alignments = new String[] { align1Buf.toString(),
                align2Buf.toString() };
        return alignments;
    }

    protected abstract boolean traceBackIsNotDone(Cell currentCell);

    public void getAlignmentScore() {
        if (alignments == null) {
            getAlignment();
        }
//TODO:score 根据比对结果判断、计算、进而得到一致性打分
        float DNAidengtityScore;
        float DNAidengtityCount = 0;
        float DNAidengtitySum = 0;
        float MethidengtityScore;
        float MethidengtityCount = 0;
        float MethidengtitySum = 0;
        float DNAMethidengtityScore;
        float DNAMethidengtityCount = 0;

        for (int i = 0; i < alignments[0].length(); i++) {
            char c1 = alignments[0].charAt(i);
            char c2 = alignments[1].charAt(i);
            if ((c1 == 'A' || c1 == 'T' || c1 == 'C' || c1 == 'G' || c1 == '-') && (c2 == 'A' || c2 == 'T' || c2 == 'C' || c2 == 'G' || c2 == '-' )){
                DNAidengtitySum = DNAidengtitySum + 1;
            }
            if ((c1 == 'L' || c1 == 'M' || c1 == 'H' || c1 == '-') && (c2 == 'L' || c2 == 'M' || c2 == 'H' || c2 == '-')){
                MethidengtitySum = MethidengtitySum + 1;
            }
            if (c1 == c2 && c1 != '-'){
                DNAMethidengtityCount = DNAMethidengtityCount + 1;
            }
            if (c1 == c2 && (c1 == 'A' || c1 == 'T' || c1 == 'C' || c1 == 'G')){
                DNAidengtityCount = DNAidengtityCount + 1;
            }
            if (c1 ==c2 && (c1 == 'L' || c1 == 'M' || c1 == 'H')){
                MethidengtityCount = MethidengtityCount + 1;
            }
        }

        DNAidengtityScore = DNAidengtityCount / DNAidengtitySum;
        MethidengtityScore = MethidengtityCount / MethidengtitySum;
        DNAMethidengtityScore = DNAMethidengtityCount/alignments[0].length();
//        System.out.println("DNAidengtityScore: "+ DNAidengtityScore);
//        System.out.println("MethidengtityScore: "+ MethidengtityScore);
        System.out.println("DNAMethidengtityScore: "+ DNAMethidengtityScore);
        System.out.println(alignments[0]);
        System.out.println(alignments[1]);
    }

    public String[] getAlignment() {
        ensureTableIsFilledIn();
        alignments = (String[]) getTraceback();
        return alignments;
    }

    protected abstract Cell getTracebackStartingCell();
}
