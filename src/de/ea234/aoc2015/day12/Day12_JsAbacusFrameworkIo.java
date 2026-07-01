package de.ea234.aoc2015.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 12: JSAbacusFramework.io ---
 * 
 * https://adventofcode.com/2015/day/12
 * 
 * https://www.reddit.com/r/adventofcode/comments/3wh73d/day_12_solutions/
 * 
 * </pre>
 */
public class Day12_JsAbacusFrameworkIo
{
  private static final Pattern PATTERN = Pattern.compile( "\\s+(\\d+),(\\d+)" );

  public static void main( String[] args )
  {

//    calculate01( "[1,{\"c\":\"red\",\"b\":2},3" );
//    calculate01( "{\"d\":\"red\",\"e\":[1,2,3,4],\"f\":5}" );
//    calculate01( "[1,\"red\",5]" );
    calculate01( getListProd() );

    //calculate01( getListProd().substring( 0, 5250 ) );

    System.exit( 0 );
  }

  private static void calculate01( String pListInput )
  {
    long result_part_01 = 0;

    result_part_01 += getSumNumbers( pListInput );

    parser_index = 0;

    knz_do_red_check = false;

    long result_part_03 = parseNumbersVersion2( pListInput + "}" );

    parser_index = 0;

    knz_do_red_check = true;

    long result_part_02 = parseNumbersVersion2( pListInput + "}" );

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Check  " + result_part_03 );
    wl( "" );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );

    if ( pListInput.length() < 300 )
    {
      wl( "pk " + pListInput );
    }
  }

  private static boolean knz_do_red_check   = true;

  private static int     parser_index       = 0;

  private static char[]  CHAR_NEGATIVE_SIGN = { ':', ',', '[' };

  private static boolean isNegativePreceedingChar( char pChar )
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

  /*
   *  93829 to low
   * 106459 to high
   * 
   * 
   * Result Part 1 156366
   * Result Check  156366
   * 
   * Result Part 2 91444
   * 
   * 
   */

  private static long parseNumbersVersion2( String pJson )
  {
    long result_cur_json_element = 0;

    long cur_number = 0;

    long negative_flag = 1;

    boolean knz_red_found = false;

    boolean knz_in_array = false;

    while ( parser_index < pJson.length() )
    {
      char cur_char = pJson.charAt( parser_index );

      if ( ( cur_char >= '0' ) && ( cur_char <= '9' ) )
      {
        /*
         * If the current char is a number, add the number up
         */

        cur_number = ( cur_number * 10 ) + ( cur_char - '0' );

        parser_index++;
      }
      else if ( ( cur_char == '-' ) && ( isNegativePreceedingChar( pJson.charAt( parser_index - 1 ) ) ) )
      {
        /*
         * Negative character found 
         */

        negative_flag = -1;

        parser_index++;
      }
      else if ( ( cur_char == 'r' ) && ( ( parser_index + 3 ) < pJson.length() ) && ( knz_in_array == false ) && ( pJson.charAt( parser_index + 1 ) == 'e' ) && ( pJson.charAt( parser_index + 2 ) == 'd' ) && ( pJson.charAt( parser_index + 3 ) == '\"' ) && ( pJson.charAt( parser_index - 1 ) == '\"' ) )
      {
        knz_red_found = true;

        //wl( pJson.substring( parser_index, parser_index + 10 ) + " " + parser_index );

        parser_index += 4;

        //wl( pJson.substring( parser_index, parser_index + 10 ) + " " + parser_index );
      }
      else if ( cur_char == '[' )
      {
        knz_in_array = true;

        parser_index++;
      }
      else if ( cur_char == ']' )
      {
        knz_in_array = false;

        parser_index++;
      }
      else if ( cur_char == '{' )
      {
        cur_number = 0;

        negative_flag = 1;

        parser_index++;

        result_cur_json_element += parseNumbersVersion2( pJson );
      }
      else if ( cur_char == '}' )
      {
        cur_number *= negative_flag;

        result_cur_json_element += cur_number;

        parser_index++;

        if ( ( knz_red_found ) && ( knz_do_red_check ) )
        {
          wl( String.format( "Index %6d   Element Number Value %10d    #### RED ####", parser_index, 0 ) );
          wl( "" );

          return 0;
        }

        wl( String.format( "Index %6d   Element Number Value %10d", parser_index, result_cur_json_element ) );
        wl( "" );

        return result_cur_json_element;
      }
      else
      {
        cur_number *= negative_flag;

        negative_flag = 1;

        result_cur_json_element += cur_number;

        if ( cur_number != 0 )
        {
          wl( String.format( "Index %6d   Element Number Value %10d    Cur Number %6d    %s", parser_index, result_cur_json_element, cur_number, ( knz_red_found ? "#### RED ####" : "" ) ) );
        }

        cur_number = 0;

        parser_index++;
      }
    }

    return result_cur_json_element;
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
  //      knz_in_array = true;
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

  private static long getSumNumbers( String pString )
  {
    long sum_numbers = 0;

    Pattern pattern = Pattern.compile( "-?\\d+" );

    Matcher matcher = pattern.matcher( pString );

    while ( matcher.find() )
    {
      sum_numbers += Long.parseLong( matcher.group() );

      wl( "A " + matcher.group() + " " + sum_numbers );
    }

    return sum_numbers;
  }

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
