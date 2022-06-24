package examination;

/**
 * @author SunKangChao
 * @date 2021/7/26 19:56
 */
public class Multi {

    public static void main(String[] args) {

        Multi instance = new Multi();
        System.out.println(instance.multi("123", "9"));
        System.out.println(instance.multi("456", "7"));
        System.out.println(instance.multi("987654321", "0"));
        System.out.println(instance.multi("1234567", "8"));
    }

    public String multi(String str1, String str2) {
        if ("0".equals(str1) || "0".equals(str2)) {
            return "0";
        }
        int carry = 0;
        int num2 = str2.charAt(0) - 48;
        StringBuilder result = new StringBuilder();

        for (int i = str1.length() - 1; i >= 0; i--) {
            int num1 = str1.charAt(i) - 48;
            int multiResult = num1 * num2;

            int tempSum = multiResult % 10 + carry;
            if (tempSum >= 10) {
                result.insert(0, tempSum % 10);
                carry = 1;
            } else {
                result.insert(0, tempSum);
                carry = 0;
            }
            if (multiResult >= 10) {
                carry = carry + (multiResult / 10);
            }
        }

        if (carry != 0) {
            result.insert(0, carry);
        }
        return result.toString();
    }


}
