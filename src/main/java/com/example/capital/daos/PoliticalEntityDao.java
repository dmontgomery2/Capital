package com.example.capital.daos;

import static com.example.capital.models.dropdownmenuoptions.AnsweredBefore.NO;
import static java.util.stream.Collectors.toList;

import com.example.capital.models.dropdownmenuoptions.AnsweredBefore;
import com.example.capital.models.PoliticalEntity;
import com.example.capital.models.PoliticalEntityQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class PoliticalEntityDao {

  private static final PoliticalEntity.PoliticalEntityBuilder B = PoliticalEntity.builder();

  public static List<PoliticalEntity> getDefaultStates() {
    return List.of(
        B.name("Alabama").capital("Montgomery").difficulty(3).isState(true).build(),
        B.name("Alaska").capital("Juneau").difficulty(4).isState(true).build(),
        B.name("Arizona").capital("Phoenix").difficulty(2).isState(true).build(),
        B.name("Arkansas").capital("Little Rock").difficulty(7).isState(true).build(),
        B.name("California").capital("Sacramento").difficulty(3).isState(true).build(),
        B.name("Colorado").capital("Denver").difficulty(3).isState(true).build(),
        B.name("Connecticut").capital("Hartford").difficulty(9).isState(true).build(),
        B.name("Delaware").capital("Dover").difficulty(9).isState(true).build(),
        B.name("Florida").capital("Tallahassee").difficulty(4).isState(true).build(),
        B.name("Georgia (state)").capital("Atlanta").difficulty(2).isState(true).build(),
        B.name("Hawaii").capital("Honolulu").difficulty(2).isState(true).build(),
        B.name("Idaho").capital("Boise").difficulty(5).isState(true).build(),
        B.name("Illinois").capital("Springfield").difficulty(2).isState(true).build(),
        B.name("Indiana").capital("Indianapolis").difficulty(2).isState(true).build(),
        B.name("Iowa").capital("Des Moines").difficulty(7).isState(true).build(),
        B.name("Kansas").capital("Topeka").difficulty(7).isState(true).isState(true).build(),
        B.name("Kentucky").capital("Frankfort").difficulty(7).isState(true).isState(true).build(),
        B.name("Louisiana").capital("Baton Rouge").difficulty(10).isState(true).build(),
        B.name("Maine").capital("Augusta").difficulty(9).isState(true).build(),
        B.name("Maryland").capital("Annapolis").difficulty(9).isState(true).build(),
        B.name("Massachusetts").capital("Boston").difficulty(1).isState(true).build(),
        B.name("Michigan").capital("Lansing").difficulty(5).isState(true).build(),
        B.name("Minnesota").capital("Saint Paul").difficulty(7).isState(true).build(),
        B.name("Mississippi").capital("Jackson").difficulty(7).isState(true).build(),
        B.name("Missouri").capital("Jefferson City").difficulty(7).isState(true).build(),
        B.name("Montana").capital("Helena").difficulty(8).isState(true).build(),
        B.name("Nebraska").capital("Lincoln").difficulty(8).isState(true).build(),
        B.name("Nevada").capital("Carson City").difficulty(4).isState(true).build(),
        B.name("New Hampshire").capital("Concord").difficulty(10).isState(true).build(),
        B.name("New Jersey").capital("Trenton").difficulty(9).isState(true).build(),
        B.name("New Mexico").capital("Santa Fe").difficulty(7).isState(true).build(),
        B.name("New York").capital("Albany").difficulty(4).isState(true).build(),
        B.name("North Carolina").capital("Raleigh").difficulty(9).isState(true).build(),
        B.name("North Dakota").capital("Bismarck").difficulty(8).isState(true).build(),
        B.name("Ohio").capital("Columbus").difficulty(2).isState(true).build(),
        B.name("Oklahoma").capital("Oklahoma City").difficulty(2).isState(true).build(),
        B.name("Oregon").capital("Salem").difficulty(7).isState(true).build(),
        B.name("Pennsylvania").capital("Harrisburg").difficulty(7).isState(true).build(),
        B.name("Rhode Island").capital("Providence").difficulty(10).isState(true).build(),
        B.name("South Carolina").capital("Columbia").difficulty(7).isState(true).build(),
        B.name("South Dakota").capital("Pierre").difficulty(5).isState(true).build(),
        B.name("Tennessee").capital("Nashville").difficulty(2).isState(true).build(),
        B.name("Texas").capital("Austin").difficulty(1).isState(true).build(),
        B.name("Utah").capital("Salt Lake City").difficulty(1).isState(true).build(),
        B.name("Vermont").capital("Montpelier").difficulty(10).isState(true).build(),
        B.name("Virginia").capital("Richmond").difficulty(8).isState(true).build(),
        B.name("Washington").capital("Olympia").difficulty(3).isState(true).build(),
        B.name("West Virginia").capital("Charleston").difficulty(8).isState(true).build(),
        B.name("Wisconsin").capital("Madison").difficulty(5).isState(true).build(),
        B.name("Wyoming").capital("Cheyenne").difficulty(8).activated(true).isState(true).build()
    );
  }

  public static List<PoliticalEntity> getDefaultCountries() {
    return List.of(
        B.name("Afghanistan").capital("Kabul").difficulty(4).isState(false).build(),
        B.name("Albania").capital("Tirana").difficulty(9).isState(false).build(),
        B.name("Algeria").capital("Algiers").difficulty(7).isState(false).build(),
        B.name("Andorra").capital("Andorra La Vella").difficulty(9).isState(false).build(),
        B.name("Angola").capital("Luanda").difficulty(9).isState(false).build(),
        B.name("Antigua & Barbuda").capital("Saint John's").difficulty(9).isState(false).build(),
        B.name("Argentina").capital("Buenos Aires").difficulty(2).isState(false).build(),
        B.name("Armenia").capital("Yerevan").difficulty(9).isState(false).build(),
        B.name("Australia").capital("Canberra").difficulty(6).isState(false).build(),
        B.name("Austria").capital("Vienna").difficulty(6).isState(false).build(),
        B.name("Azerbaijan").capital("Baku").difficulty(7).isState(false).build(),
        B.name("The Bahamas").capital("Nassau").difficulty(7).isState(false).build(),
        B.name("Bahrain").capital("Manama").difficulty(10).isState(false).build(),
        B.name("Bangladesh").capital("Dhaka").difficulty(8).isState(false).build(),
        B.name("Barbados").capital("Bridgetown").difficulty(9).isState(false).build(),
        B.name("Belarus").capital("Minsk").difficulty(9).isState(false).build(),
        B.name("Belgium").capital("Brussels").difficulty(7).isState(false).build(),
        B.name("Belize").capital("Belmopan").difficulty(10).isState(false).build(),
        B.name("Benin").capital("Porto-Novo").difficulty(10).isState(false).build(),
        B.name("Bhutan").capital("Thimphu").difficulty(10).isState(false).build(),
        B.name("Bolivia").capital("Sucre").difficulty(9).isState(false).build(),
        B.name("Bosnia & Herzegovina").capital("Sarajevo").difficulty(8).isState(false).build(),
        B.name("Botswana").capital("Gaborone").difficulty(9).isState(false).build(),
        B.name("Brazil").capital("Brasilia").difficulty(5).isState(false).build(),
        B.name("Brunei").capital("Bandar Seri Begawan").difficulty(10).isState(false).build(),
        B.name("Bulgaria").capital("Sofia").difficulty(9).isState(false).build(),
        B.name("Burkina Faso").capital("Ouagadougou").difficulty(10).isState(false).build(),
        B.name("Burundi").capital("Gitega, Bujumbura").difficulty(10).isState(false).build(),
        B.name("Cabo Verde").capital("Praia").difficulty(10).isState(false).build(),
        B.name("Cambodia").capital("Phnom Penh").difficulty(8).isState(false).build(),
        B.name("Cameroon").capital("Yaounde").difficulty(8).isState(false).build(),
        B.name("Canada").capital("Ottawa").difficulty(3).isState(false).build(),
        B.name("Central African Republic").capital("Bangui").difficulty(9).isState(false).build(),
        B.name("Chad").capital("N'Djamena").difficulty(9).isState(false).build(),
        B.name("Chile").capital("Santiago").difficulty(6).isState(false).build(),
        B.name("China").capital("Beijing").difficulty(1).isState(false).build(),
        B.name("Colombia").capital("Bogota").difficulty(5).isState(false).build(),
        B.name("Comoros").capital("Moroni").difficulty(10).isState(false).build(),
        B.name("Democratic Republic Of The Congo").capital("Kinshasa").difficulty(6).isState(false)
            .build(),
        B.name("Republic Of The Congo").capital("Brazzaville").difficulty(6).isState(false).build(),
        B.name("Costa Rica").capital("San Jose").difficulty(6).isState(false).build(),
        B.name("Cote D'Ivoire").capital("Yamoussoukro").difficulty(10).isState(false).build(),
        B.name("Croatia").capital("Zagreb").difficulty(8).isState(false).build(),
        B.name("Cuba").capital("Havana").difficulty(5).isState(false).build(),
        B.name("Cyprus").capital("Nicosia").difficulty(9).isState(false).build(),
        B.name("Czech Republic").capital("Prague").difficulty(6).isState(false).build(),
        B.name("Denmark").capital("Copenhagen").difficulty(6).isState(false).build(),
        B.name("Djibouti").capital("Djibouti").difficulty(3).isState(false).build(),
        B.name("Dominica").capital("Roseau").difficulty(10).isState(false).build(),
        B.name("Dominican Republic").capital("Santo Domingo").difficulty(8).isState(false).build(),
        B.name("Ecuador").capital("Quito").difficulty(8).isState(false).build(),
        B.name("Egypt").capital("Cairo").difficulty(1).isState(false).build(),
        B.name("El Salvador").capital("San Salvador").difficulty(4).isState(false).build(),
        B.name("Equatorial Guinea").capital("Malabo").difficulty(3).isState(false).build(),
        B.name("Eritrea").capital("Asmara").difficulty(5).isState(false).build(),
        B.name("Estonia").capital("Tallinn").difficulty(7).isState(false).build(),
        B.name("Eswatini").capital("Mbabane").difficulty(6).isState(false).build(),
        B.name("Ethiopia").capital("Addis Ababa").difficulty(7).isState(false).build(),
        B.name("Fiji").capital("Suva").difficulty(10).isState(false).build(),
        B.name("Finland").capital("Helsinki").difficulty(6).isState(false).build(),
        B.name("France").capital("Paris").difficulty(1).isState(false).build(),
        B.name("Gabon").capital("Libreville").difficulty(10).isState(false).build(),
        B.name("The Gambia").capital("Banjul").difficulty(10).isState(false).build(),
        B.name("Georgia").capital("Tbilisi").difficulty(10).isState(false).build(),
        B.name("Germany").capital("Berlin").difficulty(1).isState(false).build(),
        B.name("Ghana").capital("Accra").difficulty(7).isState(false).build(),
        B.name("Greece").capital("Athens").difficulty(1).isState(false).build(),
        B.name("Grenada").capital("Saint George's").difficulty(10).isState(false).build(),
        B.name("Guatemala").capital("Guatemala City").difficulty(5).isState(false).build(),
        B.name("Guinea").capital("Conakry").difficulty(8).isState(false).build(),
        B.name("Guinea-Bissau").capital("Bissau").difficulty(7).isState(false).build(),
        B.name("Guyana").capital("Georgetown").difficulty(9).isState(false).build(),
        B.name("Haiti").capital("Port-au-Prince").difficulty(3).isState(false).build(),
        B.name("Honduras").capital("Tegucigalpa").difficulty(5).isState(false).build(),
        B.name("Hungary").capital("Budapest").difficulty(7).isState(false).build(),
        B.name("Iceland").capital("Reykjavik").difficulty(8).isState(false).build(),
        B.name("India").capital("New Delhi").difficulty(2).isState(false).build(),
        B.name("Indonesia").capital("Jakarta").difficulty(4).isState(false).build(),
        B.name("Iran").capital("Tehran").difficulty(4).isState(false).build(),
        B.name("Iraq").capital("Baghdad").difficulty(3).isState(false).build(),
        B.name("Ireland").capital("Dublin").difficulty(4).isState(false).build(),
        B.name("Israel").capital("Jerusalem").difficulty(3).isState(false).build(),
        B.name("Italy").capital("Rome").difficulty(1).isState(false).build(),
        B.name("Jamaica").capital("Kingston").difficulty(5).isState(false).build(),
        B.name("Japan").capital("Tokyo").difficulty(1).isState(false).build(),
        B.name("Jordan").capital("Amman").difficulty(8).isState(false).build(),
        B.name("Kazakhstan").capital("Astana").difficulty(10).isState(false).build(),
        B.name("Kenya").capital("Nairobi").difficulty(5).isState(false).build(),
        B.name("Kiribati").capital("South Tarawa").difficulty(10).isState(false).build(),
        B.name("Kosovo").capital("Pristina").difficulty(10).isState(false).build(),
        B.name("Kuwait").capital("Kuwait City").difficulty(6).isState(false).build(),
        B.name("Kyrgyzstan").capital("Bishkek").difficulty(10).isState(false).build(),
        B.name("Laos").capital("Vientiane").difficulty(8).isState(false).build(),
        B.name("Latvia").capital("Riga").difficulty(10).isState(false).build(),
        B.name("Lebanon").capital("Beirut").difficulty(7).isState(false).build(),
        B.name("Lesotho").capital("Maseru").difficulty(10).isState(false).build(),
        B.name("Liberia").capital("Monrovia").difficulty(10).isState(false).build(),
        B.name("Libya").capital("Tripoli").difficulty(10).isState(false).build(),
        B.name("Liechtenstein").capital("Vilnius").difficulty(10).isState(false).build(),
        B.name("Lithuania").capital("Vilnius").difficulty(10).isState(false).build(),
        B.name("Luxembourg").capital("Luxembourg").difficulty(4).isState(false).build(),
        B.name("Madagascar").capital("Antananarivo").difficulty(8).isState(false).build(),
        B.name("Malawi").capital("Lilongwe").difficulty(9).isState(false).build(),
        B.name("Malaysia").capital("Kuala Lumpur").difficulty(6).isState(false).build(),
        B.name("Maldives").capital("Male").difficulty(9).isState(false).build(),
        B.name("Mali").capital("Bamako").difficulty(8).isState(false).build(),
        B.name("Malta").capital("Valletta").difficulty(9).isState(false).build(),
        B.name("Marshall Islands").capital("Majuro").difficulty(8).isState(false).build(),
        B.name("Mauritania").capital("Nouakchott").difficulty(10).isState(false).build(),
        B.name("Mauritius").capital("Port Louis").difficulty(8).isState(false).build(),
        B.name("Mexico").capital("Mexico City").difficulty(1).isState(false).build(),
        B.name("Federated States Of Micronesia").capital("Palikir").difficulty(10).isState(false)
            .build(),
        B.name("Moldova").capital("Chisinau").difficulty(10).isState(false).build(),
        B.name("Monaco").capital("Monaco").difficulty(6).isState(false).build(),
        B.name("Mongolia").capital("Ulaanbaatar").difficulty(10).isState(false).build(),
        B.name("Montenegro").capital("Podgorica").difficulty(10).isState(false).build(),
        B.name("Morocco").capital("Rabat").difficulty(8).isState(false).build(),
        B.name("Mozambique").capital("Maputo").difficulty(10).isState(false).build(),
        B.name("Myanmar").capital("Nay Pyi Taw").difficulty(10).isState(false).build(),
        B.name("Namibia").capital("Windhoek").difficulty(10).isState(false).build(),
        B.name("Nauru").capital("Yaren District").difficulty(10).isState(false).build(),
        B.name("Nepal").capital("Kathmandu").difficulty(9).isState(false).build(),
        B.name("Netherlands").capital("Amsterdam").difficulty(6).isState(false).build(),
        B.name("New Zealand").capital("Wellington").difficulty(8).isState(false).build(),
        B.name("Nicaragua").capital("Managua").difficulty(7).isState(false).build(),
        B.name("Niger").capital("Niamey").difficulty(8).isState(false).build(),
        B.name("Nigeria").capital("Abuja").difficulty(8).isState(false).build(),
        B.name("North Korea").capital("Pyongyang").difficulty(8).isState(false).build(),
        B.name("North Macedonia").capital("Skopje").difficulty(9).isState(false).build(),
        B.name("Norway").capital("Oslo").difficulty(6).isState(false).build(),
        B.name("Oman").capital("Muscat").difficulty(8).isState(false).build(),
        B.name("Pakistan").capital("Islamabad").difficulty(7).isState(false).build(),
        B.name("Palau").capital("Ngerulmud").difficulty(10).isState(false).build(),
        B.name("Palestine").capital("East Jerusalem").difficulty(6).isState(false).build(),
        B.name("Panama").capital("Panama City").difficulty(5).isState(false).build(),
        B.name("Papua New Guinea").capital("Port Moresby").difficulty(9).isState(false).build(),
        B.name("Paraguay").capital("Asuncion").difficulty(8).isState(false).build(),
        B.name("Peru").capital("Lima").difficulty(5).isState(false).build(),
        B.name("Philippines").capital("Manila").difficulty(5).isState(false).build(),
        B.name("Poland").capital("Warsaw").difficulty(3).isState(false).build(),
        B.name("Portugal").capital("Lisbon").difficulty(4).isState(false).build(),
        B.name("Qatar").capital("Doha").difficulty(9).isState(false).build(),
        B.name("Romania").capital("Bucharest").difficulty(8).isState(false).build(),
        B.name("Russia").capital("Moscow").difficulty(1).isState(false).build(),
        B.name("Rwanda").capital("Kigali").difficulty(10).isState(false).build(),
        B.name("Saint Kitts & Nevis").capital("Basseterre").difficulty(10).isState(false).build(),
        B.name("Saint Lucia").capital("Castries").difficulty(10).isState(false).build(),
        B.name("Saint Vincent & The Grenadines").capital("Kingstown").difficulty(10).isState(false)
            .build(),
        B.name("Samoa").capital("Apia").difficulty(8).isState(false).build(),
        B.name("San Marino").capital("San Marino").difficulty(7).isState(false).build(),
        B.name("Sao Tome & Principe").capital("Sao Tome").difficulty(8).isState(false).build(),
        B.name("Saudi Arabia").capital("Riyadh").difficulty(6).isState(false).build(),
        B.name("Senegal").capital("Dakar").difficulty(9).isState(false).build(),
        B.name("Serbia").capital("Belgrade").difficulty(8).isState(false).build(),
        B.name("Seychelles").capital("Victoria").difficulty(10).isState(false).build(),
        B.name("Sierra Leone").capital("Freetown").difficulty(9).isState(false).build(),
        B.name("Singapore").capital("Singapore").difficulty(4).isState(false).build(),
        B.name("Slovakia").capital("Bratislava").difficulty(8).isState(false).build(),
        B.name("Slovenia").capital("Ljubljana").difficulty(8).isState(false).build(),
        B.name("Solomon Islands").capital("Honiara").difficulty(9).isState(false).build(),
        B.name("Somalia").capital("Mogadishu").difficulty(8).isState(false).build(),
        B.name("South Africa").capital("Bloemfontein, Cape Town, Pretoria").difficulty(10)
            .isState(false).build(),
        B.name("South Korea").capital("Seoul").difficulty(1).isState(false).build(),
        B.name("South Sudan").capital("Juba").difficulty(10).isState(false).build(),
        B.name("Spain").capital("Madrid").difficulty(1).isState(false).build(),
        B.name("Sri Lanka").capital("Colombo, Sri Jayawardenepura Kotte").difficulty(10)
            .isState(false).build(),
        B.name("Sudan").capital("Khartoum").difficulty(10).isState(false).build(),
        B.name("Suriname").capital("Paramaribo").difficulty(10).isState(false).build(),
        B.name("Sweden").capital("Stockholm").difficulty(3).isState(false).build(),
        B.name("Switzerland").capital("Bern").difficulty(6).isState(false).build(),
        B.name("Syria").capital("Damascus").difficulty(5).isState(false).build(),
        B.name("Tajikistan").capital("Dushanbe").difficulty(7).isState(false).build(),
        B.name("Tanzania").capital("Dodoma").difficulty(7).isState(false).build(),
        B.name("Thailand").capital("Bangkok").difficulty(3).isState(false).build(),
        B.name("Timor-Leste").capital("Dili").difficulty(10).isState(false).build(),
        B.name("Togo").capital("Lome").difficulty(8).isState(false).build(),
        B.name("Tonga").capital("Nuku'alofa").difficulty(10).isState(false).build(),
        B.name("Trinidad & Tobago").capital("Port of Spain").difficulty(6).isState(false).build(),
        B.name("Tunisia").capital("Tunis").difficulty(6).isState(false).build(),
        B.name("Turkiye").capital("Ankara").difficulty(10).isState(false).build(),
        B.name("Turkmenistan").capital("Ashgabat").difficulty(8).isState(false).build(),
        B.name("Tuvalu").capital("Funafuti").difficulty(9).isState(false).build(),
        B.name("Uganda").capital("Kampala").difficulty(8).isState(false).build(),
        B.name("Ukraine").capital("Kiev").difficulty(5).isState(false).build(),
        B.name("United Arab Emirates").capital("Abu Dhabi").difficulty(6).isState(false).build(),
        B.name("United Kingdom").capital("London").difficulty(1).isState(false).build(),
        B.name("United States of America").capital("Washington, D.C.").difficulty(1).isState(false)
            .build(),
        B.name("Uruguay").capital("Montevideo").difficulty(7).isState(false).build(),
        B.name("Uzbekistan").capital("Tashkent").difficulty(8).isState(false).build(),
        B.name("Vanuatu").capital("Port Vila").difficulty(8).isState(false).build(),
        B.name("Vatican City").capital("Vatican City").difficulty(3).isState(false).build(),
        B.name("Venezuela").capital("Caracas").difficulty(4).isState(false).build(),
        B.name("Vietnam").capital("Hanoi").difficulty(5).isState(false).build(),
        B.name("Yemen").capital("Sana'a").difficulty(6).isState(false).build(),
        B.name("Zambia").capital("Lusaka").difficulty(9).isState(false).build(),
        B.name("Zimbabwe").capital("Harare").difficulty(9).isState(false).build()
    );
  }

  private static List<PoliticalEntity> currentStates = getDefaultStates();
  private static List<PoliticalEntity> currentCountries = getDefaultCountries();

  public static void setCurrentStates(List<PoliticalEntity> newStates) {
    currentStates = newStates;
  }

  public static void setCurrentCountries(List<PoliticalEntity> newCountries) {
    currentCountries = newCountries;
  }

  public static void updateStateDifficultyByName(String name, int difficulty) {
    updateEntityDifficultyByName(currentStates, name, difficulty);
  }

  public static void updateCountryDifficultyByName(String name, int difficulty) {
    updateEntityDifficultyByName(currentCountries, name, difficulty);
  }

  private static void updateEntityDifficultyByName(List<PoliticalEntity> entities, String name,
      int difficulty) {
    entities.stream()
        .filter(s -> normalize(s.getName()).equalsIgnoreCase(name))
        .forEach(s -> s.setDifficulty(difficulty));
  }

  public static PoliticalEntity getStateByName(String name) {
    return getEntityByName(name, currentStates);
  }

  public static int getDefaultDifficultyForStateName(String name) {
    return getEntityByName(name, getDefaultStates()).getDifficulty();
  }

  public static PoliticalEntity getCountryByName(String name) {
    return getEntityByName(name, currentCountries);
  }

  public static int getDefaultDifficultyForCountryName(String name) {
    return getEntityByName(name, getDefaultCountries()).getDifficulty();
  }

  private static PoliticalEntity getEntityByName(String name, List<PoliticalEntity> entities) {
    return entities.stream()
        .filter(s -> normalize(s.getName()).equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }

  public static void activateState(String name) {
    setStateActivatedByName(name, true);
  }

  public static void deactivateState(String name) {
    setStateActivatedByName(name, false);
  }

  public static void activateCountry(String name) {
    setCountryActivatedByName(name, true);
  }

  public static void deactivateCountry(String name) {
    setCountryActivatedByName(name, false);
  }

  private static void setStateActivatedByName(String name, boolean activated) {
    getStateByName(name).setActivated(activated);
  }

  private static void setCountryActivatedByName(String name, boolean activated) {
    getCountryByName(name).setActivated(activated);
  }


  public static List<PoliticalEntity> getStates(PoliticalEntityQuery query) {
    return queryList(currentStates, query);
  }

  public static List<PoliticalEntity> getCountries(PoliticalEntityQuery query) {
    return queryList(currentCountries, query);
  }

  private static List<PoliticalEntity> queryList(List<PoliticalEntity> entities,
      PoliticalEntityQuery query) {
    List<PoliticalEntity> politicalEntities = new ArrayList<>(entities);
    Collections.shuffle(politicalEntities);
    politicalEntities = politicalEntities.stream()
        .filter(PoliticalEntity::isActivated)
        .filter(difficultyInRange(query.getMinDiff(), query.getMaxDiff()))
        .filter(matchesAnsweredBefore(query.getAnsweredBefore()))
        .filter(correctInRange(query))
        .limit(query.getQuantity())
        .collect(toList());
    return politicalEntities;
  }

  private static Predicate<PoliticalEntity> difficultyInRange(int min, int max) {
    return ent -> ent.getDifficulty() >= min && ent.getDifficulty() <= max;
  }

  private static Predicate<PoliticalEntity> matchesAnsweredBefore(AnsweredBefore answeredBefore) {
    switch (answeredBefore) {
      case YES:
        return ent -> UserDao.getCurrentUser().hasStatsForEntity(ent);
      case NO:
        return ent -> !UserDao.getCurrentUser().hasStatsForEntity(ent);
      default:
        return ent -> true;
    }
  }

  private static Predicate<PoliticalEntity> correctInRange(PoliticalEntityQuery query) {
    if (query.getAnsweredBefore().equals(NO)) {
      return ent -> true;
    }
    return ent -> UserDao.getCurrentUser()
        .getPercentageCorrectForEntityName(ent.getName(), query.getTimePeriod())
        >= query.getMinCorr() && UserDao.getCurrentUser()
        .getPercentageCorrectForEntityName(ent.getName(), query.getTimePeriod())
        <= query.getMaxCorr();
  }

  private static String normalize(String string) {
    return string.replaceAll("[() '-]", "")
        .replaceAll("&", "and");
  }

  public static int getNumberOfActivatedStates() {
    return getNumberOfActivatedEntities(currentStates);
  }

  public static int getNumberOfActivatedCountries() {
    return getNumberOfActivatedEntities(currentCountries);
  }

  private static int getNumberOfActivatedEntities(List<PoliticalEntity> entities) {
    return (int) (entities.stream()
        .filter(PoliticalEntity::isActivated)
        .count());
  }

  public static PoliticalEntity getPreviousAlphabetically(PoliticalEntity politicalEntity) {
    if (politicalEntity.isState()) {
      return getPreviousEntity(politicalEntity, currentStates);
    }
    return getPreviousEntity(politicalEntity, currentCountries);
  }

  public static PoliticalEntity getNextAlphabetically(PoliticalEntity politicalEntity) {
    if (politicalEntity.isState()) {
      return getNextEntity(politicalEntity, currentStates);
    }
    return getNextEntity(politicalEntity, currentCountries);
  }

  private static PoliticalEntity getPreviousEntity(PoliticalEntity entity,
      List<PoliticalEntity> entities) {
    int index = entities.indexOf(entity);
    if (index == 0) {
      return entities.get(entities.size() - 1);
    }
    return entities.get(index - 1);
  }

  private static PoliticalEntity getNextEntity(PoliticalEntity entity,
      List<PoliticalEntity> entities) {
    int index = entities.indexOf(entity);
    if (index == entities.size() - 1) {
      return entities.get(0);
    }
    return entities.get(index + 1);
  }
}
