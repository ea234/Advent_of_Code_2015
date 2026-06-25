package de.ea234.aoc2015.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 5: Doesn't He Have Intern-Elves For This? ---
 * https://adventofcode.com/2015/day/5
 * 
 * https://www.reddit.com/r/adventofcode/comments/3viazx/day_5_solutions/
 * 
 * 
 * checkStringPart1 aaa                   a  3  e  0  i  0  o  0  u  0 =  3   letter twice true      Is Nice true   
 * checkStringPart1 aaa                   a  3  e  0  i  0  o  0  u  0 =  3   letter twice true      Is Nice true   
 * checkStringPart1 aaa                   a  3  e  0  i  0  o  0  u  0 =  3   letter twice true      Is Nice true   
 * checkStringPart1 xazegov               a  1  e  1  i  0  o  1  u  0 =  3   letter twice false     Is Nice false  
 * checkStringPart1 aeiouaeiouaeiou       a  3  e  3  i  3  o  3  u  3 = 15   letter twice false     Is Nice false  
 * checkStringPart1 ugknbfddgicrmopn      a  0  e  0  i  1  o  1  u  1 =  3   letter twice true      Is Nice true   
 * checkStringPart1 jchzalrnumimnmhp      a  1  e  0  i  1  o  0  u  1 =  3   letter twice false     Is Nice false  
 * checkStringPart1 dvszwmarrgswjxmb      a  1  e  0  i  0  o  0  u  0 =  1   letter twice true      Is Nice false  
 * 
 * 
 * testCheckABA  abcdefeghi             CheckResult 1
 * testCheckABA  xyx                    CheckResult 1
 * testCheckABA  aaa                    CheckResult 1
 * 
 * 
 * testDoublePair  aabcdefgaa             CheckResult 1
 * testDoublePair  daabcdefgaad           CheckResult 1
 * testDoublePair  xyxy                   CheckResult 1
 * 
 * 
 * checkStringPart2  oanqfyqirkraesfq       aba 1   double pair 0   Is Nice false  
 * checkStringPart2  xilodxfuxphuiiii       aba 1   double pair 1   Is Nice true   
 * checkStringPart2  yukhnchvjkfwcbiq       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  bdaibcbzeuxqplop       aba 1   double pair 0   Is Nice false  
 * checkStringPart2  rqxvcultipkecdtu       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  kujsaiqojopvrygg       aba 1   double pair 0   Is Nice false  
 * checkStringPart2  vebzobmdbzvjnjtk       aba 1   double pair 1   Is Nice true   
 * checkStringPart2  vxsluutrwskslnye       aba 1   double pair 1   Is Nice true   
 * checkStringPart2  fdmzppzphhpzyuiz       aba 1   double pair 1   Is Nice true   
 * checkStringPart2  oehmvziiqwkzhzib       aba 1   double pair 1   Is Nice true   
 * checkStringPart2  dcjmxyzbzpohzprl       aba 1   double pair 1   Is Nice true   
 * checkStringPart2  uqfnmjwniyqgsowb       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  rbmxpqoblyxdocqc       aba 1   double pair 0   Is Nice false  
 * checkStringPart2  ebjclrdbqjhladem       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  uunoygzqjopwgmbg       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  piljqxgicjzgifso       aba 0   double pair 1   Is Nice false  
 * checkStringPart2  srlrukoaenyevykt       aba 1   double pair 0   Is Nice false  
 * checkStringPart2  tnpjtpwawrxbikct       aba 1   double pair 0   Is Nice false  
 * checkStringPart2  geczalxmgxejulcv       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  bzukgvyoqewniivj       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  iduapzclhhyfladn       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  fbpyzxdfmkrtfaeg       aba 0   double pair 0   Is Nice false  
 * checkStringPart2  yzsmlbnftftgwadz       aba 1   double pair 1   Is Nice true   
 * 
 * Result Part 1 255
 * Result Part 2 55
 * 
 *  
 * </pre>
 */
