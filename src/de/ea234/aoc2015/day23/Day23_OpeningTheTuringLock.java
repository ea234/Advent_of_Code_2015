package de.ea234.aoc2015.day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 23: Opening the Turing Lock ---
 * https://adventofcode.com/2015/day/23
 * 
 * https://www.reddit.com/r/adventofcode/comments/3xxdxt/day_23_solutions/
 * 
 *
 * https://adventofcode.com/2016/day/25
 * https://adventofcode.com/2016/day/12
 * https://adventofcode.com/2016/day/23
 * 
 * https://github.com/ea234/Advent_of_Code_2016/blob/main/src/de/ea234/aoc2016/day12/Day12_LeonardosMonorail.java
 * https://github.com/ea234/Advent_of_Code_2016/blob/main/src/de/ea234/aoc2016/day23/Day23_SafeCracking.java
 * https://github.com/ea234/Advent_of_Code_2016/blob/main/src/de/ea234/aoc2016/day25/Day25_ClockSignal.java
 *
 * ------------------------------------------------------------------------------------------
 * RUN 
 * A init = 1
 * 
 *      0    0 JIO a  VAL    1   JMP   18     #### JMP OK ####
 *      1   18 TPL a  OLD    1   NEW    3 
 *      2   19 INC a  OLD    3   NEW    4 
 *      3   20 TPL a  OLD    4   NEW   12 
 *      4   21 INC a  OLD   12   NEW   13 
 *      5   22 INC a  OLD   13   NEW   14 
 *      6   23 TPL a  OLD   14   NEW   42 
 *      7   24 INC a  OLD   42   NEW   43 
 *      8   25 TPL a  OLD   43   NEW  129 
 *      9   26 INC a  OLD  129   NEW  130 
 *     10   27 INC a  OLD  130   NEW  131 
 *     11   28 TPL a  OLD  131   NEW  393 
 *     12   29 TPL a  OLD  393   NEW 1179 
 *     13   30 INC a  OLD 1179   NEW 1180 
 *     14   31 INC a  OLD 1180   NEW 1181 
 *     15   32 TPL a  OLD 1181   NEW 3543 
 *     16   33 INC a  OLD 3543   NEW 3544 
 *     17   34 INC a  OLD 3544   NEW 3545 
 *     18   35 TPL a  OLD 3545   NEW 10635 
 *     19   36 INC a  OLD 10635   NEW 10636 
 *     20   37 INC a  OLD 10636   NEW 10637 
 *     21   38 TPL a  OLD 10637   NEW 31911 
 *     22   39 JIO a  VAL 31911               ---- NO JMP ----
 *     23   40 INC b  OLD    0   NEW    1
 *     ... 
 *    914   39 JIO a  VAL   16               ---- NO JMP ----
 *    915   40 INC b  OLD  156   NEW  157 
 *    916   41 JIE a  VAL   16   JMP    4     #### JMP OK ####
 *    917   45 HLF a  OLD   16   NEW    8 
 *    918   46 JMP   -7
 *    919   39 JIO a  VAL    8               ---- NO JMP ----
 *    920   40 INC b  OLD  157   NEW  158 
 *    921   41 JIE a  VAL    8   JMP    4     #### JMP OK ####
 *    922   45 HLF a  OLD    8   NEW    4 
 *    923   46 JMP   -7
 *    924   39 JIO a  VAL    4               ---- NO JMP ----
 *    925   40 INC b  OLD  158   NEW  159 
 *    926   41 JIE a  VAL    4   JMP    4     #### JMP OK ####
 *    927   45 HLF a  OLD    4   NEW    2 
 *    928   46 JMP   -7
 *    929   39 JIO a  VAL    2               ---- NO JMP ----
 *    930   40 INC b  OLD  159   NEW  160 
 *    931   41 JIE a  VAL    2   JMP    4     #### JMP OK ####
 *    932   45 HLF a  OLD    2   NEW    1 
 *    933   46 JMP   -7
 *    934   39 JIO a  VAL    1   JMP    8     #### JMP OK ####
 * 
 * Register 97  a  = 1
 * Register 98  b  = 160
 * 
 * ------------------------------------------------------------------------------------------
 * Result Part 1 307
 * Result Part 2 160
 * 
 * </pre> 
 */
public class Day23_OpeningTheTuringLock
{
  private static final int NO_INT_NUMBER = -1234;

