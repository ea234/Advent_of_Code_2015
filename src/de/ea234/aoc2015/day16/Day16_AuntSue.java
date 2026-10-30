package de.ea234.aoc2015.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
 * 
 * LIST Number 494        | children   -1 | cats   -1 | samoyeds    1 | pomeranians   -1 | akitas   -1 | vizslas   -1 | goldfish   -1 | trees    8 | cars   -1 | perfumes    5 
 * LIST Number 495        | children    9 | cats   -1 | samoyeds   -1 | pomeranians   -1 | akitas    8 | vizslas    4 | goldfish   -1 | trees   -1 | cars   -1 | perfumes   -1 
 * LIST Number 496        | children   -1 | cats   -1 | samoyeds   -1 | pomeranians    1 | akitas   -1 | vizslas    2 | goldfish   -1 | trees   -1 | cars   -1 | perfumes    7 
 * LIST Number 497        | children   -1 | cats   -1 | samoyeds   -1 | pomeranians   -1 | akitas    4 | vizslas    6 | goldfish   -1 | trees    2 | cars   -1 | perfumes   -1 
 * LIST Number 498        | children   -1 | cats   -1 | samoyeds   -1 | pomeranians    7 | akitas    8 | vizslas   -1 | goldfish   -1 | trees    0 | cars   -1 | perfumes   -1 
 * LIST Number 499        | children   -1 | cats   -1 | samoyeds   -1 | pomeranians   -1 | akitas   -1 | vizslas    7 | goldfish    3 | trees   -1 | cars   -1 | perfumes    6 
 * LIST Number 500        | children   -1 | cats   -1 | samoyeds   -1 | pomeranians   -1 | akitas   -1 | vizslas    1 | goldfish   -1 | trees   -1 | cars    1 | perfumes    6 
 * 
 * 
 * OK Number 213        | children    3 | cats   -1 | samoyeds   -1 | pomeranians   -1 | akitas   -1 | vizslas    0 | goldfish    5 | trees   -1 | cars   -1 | perfumes   -1 
 * S0 Number 0          | children    3 | cats    7 | samoyeds    2 | pomeranians    3 | akitas    0 | vizslas    0 | goldfish    5 | trees    3 | cars    2 | perfumes    1 
 * 
 * </pre>
 */
public class Day16_AuntSue
{
  public static void main( String[] args )
  {
    calculate01( getListProd(), "Sue 0: children: 3,cats: 7,samoyeds: 2,pomeranians: 3,akitas: 0,vizslas: 0,goldfish: 5,trees: 3,cars: 2,perfumes: 1" );

    System.exit( 0 );
  }

