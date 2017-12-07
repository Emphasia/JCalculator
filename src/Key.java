class Key {
    String value;
    //int pos;

    boolean isNULL = true;

    private boolean isOP;
    private boolean isNum;

    boolean isOP() {
        return isOP;
    }
    boolean isNum() {
        return isNum;
    }

    Key() {
        isNULL = true;
        isOP = false;
        isNum = false;
    }

    Key(double num) {
        value = String.valueOf(num);
        isOP = false;
        isNum = true;
        isNULL = false;
    }

    Key(String str) {
        value = str;
        isOP = true;
        isNum = false;
        isNULL = false;
    }

    static Operator[] Op = {
            //0
            new Operator_ADD(),
            new Operator_SUB(),
            new Operator_MUL(),
            new Operator_DIV(),
            new Operator_DIV2(),
            new Operator_MOD(),
            //6
            new Operator_COMMA(),
            new Operator_BRACKET_L(),
            new Operator_BRACKET_R(),
    };

    static int[] Priority = {7, 5, 2, 3, 0, 1};//根据Op[]中运算符序号

    public double getDouble() throws Exception {
        if (isNULL) throw new Exception();
        if (isOP || !isNum) throw new NumberFormatException();
        return Double.parseDouble(value);
    }

    public String getString() throws Exception {
        if (isNULL) throw new Exception();
        return value;
    }
}
