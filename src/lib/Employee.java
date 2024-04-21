package lib;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Employee {
	// large class solved

	private EmployeeIdentity identity;
	// data clumps solved
	
	private LocalDateTime dateJoined;
	// primitive obsession solved

	private int monthWorkingInYear;
	
	private boolean isForeigner;
	private boolean gender; 
	// true = Laki-laki, false = Perempuan
	
	private EmployeeSalary salary;
	// data clumps solved
	
	private EmployeeRelatives relatives;
	// data clumps solved

	
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
			salary.setMonthlySalary(3000000);
			if (isForeigner) {
				salary.setMonthlySalary((int)(3000000 * 1.5));
			}
		}else if (grade == 2) {
			salary.setMonthlySalary(5000000);
			if (isForeigner) {
				salary.setMonthlySalary((int)(3000000 * 1.5));
			}
		}else if (grade == 3) {
			salary.setMonthlySalary(7000000);
			if (isForeigner) {
				salary.setMonthlySalary((int)(3000000 * 1.5));
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
		
		return TaxFunction.calculateTax(salary.getMonthlySalary(), salary.getOtherMonthlyIncome(), monthWorkingInYear, salary.getAnnualDeductible(), relatives.getSpouseIdNumber().equals(""), relatives.getChildIdNumbers().size());
	}

}
