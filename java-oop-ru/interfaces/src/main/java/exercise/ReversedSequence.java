package exercise;

// BEGIN
class ReversedSequence implements CharSequence {
    String sourceString;
    String reversedString;
    ReversedSequence(String text) {
        sourceString = text;
        reversedString = reverseString(sourceString);
    }

    @Override
    public int length() {
        return reversedString.length();
    }

    @Override
    public char charAt(int index) {
        return reversedString.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedString.subSequence(start, end);
    }

    @Override
    public String toString() {
        return reversedString;
    }

    private String reverseString(String sourceString) {
        StringBuilder stringBuilder = new StringBuilder(sourceString);
        return stringBuilder.reverse().toString();
    }
}
// END
