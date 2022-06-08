import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFrameImp implements DataFrame {

	private LinkedListMatrix<Object> matrix = new LinkedListMatrix<Object>();
	private DynamicArray<Object> types = new DynamicArray<Object>();
	private Object o;
	private Integer in = 0;
	private Double d = 0.0;
	private Date dt = new Date();
	private String s = "";
	private Number n = 0;

	@Override
	public int getNbCols() {

		return matrix.getnBC();
	}

	@Override
	public boolean addCol(String colName, Class<?> colType) {
		if (matrix.getnBC() != 0)
			if (matrix.findC(colName))
				return false;

		types.add(colType);

		matrix.insertC(colName);
		return true;

	}

	@Override
	public boolean addCol(String colName, Class<?> colType, Array<Object> col) {
		if (col.size() != getNbRows())
			return false;
		if (matrix.findC(colName))
			return false;

		types.add(colType);
		matrix.insertC2(colName);
		matrix.findLast();
		for (int i = 0; i < matrix.getnBR(); i++) {
			matrix.findDown();
			matrix.setData(col.get(i));
		}

		return true;
	}

	@Override
	public boolean isCol(String colName) {
		return matrix.findC(colName);

	}

	@Override
	public int getColInd(String colName) {
		matrix.findFirstC();
		for (int i = 0; i < matrix.getnBC(); i++)
			if (((String) matrix.retrieve()).equalsIgnoreCase(colName))
				return i;
			else
				matrix.findRight();
		throw new IllegalArgumentException();
	}

	@Override
	public String getColName(int j) {
		if (j >= matrix.getnBC())
			throw new ArrayIndexOutOfBoundsException();
		matrix.findFirstC();
		for (int i = 0; i < j; i++)
			matrix.findRight();
		return (String) matrix.retrieve();
	}

	@Override
	public Map<String, Pair<Integer, Class<?>>> getColInfo() {
		Map<String, Pair<Integer, Class<?>>> temp = new BSTMap<String, Pair<Integer, Class<?>>>();
		Pair<Integer, Class<?>> temp2;
		matrix.findFirstC();
		for (int i = 0; i < matrix.getnBC(); i++) {
			temp2 = new Pair<Integer, Class<?>>(i, matrix.getClass());
			temp.insert((String) matrix.retrieve(), temp2);
			matrix.findRight();
		}
		return temp;

	}

	@Override
	public Array<Object> getCol(int j) {
		if (j >= matrix.getnBC())
			throw new ArrayIndexOutOfBoundsException();
		Array<Object> temp = new DynamicArray<Object>();
		matrix.findFirstC();
		for (int i = 0; i < j; i++) {
			matrix.findRight();
		}
		for (int i = 0; i < matrix.getnBR(); i++) {
			matrix.findDown();
			temp.add(matrix.retrieve());

		}

		return temp;
	}

	@Override
	public Array<Object> getCol(String colName) {
		Array<Object> temp = new DynamicArray<Object>();
		if (!(matrix.findC(colName)))
			throw new IllegalArgumentException();

		for (int i = 0; i < getNbRows(); i++) {
			matrix.findDown();
			temp.add(matrix.retrieve());

		}

		return temp;

	}

	@Override
	public int getNbRows() {
		return matrix.getnBR();
	}

	@Override
	public void addRow(Array<Object> row) {
		if (getNbCols() == 0 || row.size() != getNbCols()) {

			throw new IllegalArgumentException();
		}
		matrix.findFirstR();
		matrix.insertR();
		matrix.findFirstR();
		for (int i = 0; i < matrix.getnBC(); i++)
			if (!(types.get(i) == row.get(i).getClass())) {

				throw new IllegalArgumentException();
			}

		for (int i = 0; i < matrix.getnBC(); i++) {
			matrix.setData(row.get(i));
			matrix.findRight();
		}

	}

	@Override
	public Array<Object> getRow(int i) {
		if (i >= getNbRows())
			throw new ArrayIndexOutOfBoundsException();

		Array<Object> temp = new DynamicArray<Object>();

		matrix.findFirstC();
		for (int j = 0; j <= i; j++)
			matrix.findDown();

		for (int x = 0; x < matrix.getnBC(); x++) {
			temp.add(matrix.retrieve());
			matrix.findRight();

		}
		return temp;
	}

	@Override
	public boolean loadCSV(String fileName) {
		matrix = new LinkedListMatrix<Object>();
		types = new DynamicArray<Object>();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		boolean check;
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();
			String[] values = line.split(",");
			line = br.readLine();
			String[] valuesR = line.split(",");
			Class<?> colType = null;
			Array<Object> temp = new DynamicArray<Object>();
			if (valuesR != null) {
				for (int i = 0; i < values.length; i++) {
					check = true;

					try {

						temp.add(Integer.parseInt(valuesR[i]));
						check = false;

					} catch (NumberFormatException e) {

					}
					if (check)
						try {

							temp.add(Double.parseDouble(valuesR[i]));
							check = false;

						} catch (NumberFormatException e) {

						}
					if (check)
						try {

							temp.add(format.parse(valuesR[i]));
							check = false;
						} catch (ParseException e) {

						}
					if (check)
						temp.add((valuesR[i]));
					try {

						if (temp.get(i).getClass() == in.getClass()) {

							colType = Class.forName("java.lang.Integer");
							
						}
						else if (temp.get(i).getClass() == d.getClass()) {

							colType = Class.forName("java.lang.Double");
						} 
						else if (temp.get(i).getClass() == dt.getClass()) {

							colType = Class.forName("java.util.Date");
						} 
						else {

							colType = Class.forName("java.lang.String");
						}

						if (!addCol(values[i], colType))
							return false;

					} catch (ArrayIndexOutOfBoundsException e) {
						return false;
					}

				}

				try {

					addRow(temp);
				} catch (IllegalArgumentException e) {

					matrix.Clear();
					return false;
				}

			} else {
				colType = Class.forName("java.lang.String");
				for (int i = 0; i < values.length; i++) {
					if (!addCol(values[i], colType))
						return false;
				}
				return true;
			}

			while ((line = br.readLine()) != null) {
				values = line.split(",");
				temp = new DynamicArray<Object>();
				for (int i = 0; i < values.length; i++) {
					check = true;

					try {

						temp.add(Integer.parseInt(values[i]));
						check = false;

					} catch (NumberFormatException e) {

					}
					if (check)
						try {

							temp.add(Double.parseDouble(values[i]));
							check = false;

						} catch (NumberFormatException e) {

						}
					if (check)
						try {

							temp.add(format.parse(values[i]));
							check = false;
						} catch (ParseException e) {

						}
					if (check)
						temp.add((values[i]));

				}

				try {

					addRow(temp);
				} catch (IllegalArgumentException e) {

					matrix.Clear();
					return false;
				}

			}
			br.close();
		} catch (FileNotFoundException e) {

			return false;
		} catch (IOException e) {

			return false;
		} catch (ClassNotFoundException e) {

			return false;
		}

		return true;
	}

	@Override
	public DataFrame filterCols(Array<String> colNames) {
		for (int i = 0; i < colNames.size(); i++)
			if (!isCol(colNames.get(i)))
				throw new IllegalArgumentException();
		DataFrame dF = new DataFrameImp();
		Array<Object> dA = new DynamicArray<Object>();
		Array<Object> dA2 = new DynamicArray<Object>();
		int j;
		for (int i = 0; i < colNames.size(); i++) {
			j = getColInd(colNames.get(i));
			dF.addCol(colNames.get(i), (Class<?>) types.get(j));
		}
		for (int i = 0; i < getNbRows(); i++) {
			dA = getRow(i);
			dA2 = new DynamicArray<Object>();
			for (int x = 0; x < colNames.size(); x++) {
				j = getColInd(colNames.get(x));
				dA2.add(dA.get(j));

			}
			dF.addRow(dA2);
		}

		return dF;

	}

	@Override
	public DataFrame filterRows(Condition cond) {
		DataFrameImp temp = new DataFrameImp();
		matrix.findFirstC();
		for (int i = 0; i < getNbCols(); i++) {
			temp.addCol((String) matrix.retrieve(), (Class<?>) types.get(i));
			matrix.findRight();
		}

		for (int i = 0; i < getNbRows(); i++)
			if (cond.test(getRow(i)))
				temp.addRow(getRow(i));
		return temp;
	}

	@Override
	public double mean(String colName) {
		int i = getColInd(colName);

		if (matrix.findC(colName)) {
			if ((Class<?>) types.get(i) == in.getClass() || (Class<?>) types.get(i) == d.getClass()) {

				return matrix.getMean(colName);
			}
		}

		throw new IllegalArgumentException();

	}

}
