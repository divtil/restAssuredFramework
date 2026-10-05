import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Snippetss {
static boolean isPalindrome(int x){
    int remainder;
         int y =x;   
        int palindrome=0;

        if(x>0){
        while(y>1){
            remainder = y%10;
            System.out.println("remainder "+remainder);
            palindrome = palindrome*10+remainder;
            System.out.println("y before "+y);
            y=y/10;
             System.out.println("y after "+y);
             System.out.println("palindrome "+palindrome);

        } if(palindrome == x){
            return true;
        }
            
        } return false;
}

static boolean isValid(String s){
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Opening brackets
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }

            // Closing brackets
            else {
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    static String removeDuplicates(String s) {
        String result = "";
        String [] arr = s.split(" ");
        ArrayList <String> list = new ArrayList<>(Arrays.asList(arr));
        int i = 0;
        for(int j = i+1; j<list.size(); j++){
            if(list.get(i).equals(list.get(j))){
                list.remove(j);
               i++;
            }
        }   
        String[] array = list.toArray(String[]::new);
        return String.join(" ", array);

    }
    public static void main(String[] args) {
     
        System.out.println(isPalindrome(6336));
        System.out.println(isValid("(){}[]"));
        System.out.println(removeDuplicates("Hare Krishna Hare Raam"));
    }

}
