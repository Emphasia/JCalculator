public class Expression {
    public String expression;
    public Key[] exprKeyArray = new Key[101];
    private int exprKeyArrayCnt = 0;

    Expression(String expr) {
        expression = expr;
        for (int i = 0; i < 100; i++) {
            exprKeyArray[i] = new Key();
        }
        Parse();
    }

    //运算符
    static final String BRACKET_L = "(";//
    static final String BRACKET_R = ")";//
    static final String ADD = "+";//
    static final String SUB = "-";//
    static final String MUL = "*";//
    static final String DIV = "/";//
    /*
    static final String DIV2 = "div";//
    static final String MOD = "mod";//
    static final String SIN = "sin";//
    static final String COS = "cos";//
    static final String TAN = "tan";//
    static final String ARCSIN = "arcsin";//
    static final String ARCCOS = "arccos";//
    static final String ARCTAN = "arctan";//
    */
    //enum KeyStrEnum {ADD,SUB,MUL,DIV};
    static final String KeyStr[] = {ADD, SUB, MUL, DIV};
    final int KeyIndex[][] = new int[KeyStr.length][];

    final void Parse() {                            //将String类型表达式转换成Key[]
        int p = 1;
        String noMatch = "";
        for (int i = 0; i < expression.length(); i++) {
            int j;
            for (j = 0; j < KeyStr.length; j++) {
                if (expression.substring(i, i + KeyStr[j].length()).equals(KeyStr[j])) {
                    if (noMatch != "") {
                        if (isNum(noMatch)) {
                            exprKeyArray[p++] = new Key(Double.parseDouble(noMatch));
                            noMatch = "";
                        }
                        //else thorw error
                    }
                    exprKeyArray[p++] = new Key(KeyStr[j]);
                    i += KeyStr[j].length() - 1;
                    break;
                }
            }
            if (j == KeyStr.length) noMatch += expression.charAt(i);
        }
        if (noMatch != "" & isNum(noMatch)) {
            exprKeyArray[p] = new Key(Double.parseDouble(noMatch));
            //else thorw error
        }
        exprKeyArray[0] = new Key(String.valueOf(p));//保存Key[]即exprKeyArray个数
        /*
        for (int i = 0; i < KeyStr.length; i++) {
            int j = 0, pos = 0;
            KeyIndex[i] = new int[expression.length()];
            while (pos + KeyStr[i].length() <= expression.length()) {
                int id = expression.indexOf(KeyStr[i], pos);
                if (id >= 0) {
                    KeyIndex[i][j] = id;
                    j++;
                    pos = id;
                }
                pos++;
            }
        }
        */
    }

    //static final String patternStringInt="-?[1-9]\\d*";
    //static final String patternStringDounle="-?[1-9]\\d*\\.\\d*|-?0\\.\\d*[1-9]\\d*";
    /*
    final boolean isInt(String str) {
        return Pattern.matches('^'+patternStringInt+'$', expression)&&str!=null;
    }
    final boolean isDouble(String str) {
        return Pattern.matches('^'+patternStringDounle+'$', expression)&&str!=null;
    }
    final boolean isNum(String str) {
        return isInt(str) || isDouble(str);
    }
    */

    private boolean isNum(String str) {
        boolean ret = true;
        try {
            Double.parseDouble(str);
        } catch (Exception ex) {
            ret = false;
        }
        return ret;
    }

    public Key KeyOf(int pos) {
        return exprKeyArray[pos];
    }

    public Key NextKeyOf(int pos) {
        if (exprKeyArray[pos + 1].isNULL)
            return NextKeyOf(pos + 2);
        return exprKeyArray[pos + 1];
    }

    public Key PreKeyOf(int pos) {
        if (exprKeyArray[pos - 1].isNULL)
            return NextKeyOf(pos - 2);
        return exprKeyArray[pos - 1];
    }

    public int NextKeyIndexOf(int pos) {
        if (pos > Integer.parseInt(exprKeyArray[0].value)) return Integer.parseInt(exprKeyArray[0].value);
        if (exprKeyArray[pos + 1].isNULL)
            return NextKeyIndexOf(pos + 2);
        return pos + 1;
    }

    public int PreKeyIndexOf(int pos) {
        if (pos < 1) return 1;
        if (exprKeyArray[pos - 1].isNULL)
            return PreKeyIndexOf(pos - 2);
        return pos - 1;
    }

    /*
    private int pos = 1;
    public Key NextKey() {
        return exprKeyArray[pos++];
    }

    static public int IndexOf(String keyStr){
        for(int i=0;i<KeyStr.length;i++){
            if(KeyStr[i]==keyStr){
                return i;
            }
        }
        return -1;
    }
    */

    public int IndexOfLast(int i) {
        return KeyIndex[1][KeyIndex[1].length - 1];
    }

    public void setexprKeyArray(int setPos, Key setValue) {
        exprKeyArray[setPos] = setValue;
    }

    public void setexprKeyArray(int setPos, Key setValue, int NULLstart, int NULLend) {
        exprKeyArray[setPos] = setValue;
        for (int i = NULLstart; i <= NULLend; i++) {
            exprKeyArray[i].isNULL = true;
        }
    }

    public double getexprKeyArrayDouble(int pos) throws Exception {
        //if (pos >= exprKeyArrayCnt) throw new Exception();
        if (exprKeyArray[pos].isNULL)
            return getexprKeyArrayDouble(pos + 1);
        return exprKeyArray[pos].getDouble();
    }

    public String getexprKeyArrayString(int pos) throws Exception {
        if (pos >= exprKeyArrayCnt) throw new Exception();
        if (exprKeyArray[pos].isNULL)
            return getexprKeyArrayString(pos + 1);
        return exprKeyArray[pos].getString();
    }

    //1+2*2+3
    public void Calculate(int posA, int posB) {                //递归计算
        for (int i = 0; i < Key.Op.length; i++) {
            for (int j = posA; j <= posB; j++) {
                if (exprKeyArray[j].value == Key.Op[i].KeyStr)
                    Key.Op[i].Operate(this, j);
                //for(int pos:KeyIndex[i])
            }
        }
    }

    public double value() throws Exception {
        double value = 0;
        try {
            Calculate(1, Integer.parseInt(exprKeyArray[0].value));
            value = KeyOf(1).getDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
