
public class BeerInfo {
	/**
	 * name of the beer
	 */
	public String name;
	/**
	 * relative url of the beer on the BeerAdvocate Website
	 */
	public String url;
	/**
	 * name of the company
	 */
	public String company;
	/**
	 * Score of the be beer
	 */
	public float score;
	/**
	 * Beer Style
	 */
	public String style;
	/**
	 * Amount of Alcohol in this beer
	 */
	public float alcohol;
	/**
	 * Define if a beer is updated or not
	 */
	public boolean updated;
	
	
	/**
	 * @param url relative url of the beer on beerAdvocate
	 * @param name name of the beer
	 * @param company name of the company
	 */
	public BeerInfo(String url,String name,  String company) {
		this.name = name;
		this.url = url;
		this.company = company;
		updated = false;
	}


	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		if(updated)
			return "BeerInfo [name=" + name + ", url=" + url + ", company="
			+ company + ", score=" + score + ", style=" + style
			+ ", alcohol=" + alcohol + "]";
		return "BeerInfo [name=" + name + ", url=" + url + ", company="
				+ company + "]";
	}
	
	

}
