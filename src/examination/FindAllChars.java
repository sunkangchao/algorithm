package examination;

/**
 * 找寻出现过的全部字符
 *
 * @author SunKangChao
 * @date 2021/8/8 02:28
 */
public class FindAllChars {

    public String findAllChars(String str) {
        int temp = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            temp |= 1 << (c - 97);
        }

        for (int i = 0; i < 26; i++) {
            int rs = (temp >> i) & 1;
            if (rs == 1) {
                result.append((char) (i + 97));
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String str = "avbcdeeeefazy";
        FindAllChars instance = new FindAllChars();
        System.out.println(instance.findAllChars(str));
    }


}
