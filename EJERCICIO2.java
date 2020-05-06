import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
 
import org.arpit.java2blog.bean.Country;
 
/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
public class CountryService {
 
	static HashMap<Integer,Country> countryIdMap=getCountryIdMap();
	CountryService countryService = new CountryService();
 
	@RequestMapping(value = "/countries", method = RequestMethod.GET, headers = "Accept=application/json")
	public List getCountries() {
		List listOfCountries = countryService.getAllCountries();
		return listOfCountries;
	}
 
	@RequestMapping(value = "/country/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public Country getCountryById(@PathVariable int id) {
		return countryService.getCountry(id);
	}
 
	@RequestMapping(value = "/countries", method = RequestMethod.POST, headers = "Accept=application/json")
	public Country addCountry(@RequestBody Country country) {
		return countryService.addCountry(country);
	}
 
	@RequestMapping(value = "/countries", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Country updateCountry(@RequestBody Country country) {
		return countryService.updateCountry(country);
 
	}
 
	@RequestMapping(value = "/country/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public void deleteCountry(@PathVariable("id") int id) {
		countryService.deleteCountry(id);
 
 
	public CountryService() {
		super();
 
		if(countryIdMap==null)
		{
			countryIdMap=new HashMap<Integer,Country>();
			// Creating some objects of Country while initializing
			Country indiaCountry=new Country(1, "India",10000);
			Country chinaCountry=new Country(4, "China",20000);
			Country nepalCountry=new Country(3, "Nepal",8000);
			Country bhutanCountry=new Country(2, "Bhutan",7000);
 
 
			countryIdMap.put(1,indiaCountry);
			countryIdMap.put(4,chinaCountry);
			countryIdMap.put(3,nepalCountry);
			countryIdMap.put(2,bhutanCountry);
		}
	}
 
	public List getAllCountries()
	{
		List countries = new ArrayList(countryIdMap.values());
		return countries;
	}
 
	public Country getCountry(int id)
	{
		Country country= countryIdMap.get(id);
		return country;
	}
	public Country addCountry(Country country)
	{
		country.setId(getMaxId()+1);
		countryIdMap.put(country.getId(), country);
		return country;
	}
 
	public Country updateCountry(Country country)
	{
		if(country.getId()<=0)
			return null;
		countryIdMap.put(country.getId(), country);
		return country;
 
	}
	public void deleteCountry(int id)
	{
		countryIdMap.remove(id);
	}
 
	public static HashMap<Integer, Country> getCountryIdMap() {
		return countryIdMap;
	}
 
	// Utility method to get max id
	public static int getMaxId()
	{ int max=0;
	for (int id:countryIdMap.keySet()) { 
		if(max<=id)
			max=id;
 
	} 
	return max;
	}

	public Country() {
		super();
	}
	public Country(int i, String countryName,long population) {
		super();
		this.id = i;
		this.countryName = countryName;
		this.population=population;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public long getPopulation() {
		return population;
	}
	public void setPopulation(long population) {
		this.population = population;
	} 
	
}