  public static void main( String[] args )
  {
    String test_input = "inc a;jio a, +2;tpl a;inc a";

    calculatePart01( test_input, 0, true );

    calculate01( getListProd(), 1, true );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, int pInitValueA, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pInitValueA, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, int pInitValueA, boolean pKnzDebug )
  {
    int result_part_01 = 0;
    int result_part_02 = 0;

    result_part_01 = run( pListInput, 0, pKnzDebug );
    result_part_02 = run( pListInput, pInitValueA, pKnzDebug );

    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static int run( List< String > pListInput, int pInitValueA, boolean pKnzDebug )
  {
    if ( pKnzDebug )
    {
      wl( "" );
      wl( "------------------------------------------------------------------------------------------" );
      wl( "RUN " );
      wl( "A init = " + pInitValueA );
      wl( "" );
    }

    int[] register_vector = new int[ 130 ];

    for ( int idx = 97; idx < 130; idx++ )
    {
      register_vector[ idx ] = 0;
    }

    register_vector[ 97 ] = pInitValueA;

    int pgm_counter  = 0;
    int step_counter = 0;

    while ( ( pgm_counter < pListInput.size() ) && ( step_counter < 1_000_000_000 ) )
    {
      String input_str = pListInput.get( pgm_counter );

      char char_register_op = input_str.charAt( 4 );

      if ( !input_str.isBlank() )
      {
        if ( input_str.startsWith( "hlf" ) )
        {
          int value_old = register_vector[ ( (int) char_register_op ) ];

          int value_new = value_old / 2;

          register_vector[ ( (int) char_register_op ) ] = value_new;

          if ( pKnzDebug )
          {
            wl( String.format( "%6d %4d HLF %1s  OLD %4d   NEW %4d ", step_counter, pgm_counter, char_register_op, value_old, value_new ) );
          }

          pgm_counter++;
        }
        else if ( input_str.startsWith( "tpl" ) )
        {
          int value_old = register_vector[ ( (int) char_register_op ) ];

          int value_new = value_old * 3;

          register_vector[ ( (int) char_register_op ) ] = value_new;

          if ( pKnzDebug )
          {
            wl( String.format( "%6d %4d TPL %1s  OLD %4d   NEW %4d ", step_counter, pgm_counter, char_register_op, value_old, value_new ) );
          }

          pgm_counter++;
        }
        else if ( input_str.startsWith( "inc" ) )
        {
          int value_old = register_vector[ ( (int) char_register_op ) ];

          int value_new = value_old + 1;

          register_vector[ ( (int) char_register_op ) ] = value_new;

          if ( pKnzDebug )
          {
            wl( String.format( "%6d %4d INC %1s  OLD %4d   NEW %4d ", step_counter, pgm_counter, char_register_op, value_old, value_new ) );
          }

          pgm_counter++;
        }
        else if ( input_str.startsWith( "jmp" ) )
        {
          String cmd_parameter = input_str.substring( 4 );

          int value_p1 = getIntNumber( cmd_parameter );

          if ( pKnzDebug )
          {
            wl( String.format( "%6d %4d JMP %4d", step_counter, pgm_counter, value_p1 ) );
          }

          pgm_counter += value_p1;
        }
        else if ( input_str.startsWith( "jio" ) )
        {
          int value_old = register_vector[ ( (int) char_register_op ) ];

          if ( value_old == 1 )
          {
            String cmd_parameter = input_str.substring( 7 );

            int value_p1 = getIntNumber( cmd_parameter );

            if ( pKnzDebug )
            {
              wl( String.format( "%6d %4d JIO %1s  VAL %4d   JMP %4d     #### JMP OK ####", step_counter, pgm_counter, char_register_op, value_old, value_p1 ) );
            }

            pgm_counter += value_p1;
          }
          else
          {
            if ( pKnzDebug )
            {
              wl( String.format( "%6d %4d JIO %1s  VAL %4d               ---- NO JMP ----", step_counter, pgm_counter, char_register_op, value_old ) );
            }

            pgm_counter++;
          }
        }
        else if ( input_str.startsWith( "jie" ) )
        {
          int value_old = register_vector[ ( (int) char_register_op ) ];

          if ( ( value_old % 2 ) == 0 )
          {
            String cmd_parameter = input_str.substring( 7 );

            int value_p1 = getIntNumber( cmd_parameter );

            if ( pKnzDebug )
            {
              wl( String.format( "%6d %4d JIE %1s  VAL %4d   JMP %4d     #### JMP OK ####", step_counter, pgm_counter, char_register_op, value_old, value_p1 ) );
            }

            pgm_counter += value_p1;
          }
          else
          {
            if ( pKnzDebug )
            {
              wl( String.format( "%6d %4d JIE %1s  VAL %4d               ---- NO JMP ----", step_counter, pgm_counter, char_register_op, value_old ) );
            }

            pgm_counter++;
          }
        }
      }

      step_counter++;
    }

    wl( "" );

    for ( int idx = 97; idx < 99; idx++ )
    {
        wl( "Register " + idx + "  " + ( (char) idx ) + "  = " + register_vector[ idx ] );
    }

    return register_vector[ 98 ]; // 98 = ASCII b
  }

  private static int getIntNumber( String pString )
  {
    try
    {
      if ( pString != null )
      {
        return Integer.parseInt( pString.trim() );
      }
    }
    catch ( NumberFormatException err_inst )
    {
      // 
    }

    return NO_INT_NUMBER;
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day23_input.txt";

    try
    {
      string_array = Files.readAllLines( Path.of( datei_input ) );
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
