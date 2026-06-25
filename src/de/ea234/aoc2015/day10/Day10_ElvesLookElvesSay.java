package de.ea234.aoc2015.day10;

/**
 * <pre>
 * 
 * --- Day 10: Elves Look, Elves Say ---
 * https://adventofcode.com/2015/day/10
 * 
 * https://www.reddit.com/r/adventofcode/comments/3w6h3m/day_10_solutions/
 * 
 * 1                      11
 * 11                     21
 * 1211                   111221
 * 111221                 312211
 * 211                    1221
 * 
 * 
 * Nr   0  Length         22   Time 00:00:00:000
 * Nr   1  Length         52   Time 00:00:00:000
 * Nr   2  Length        122   Time 00:00:00:000
 * Nr   3  Length        280   Time 00:00:00:001
 * Nr   4  Length        636   Time 00:00:00:000
 * Nr   5  Length       1446   Time 00:00:00:001
 * Nr   6  Length       3306   Time 00:00:00:003
 * Nr   7  Length       7596   Time 00:00:00:010
 * Nr   8  Length      17498   Time 00:00:00:026
 * Nr   9  Length      40330   Time 00:00:00:097
 * Nr  10  Length      92882   Time 00:00:00:364
 * Nr  11  Length     213570   Time 00:00:01:486
 * Nr  12  Length     490064   Time 00:00:07:187
 * Nr  13  Length    1122108   Time 00:00:35:140
 *  
 * </pre>
 */
public class Day10_ElvesLookElvesSay
{
  public static void main( String[] args )
  {
    testGetNewString( "1"      );
    testGetNewString( "11"     );
    testGetNewString( "1211"   );
    testGetNewString( "111221" );
    testGetNewString( "211"    );

    calculate01();

    System.exit( 0 );
  }

  private static void calculate01()
  {
    int result_part_01 = 0;
    int result_part_02 = 0;

    wl( "" );
    wl( "" );

    String cur_string = "3113322113";

    for ( int round_nr = 0; round_nr < 40; round_nr++ )
    {
      long time_start = System.currentTimeMillis();

      cur_string += getNewString( cur_string );

      wl( String.format( "Nr %3d  Length %10d   Time %s", round_nr, cur_string.length(), getDebugLaufzeit( System.currentTimeMillis() - time_start ) ) );
    }

    result_part_01 = cur_string.length();

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
    wl( "" );
  }

  private static String testGetNewString( String pInput )
  {
    String result_string = getNewString( pInput );

    wl( String.format( "%-20s   %s", pInput, result_string ) );

    return result_string;
  }

  private static String getNewString( String pInput )
  {
    String result_string = "";

    int cur_index = 0;

    char cur_number_char = pInput.charAt( 0 );
    long cur_number_count = 0;

    /*
     * While-Schleife for parsing the input
     */
    while ( cur_index < pInput.length() )
    {
      /*
       * Aktuelles Zeichen aus der Eingabe lesen
       */
      char akt_char = pInput.charAt( cur_index );

      if ( akt_char == cur_number_char )
      {
        cur_number_count++;
      }
      else
      {
        result_string += "" + cur_number_count;

        result_string += "" + cur_number_char;

        cur_number_char = akt_char;

        cur_number_count = 1;
      }

      cur_index++;
    }

    result_string += "" + cur_number_count;

    result_string += "" + cur_number_char;

    return result_string;
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }

  private static String getDebugLaufzeit( long pAnzahlMillisekunden )
  {
    long zeit_differenz_betrag = pAnzahlMillisekunden;

    long m_laufzeit_stunden  = 0;
    long m_laufzeit_minuten  = 0;
    long m_laufzeit_sekunden = 0;
    long m_laufzeit_milli_s  = 0;

    if ( zeit_differenz_betrag > 0 )
    {
      m_laufzeit_milli_s = (long) ( zeit_differenz_betrag % 1000 );

      zeit_differenz_betrag /= 1000;

      m_laufzeit_sekunden = (long) ( zeit_differenz_betrag % 60 );

      zeit_differenz_betrag /= 60;

      m_laufzeit_minuten = (long) ( zeit_differenz_betrag % 60 );

      m_laufzeit_stunden = (long) zeit_differenz_betrag / 60;
    }

    return ( m_laufzeit_stunden < 10 ? "0" : "" ) + m_laufzeit_stunden + ":" + ( m_laufzeit_minuten < 10 ? "0" : "" ) + m_laufzeit_minuten + ":" + ( m_laufzeit_sekunden < 10 ? "0" : "" ) + m_laufzeit_sekunden + ":" + ( m_laufzeit_milli_s < 10 ? "00" : ( m_laufzeit_milli_s < 100 ? "0" : "" ) ) + m_laufzeit_milli_s;
  }
}
