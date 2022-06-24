package examination;

/**
 * 计算字符串表达式的值
 *
 * @author SunKangChao
 * @date 2021/7/14 16:07
 */
public class ExpressionCalc {

    //   ((10+2)*10-(100-(10+20*10-(2*3)))*10*1*2)-2

    public int solve(String s) {

        //"(2*(3-5))*5"
        if (s.contains("(") && s.contains(")")) {
            int firstLeftIndex = s.indexOf("(");
            int startIndex = s.indexOf("(");

            int firstRightIndex = s.indexOf(")");
            int lastIndex = s.indexOf(")");

            while (s.indexOf("(", startIndex + 1) != -1) {
                startIndex = s.indexOf("(", startIndex + 1);
                if (startIndex < firstRightIndex) {
                    lastIndex = s.indexOf(")", lastIndex + 1);
                }
            }

            int rs = solve(s.substring(firstLeftIndex + 1, lastIndex));
            String resultStr = s.substring(0, firstLeftIndex) + rs + s.substring(lastIndex + 1);

            return solve(resultStr);
        }
        return addSolve(s);

    }

    // 2*2-2
    public int addSolve(String str) {

        if (!str.contains("+")) {
            if (str.contains("-")) {
                return subSolve(str);
            } else if (str.contains("*")) {
                return mulSolve(str);
            } else {
                return Integer.parseInt(str);
            }
        }

        String[] addList = str.split("\\+");
        int sum = 0;
        for (String expr : addList) {
            if (expr.contains("-")) {
                sum += subSolve(expr);
            } else if (expr.contains("*")) {
                sum += mulSolve(expr);
            } else {
                //不含加号、减号、乘号
                sum += Integer.parseInt(expr);
            }
        }
        return sum;

    }

    public int subSolve(String str) {

        //直接带*-相邻的情况，直接走乘法，不能在分割了
        if(str.indexOf("*") == str.indexOf("-") - 1){
            return mulSolve(str);
        }

        //含减号
        String[] subList = str.split("-");
        int sum = mulSolve(subList[0]);
        for (int i = 1; i < subList.length; i++) {
            //是否含有乘号
            if (subList[i].contains("*")) {
                sum -= mulSolve(subList[i]);
            } else {
                //没有符号
                sum -= Integer.parseInt(subList[i]);
            }
        }
        return sum;

    }

    public int mulSolve(String str) {

        if ("".equals(str)) {
            return 0;
        }
        if (!str.contains("*")) {
            return Integer.parseInt(str);
        }
        //含乘号

        String[] mulList = str.split("\\*");
        int sum = 1;
        for (String expr : mulList) {
            sum *= Integer.parseInt(expr);
        }
        return sum;
    }

    public static void main(String[] args) {
        ExpressionCalc calc = new ExpressionCalc();
//        int result = calc.solve("(2*(3-5))*5");
//        int result2 = calc.addSolve("2*-2");
        int result3 = calc.solve("((10+2)*10-(100-(10+20*10-(2*3)))*10*1*2)-2");
        System.out.println(result3);
    }


}
