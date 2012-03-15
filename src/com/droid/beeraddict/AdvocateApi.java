import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvocateApi {

	/**
	 * Generate a list of basic BeerInfo corresponding to the input request
	 * @param request String containing the request
	 * @return ArrayList<BeerInfo> containing every BeerInfo matching the request
	 */
	public static ArrayList<BeerInfo> GetList(String request) 
	{
		ArrayList<BeerInfo> beers = new ArrayList<BeerInfo>();
		String page = "";
		try {
			page = getStringfromUrl("http://beeradvocate.com/search?q="+URLEncoder.encode(request,"UTF-8")+"&qt=beer&retired=N");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
    	//Pattern p = Pattern.compile(".*<a href=\"/beer/profile/([^\"]*)\"><b>([^<]*)</b></a>.*");
		Pattern p = Pattern.compile("/beer/profile/([^\"]+)\"><b>([^<]+)</b></a><br><[^>]+>([^<]+)");
		Matcher m = p.matcher(page);
    	while(m.find()){
	    	beers.add(new BeerInfo(m.group(1),m.group(2),m.group(3)));
	    }
    	return beers;
	}
	
	
	/**
	 * Update a Beerinfo and add the style and the alcohol amount to the object
	 * @param b BeerInfo that should be updated
	 * @return b BeerInfo updated
	 */
	public static BeerInfo updateBeer(BeerInfo b){
		String page = getStringfromUrl("http://beeradvocate.com/beer/profile/"+b.url);
		//<span class="BAscore_big">92</span>
		Pattern p = Pattern.compile("BAscore_big\">([^<]+)</span>");
		Matcher m = p.matcher(page);
		if(m.find())
			b.score = Float.parseFloat(m.group(1));
		p = Pattern.compile("/beer/style/\\d+\"><b>([^<]+)[^%\\d]*([\\d\\.]+)%");
		m = p.matcher(page);
		if(m.find())
		{
			b.style = m.group(1);
			b.alcohol = Float.parseFloat(m.group(2));
		}
		b.updated = true;
		return b;
	}

	/**
	 * gets a String from a specific url
	 * @param urlString the source url
	 * @return a one line string conversion of the page accessed at urlString
	 */
	private static String getStringfromUrl(String urlString) {
		URL url;
		InputStream is = null;
		DataInputStream dis;
		String line="";
		String page="";
		try {
		    url = new URL(urlString);
		    is = url.openStream();  // throws an IOException
		    dis = new DataInputStream(new BufferedInputStream(is));

		    while ((line = dis.readLine()) != null) {
		        page += line;
		    }
		} catch (Exception mue) {
		     mue.printStackTrace();
		} finally {
		    try {
		        is.close();
		    } catch (IOException ioe) {
		        // nothing to see here
		    }
		}
		return page;
	}
	
}
