package de.ea234.aoc2015.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 16: Aunt Sue ---
 * 
 * https://adventofcode.com/2015/day/16
 * 
 * https://www.reddit.com/r/adventofcode/comments/3x1i26/day_16_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2015/blob/main/src/de/ea234/aoc2015/day16/Day16_AuntSue.java
 * 
 * 
 * Aunt Sue Part 0 Number 0    | children  3 | cats  7 | samoyeds  2 | pomeranians  3 | akitas  0 | vizslas  0 | goldfish  5 | trees  3 | cars  2 | perfumes  1 
 * 
 * Aunt Sue Part 1 Number 0    | children  3 | cats  7 | samoyeds  2 | pomeranians  3 | akitas  0 | vizslas  0 | goldfish  5 | trees  3 | cars  2 | perfumes  1 
 * Aunt Sue Part 2 Number 0    | children  3 | cats  7 | samoyeds  2 | pomeranians  3 | akitas  0 | vizslas  0 | goldfish  5 | trees  3 | cars  2 | perfumes  1 
 * 
 * Result Part 1 213
 * Result Part 2 323
 * 
 * </pre>
 */
public class Day16_AuntSue
{
  private static int STATUS_OK         =  1;

  private static int STATUS_NEGATIVE   =  2;

  private static int NOT_PRESENT       = -1;

  public static void main( String[] args )
  {
    calculate01( getListProd(), "Sue 0: children: 3,cats: 7,samoyeds: 2,pomeranians: 3,akitas: 0,vizslas: 0,goldfish: 5,trees: 3,cars: 2,perfumes: 1" );

    System.exit( 0 );
  }

