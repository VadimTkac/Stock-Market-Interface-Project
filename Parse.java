/*
 * String parsing and token handling routines
 * Tim McGowen
 * 2/4/2017
 */
public class Parse
{
  private int tokenPointer;
  private String[] tokens;
  private int numTokens;

  public Parse(String input)
  {
	// Break string into tokens. One or more consecutive spaces separates tokens.
    this.tokens = input.split(" +");
    this.tokenPointer = 0;
    this.numTokens = tokens.length;
  }

  public int size()
  {
    return numTokens;
  }

  public String toString()
  {
    String output = "No Tokens";

    if (! tokens[0].isEmpty())
    {
      output = tokens[0];
      for (int i=1; i<numTokens; i++)
        output = output + ":" + tokens[i];
    }

    return output;
  }

  // Every time this method is called, the next token in the tokens array is 
  // returned. The tokenPointer is incremented. A token of "" denotes no more
  // tokens are available.
  public String getNextToken()
  {
    String token = "";

    if (tokenPointer == 0 && tokens[0].isEmpty())
      return token;
    else
    {
      if (tokenPointer < numTokens)
      {
        token = tokens[tokenPointer];
        tokenPointer++;
        return token;
      }
      else
        return token;
    }
  }

  // This method is equivalent to the expression
  // (left isLessThanOrEqualto right)
  public boolean lessThanEqualTo(String left, String right)
  {
    return (tokenPrecedence(left) <= tokenPrecedence(right));
  }

  // Return true if we have found an operator. If false, assume you have 
  // an operand or number.
  public boolean isOperator(String token)
  {
    char tkn = token.charAt(0);

    switch (tkn)
    {
      case ')': return true;
      case '+': return true;
      // If we see a minus we could have the subtract operator or
      // the first character of a negative number
      case '-': { if ( token.matches("-[0123456789]+") )
                     return false; // We have a negative number
                  else
	                 return true;}
      case '*': return true;
      case '/': return true;
      case '^': return true;
      case '(': return true;
      default:  return false;
    }
  }

  // Indicates the precedence from 1 (lowest) to 3 (highest) of an operator.
  // This is used by method lessThanEqualTo.
  private int tokenPrecedence(String token)
  {
    char tkn = token.charAt(0);

    switch (tkn)
    {
      case '+': return 1;
      case '-': return 1;
      case '*': return 2;
      case '/': return 2;
      case '^': return 3;
      default:  return 0; // Undefined token
    }
  }
}
