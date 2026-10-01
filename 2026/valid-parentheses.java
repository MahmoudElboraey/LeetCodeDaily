class Solution {

    private boolean isOpen(char c){
        return c == '(' || c == '{' || c == '[';
    }
    private static Map<Character , Character > eqChar = new HashMap<>();

    static {
        eqChar.put(')' , '(');
        eqChar.put('}' , '{');
        eqChar.put(']' , '[');
    }
    public boolean isValid(String s) {

        Deque<Character > stack = new ArrayDeque<>();
        for (char c : s.toCharArray()){
            if (isOpen(c)){
                stack.push(c);
            }else {
                char ch = eqChar.get(c);
                if (stack.isEmpty() || stack.pop() != ch) return false;
            }
        }
        return true && stack.isEmpty();
        
    }
}