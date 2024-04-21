package lib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {
	// large class

	private EmployeeIdentity identity;
	// data clumps
	
	private LocalDateTime dateJoined;
	// primitive obsession

	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; 
	// true = Laki-laki, false = Perempuan
	
	private EmployeeSalary salary;
	// data clumps
	
	private EmployeeRelatives relatives;
	// data clumps 

	
	public Employee(EmployeeIdentity identity, LocalDateTime dateJoined, boolean isForeigner, boolean gender, EmployeeRelatives relatives, EmployeeSalary salary) {
	// long method	
		this.identity = identity;
		this.relatives = relatives;
		this.salary = salary;
		this.dateJoined = dateJoined;
		
		this.isForeigner = isForeigner;
		this.gender = gender;
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya 
	 * (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */
	
	public void setMonthlySalary(int grade) {	
	// duplicate code
		if (grade == 1) {
			salary.monthlySalary = 3000000;
			if (isForeigner) {
				salary.monthlySalary = (int) (3000000 * 1.5);
			}
		}else if (grade == 2) {
			salary.monthlySalary = 5000000;
			if (isForeigner) {
				salary.monthlySalary = (int) (3000000 * 1.5);
			}
		}else if (grade == 3) {
			salary.monthlySalary = 7000000;
			if (isForeigner) {
				salary.monthlySalary = (int) (3000000 * 1.5);
			}
		}
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == dateJoined.getYear()) {
			monthWorkingInYear = date.getMonthValue() - dateJoined.getMonthValue();
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(salary.monthlySalary, salary.otherMonthlyIncome, monthWorkingInYear, salary.annualDeductible, relatives.spouseIdNumber.equals(""), relatives.childIdNumbers.size());
	}

	class EmployeeIdentity{
		private String employeeId;
		private String firstName;
		private String lastName;
		private String idNumber;
		private String address;

		public EmployeeIdentity(String employeeId, String firstName, String lastName, String idNumber, String address) {
			this.employeeId = employeeId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.idNumber = idNumber;
			this.address = address;
		}

		public String getEmployeeId() {
			return employeeId;
		}

		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getIdNumber() {
			return idNumber;
		}

		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}
		
	}

	class EmployeeRelatives{
		private String spouseName;
		private String spouseIdNumber;
		private List<String> childNames;
		private List<String> childIdNumbers;
		public EmployeeRelatives(String spouseName, String spouseIdNumber) {
			this.spouseName = spouseName;
			this.spouseIdNumber = spouseIdNumber;
			this.childNames = new LinkedList<String>();
			this.childIdNumbers = new LinkedList<String>();
		}
		public String getSpouseName() {
			return spouseName;
		}
		public void setSpouseName(String spouseName) {
			this.spouseName = spouseName;
		}
		public String getSpouseIdNumber() {
			return spouseIdNumber;
		}
		public void setSpouseIdNumber(String spouseIdNumber) {
			this.spouseIdNumber = spouseIdNumber;
		}
		public List<String> getChildNames() {
			return childNames;
		}
		public void setChildNames(List<String> childNames) {
			this.childNames = childNames;
		}
		public List<String> getChildIdNumbers() {
			return childIdNumbers;
		}
		public void setChildIdNumbers(List<String> childIdNumbers) {
			this.childIdNumbers = childIdNumbers;
		}
		
		public void setSpouse(String spouseName, String spouseIdNumber) {
			this.spouseName = spouseName;
			this.spouseIdNumber = spouseIdNumber;
		}
		
		public void addChild(String childName, String childIdNumber) {
			childNames.add(childName);
			childIdNumbers.add(childIdNumber);
		}
	}
	
	class EmployeeSalary{
		private int monthlySalary;
		private int otherMonthlyIncome;
		private int annualDeductible;
		public EmployeeSalary(int monthlySalary, int otherMonthlyIncome, int annualDeductible) {
			this.monthlySalary = monthlySalary;
			this.otherMonthlyIncome = otherMonthlyIncome;
			this.annualDeductible = annualDeductible;
		}
		public int getMonthlySalary() {
			return monthlySalary;
		}
		public void setMonthlySalary(int monthlySalary) {
			this.monthlySalary = monthlySalary;
		}
		public int getOtherMonthlyIncome() {
			return otherMonthlyIncome;
		}
		public void setOtherMonthlyIncome(int otherMonthlyIncome) {
			this.otherMonthlyIncome = otherMonthlyIncome;
		}
		public int getAnnualDeductible() {
			return annualDeductible;
		}
		public void setAnnualDeductible(int annualDeductible) {
			this.annualDeductible = annualDeductible;
		}
		
	}
}
