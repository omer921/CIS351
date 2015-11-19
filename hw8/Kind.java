// classifications of syntactic objects
public enum Kind { 
    CLOSEPAR, 
    DIVIDE, 
    ENDEXP, 
    ENDINPUT, 
    MINUS, 
    NUMBER,
    PLUS, 
    OPENPAR, 
    SPACE, 
    STRANGE, 
    SYMBOL, 
    TIMES;
    
  ////////////////////////////////////////////////////////////////////////
  // classify a syntactic object by its first character
  public static Kind classify(char first) {
    if (Character.isLetter(first)) return Kind.SYMBOL;
    if (Character.isDigit(first)) return Kind.NUMBER;
    switch (first) {
      case '+': return Kind.PLUS; 
      case '-': return Kind.MINUS;
      case '*': return Kind.TIMES;
      case '/': return Kind.DIVIDE;
      case '(': return Kind.OPENPAR;
      case ')': return Kind.CLOSEPAR;
      case ' ':
      case '\t': return Kind.SPACE;
      case '#': return Kind.ENDEXP;
      case '%': return Kind.ENDINPUT;
      default:  return Kind.STRANGE;
    }
  }
}
