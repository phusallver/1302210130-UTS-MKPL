package lib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Employee {

	private EmployeeIdentity identity;
	private LocalDateTime dateJoined;
	private int monthWorkingInYear;
	private boolean isForeigner;
	private boolean gender; 
	private EmployeeSalary salary;
	private EmployeeRelatives relatives;

	
	public Employee(EmployeeIdentity identity, LocalDateTime dateJoined, boolean isForeigner, boolean gender, EmployeeRelatives relatives, EmployeeSalary salary) {
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
		int countSalary = 0;
		switch (grade) {
			case 1:
				countSalary = 3000000;
				break;
			case 2:
				countSalary = 5000000;
				break;
			case 3:
				countSalary = 7000000;
				break;
		}
		if (isForeigner) {
			salary.setMonthlySalary((int)(3000000 * 1.5));
		}
		salary.setMonthlySalary(countSalary);
	}
	
	public int getAnnualIncomeTax() {
		
		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
		LocalDate date = LocalDate.now();
		
		if (date.getYear() == dateJoined.getYear()) {
			monthWorkingInYear = date.getMonthValue() - dateJoined.getMonthValue();
		}else {
			monthWorkingInYear = 12;
		}
		
		return TaxFunction.calculateTax(salary, monthWorkingInYear, relatives.getSpouseIdNumber().equals(""), relatives.getChildIdNumbers().size());
	}

}
