package de.ea234.aoc2015.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
 * </pre>
 */
public class Day12_JsAbacusFrameworkIo
{
  private static final Pattern PATTERN = Pattern.compile( "\\s+(\\d+),(\\d+)" );

  public static void main( String[] args )
  {
    calculate01( getListProd(), true );

    System.exit( 0 );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    long result_part_01 = 0;

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        result_part_01 += getSumNumbers( input_str );
      }
    }

    wl( "" );
    wl( "Result Part 2 " + result_part_01 );
    wl( "" );
  }

  private static long getSumNumbers( String pString )
  {
    long sum_numbers = 0;

    Pattern pattern = Pattern.compile( "-?\\d+" );

    Matcher matcher = pattern.matcher( pString );

    while ( matcher.find() )
    {
      sum_numbers += Long.parseLong( matcher.group() );
    }

    return sum_numbers;
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day12_input.txt";

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
