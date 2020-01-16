package ngdc.cn;

import ngdc.cn.Ensembl.EnsemblRest;

/**
 * 刻舟求剑整合原始序列和甲基化信息
 * @author Kang
 */
public class IntergateMethData {
    protected EnsemblRest Sequence;
    protected String filePath;
    public IntergateMethData(EnsemblRest ensemblRest,String filePath){
        this.Sequence = ensemblRest;
        this.filePath = filePath;
    }
    public String intergateSequence() throws Exception {
//        EnsemblRest Sequence= new EnsemblRest(ensemblRest.getSpecies(),ensemblRest.getChromosome(),ensemblRest.getStrand(),ensemblRest.getStart(), ensemblRest.getEnd());
        ReadFile readFile = new ReadFile(filePath);

        StringBuffer seq = new StringBuffer(Sequence.GetSequence());
//TODO:两次遍历整合序列,刻舟求剑
//        System.out.println(seq);
        if (Sequence.getStrand() == "1"){
            for (int i = 0; i < readFile.ReadFileToString().split("\n").length; i++){
                if (seq.charAt(Integer.parseInt(readFile.ReadFileToString().split("\n")[i].split("\t")[0])-Integer.parseInt(Sequence.getStart())) == 'C'){
                    seq.setCharAt(Integer.parseInt(readFile.ReadFileToString().split("\n")[i].split("\t")[0])-Integer.parseInt(Sequence.getStart()),readFile.ReadFileToString().split("\n")[i].split("\t")[1].charAt(0));
                }
            }
        }else if (Sequence.getStrand() == "-1"){
            for (int i = 0; i < readFile.ReadFileToString().split("\n").length; i++){
                if (seq.charAt(Integer.parseInt(Sequence.getEnd())-Integer.parseInt(readFile.ReadFileToString().split("\n")[i].split("\t")[0])) == 'C'){
                    seq.setCharAt(Integer.parseInt(Sequence.getEnd())-Integer.parseInt(readFile.ReadFileToString().split("\n")[i].split("\t")[0]),readFile.ReadFileToString().split("\n")[i].split("\t")[1].charAt(0));
                }
            }
        }
/*        for (int j = 0; j < seq.length(); j++){
            if (seq.charAt(j) == 'L' || seq.charAt(j) == 'M' || seq.charAt(j) == 'H'){
                seq.insert(j+1,'C');
            }
        }
        System.out.println(seq);*/
        return seq.toString();

    }
}