  private static void calculate01( List< String > pListInput, String pAuntSue0 )
  {
    wl( "" );
    wl( "---- Knz Do Red Check ----------------------------------------------------------" );
    wl( "" );

    List< AuntSue > list_aunt_sue = new ArrayList< Day16_AuntSue.AuntSue >();

    AuntSue aunt_sue_0 = new AuntSue( pAuntSue0 );

    int result_part_01 = 0;

    int result_part_02 = 0;

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        list_aunt_sue.add( new AuntSue( input_str ) );
      }
    }

    wl( "" );
    wl( "" );

    for ( AuntSue xkl : list_aunt_sue )
    {
      wl( "LIST " + xkl.toString() );

      if ( xkl.checkNegative( aunt_sue_0 ) == STATUS_OK )
      {
        result_part_01++;
      }
      else
      {
        result_part_01++;
      }
    }

    wl( "" );
    wl( "" );
    wl( "" );
    wl( "" );

    for ( AuntSue xkl : list_aunt_sue )
    {
      if ( xkl.isStatusOK() )
      {
        wl( "OK " + xkl.toString() );
      }
    }

    wl( "S0 " + aunt_sue_0.toString() );

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
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

  private static String[] splitAtIndex( String pString, int pIndex )
  {
    return new String[] { pString.substring( 0, pIndex ), pString.substring( pIndex ) };
  }

  private static int STATUS_OK         = 1;

  private static int STATUS_NEGATIVE_1 = 2;

  private static int NOT_THERE         = -1;

  private static class AuntSue
  {
    String         number             = "XXX";

    private int    m_status           = STATUS_OK;

    private int    m_children         = NOT_THERE;

    private int    m_cats             = NOT_THERE;

    private int    m_samoyeds         = NOT_THERE;

    private int    m_pomeranians      = NOT_THERE;

    private int    m_akitas           = NOT_THERE;

    private int    m_vizslas          = NOT_THERE;

    private int    m_goldfish         = NOT_THERE;

    private int    m_trees            = NOT_THERE;

    private int    m_cars             = NOT_THERE;

    private int    m_perfumes         = NOT_THERE;

    private String negative_compounds = "";

    public AuntSue( String pString )
    {
      int index_first_delimiter = pString.indexOf( ':' );

      String[] result = splitAtIndex( pString, index_first_delimiter );

      number = result[ 0 ].substring( 4 );

      List< String > converted_string_list = Arrays.stream( result[ 1 ].replaceAll( "\\s+", "" ).substring( 1 ).split( "," ) ).map( String::trim ).collect( Collectors.toList() );

      for ( String entry : converted_string_list )
      {
        String[] parts = entry.split( ":" );

        String key1 = parts[ 0 ];

        int value = Integer.parseInt( parts[ 1 ] );

        switch ( key1 )
        {
          case "children" :
            m_children = value;
            break;

          case "cats" :
            m_cats = value;
            break;

          case "samoyeds" :
            m_samoyeds = value;
            break;

          case "pomeranians" :
            m_pomeranians = value;
            break;

          case "akitas" :
            m_akitas = value;
            break;

          case "vizslas" :
            m_vizslas = value;
            break;

          case "goldfish" :
            m_goldfish = value;
            break;

          case "trees" :
            m_trees = value;
            break;

          case "cars" :
            m_cars = value;
            break;

          case "perfumes" :
            m_perfumes = value;
            break;
        }
      }
    }

    public boolean isStatusOK()
    {
      return m_status == STATUS_OK;
    }

    public int checkNegative( AuntSue pAuntSue0 )
    {

      if ( m_children > 0 )
      {
        if ( this.m_children != pAuntSue0.getChildren() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "children, ";
        }
      }

      if ( m_cats > 0 )
      {
        if ( this.m_cats != pAuntSue0.getCats() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "cats    , ";
        }
      }

      if ( m_samoyeds > 0 )
      {
        if ( this.m_samoyeds != pAuntSue0.getSamoyeds() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "samoyeds, ";
        }
      }

      if ( m_pomeranians > 0 )
      {
        if ( this.m_pomeranians != pAuntSue0.getPomeranians() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "pomeranians, ";
        }
      }

      if ( m_akitas > 0 )
      {
        if ( this.m_akitas != pAuntSue0.getAkitas() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "akitas  , ";
        }
      }

      if ( m_vizslas > 0 )
      {
        if ( this.m_vizslas != pAuntSue0.getVizslas() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "vizslas , ";
        }
      }

      if ( m_goldfish > 0 )
      {
        if ( this.m_goldfish != pAuntSue0.getGoldfish() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "goldfish, ";
        }
      }

      if ( m_trees > 0 )
      {
        if ( this.m_trees != pAuntSue0.getTrees() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "trees   , ";
        }
      }

      if ( m_cars > 0 )
      {
        if ( this.m_cars != pAuntSue0.getCars() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "cars    , ";
        }
      }

      if ( m_perfumes > 0 )
      {
        if ( this.m_perfumes != pAuntSue0.getPerfumes() )
        {
          this.m_status = STATUS_NEGATIVE_1;
          negative_compounds += "perfumes, ";
        }
      }

      return this.m_status;
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
      return String.format( "Number %-10s | children %4d | cats %4d | samoyeds %4d | pomeranians %4d | akitas %4d | vizslas %4d | goldfish %4d | trees %4d | cars %4d | perfumes %4d ", number, m_children, m_cats, m_samoyeds, m_pomeranians, m_akitas, m_vizslas, m_goldfish, m_trees, m_cars, m_perfumes );
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
