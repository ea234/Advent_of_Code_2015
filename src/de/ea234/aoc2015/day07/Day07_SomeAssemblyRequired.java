package de.ea234.aoc2015.day07;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 7: Some Assembly Required ---
 * 
 * https://adventofcode.com/2015/day/7
 * 
 * https://www.reddit.com/r/adventofcode/comments/3vr4m4/day_7_solutions/
 * 
 * 
 * 
 * 
 * 
 * 
 * --- Day 8: I Heard You Like Registers ---
 * https://adventofcode.com/2017/day/8
 * https://github.com/ea234/Advent_of_Code_2017/blob/main/src/de/ea234/aoc2017/day08/Day08_IHeardYouLikeRegisters.java 
 * 
 * --- Day 23: Coprocessor Conflagration ---
 * https://adventofcode.com/2017/day/23
 * https://github.com/ea234/Advent_of_Code_2017/blob/main/src/de/ea234/aoc2017/day23/Day23_CoprocessorConflagration.java
 * 
 * 
 * ->        SET    123 TO x 
 * ->        SET    456 TO y 
 * AND       A    123 AND B    456 =     72 TO d 
 * AND       A    123 OR  B    456 =    507 TO e 
 * LSHIFT    A    123 =    492  TO f 
 * RSHIFT    A    456 =    114  TO g 
 * NOT       A    123 =  65412  TO h 
 * NOT       A    456 =  65079  TO i 
 * 
 * 
 * 
 * Register    1 = d         72
 * Register    2 = e        507
 * Register    3 = f        492
 * Register    4 = g        114
 * Register    5 = x        123
 * Register    6 = h      65412
 * Register    7 = y        456
 * Register    8 = i      65079
 * 
 * Result Part 1 -10
 * Result Part 2 0
 * 
 * 
 * 
 *  
 * </pre>
 */
public class Day07_SomeAssemblyRequired
{
  private static final int     LIGHT_ON       = 1;

  private static final int     LIGHT_OFF      = 0;

  private static final String  CHAR_LIGHT_ON  = "#";

  private static final String  CHAR_LIGHT_OFF = ".";

  private static final Pattern PATTERN        = Pattern.compile( "(\\d+),(\\d+)\\s+through\\s+(\\d+),(\\d+)" );

  public static void main( String[] args )
  {
    String j_str = "";

    j_str += "123 -> x";
    j_str += ",456 -> y";
    j_str += ",x AND y -> d";
    j_str += ",x OR y -> e";
    j_str += ",x LSHIFT 2 -> f";
    j_str += ",y RSHIFT 2 -> g";
    j_str += ",NOT x -> h";
    j_str += ",NOT y -> i";

    calculatePart01( j_str, true );

    calculate01( getListProd(), true );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    long result_part_02 = 0;

    int bit_mask_16bit = 0xFFFF;

    HashMap< String, Long > registers = new HashMap< String, Long >();

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        List< String > cmd = Arrays.stream( input_str.split( " " ) ).map( String::trim ).collect( Collectors.toList() );

        if ( cmd.get( 0 ).equals( "NOT" ) )
        {
          long register_value_a = registers.getOrDefault( cmd.get( 1 ), Long.valueOf( 0 ) ).longValue();

          long register_value_write = ( ~register_value_a ) & bit_mask_16bit;

          registers.put( cmd.get( 3 ), Long.valueOf( register_value_write ) );

          wl( String.format( "%-8s  A %6d = %6d  TO %s ", "NOT", register_value_a, register_value_write, cmd.get( 3 ) ) );
        }
        else if ( cmd.get( 1 ).equals( "->" ) )
        {
          long register_value_write = getRegisterOrVal( registers, cmd.get( 0 ) );

          registers.put( cmd.get( 2 ), Long.valueOf( register_value_write ) );

          wl( String.format( "%-8s  SET %6d TO %s ", "->", register_value_write, cmd.get( 2 ) ) );
        }
        else
        {
          long register_value_a = getRegisterOrVal( registers, cmd.get( 0 ) );

          long register_value_b = getRegisterOrVal( registers, cmd.get( 2 ) );

          long register_value_write = 0;

          if ( cmd.get( 1 ).equals( "RSHIFT" ) )
          {
            long shift_amount = Long.parseLong( cmd.get( 2 ) );

            register_value_write = ( register_value_a >> shift_amount ) & bit_mask_16bit;

            wl( String.format( "%-8s  A %6d = %6d  TO %s ", "RSHIFT", register_value_a, register_value_write, cmd.get( 4 ) ) );
          }
          else if ( cmd.get( 1 ).equals( "LSHIFT" ) )
          {
            long shift_amount = Long.parseLong( cmd.get( 2 ) );

            register_value_write = ( register_value_a << shift_amount ) & bit_mask_16bit;

            wl( String.format( "%-8s  A %6d = %6d  TO %s ", "LSHIFT", register_value_a, register_value_write, cmd.get( 4 ) ) );
          }
          else if ( cmd.get( 1 ).equals( "AND" ) )
          {
            register_value_write = ( register_value_a & register_value_b ) & bit_mask_16bit;

            wl( String.format( "%-8s  A %6d AND B %6d = %6d TO %s ", "AND", register_value_a, register_value_b, register_value_write, cmd.get( 4 ) ) );
          }
          else if ( cmd.get( 1 ).equals( "OR" ) )
          {
            register_value_write = ( register_value_a | register_value_b ) & bit_mask_16bit;

            wl( String.format( "%-8s  A %6d OR  B %6d = %6d TO %s ", "OR", register_value_a, register_value_b, register_value_write, cmd.get( 4 ) ) );
          }

          registers.put( cmd.get( 4 ), Long.valueOf( register_value_write ) );
        }
      }
    }

    wl( "" );
    wl( "" );
    wl( "" );

    int register_nr = 0;

    for ( String key : registers.keySet() )
    {
      register_nr++;

      long value = registers.getOrDefault( key, Long.valueOf( 0 ) ).longValue();

      if ( ( pKnzDebug ) && ( value > 0 ) )
      {
        wl( String.format( "Register %4d = %-4s  %6d", register_nr, key, value ) );
      }
    }

    long result_part_01 = registers.getOrDefault( "a", Long.valueOf( -10 ) ).longValue();

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static long getRegisterOrVal( HashMap< String, Long > registers, String pString )
  {
    long x_val = parseLongOrDefault( pString, -100 );

    if ( x_val == -100 )
    {
      x_val = registers.getOrDefault( pString, Long.valueOf( 0 ) ).longValue();
    }

    return x_val;
  }

  public static long parseLongOrDefault( String pString, long pDefaultValue )
  {
    try
    {
      return Long.parseLong( pString );
    }
    catch ( NumberFormatException err_inst )
    {
      return pDefaultValue;
    }
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day07_input.txt";

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
