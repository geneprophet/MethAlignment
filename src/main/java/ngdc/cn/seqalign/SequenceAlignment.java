package ngdc.cn.seqalign;

import ngdc.cn.Cell;

/**
 * @author Kang
 * 继承自动态规划，仅DNA序列比对
 */
public abstract class SequenceAlignment extends DynamicProgramming {

    protected int match;
    protected int mismatch;
    protected int gap;
    protected int gapExtend;
    protected String[] alignments;

    public SequenceAlignment(String sequence1, String sequence2) {
        this(sequence1, sequence2, 5, -4, -7,-2);
    }

    public SequenceAlignment(String sequence1, String sequence2, int match,
                             int mismatch, int gap,int gapExtend) {
        super(sequence1, sequence2);

        this.match = match;
        this.mismatch = mismatch;
        this.gap = gap;
        this.gapExtend = gapExtend;
    }

    protected Object getTraceback() {
        StringBuffer align1Buf = new StringBuffer();
        StringBuffer align2Buf = new StringBuffer();
        Cell currentCell = getTracebackStartingCell();
        System.out.println("Sequence only Score: " + currentCell.getScore());
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


        float DNAidengtityScore;
        float DNAidengtityCount = 0;

        for (int i = 0; i < alignments[0].length(); i++) {
            char c1 = alignments[0].charAt(i);
            char c2 = alignments[1].charAt(i);

            if (c1 == c2 && (c1 == 'A' || c1 == 'T' || c1 == 'C' || c1 == 'G')){
                DNAidengtityCount = DNAidengtityCount + 1;
            }
        }
        DNAidengtityScore = DNAidengtityCount / alignments[0].length();
        System.out.println("IdengtityScore: "+ DNAidengtityScore);
        System.out.println(alignments[0]);
        System.out.println(alignments[1]);


//        return score;
    }

    public String[] getAlignment() {
        ensureTableIsFilledIn();
        alignments = (String[]) getTraceback();
        return alignments;
    }

    protected abstract Cell getTracebackStartingCell();
}