  private static void calculate01( List< String > pListInput, String pAuntSue0 )
  {
    List< AuntSue > list_aunt_sue = new ArrayList< Day16_AuntSue.AuntSue >();

    /*
     * *******************************************************************************************************
     * Creating a "Aunt Sue" list from the input
     * *******************************************************************************************************
     */
    
    AuntSue aunt_sue_0 = new AuntSue( pAuntSue0 );

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        list_aunt_sue.add( new AuntSue( input_str ) );
      }
    }

    /*
     * *******************************************************************************************************
     * Check for invalidating the invalid Aunt Sue's
     * *******************************************************************************************************
     */

    wl( "" );

    for ( AuntSue cur_aunt_sue : list_aunt_sue )
    {
      cur_aunt_sue.checkNegativePart01( aunt_sue_0 );

      cur_aunt_sue.checkNegativePart02( aunt_sue_0 );
    }

    /*
     * *******************************************************************************************************
     * Creating Results
     * *******************************************************************************************************
     */

    String result_part_01 = "---";
    String result_part_02 = "---";
    
    wl( "" );
    wl( "Aunt Sue Part 0 " + aunt_sue_0.toString() );
    wl( "" );

    for ( AuntSue cur_aunt_sue : list_aunt_sue )
    {
      if ( cur_aunt_sue.isPart1StatusOK() )
      {
        wl( "Aunt Sue Part 1 " + aunt_sue_0.toString() );
        
        result_part_01 = cur_aunt_sue.getNumber();
      }
    }

    for ( AuntSue cur_aunt_sue : list_aunt_sue )
    {
      if ( cur_aunt_sue.isPart2StatusOK() )
      {
        wl( "Aunt Sue Part 2 " + aunt_sue_0.toString() );
        
        result_part_02 = cur_aunt_sue.getNumber();
      }
    }

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
    wl( "" );
  }

  private static String[] splitAtIndex( String pString, int pIndex )
  {
    return new String[] { pString.substring( 0, pIndex ), pString.substring( pIndex ) };
  }

  private static class AuntSue
  {
    String      m_number      = "XXX";

    private int m_status_01   = STATUS_OK;

    private int m_status_02   = STATUS_OK;

    private int m_children    = NOT_PRESENT;

    private int m_cats        = NOT_PRESENT;

    private int m_samoyeds    = NOT_PRESENT;

    private int m_pomeranians = NOT_PRESENT;

    private int m_akitas      = NOT_PRESENT;

    private int m_vizslas     = NOT_PRESENT;

    private int m_goldfish    = NOT_PRESENT;

    private int m_trees       = NOT_PRESENT;

    private int m_cars        = NOT_PRESENT;

    private int m_perfumes    = NOT_PRESENT;

    public AuntSue( String pString )
    {
      /*
       * Search the first delimiter 
       * 
       * Sue 268: akitas: 10, vizslas: 3, cats: 2
       */
      int index_first_delimiter = pString.indexOf( ':' );

      /*
       * Split the input string, to get the list number
       */
      String[] string_parts_1 = splitAtIndex( pString, index_first_delimiter );

      m_number = string_parts_1[ 0 ].substring( 4 );

      /*
       * Split the second half to get the items-list 
       */
      List< String > converted_string_list = Arrays.stream( string_parts_1[ 1 ].replaceAll( "\\s+", "" ).substring( 1 ).split( "," ) ).map( String::trim ).collect( Collectors.toList() );

      for ( String entry : converted_string_list )
      {
        String[] string_key_value = entry.split( ":" );

        String key_name = string_key_value[ 0 ];

        int value = Integer.parseInt( string_key_value[ 1 ] );

        switch ( key_name )
        {
          case "children"    : m_children    = value; break;
          case "cats"        : m_cats        = value; break;
          case "samoyeds"    : m_samoyeds    = value; break;
          case "pomeranians" : m_pomeranians = value; break;
          case "akitas"      : m_akitas      = value; break;
          case "vizslas"     : m_vizslas     = value; break;
          case "goldfish"    : m_goldfish    = value; break;
          case "trees"       : m_trees       = value; break;
          case "cars"        : m_cars        = value; break;
          case "perfumes"    : m_perfumes    = value; break;

          default :
            System.out.println( "not known " + key_name );
            break;
        }
      }
    }
    
    public void checkNegativePart01( AuntSue pAuntSue0 )
    {
      if ( m_children    != NOT_PRESENT ) { if ( m_children    != pAuntSue0.getChildren()    ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_cats        != NOT_PRESENT ) { if ( m_cats        != pAuntSue0.getCats()        ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_samoyeds    != NOT_PRESENT ) { if ( m_samoyeds    != pAuntSue0.getSamoyeds()    ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_pomeranians != NOT_PRESENT ) { if ( m_pomeranians != pAuntSue0.getPomeranians() ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_akitas      != NOT_PRESENT ) { if ( m_akitas      != pAuntSue0.getAkitas()      ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_vizslas     != NOT_PRESENT ) { if ( m_vizslas     != pAuntSue0.getVizslas()     ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_goldfish    != NOT_PRESENT ) { if ( m_goldfish    != pAuntSue0.getGoldfish()    ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_trees       != NOT_PRESENT ) { if ( m_trees       != pAuntSue0.getTrees()       ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_cars        != NOT_PRESENT ) { if ( m_cars        != pAuntSue0.getCars()        ) { m_status_01 = STATUS_NEGATIVE; } }
      if ( m_perfumes    != NOT_PRESENT ) { if ( m_perfumes    != pAuntSue0.getPerfumes()    ) { m_status_01 = STATUS_NEGATIVE; } }      
      if ( m_children    != NOT_PRESENT ) { if ( m_children    != pAuntSue0.getChildren()    ) { m_status_01 = STATUS_NEGATIVE; } }
    }

    public void checkNegativePart02( AuntSue pAuntSue0 )
    {
      if ( m_children    != NOT_PRESENT ) { if ( m_children != pAuntSue0.getChildren()       ) { m_status_02 = STATUS_NEGATIVE; } }
      if ( m_samoyeds    != NOT_PRESENT ) { if ( m_samoyeds != pAuntSue0.getSamoyeds()       ) { m_status_02 = STATUS_NEGATIVE; } }
      if ( m_akitas      != NOT_PRESENT ) { if ( m_akitas   != pAuntSue0.getAkitas()         ) { m_status_02 = STATUS_NEGATIVE; } }
      if ( m_vizslas     != NOT_PRESENT ) { if ( m_vizslas  != pAuntSue0.getVizslas()        ) { m_status_02 = STATUS_NEGATIVE; } }
      if ( m_cars        != NOT_PRESENT ) { if ( m_cars     != pAuntSue0.getCars()           ) { m_status_02 = STATUS_NEGATIVE; } }
      if ( m_perfumes    != NOT_PRESENT ) { if ( m_perfumes != pAuntSue0.getPerfumes()       ) { m_status_02 = STATUS_NEGATIVE; } }

      /*
       * cats and trees = there are greater than that many
       *
       * negative less or equal
       */

      if ( m_trees       != NOT_PRESENT ) { if ( m_trees <= pAuntSue0.getTrees()             ) { m_status_02 = STATUS_NEGATIVE; } }
      if ( m_cats        != NOT_PRESENT ) { if ( m_cats  <= pAuntSue0.getCats()              ) { m_status_02 = STATUS_NEGATIVE; } }

      /*
       * pomeranians and goldfish = that there are fewer than that many
       *
       * negative greater or equal
       */

      if ( m_pomeranians != NOT_PRESENT ) { if ( m_pomeranians >= pAuntSue0.getPomeranians() ) { m_status_02 = STATUS_NEGATIVE; } }
      if ( m_goldfish    != NOT_PRESENT ) { if ( m_goldfish >= pAuntSue0.getGoldfish()       ) { m_status_02 = STATUS_NEGATIVE; } }
    }

    public String getNumber() 
    {
      return m_number;
    }

    public boolean isPart1StatusOK()
    {
      return m_status_01 == STATUS_OK;
    }

    public boolean isPart2StatusOK()
    {
      return m_status_02 == STATUS_OK;
    }

    public int getChildren()
    {
      return m_children;
    }

    public int getCats()
    {
      return m_cats;
    }

    public int getSamoyeds()
    {
      return m_samoyeds;
    }

    public int getPomeranians()
    {
      return m_pomeranians;
    }

    public int getAkitas()
    {
      return m_akitas;
    }

    public int getVizslas()
    {
      return m_vizslas;
    }

    public int getGoldfish()
    {
      return m_goldfish;
    }

    public int getTrees()
    {
      return m_trees;
    }

    public int getCars()
    {
      return m_cars;
    }

    public int getPerfumes()
    {
      return m_perfumes;
    }

    public String toString()
    {
      return String.format( "Number %-4s | children %2d | cats %2d | samoyeds %2d | pomeranians %2d | akitas %2d | vizslas %2d | goldfish %2d | trees %2d | cars %2d | perfumes %2d ", m_number, m_children, m_cats, m_samoyeds, m_pomeranians, m_akitas, m_vizslas, m_goldfish, m_trees, m_cars, m_perfumes );
    }
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2015__day16_input.txt";

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

