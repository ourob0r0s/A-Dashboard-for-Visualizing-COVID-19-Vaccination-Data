
public class test {
	public static void testDashboard() {
				COVID19Vaccination vac = new COVID19VaccinationImp();
				DataFrame df = new DataFrameImp();
				df.loadCSV("Vaccination.csv");
				vac.setData(df);
				Dashboard db = new Dashboard(vac, "Italy");
			}

	public static<K extends Comparable<K>, T> void main(String[] args) {
		
/*		COVID19Vaccination vac = new COVID19VaccinationImp();
		DataFrame df = new DataFrameImp();
		df.loadCSV("Vaccination.csv");
		vac.setData(df);
		df = vac.getPeopleVaccinatedPerHundred("italy");
		for(int i = 0; i< 78; i++) {
		DynamicArray<Object> temp = (DynamicArray<Object>) df.getRow(i);
		for(int j = 0; j< 2; j++)
		System.out.println(temp.get(j)); */
	//	testDashboard();
		BSTSet temp = new BSTSet<>();
		int k =  100;
		String t = "meow";
		temp.insert(k);
		 k = 99;
		temp.insert(k);
		
		 k =  101;
		temp.insert(k);
		
		 k =  103;
		temp.insert(k);
		
		 k =  102;
		temp.insert(k);
		
		 k =  98;
		temp.insert(k);
		
		 k =  97;
		temp.insert(k);
		
		System.out.println(temp.nbKeyComp(100));
		System.out.println(temp.nbKeyComp(99));
		System.out.println(temp.nbKeyComp(98));
		System.out.println(temp.nbKeyComp(97));
		//boolean  m = temp.update(3, "mewo");
	    //System.out.println(m);
		LinkedList v = new LinkedList();
		v.insert("m");
		v.insert("o");
		v.insert("m");
		v.insert("m");
		v.insert("y");
		v.findFirst();
		while(!v.last()) {
			System.out.println(v.retrieve());
			v.findNext();
		}
		System.out.println(v.retrieve());
		
		
		
			 
		
		
		}

	}


