public abstract class Operator {
    public String KeyStr;
    //int Priority;
    abstract void Operate(Expression expr, int OperatorPos);
}

final class Operator_ADD extends Operator {
    Operator_ADD() {
        super.KeyStr = "+";
    }
    void Operate(Expression expr, int OperatorPos) {
        try {
            int PosA = expr.PreKeyIndexOf(OperatorPos);
            int PosB = expr.NextKeyIndexOf(OperatorPos);
            Key KeyA = expr.KeyOf(PosA);
            Key KeyB = expr.KeyOf(PosB);
            double a = 0, b = 0, result;
            if (KeyA.isNum() & KeyB.isNum()) {
                a = KeyA.getDouble();
                b = KeyB.getDouble();
            } else {
                if (KeyA.isOP())
                    Operate(expr, PosA);
                if (KeyB.isOP())
                    Operate(expr, PosB);
            }
            result = a + b;
            expr.setexprKeyArray(PosA, new Key(result), OperatorPos, PosB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class Operator_SUB extends Operator {
    Operator_SUB() {
        super.KeyStr = "-";
    }
    void Operate(Expression expr, int OperatorPos) {
        try {
            int PosA = expr.PreKeyIndexOf(OperatorPos);
            int PosB = expr.NextKeyIndexOf(OperatorPos);
            Key KeyA = expr.KeyOf(PosA);
            Key KeyB = expr.KeyOf(PosB);
            double a = 0, b = 0, result;
            if (KeyA.isNum() & KeyB.isNum()) {
                a = KeyA.getDouble();
                b = KeyB.getDouble();
            } else {
                if (KeyA.isOP())
                    Operate(expr, PosA);
                if (KeyB.isOP())
                    Operate(expr, PosB);
            }
            result = a - b;
            expr.setexprKeyArray(PosA, new Key(result), OperatorPos, PosB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class Operator_MUL extends Operator {
    Operator_MUL() {
        super.KeyStr = "*";
    }
    void Operate(Expression expr, int OperatorPos) {
        try {
            int PosA = expr.PreKeyIndexOf(OperatorPos);
            int PosB = expr.NextKeyIndexOf(OperatorPos);
            Key KeyA = expr.KeyOf(PosA);
            Key KeyB = expr.KeyOf(PosB);
            double a = 0, b = 0, result;
            if (KeyA.isNum() & KeyB.isNum()) {
                a = KeyA.getDouble();
                b = KeyB.getDouble();
            } else {
                if (KeyA.isOP())
                    Operate(expr, PosA);
                if (KeyB.isOP())
                    Operate(expr, PosB);
            }
            result = a * b;
            expr.setexprKeyArray(PosA, new Key(result), OperatorPos, PosB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class Operator_DIV extends Operator {
    Operator_DIV() {
        super.KeyStr = "/";
    }
    void Operate(Expression expr, int OperatorPos) {
        try {
            int PosA = expr.PreKeyIndexOf(OperatorPos);
            int PosB = expr.NextKeyIndexOf(OperatorPos);
            Key KeyA = expr.KeyOf(PosA);
            Key KeyB = expr.KeyOf(PosB);
            double a = 0, b = 0, result;
            if (KeyA.isNum() & KeyB.isNum()) {
                a = KeyA.getDouble();
                b = KeyB.getDouble();
            } else {
                if (KeyA.isOP())
                    Operate(expr, PosA);
                if (KeyB.isOP())
                    Operate(expr, PosB);
            }
            result = a / b;
            expr.setexprKeyArray(PosA, new Key(result), OperatorPos, PosB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class Operator_DIV2 extends Operator {
    Operator_DIV2() {
        super.KeyStr = "div";
    }
    void Operate(Expression expr, int OperatorPos) {
        try {
            int PosA = expr.PreKeyIndexOf(OperatorPos);
            int PosB = expr.NextKeyIndexOf(OperatorPos);
            Key KeyA = expr.KeyOf(PosA);
            Key KeyB = expr.KeyOf(PosB);
            int a = 0, b = 0, result;
            if (KeyA.isNum() & KeyB.isNum()) {
                a = (int) KeyA.getDouble();
                b = (int) KeyB.getDouble();
            } else {
                if (KeyA.isOP())
                    Operate(expr, PosA);
                if (KeyB.isOP())
                    Operate(expr, PosB);
            }
            result = a / b;
            expr.setexprKeyArray(PosA, new Key(result), OperatorPos, PosB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class Operator_MOD extends Operator {
    Operator_MOD() {
        super.KeyStr = "mod";
    }
    void Operate(Expression expr, int OperatorPos) {
        try {
            int PosA = expr.PreKeyIndexOf(OperatorPos);
            int PosB = expr.NextKeyIndexOf(OperatorPos);
            Key KeyA = expr.KeyOf(PosA);
            Key KeyB = expr.KeyOf(PosB);
            double a = 0, b = 0, result;
            if (KeyA.isNum() & KeyB.isNum()) {
                a = KeyA.getDouble();
                b = KeyB.getDouble();
            } else {
                if (KeyA.isOP())
                    Operate(expr, PosA);
                if (KeyB.isOP())
                    Operate(expr, PosB);
            }
            result = a % b;
            expr.setexprKeyArray(PosA, new Key(result), OperatorPos, PosB);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class Operator_COMMA extends Operator {
    Operator_COMMA() {
        super.KeyStr = ",";
    }
    void Operate(Expression expr, int OperatorPos) {
    }
}

final class Operator_BRACKET_L extends Operator {   //TO-DO
    Operator_BRACKET_L() {
        super.KeyStr = "(";
    }
    void Operate(Expression expr, int OperatorPos) {
        try {
            int PosA = expr.NextKeyIndexOf(OperatorPos);
            int PosB = PosA;
            Key preKey=expr.KeyOf(expr.PreKeyIndexOf(OperatorPos));
            if (preKey.isOP()) {    //3*(1+2)
                int level = 1;
                for (int i = PosA; i <= expr.exprKeyArrayCnt; i = expr.NextKeyIndexOf(i)) {
                    Key key = expr.KeyOf(i);
                    switch (key.value) {
                        case "(":
                            level++;
                            break;
                        case ")":
                            if (level == 1)
                                PosB = expr.PreKeyIndexOf(i);
                            else level--;
                            break;
                        default:
                    }
                    if (PosB != PosA)
                        break;
                }
                expr.Calculate(PosA, PosB);
                expr.setexprKeyArray(OperatorPos, new Key());
                expr.setexprKeyArray(expr.NextKeyIndexOf(PosB), new Key());//i
            }
            //else throw
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

final class Operator_BRACKET_R extends Operator {
    Operator_BRACKET_R() {
        super.KeyStr = ")";
    }
    void Operate(Expression expr, int OperatorPos) {
    }
}

