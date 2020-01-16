package ngdc.cn.seqalign;
/**
 * @quthor Kang
 * 自定义打分矩阵的每个值
 * 除了gap罚分和extend罚分
 */

public class ScoreMatrix {
    float ATCG_gap;
    float ATCG_gapExtend;
    float LMH_gap;
    float LMH_gapExtend;
    float ATCG_LMH_gapExtend;
    float LMH_ATCG_gapExtend;
    float AAmatch;
    float ATmismatch;
    float ACmismatch;
    float AGmismatch;
    float ALmismatch;
    float AMmismatch;
    float AHmismatch;
    float TTmatch;
    float TCmismatch;
    float TGmismatch;
    float TLmismatch;
    float TMmismatch;
    float THmismatch;
    float CCmatch;
    float CGmismatch;
    float CLmismatch;
    float CMmismatch;
    float CHmismatch;
    float GGmatch;
    float GLmismatch;
    float GMmismatch;
    float GHmismatch;
    float LLmatch;
    float LMmismatch;
    float LHmismatch;
    float MMmatch;
    float MHmismatch;
    float HHmatch;
    public ScoreMatrix(){
        this.ATCG_gap = -7;
        this.ATCG_gapExtend = -2;
        this.LMH_gap = -7;
        this.LMH_gapExtend = -2;
        this.ATCG_LMH_gapExtend = -2;
        this.LMH_ATCG_gapExtend = -2;
        this.AAmatch = 5;
        this.ATmismatch = -4;
        this.ACmismatch = -4;
        this.AGmismatch = -4;
        this.ALmismatch = -4;
        this.AMmismatch = -4;
        this.AHmismatch = -4;
        this.TTmatch = 5;
        this.TCmismatch = -4;
        this.TGmismatch = -4;
        this.TLmismatch = -4;
        this.TMmismatch = -4;
        this.THmismatch = -4;
        this.CCmatch = 5;
        this.CGmismatch = -4;
        this.CLmismatch = -4;
        this.CMmismatch = -4;
        this.CHmismatch = -4;
        this.GGmatch = 5;
        this.GLmismatch = -4;
        this.GMmismatch = -4;
        this.GHmismatch = -4;
        this.LLmatch = 6;
        this.LMmismatch = -2;
        this.LHmismatch = -2;
        this.MMmatch = 6;
        this.MHmismatch = -2;
        this.HHmatch = 6;
    }
    public ScoreMatrix(float ATCG_gap, float ATCG_gapExtend, float LMH_gap, float LMH_gapExtend,float ATCG_LMH_gapExtend,float LMH_ATCG_gapExtend, float AAmatch, float ATmismatch, float ACmismatch, float AGmismatch, float ALmismatch, float AMmismatch, float AHmismatch, float TTmatch, float TCmismatch, float TGmismatch, float TLmismatch, float TMmismatch, float THmismatch, float CCmatch, float CGmismatch, float CLmismatch, float CMmismatch, float CHmismatch, float GGmatch, float GLmismatch, float GMmismatch, float GHmismatch, float LLmatch, float LMmismatch, float LHmismatch, float MMmatch, float MHmismatch, float HHmatch) {
        this.ATCG_gap = ATCG_gap;
        this.ATCG_gapExtend = ATCG_gapExtend;
        this.LMH_gap = LMH_gap;
        this.LMH_gapExtend = LMH_gapExtend;
        this.ATCG_LMH_gapExtend = ATCG_LMH_gapExtend;
        this.LMH_ATCG_gapExtend = LMH_ATCG_gapExtend;
        this.AAmatch = AAmatch;
        this.ATmismatch = ATmismatch;
        this.ACmismatch = ACmismatch;
        this.AGmismatch = AGmismatch;
        this.ALmismatch = ALmismatch;
        this.AMmismatch = AMmismatch;
        this.AHmismatch = AHmismatch;
        this.TTmatch = TTmatch;
        this.TCmismatch = TCmismatch;
        this.TGmismatch = TGmismatch;
        this.TLmismatch = TLmismatch;
        this.TMmismatch = TMmismatch;
        this.THmismatch = THmismatch;
        this.CCmatch = CCmatch;
        this.CGmismatch = CGmismatch;
        this.CLmismatch = CLmismatch;
        this.CMmismatch = CMmismatch;
        this.CHmismatch = CHmismatch;
        this.GGmatch = GGmatch;
        this.GLmismatch = GLmismatch;
        this.GMmismatch = GMmismatch;
        this.GHmismatch = GHmismatch;
        this.LLmatch = LLmatch;
        this.LMmismatch = LMmismatch;
        this.LHmismatch = LHmismatch;
        this.MMmatch = MMmatch;
        this.MHmismatch = MHmismatch;
        this.HHmatch = HHmatch;
    }

    public float getATCG_LMH_gapExtend() {
        return ATCG_LMH_gapExtend;
    }

    public void setATCG_LMH_gapExtend(float ATCG_LMH_gapExtend) {
        this.ATCG_LMH_gapExtend = ATCG_LMH_gapExtend;
    }

    public float getLMH_ATCG_gapExtend() {
        return LMH_ATCG_gapExtend;
    }

    public void setLMH_ATCG_gapExtend(float LMH_ATCG_gapExtend) {
        this.LMH_ATCG_gapExtend = LMH_ATCG_gapExtend;
    }

    public float getATCG_gap() {
        return ATCG_gap;
    }

