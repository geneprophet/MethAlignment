package ngdc.cn;


import ngdc.cn.Ensembl.EnsemblRest;
import ngdc.cn.seqalign.MethNeedlemanWunsch;
import ngdc.cn.seqalign.MethSmithWaterman;
import ngdc.cn.seqalign.NeedlemanWunsch;
import ngdc.cn.seqalign.SmithWaterman;
import org.apache.commons.cli.*;

/**
 * Hello world!
 * @author Kang
 * 主程序入口
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        System.out.println( "Hello World!" );
        //创建选项集
        Options options = new Options();
        //添加选项
        options.addOption("h","help",false,"the description of help!");
        options.addOption("q","QuerySpecies",true,"please specify the specie of query region!");
        options.addOption("a","QueryChromosome",true,"please specify the chromosome of query region!");
        options.addOption("b","QueryStrand",true,"please specify the strand of query region: 1 or -1");
        options.addOption("c","QueryStart",true,"please specify the start of query region!");
        options.addOption("d","QueryEnd",true,"please specify the end of query region!");
        options.addOption("e","QueryMethylation",true,"please specify the file path of the query  methylation!");
        options.addOption("t","TargetSpecies",true,"please specify the specie of target region!");
        options.addOption("v","TargetChromosome",true,"please specify the chromosome of target region!");
        options.addOption("w","TargetStrand",true,"please specify the strand of target region: 1 or -1");
        options.addOption("x","TargetStart",true,"please specify the start of target region!");
        options.addOption("y","TargetEnd",true,"please specify the end of target region!");
        options.addOption("z","TargetMethylation",true,"please specify the file path of target the methylation!");
        //解析参数
        CommandLineParser parser = new PosixParser();
        CommandLine cmd = parser.parse(options, args);
        if(cmd.hasOption("h")){
            System.out.println("help information....");
            System.out.println(cmd.getOptionValue("h"));
            System.exit(0);
        }


//        IntergateMethData intergateMethData1 = new IntergateMethData(cmd.getOptionValue("q"), cmd.getOptionValue("a"), cmd.getOptionValue("b"), cmd.getOptionValue("c"), cmd.getOptionValue("d"),cmd.getOptionValue("e"));
//        String seq1 = intergateMethData1.intergateSequence();
//        IntergateMethData intergateMethData2 = new IntergateMethData(cmd.getOptionValue("t"), cmd.getOptionValue("v"), cmd.getOptionValue("w"), cmd.getOptionValue("x"), cmd.getOptionValue("y"),cmd.getOptionValue("z"));
//        String seq2 = intergateMethData2.intergateSequence();


        EnsemblRest human = new EnsemblRest("human", "5", "-1", "109687366", "109689865");
        String dna1 = human.GetSequence();
        EnsemblRest mouse = new EnsemblRest("mouse", "17", "-1", "64598736", "64601235");
        String dna2 = mouse.GetSequence();

//        System.out.println(dna1);
//        System.out.println(dna2);

        IntergateMethData intergateMethData1 = new IntergateMethData(human,"E:/IdeaProjects/ENSG00000112893_promoter2.txt");
        String seq1 = intergateMethData1.intergateSequence();
        IntergateMethData intergateMethData2 = new IntergateMethData(mouse,"E:/IdeaProjects/ENSMUSG00000024085_promoter2.txt");
        String seq2 = intergateMethData2.intergateSequence();

        SmithWaterman smithWaterman = new SmithWaterman(dna1, dna2);
        smithWaterman.getAlignmentScore();

        NeedlemanWunsch needlemanWunsch = new NeedlemanWunsch(dna1, dna2);
        needlemanWunsch.getAlignmentScore();


        MethSmithWaterman methSmithWaterman2 = new MethSmithWaterman(seq1,seq2);
        methSmithWaterman2.getAlignmentScore();


        MethNeedlemanWunsch methNeedlemanWunsch = new MethNeedlemanWunsch(seq1,seq2);
        methNeedlemanWunsch.getAlignmentScore();


    }
}
