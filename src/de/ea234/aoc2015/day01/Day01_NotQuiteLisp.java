package de.ea234.aoc2015.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * <pre>
 * --- Day 1: Not Quite Lisp ---
 * https://adventofcode.com/2015/day/1
 * 
 * len      4   floor_up     2   floor_down     2   =     0    (step    -1)  Input (()) 
 * len      4   floor_up     2   floor_down     2   =     0    (step    -1)  Input ()() 
 * len      7   floor_up     5   floor_down     2   =     3    (step    -1)  Input (()(()( 
 * len      7   floor_up     5   floor_down     2   =     3    (step     0)  Input ))((((( 
 * len      7   floor_up     2   floor_down     5   =    -3    (step     0)  Input )())()) 
 * len      5   floor_up     2   floor_down     3   =    -1    (step     4)  Input ()()) 
 * len   7000   floor_up  3569   floor_down  3431   =   138    (step  1770)  Input ()(((()))(()()()((((()(((())((... 
 * 
 * </pre>
 */
public class Day01_NotQuiteLisp
{
  public static void main( String[] args )
  {
    calcFloor( "(())" );
    calcFloor( "()()" );
    calcFloor( "(()(()(" );
    calcFloor( "))(((((" );
    calcFloor( ")())())" );
    calcFloor( "()())" );

    calcFloor( getListProd() );

    System.exit( 0 );
  }

  private static int calcFloor( String pInput )
  {
    int len_input = pInput.length();

    int floor_up = 0;
    int floor_down = 0;
    int floor_nr = 0;

    int step_floor_nr_1 = -1;

    for ( int idx = 0; idx < len_input; idx++ )
    {
      char cur_char_idx = pInput.charAt( idx );

      if ( cur_char_idx == '(' )
      {
        floor_up++;

        floor_nr++;
      }
      else if ( cur_char_idx == ')' )
      {
        floor_down++;

        floor_nr--;
      }

      if ( floor_nr == -1 )
      {
        if ( step_floor_nr_1 == -1 )
        {
          step_floor_nr_1 = idx;
        }
      }
    }

    wl( String.format( "len %6d   floor_up %5d   floor_down %5d   = %5d    (step %5d)  Input %s ", len_input, floor_up, floor_down, ( floor_up - floor_down ), step_floor_nr_1, pInput.substring( 0, Math.min( len_input, 30 ) ) ) );

    return floor_up - floor_down;
  }

  private static String getListProd()
  {
    String string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day01_input.txt";

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