    public void setATCG_gap(float ATCG_gap) {
        this.ATCG_gap = ATCG_gap;
    }

    public float getATCG_gapExtend() {
        return ATCG_gapExtend;
    }

    public void setATCG_gapExtend(float ATCG_gapExtend) {
        this.ATCG_gapExtend = ATCG_gapExtend;
    }

    public float getLMH_gap() {
        return LMH_gap;
    }

    public void setLMH_gap(float LMH_gap) {
        this.LMH_gap = LMH_gap;
    }

    public float getLMH_gapExtend() {
        return LMH_gapExtend;
    }

    public void setLMH_gapExtend(float LMH_gapExtend) {
        this.LMH_gapExtend = LMH_gapExtend;
    }

    public float getAAmatch() {
        return AAmatch;
    }

    public void setAAmatch(float AAmatch) {
        this.AAmatch = AAmatch;
    }

    public float getATmismatch() {
        return ATmismatch;
    }

    public void setATmismatch(float ATmismatch) {
        this.ATmismatch = ATmismatch;
    }

    public float getACmismatch() {
        return ACmismatch;
    }

    public void setACmismatch(float ACmismatch) {
        this.ACmismatch = ACmismatch;
    }

    public float getAGmismatch() {
        return AGmismatch;
    }

    public void setAGmismatch(float AGmismatch) {
        this.AGmismatch = AGmismatch;
    }

    public float getALmismatch() {
        return ALmismatch;
    }

    public void setALmismatch(float ALmismatch) {
        this.ALmismatch = ALmismatch;
    }

    public float getAMmismatch() {
        return AMmismatch;
    }

    public void setAMmismatch(float AMmismatch) {
        this.AMmismatch = AMmismatch;
    }

    public float getAHmismatch() {
        return AHmismatch;
    }

    public void setAHmismatch(float AHmismatch) {
        this.AHmismatch = AHmismatch;
    }

    public float getTTmatch() {
        return TTmatch;
    }

    public void setTTmatch(float TTmatch) {
        this.TTmatch = TTmatch;
    }

    public float getTCmismatch() {
        return TCmismatch;
    }

    public void setTCmismatch(float TCmismatch) {
        this.TCmismatch = TCmismatch;
    }

    public float getTGmismatch() {
        return TGmismatch;
    }

    public void setTGmismatch(float TGmismatch) {
        this.TGmismatch = TGmismatch;
    }

    public float getTLmismatch() {
        return TLmismatch;
    }

    public void setTLmismatch(float TLmismatch) {
        this.TLmismatch = TLmismatch;
    }

    public float getTMmismatch() {
        return TMmismatch;
    }

    public void setTMmismatch(float TMmismatch) {
        this.TMmismatch = TMmismatch;
    }

    public float getTHmismatch() {
        return THmismatch;
    }

    public void setTHmismatch(float THmismatch) {
        this.THmismatch = THmismatch;
    }

    public float getCCmatch() {
        return CCmatch;
    }

    public void setCCmatch(float CCmatch) {
        this.CCmatch = CCmatch;
    }

    public float getCGmismatch() {
        return CGmismatch;
    }

    public void setCGmismatch(float CGmismatch) {
        this.CGmismatch = CGmismatch;
    }

    public float getCLmismatch() {
        return CLmismatch;
    }

    public void setCLmismatch(float CLmismatch) {
        this.CLmismatch = CLmismatch;
    }

    public float getCMmismatch() {
        return CMmismatch;
    }

    public void setCMmismatch(float CMmismatch) {
        this.CMmismatch = CMmismatch;
    }

    public float getCHmismatch() {
        return CHmismatch;
    }

    public void setCHmismatch(float CHmismatch) {
        this.CHmismatch = CHmismatch;
    }

    public float getGGmatch() {
        return GGmatch;
    }

    public void setGGmatch(float GGmatch) {
        this.GGmatch = GGmatch;
    }

    public float getGLmismatch() {
        return GLmismatch;
    }

    public void setGLmismatch(float GLmismatch) {
        this.GLmismatch = GLmismatch;
    }

    public float getGMmismatch() {
        return GMmismatch;
    }

    public void setGMmismatch(float GMmismatch) {
        this.GMmismatch = GMmismatch;
    }

    public float getGHmismatch() {
        return GHmismatch;
    }

    public void setGHmismatch(float GHmismatch) {
        this.GHmismatch = GHmismatch;
    }

    public float getLLmatch() {
        return LLmatch;
    }

    public void setLLmatch(float LLmatch) {
        this.LLmatch = LLmatch;
    }

    public float getLMmismatch() {
        return LMmismatch;
    }

    public void setLMmismatch(float LMmismatch) {
        this.LMmismatch = LMmismatch;
    }

    public float getLHmismatch() {
        return LHmismatch;
    }

    public void setLHmismatch(float LHmismatch) {
        this.LHmismatch = LHmismatch;
    }

    public float getMMmatch() {
        return MMmatch;
    }

    public void setMMmatch(float MMmatch) {
        this.MMmatch = MMmatch;
    }

    public float getMHmismatch() {
        return MHmismatch;
    }

    public void setMHmismatch(float MHmismatch) {
        this.MHmismatch = MHmismatch;
    }

    public float getHHmatch() {
        return HHmatch;
    }

    public void setHHmatch(float HHmatch) {
        this.HHmatch = HHmatch;
    }
}
