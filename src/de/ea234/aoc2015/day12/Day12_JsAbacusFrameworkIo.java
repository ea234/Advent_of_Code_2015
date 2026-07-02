package de.ea234.aoc2015.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * 
 * --- Day 12: JSAbacusFramework.io ---
 * 
 * https://adventofcode.com/2015/day/12
 * 
 * https://www.reddit.com/r/adventofcode/comments/3wh73d/day_12_solutions/
 * 
 * RegEx   Nr     1   Value      1   Sum          1
 * RegEx   Nr     2   Value      5   Sum          6
 * RegEx   Nr     3   Value      1   Sum          7
 * RegEx   Nr     4   Value      2   Sum          9
 * RegEx   Nr     5   Value      3   Sum         12
 * RegEx   Nr     6   Value      1   Sum         13
 * RegEx   Nr     7   Value      2   Sum         15
 * RegEx   Nr     8   Value      3   Sum         18
 * RegEx   Nr     9   Value      4   Sum         22
 * RegEx   Nr    10   Value      5   Sum         27
 * 
 * 
 * ---- Test Json Number Parser ---------------------------------------------------
 * 
 * JSON Length     68   {[1,"red",5],[1,{"c":"red","b":2},3]{"d":"red","e":[1,2,3,4],"f":5}}
 * 
 * +
 * Index      1   RD    0   Element Number Value        0
 * Index      3   RD    1   Cur Number                  1    
 * Index     11   RD    1   Cur Number                  5    
 * Index     15   RD    1   Cur Number                  1    
 * +
 * Index     17   RD    1   Element Number Value        7
 * Index     32   RD    2   Cur Number                  2    #### RED ####
 * Index     33   RD    2   Element Number Value        2
 * -
 * Index     35   RD    1   Cur Number                  3    
 * +
 * Index     37   RD    1   Element Number Value       12
 * Index     53   RD    2   Cur Number                  1    #### RED ####
 * Index     55   RD    2   Cur Number                  2    #### RED ####
 * Index     57   RD    2   Cur Number                  3    #### RED ####
 * Index     59   RD    2   Cur Number                  4    #### RED ####
 * Index     66   RD    2   Cur Number                  5    #### RED ####
 * Index     67   RD    2   Element Number Value       15
 * -
 * Index     68   RD    1   Element Number Value       27
 * -
 * =
 * Index     68   RD    0   Element Number Value       27
 * 
 * 
 * ---- Knz Do Red Check ----------------------------------------------------------
 * 
 * JSON Length     68   {[1,"red",5],[1,{"c":"red","b":2},3]{"d":"red","e":[1,2,3,4],"f":5}}
 * 
 * +
 * Index      1   RD    0   Element Number Value        0
 * Index      3   RD    1   Cur Number                  1    
 * Index     11   RD    1   Cur Number                  5    
 * Index     15   RD    1   Cur Number                  1    
 * +
 * Index     17   RD    1   Element Number Value        7
 * Index     32   RD    2   Cur Number                  2    #### RED ####
 * Index     33   RD    2   Element Number Value        0    #### RED ####
 * -
 * Index     35   RD    1   Cur Number                  3    
 * +
 * Index     37   RD    1   Element Number Value       10
 * Index     53   RD    2   Cur Number                  1    #### RED ####
 * Index     55   RD    2   Cur Number                  2    #### RED ####
 * Index     57   RD    2   Cur Number                  3    #### RED ####
 * Index     59   RD    2   Cur Number                  4    #### RED ####
 * Index     66   RD    2   Cur Number                  5    #### RED ####
 * Index     67   RD    2   Element Number Value        0    #### RED ####
 * -
 * Index     68   RD    1   Element Number Value       10
 * -
 * =
 * Index     68   RD    0   Element Number Value       10
 * 
 * Result Part 1 27
 * Result Check  27
 * 
 * Result Part 2 10
 * 
 * ----------------------------------------------------------------------------
 * 
 * 
 * Result Part 1 156366
 * Result Check  156366
 * 
 * Result Part 2 53536
 * 
 * 
 * Result Part 1 156366
 * Result Check  156366
 * 
 * Result Part 2 91444
 *
 * 
 *  93829 to low
 * 106459 to high
 * 
 * </pre>
 */
public class Day12_JsAbacusFrameworkIo
{
  private static final Pattern PATTERN = Pattern.compile( "\\s+(\\d+),(\\d+)" );

