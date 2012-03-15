import java.util.ArrayList;



public class test {

	public static void main(String[] args) {
		ArrayList<BeerInfo> a = AdvocateApi.GetList("\"La Terrible\"");
		for(BeerInfo b : a)
			System.out.println(b.toString());
		AdvocateApi.updateBeer(a.get(0));
		System.out.println(a.get(0).toString());
	}

}
