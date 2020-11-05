import java.util.List;

class Main {

	public static void main(String[] args) {

		String fileName = "Covid19Casos-10.csv";//Nombre del archivo
		List<TestSubject> subjectList = FileUtil.readCases(fileName);

		for (TestSubject t : subjectList)
			System.out.println(t+"\n");

			/*
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			System.out.println(formatDate.parse("2020-06-12"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/

	}











}