  public static void main( String[] args )
  {
    //calculate01( "[1,{\"c\":\"red\",\"b\":2},3]" );
    //calculate01( "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}" );
    //calculate01( "[1,\"red\",5]" );

    calculate01( "{[1,\"red\",5],[1,{\"c\":\"red\",\"b\":2},3]{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}}" );

    //calculate01( getListProd() );

    //calculate01( getListProd().substring( 0, 5250 ) );

    System.exit( 0 );
  }

  private static void calculate01( String pListInput )
  {
    /*
     * Get the sum of all the numbers via reg ex matcher
     */
    long result_part_01 = getSumNumbersRegExMatcher( pListInput );

    wl( "" );
    wl( "" );
    wl( "---- Test Json Number Parser ---------------------------------------------------" );
    wl( "" );
    wl( String.format( "JSON Length %6d   %s", pListInput.length(), pListInput.subSequence( 0, Math.min( pListInput.length(), 350 ) ) ) );
    wl( "" );

    /*
     * Get the sum of all the numbers via recursive function.
     * 
     * The recursive function must return the same value as the reg ex function.
     * The value of result_part_03 must equal the value of result_part_01.
     */
    long result_part_03 = new JsonNumberParser().parseNumbersVersion3( pListInput, 0 );

    wl( "" );
    wl( "" );
    wl( "---- Knz Do Red Check ----------------------------------------------------------" );
    wl( "" );
    wl( String.format( "JSON Length %6d   %s", pListInput.length(), pListInput.subSequence( 0, Math.min( pListInput.length(), 350 ) ) ) );
    wl( "" );

    JsonNumberParser mini_json_parser = new JsonNumberParser();

    mini_json_parser.setKnzDoRedCheck( true );

    long result_part_02 = mini_json_parser.parseNumbersVersion3( pListInput, 0 );

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Check  " + result_part_03 );
    wl( "" );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static long getSumNumbersRegExMatcher( String pString )
  {
    long sum_numbers = 0;

    Pattern pattern = Pattern.compile( "-?\\d+" );

    Matcher matcher = pattern.matcher( pString );

    int number_nr = 0;

    while ( matcher.find() )
    {
      number_nr++;

      sum_numbers += Long.parseLong( matcher.group() );

      wl( String.format( "RegEx   Nr %5d   Value %6s   Sum %10d", number_nr, matcher.group(), sum_numbers ) );
    }

    return sum_numbers;
  }

  private static class JsonNumberParser
  {
    private boolean             knz_do_red_check   = false;

    private int                 parser_index       = 0;

    private static final char[] CHAR_NEGATIVE_SIGN = { ':', ',', '[' };

    public void setKnzDoRedCheck( boolean pKnzFlag )
    {
      knz_do_red_check = pKnzFlag;
    }

    private boolean isNegativePreceedingChar( char pChar )
    {
      for ( char x : CHAR_NEGATIVE_SIGN )
      {
        if ( x == pChar )
        {
          return true;
        }
      }

      return false;
    }

    public long parseNumbersVersion3( String pJson, int pRecursionDepth )
    {
      long result_cur_json_element = 0;

      long cur_number = 0;

      long negative_flag = 1;

      boolean knz_red_found = false;

      boolean knz_in_array = false;

      boolean knz_add_cur_numer = false;

      while ( parser_index < pJson.length() )
      {
        char cur_char = pJson.charAt( parser_index );

        if ( ( cur_char >= '0' ) && ( cur_char <= '9' ) )
        {
          /*
           * If the current char is a number, add the number up
           */

          cur_number = ( cur_number * 10 ) + ( cur_char - '0' );
        }
        else if ( ( cur_char == '-' ) && ( isNegativePreceedingChar( pJson.charAt( parser_index - 1 ) ) ) )
        {
          /*
           * Negative character found, set the value -1 to the negative-variable 
           */

          negative_flag = -1;
        }
        else if ( ( cur_char == 'r' ) && ( ( parser_index + 3 ) < pJson.length() ) && ( knz_in_array == false ) && ( pJson.charAt( parser_index + 1 ) == 'e' ) && ( pJson.charAt( parser_index + 2 ) == 'd' ) && ( pJson.charAt( parser_index + 3 ) == '\"' ) && ( pJson.charAt( parser_index - 1 ) == '\"' ) )
        {
          /*
           * Word "red" found. 
           */

          knz_red_found = true;

          /*
           * Consume the word "red", that is:
           * - 1 character for the current parser index
           * - 1 character for "r"
           * - 1 character for "e"
           * - 1 character for "
           * 
           * = 4 characters to consume
           * 
           * 
           * 
           * A    red","e":[    parser_index = 42
           * B    ,"e":[1,2,    parser_index = 46
           * 
           * (In the debug-statement the parser_index for B is increased by 11.
           *  That is because, the consuming of the current char is done further down the line)
           */
          //wl( "A    " + pJson.substring( parser_index, parser_index + 10 ) + "    parser_index = " + parser_index );

          parser_index += 3;

          //wl( "B    " + pJson.substring( parser_index, parser_index + 11 ) + "    parser_index = " + parser_index );

          //knz_add_cur_numer = ( cur_number != 0 );
        }
        else if ( cur_char == '[' )
        {
          /*
           * Array-Start
           * - Set flag-variable "in array" to true
           * - Set flag-variable "add number" to true
           * - consume the square bracket
           */

          knz_in_array = true;

          knz_add_cur_numer = ( cur_number != 0 );
        }
        else if ( cur_char == ']' )
        {
          /*
           * Array-End
           * - Set flag-variable "in array" to false
           * - Set flag-variable "add number" to true
           * - consume the square bracket
           */

          knz_add_cur_numer = ( cur_number != 0 );

          knz_in_array = false;
        }
        else
        {
          /*
           * Any other character
           * 
           * - Set flag-variable "add number" to true
           * - consume the character
           */

          knz_add_cur_numer = ( cur_number != 0 );
        }

        /*
         * *****************************************************************************
         */

        if ( knz_add_cur_numer )
        {
          /*
           * Multiply the current number with the negative flag value.
           * For negative values, this will be -1.
           * For positive values, this will be 1.
           */
          cur_number *= negative_flag;

          /*
           * Add the number to the current object value.
           */
          result_cur_json_element += cur_number;

          /*
           * Do some debug stuff
           */
          wl( String.format( "Index %6d   RD %4d   Cur Number           %8d    %s", parser_index, pRecursionDepth, cur_number, ( knz_red_found ? "#### RED ####" : "" ) ) );

          /*
           * Set the current number to 0
           */
          cur_number = 0;

          /*
           * Set the negative flag to 1
           */
          negative_flag = 1;

          /*
           * Reset the flag-variable "add number" to false;
           */
          knz_add_cur_numer = false;
        }

        /*
         * Consume the current index
         */
        parser_index++;

        /*
         * *****************************************************************************
         */

        if ( cur_char == '{' )
        {
          wl( "+" );
          wl( String.format( "Index %6d   RD %4d   Element Number Value %8d", parser_index, pRecursionDepth, result_cur_json_element ) );

          result_cur_json_element += parseNumbersVersion3( pJson, pRecursionDepth + 1 );

          wl( "-" );
        }
        else if ( cur_char == '}' )
        {
          if ( ( knz_red_found ) && ( knz_do_red_check ) )
          {
            wl( String.format( "Index %6d   RD %4d   Element Number Value %8d    #### RED ####", parser_index, pRecursionDepth, 0 ) );

            return 0;
          }

          wl( String.format( "Index %6d   RD %4d   Element Number Value %8d", parser_index, pRecursionDepth, result_cur_json_element ) );

          return result_cur_json_element;
        }
      }

      wl( "=" );
      wl( String.format( "Index %6d   RD %4d   Element Number Value %8d", parser_index, pRecursionDepth, result_cur_json_element ) );

      return result_cur_json_element;
    }

    //public long parseNumbersVersion2( String pJson, int pRecursionDepth )
    //{
    //  long result_cur_json_element = 0;
    //
    //  long cur_number              = 0;
    //
    //  long negative_flag           = 1;
    //
    //  boolean knz_red_found        = false;
    //
    //  boolean knz_in_array         = false;
    //
    //  boolean knz_add_cur_numer    = false;
    //
    //  while ( parser_index < pJson.length() )
    //  {
    //    char cur_char = pJson.charAt( parser_index );
    //
    //    if ( ( cur_char >= '0' ) && ( cur_char <= '9' ) )
    //    {
    //      /*
    //       * If the current char is a number, add the number up
    //       */
    //
    //      cur_number = ( cur_number * 10 ) + ( cur_char - '0' );
    //
    //      /*
    //       * Consume the number index
    //       */
    //      parser_index++;
    //    }
    //    else if ( ( cur_char == '-' ) && ( isNegativePreceedingChar( pJson.charAt( parser_index - 1 ) ) ) )
    //    {
    //      /*
    //       * Negative character found, set the value -1 to the negative-variable 
    //       */
    //
    //      negative_flag = -1;
    //
    //      /*
    //       * Consume the negative sign
    //       */
    //      parser_index++;
    //    }
    //    else if ( ( cur_char == 'r' ) && ( ( parser_index + 3 ) < pJson.length() ) && ( knz_in_array == false ) && ( pJson.charAt( parser_index + 1 ) == 'e' ) && ( pJson.charAt( parser_index + 2 ) == 'd' ) && ( pJson.charAt( parser_index + 3 ) == '\"' ) && ( pJson.charAt( parser_index - 1 ) == '\"' ) )
    //    {
    //      /*
    //       * Word "red" found. 
    //       */
    //
    //      knz_red_found = true;
    //
    //      /*
    //       * Consume the word "red", that is:
    //       * - 1 character for the current parser index
    //       * - 1 character for "r"
    //       * - 1 character for "e"
    //       * - 1 character for "
    //       * 
    //       * = 4 characters to consume
    //       * 
    //       * A    red","e":[    parser_index = 42
    //       * B    ,"e":[1,2,    parser_index = 46
    //       */
    //      //wl( "A    " + pJson.substring( parser_index, parser_index + 10 ) + "    parser_index = " + parser_index );
    //
    //      parser_index += 4;
    //
    //      //wl( "B    " + pJson.substring( parser_index, parser_index + 10 ) + "    parser_index = " + parser_index );
    //    }
    //    else if ( cur_char == '{' )
    //    {
    //      /*
    //       * Object-Start (Recursion Start)
    //       * - Add the current number to the total result
    //       * - Consume the curly bracket
    //       * - Parse the Object with recursion and add the result
    //       *   to the total result.
    //       */
    //
    //      cur_number *= negative_flag;
    //
    //      result_cur_json_element += cur_number;
    //
    //      cur_number    = 0;
    //
    //      negative_flag = 1;
    //
    //      knz_add_cur_numer = false;
    //
    //      parser_index++;
    //
    //      wl( "+" );
    //      wl( String.format( "Index %6d   RD %4d   Element Number Value %8d", parser_index, pRecursionDepth, result_cur_json_element ) );
    //
    //      result_cur_json_element += parseNumbersVersion2( pJson, pRecursionDepth + 1 );
    //
    //      wl( "-" );
    //    }
    //    else if ( cur_char == '}' )
    //    {
    //      /*
    //       * Object-End (Recursion End)
    //       * - Add the current number to the total resultnegative_flag
    //       * - Consume the curly bracket
    //       * - Return the value of the JSON-Object:
    //       *      - If the red-check is on and there was a "red", return 0
    //       *      - Otherwise, return the result of the object
    //       */
    //
    //      cur_number *= negative_flag;
    //
    //      result_cur_json_element += cur_number;
    //
    //      parser_index++;
    //
    //      if ( cur_number != 0 )
    //      {
    //        wl( String.format( "Index %6d   RD %4d   Cur Number           %8d    %s", parser_index, pRecursionDepth, cur_number, ( knz_red_found ? "#### RED ####" : "" ) ) );
    //      }
    //
    //      if ( ( knz_red_found ) && ( knz_do_red_check ) )
    //      {
    //        wl( String.format( "Index %6d   RD %4d   Element Number Value %8d    #### RED ####", parser_index, pRecursionDepth, 0 ) );
    //
    //        return 0;
    //      }
    //
    //      wl( String.format( "Index %6d   RD %4d   Element Number Value %8d", parser_index, pRecursionDepth, result_cur_json_element ) );
    //
    //      return result_cur_json_element;
    //    }
    //    else if ( cur_char == '[' )
    //    {
    //      /*
    //       * Array-Start
    //       * - Set flag-variable "in array" to true
    //       * - Set flag-variable "add number" to true
    //       * - consume the square bracket
    //       */
    //
    //      knz_in_array = true;
    //
    //      knz_add_cur_numer = ( cur_number != 0 );
    //
    //      parser_index++;
    //    }
    //    else if ( cur_char == ']' )
    //    {
    //      /*
    //       * Array-End
    //       * - Set flag-variable "in array" to false
    //       * - Set flag-variable "add number" to true
    //       * - consume the square bracket
    //       */
    //
    //      knz_add_cur_numer = ( cur_number != 0 );
    //
    //      knz_in_array = false;
    //
    //      parser_index++;
    //    }
    //    else
    //    {
    //      /*
    //       * Any other character
    //       * 
    //       * - Set flag-variable "add number" to true
    //       * - consume the character
    //       */
    //
    //      knz_add_cur_numer = ( cur_number != 0 );
    //
    //      parser_index++;
    //    }
    //
    //    if ( knz_add_cur_numer )
    //    {
    //      /*
    //       * Multiply the current number with the negative flag value.
    //       * For negative values, this will be -1.
    //       * For positive values, this will be 1.
    //       */
    //      cur_number *= negative_flag;
    //
    //      /*
    //       * Add the number to the current object value.
    //       */
    //      result_cur_json_element += cur_number;
    //
    //      /*
    //       * Do some debug stuff
    //       */
    //      wl( String.format( "Index %6d   RD %4d   Cur Number           %8d    %s", parser_index, pRecursionDepth, cur_number, ( knz_red_found ? "#### RED ####" : "" ) ) );
    //
    //      /*
    //       * Set the current number to 0
    //       */
    //      cur_number = 0;
    //
    //      /*
    //       * Set the negative flag to 1
    //       */
    //      negative_flag = 1;
    //
    //      /*
    //       * Reset the flag-variable "add number" to false;
    //       */
    //      knz_add_cur_numer = false;
    //    }
    //  }
    //
    //  wl( "=" );
    //  wl( String.format( "Index %6d   RD %4d   Element Number Value %8d", parser_index, pRecursionDepth, result_cur_json_element ) );
    //
    //  return result_cur_json_element;
    //}

  }

  //private static long parseNumbersVersion1( String pJson )
  //{
  //  long result_cur_json_element = 0;
  //
  //  long cur_number              = 0;
  //
  //  long negative_flag           = 1;
  //
  //  boolean knz_red_found        = false;
  //
  //  boolean knz_in_array         = false;
  //
  //  while ( parser_index < pJson.length() )
  //  {
  //    char cur_char = pJson.charAt( parser_index );
  //
  //    if ( ( cur_char >= '0' ) && ( cur_char <= '9' ) )
  //    {
  //      /*
  //       * If the current char is a number, add the number up
  //       */
  //
  //      cur_number = ( cur_number * 10 ) + ( cur_char - '0' );
  //    }
  //    else if ( ( cur_char == '-' ) && ( isNegativePreceedingChar( pJson.charAt( parser_index - 1 ) ) ) )
  //    {
  //      /*
  //       * Negative character found 
  //       */
  //
  //      negative_flag = -1;
  //    }
  //    else if ( ( cur_char == 'r' ) && ( ( parser_index + 3 ) < pJson.length() ) && ( knz_in_array == false ) )
  //    {
  //      /*
  //       * If the current char is 'r' 
  //       * check for "red"
  //       */
  //      if ( ( pJson.charAt( parser_index + 1 ) == 'e' ) && ( pJson.charAt( parser_index + 2 ) == 'd' ) && ( pJson.charAt( parser_index + 3 ) == '\"' ) && ( pJson.charAt( parser_index - 1 ) == '\"' ) )
  //      {
  //        knz_red_found = true;
  //      }
  //    }
  //    else if ( cur_char == '[' )
  //    {
  //      knz_in_array = true;parseNumbersVersion1
  //    }
  //    else if ( cur_char == ']' )
  //    {
  //      knz_in_array = false;
  //    }
  //    else if ( cur_char == '{' )
  //    {
  //      cur_number *= negative_flag;
  //
  //      result_cur_json_element += cur_number;
  //
  //      cur_number = 0;
  //
  //      negative_flag = 1;
  //
  //      parser_index++;
  //
  //      result_cur_json_element += parseNumbersVersion1( pJson );
  //    }
  //    else if ( cur_char == '}' )
  //    {
  //      cur_number *= negative_flag;
  //
  //      result_cur_json_element += cur_number;
  //
  //      parser_index++;
  //
  //      wl( "B " + cur_number + " " + result_cur_json_element );
  //
  //      if ( ( knz_red_found ) && ( knz_do_red_check ) )
  //      {
  //        return 0;
  //      }
  //
  //      return result_cur_json_element;
  //    }
  //    else
  //    {
  //      cur_number *= negative_flag;
  //
  //      result_cur_json_element += cur_number;
  //
  //      cur_number = 0;
  //
  //      negative_flag = 1;
  //
  //      wl( "B " + cur_number + " " + result_cur_json_element );
  //    }
  //
  //    parser_index++;
  //  }
  //
  //  wl( "B " + cur_number + " " + result_cur_json_element );
  //
  //  return result_cur_json_element;
  //}

  private static String getListProd()
  {
    String string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day12_input.txt";

    try
    {
      string_array = Files.readString( Path.of( datei_input ) );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }

    return string_array;
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }
}
