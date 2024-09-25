package examination;
;

/**
 * 二分查找
 * 二分查找时一定需要保证自身是有序的
 * 完成基本功能离优质代码还有一步优化之路
 * @author SunKangChao
 * @date 2021/8/16 00:18
 */
public class SearchByBinarySpit {

    public int search(int[] nums, int target) {
        // write code here
        int start = 0;
        int end = nums.length - 1;
        //[1,2,3,4,5]
        while (end >= start) {
            int medium = (start + end) / 2, mediumValue = nums[medium];

            if (mediumValue == target) {
                return medium;
            } else if (mediumValue > target) {
                end = medium - 1;
            } else {
                start = medium + 1;
            }
        }
        //没找到
        return -1;
    }


    public static void main(String[] args) {
        SearchByBinarySpit instance = new SearchByBinarySpit();
        System.out.println(instance.search(new int[]{1, 4, 5, 6, 8, 9}, 4));

    }
}
