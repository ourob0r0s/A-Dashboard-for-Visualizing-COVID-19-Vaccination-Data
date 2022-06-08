
public class ConditionImp implements Condition{
    public ConditionImp(String r1) {
		this.r1 = r1;
	}
	private String r1;
	@Override
	public boolean test(Array<Object> row) {
		for(int i = 0 ; i < row.size(); i++)
			if(row.get(i).getClass() == r1.getClass())
			if (((String) row.get(i)).equalsIgnoreCase(r1))
		return true;
		
		return false;

		 
	}

}

