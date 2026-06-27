package de.ea234.aoc2015.day09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 9: All in a Single Night ---
 * https://adventofcode.com/2015/day/9
 * 
 * https://www.reddit.com/r/adventofcode/comments/3w192e/day_9_solutions/
 * 
 *  
 * </pre>
 */
public class Day09_AllInASingleNight
{
  public static void main( String[] args )
  {
    String test_data = "";

    test_data += "London to Dublin = 464";
    test_data += ",London to Belfast = 518";
    test_data += ",Dublin to Belfast = 141";

    calculatePart01( test_data, false );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    int result_part_01 = 0;

    int result_part_02 = 0;

    Properties prop_town_list = new Properties();

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        String[] parts = input_str.replaceAll( " to ", "," ).replaceAll( " = ", "," ).split( "," );

        wl( String.format( "From %-20s To %-20s  =  %s", parts[ 0 ], parts[ 1 ], parts[ 2 ] ) );

        prop_town_list.put( parts[ 0 ], "x" );
        prop_town_list.put( parts[ 1 ], "x" );
      }
    }

    for ( String key : prop_town_list.stringPropertyNames() )
    {
      String value = prop_town_list.getProperty( key );

      System.out.println( key + " = " + value );
    }

    wl( "" );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
    wl( "" );
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day09_input.txt";

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
