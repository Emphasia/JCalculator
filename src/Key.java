class Key {
    String value;
    //int pos;

    boolean isNULL = true;

    boolean isOP;
    boolean isNum;

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
            new Operator_Mul(),
            new Operator_Add()
    };

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
