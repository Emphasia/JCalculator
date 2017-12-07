/*
+ - * /
"取余", "向下取整", "向上取整", "四舍五入/取整",
"平方根", "立方根", "e底指数", "次方",
"对数", "自然对数",
"绝对值",
"阶乘", "求和", "求积",
"正弦", "余弦", "正切", "反正弦", "反余弦", "反正切",
"最小", "最大",

"实部", "虚部",
"辐角", "范数", "共轭复数",
"导函数", "极限", "求值", "函数零点", "定积分",
"最大公约", "最小公倍",
"排列", "组合",
"进制",

"入桟", "出桟",
"字体大小", "自定义"
"清空",
*/
public class JCalculator {
    public static void main(String[] args) {
        String string = "1+2*(2+3)";
        Expression expr = new Expression(string);
        try {
            System.out.println(expr.value());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
