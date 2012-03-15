import java.util.ArrayList;



public class test {

	public static void main(String[] args) {
		//We search for a beer named La terrible
		ArrayList<BeerInfo> a = AdvocateApi.GetList("\"La Terrible\"");
		for(BeerInfo b : a)
			//We print every beer corresponding to the search
			System.out.println(b.toString());
		//We update the first beert then print it
		AdvocateApi.updateBeer(a.get(0));
		System.out.println(a.get(0).toString());
	}

}
