package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan 
	 * jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(EmployeeSalary salary, int numberOfMonthWorking, boolean isMarried, int numberOfChildren) {
		int tax = 0;
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		numberOfChildren = Math.min(numberOfChildren, 3); 
		
		int netAnnualIncome = calculateNetAnnualIncome(salary, numberOfMonthWorking, isMarried, numberOfChildren);
		int taxExemptIncome = calculateTaxExemptIncome(isMarried, numberOfChildren);
		tax = calculateIncomeTax(netAnnualIncome, salary.getAnnualDeductible(), taxExemptIncome);
		
		return Math.max(tax, 0);
	}

	private static int calculateNetAnnualIncome(EmployeeSalary salary, int numberOfMonthWorking, boolean isMarried, int numberOfChildren) {
		int monthlyIncome = salary.getMonthlySalary() + salary.getOtherMonthlyIncome();
		int totalWorkingMonths = Math.min(numberOfMonthWorking, 12);
		int annualIncome = monthlyIncome * totalWorkingMonths;
		return annualIncome;
	}

	private static int calculateTaxExemptIncome(boolean isMarried, int numberOfChildren) {
		int taxExemptIncome = 54000000;
		if (isMarried) {
			taxExemptIncome += 4500000; 
		}
		taxExemptIncome += numberOfChildren * 1500000;
		return taxExemptIncome;
	}

	private static int calculateIncomeTax(int netAnnualIncome, int annualDeductible, int taxExemptIncome) {
		int taxableIncome = netAnnualIncome - annualDeductible - taxExemptIncome;
		double taxRate = 0.05;
		int tax = (int) Math.round(taxRate * taxableIncome);
		return Math.max(tax, 0);
	}
	
}
