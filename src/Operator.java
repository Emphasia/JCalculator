public abstract class Operator {
    public String KeyStr;
    //int Priority;
    abstract void Operate(Expression expr, int OperatorPos);
}

final class Operator_Add extends Operator {
    Operator_Add() {
        super.KeyStr = "+";
    }
    void Operate(Expression expr, int OperatorPos) {
        int aPos = expr.PreKeyIndexOf(OperatorPos);
        int bPos = expr.NextKeyIndexOf(OperatorPos);
        double a = 0, b = 0, result;
        if (expr.PreKeyOf(OperatorPos).isNum() & expr.NextKeyOf(OperatorPos).isNum()) {
            try {
                a = expr.KeyOf(aPos).getDouble();
                b = expr.KeyOf(bPos).getDouble();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (expr.KeyOf(aPos).isOP())
                Operate(expr, aPos);
            if (expr.KeyOf(bPos).isOP())
                Operate(expr, bPos);
        }
        result = a + b;
        expr.setexprKeyArray(aPos, new Key(result), OperatorPos, bPos);
    }
}

final class Operator_Mul extends Operator {
    Operator_Mul() {
        super.KeyStr = "*";
    }
    void Operate(Expression expr, int OperatorPos) {
        int aPos = expr.PreKeyIndexOf(OperatorPos);
        int bPos = expr.NextKeyIndexOf(OperatorPos);
        double a = 0, b = 0, result;
        if (expr.PreKeyOf(OperatorPos).isNum() & expr.NextKeyOf(OperatorPos).isNum()) {
            try {
                a = expr.KeyOf(aPos).getDouble();
                b = expr.KeyOf(bPos).getDouble();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (expr.KeyOf(aPos).isOP())
                Operate(expr, aPos);
            if (expr.KeyOf(bPos).isOP())
                Operate(expr, bPos);
        }
        result = a * b;
        expr.setexprKeyArray(aPos, new Key(result), OperatorPos, bPos);
    }
}
