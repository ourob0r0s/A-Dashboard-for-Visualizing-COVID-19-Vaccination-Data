public class COVID19VaccinationImp implements COVID19Vaccination {
	DataFrame dafr = new DataFrameImp();
	@Override
	public void setData(DataFrame df) {
		dafr = df;
	}

	@Override
	public DataFrame getPeopleVaccinated(String countryName) {
		Array<String> colNames = new DynamicArray<String>();
		colNames.add("Date");
		colNames.add("People Vaccinated");
		DataFrameImp df = new DataFrameImp();
	
		ConditionImp cond = new ConditionImp(countryName);

		df = (DataFrameImp) dafr.filterRows(cond);
		df = (DataFrameImp) df.filterCols(colNames);
		return df;

	}

	@Override
	public DataFrame getPeopleFullyVaccinated(String countryName) {
		Array<String> colNames = new DynamicArray<String>();
		colNames.add("Date");
		colNames.add("People Fully Vaccinated");
		DataFrameImp df = new DataFrameImp();
		
		ConditionImp cond = new ConditionImp(countryName);

		df = (DataFrameImp) dafr.filterRows(cond);
		df = (DataFrameImp) df.filterCols(colNames);
		return df;
	}

	@Override
	public DataFrame getPeopleVaccinatedPerHundred(String countryName) {
		Array<String> colNames = new DynamicArray<String>();
		colNames.add("Date");
		colNames.add("Percentage of People Vaccinated");
		DataFrameImp df = new DataFrameImp();
		
		ConditionImp cond = new ConditionImp(countryName);
		df = (DataFrameImp) dafr.filterRows(cond);
		df = (DataFrameImp) df.filterCols(colNames);
		return df;
	}

	@Override
	public DataFrame getPercentageOfPeopleFullyVaccinated(String countryName) {
		Array<String> colNames = new DynamicArray<String>();
		colNames.add("Date");
		colNames.add("Percentage of People Fully Vaccinated");
		DataFrameImp df = new DataFrameImp();
		
		ConditionImp cond = new ConditionImp(countryName);

		df = (DataFrameImp) dafr.filterRows(cond);
		df = (DataFrameImp) df.filterCols(colNames);
		return df;
	}

	@Override
	public Set<String> getVaccines(String countryName) {
		
		Set<String> s = new BSTSet<String>();
		DataFrameImp df = new DataFrameImp();
		Array tm = new DynamicArray();
		ConditionImp cond = new ConditionImp(countryName);
		
		
		df = (DataFrameImp) dafr.filterRows(cond);
		
		
     
		
		tm = df.getRow(0);
		
		String values = (String)tm.get(6);
		String[] t = values.split("/");
		for(int i = 0; i < t.length ; i++) {
			
			s.insert(t[i]);
		}
		return s;
		
		

	}

	@Override
	public double getAvgVaccinatedPerDay(String countryName) {
		DataFrameImp df = new DataFrameImp();
		ConditionImp cond = new ConditionImp(countryName);
		df = (DataFrameImp) dafr.filterRows(cond);
		double avg = df.mean("People Vaccinated");
		
		return avg;
		
		

	}

	@Override
	public double getAvgFullyVaccinatedPerDay(String countryName) {
		DataFrameImp df = new DataFrameImp();
		ConditionImp cond = new ConditionImp(countryName);
		df = (DataFrameImp) dafr.filterRows(cond);
		double avg = df.mean("People Fully Vaccinated");
		
		return avg;
		
	}
	

}