public class Day05_DoesntHeHaveInternElvesForThis
{
  public static void main( String[] args )
  {
    checkStringPart1( "aaa",              true );
    checkStringPart1( "aaa",              true );
    checkStringPart1( "aaa",              true );
    checkStringPart1( "aabbccdd",         true );
    checkStringPart1( "xazegov",          true );
    checkStringPart1( "aeiouaeiouaeiou",  true );
    checkStringPart1( "ugknbfddgicrmopn", true );
    checkStringPart1( "jchzalrnumimnmhp", true );
    checkStringPart1( "haegwjzuvuyypxyu", true );
    checkStringPart1( "dvszwmarrgswjxmb", true );

    wl( "" );
    wl( "" );

    testCheckABA( "abcdefeghi" );
    testCheckABA( "xyx"        );
    testCheckABA( "aaa"        );

    wl( "" );
    wl( "" );

    testDoublePair( "aabcdefgaa"   );
    testDoublePair( "daabcdefgaad" );
    testDoublePair( "xyxy"         );

    wl( "" );
    wl( "" );

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
    int result_part_01 = 0;

    int result_part_02 = 0;

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        result_part_01 += checkStringPart1( input_str, false );
      }
    }

    wl( "" );
    wl( "" );

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        result_part_02 += checkStringPart2( input_str, false );
      }
    }

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
    wl( "" );
  }

  private static int checkStringPart1( String pInput, boolean pKnzDebug )
  {
    /*
     * Check if Input it null?
     * If the input string is "null", 0 is returned
     */
    if ( pInput == null )
    {
      return 0;
    }

    int knz_vowel_a = 0;
    int knz_vowel_e = 0;
    int knz_vowel_i = 0;
    int knz_vowel_o = 0;
    int knz_vowel_u = 0;

    boolean knz_letter_appears_twice = false;

    int cur_index = 0;

    char previous_char = '0';

    /*
     * While-Schleife for parsing the input
     */
    while ( cur_index < pInput.length() )
    {
      /*
       * Aktuelles Zeichen aus der Eingabe lesen
       */
      char akt_char = pInput.charAt( cur_index );

      /*
       * Check for bad character sequenzes
       */
      if ( ( previous_char == 'a' ) && ( akt_char == 'b' ) ) return 0;
      if ( ( previous_char == 'c' ) && ( akt_char == 'd' ) ) return 0;
      if ( ( previous_char == 'p' ) && ( akt_char == 'q' ) ) return 0;
      if ( ( previous_char == 'x' ) && ( akt_char == 'y' ) ) return 0;

      /*
       * It contains at least three vowels (aeiou only), like aei, xazegov, or aeiouaeiouaeiou.
       */
      if ( akt_char == 'a' ) knz_vowel_a += 1;
      if ( akt_char == 'e' ) knz_vowel_e += 1;
      if ( akt_char == 'i' ) knz_vowel_i += 1;
      if ( akt_char == 'o' ) knz_vowel_o += 1;
      if ( akt_char == 'u' ) knz_vowel_u += 1;

      if ( akt_char == previous_char ) knz_letter_appears_twice = true;

      previous_char = akt_char;

      cur_index++;
    }

    int vowel_count = knz_vowel_a + knz_vowel_e + knz_vowel_i + knz_vowel_o + knz_vowel_u;

    boolean knz_is_nice_string = ( ( vowel_count >= 3 ) && knz_letter_appears_twice );

    if ( pKnzDebug )
    {
      wl( String.format( "checkStringPart1 %-20s  a %2d  e %2d  i %2d  o %2d  u %2d = %2d   letter twice %-6b    Is Nice %-6b ", pInput, knz_vowel_a, knz_vowel_e, knz_vowel_i, knz_vowel_o, knz_vowel_u, vowel_count, knz_letter_appears_twice, knz_is_nice_string ) );
    }

    return knz_is_nice_string ? 1 : 0;
  }

  private static int checkStringPart2( String pInput, boolean pKnzDebug )
  {
    int check_result_aba = checkABA( pInput );

    int check_result_double_pair = checkDoublePair( pInput );

    boolean knz_is_nice_string = ( check_result_aba + check_result_double_pair ) == 2;

    wl( String.format( "checkStringPart2  %-20s   aba %1d   double pair %1d   Is Nice %-6b ", pInput, check_result_aba, check_result_double_pair, knz_is_nice_string ) );

    return knz_is_nice_string ? 1 : 0;
  }

  private static int testCheckABA( String pInput )
  {
    int check_result_aba = checkABA( pInput );

    wl( String.format( "testCheckABA  %-20s   CheckResult %d", pInput, check_result_aba ) );

    return check_result_aba;
  }

  private static int checkABA( String pInput )
  {
    /*
     * Similar to:
     * 
     * --- Day 7: Internet Protocol Version 7 ---
     * https://adventofcode.com/2016/day/7
     * 
     * https://www.reddit.com/r/adventofcode/comments/5gy1f2/2016_day_7_solutions/
     * 
     * https://github.com/ea234/Advent_of_Code_2016/blob/main/src/de/ea234/aoc2016/day07/Day07__InternetProtocolV7.java
     * 
     */

    if ( pInput.isBlank() ) return 0;

    for ( int cur_index = 2; cur_index < pInput.length(); cur_index++ )
    {
      char char_1 = pInput.charAt( cur_index - 2 );

      //char char_2 = pInput.charAt( cur_index - 1 );

      char char_3 = pInput.charAt( cur_index );

      if ( char_1 == char_3 )
      {
        return 1;
      }
    }

    return 0;
  }

  private static int testDoublePair( String pInput )
  {
    int check_result_double_pair = checkDoublePair( pInput );

    wl( String.format( "testDoublePair  %-20s   CheckResult %d", pInput, check_result_double_pair ) );

    return check_result_double_pair;
  }

  private static int checkDoublePair( String pInput )
  {
    /*
     * Similar to:
     * 
     * --- Day 2: Gift Shop ---
     * https://adventofcode.com/2025/day/2
     * https://www.youtube.com/watch?v=bPMhQiVn6KI
     * 
     * https://github.com/ea234/Advent_of_Code_2025/blob/main/src/de/ea234/day2/Day2GiftShop.java
     * 
     */

    if ( pInput.isBlank() ) return 0;

    for ( int cur_index = 0; cur_index < ( pInput.length() - 2 ); cur_index++ )
    {
      char char_1 = pInput.charAt( cur_index );

      char char_2 = pInput.charAt( cur_index + 1 );

      //wl( cur_index + "  " + char_1 + char_2 + " " + pInput.indexOf( "" + char_1 + char_2, cur_index + 2 ) );

      if ( pInput.indexOf( "" + char_1 + char_2, cur_index + 2 ) > 0 )
      {
        return 1;
      }
    }

    return 0;
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day05_input.txt";

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
