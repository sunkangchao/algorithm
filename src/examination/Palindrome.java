package examination;

/**
 * 最长回文字符串
 *
 * @author SunKangChao
 * @date 2021/7/13 23:46
 */
public class Palindrome {

    public int getLongestPalindrome(String A, int n) {
        // write code here

        int longestSize = 0;
        for (int i = 0; i < n; i++) {
             for (int j = 0; j <= i; j++) {
                //j到i之间是否有回文字符串
                boolean flag = isPalindrome(A, j, i);
                if (flag) {
                    int palindromeSize = i - j + 1;
                    if (palindromeSize > longestSize) {
                        longestSize = palindromeSize;
                    }
                    break;
                }
            }
        }
        return longestSize;

    }

    //回文字符串判断
    public boolean isPalindrome(String str, int start, int end) {
        if (start < 0 || end > str.length()) {
            return false;
        }
        int length = end - start + 1;
        for (int i = start, j = 0; i < start + length / 2; i++, j++) {
            if (str.charAt(i) == str.charAt(end - j)) {
                continue;
            }
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        System.out.println(palindrome.isPalindrome("abba", 1, 2));
        System.out.println(palindrome.getLongestPalindrome("baabccc", 7));
    }

}
