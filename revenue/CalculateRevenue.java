import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.*;

class CalculateRevenue {

	private static final String YEAR_MONTH = "2022-10";
	private static final BigDecimal MONTHLY_CHARGE = BigDecimal.valueOf(50);

	public static Money calculateRevenue(Users users) {
		TempValObj yearAndMonth = getYearAndMonth(YEAR_MONTH);

		int daysOfMonth = YearMonth.of(yearAndMonth.getYear(), yearAndMonth.getMonth()).lengthOfMonth();
		BigDecimal chargeRate = getDailyChargeRate(daysOfMonth);

		double total = getTotalForActiveUsers(users,
				yearAndMonth,
				chargeRate); 
		return new Money(total);
	}

	private static TempValObj getYearAndMonth(String yearMonth) { 
		String[] yearMonthArray = yearMonth.split("-");
		int year = Integer.valueOf(yearMonthArray[0]);
		int month = Integer.valueOf(yearMonthArray[1]);
		return new TempValObj(year, month);
	}

	private static BigDecimal getDailyChargeRate(int daysInMonth) {
		return MONTHLY_CHARGE.divide(BigDecimal.valueOf(daysInMonth), 2, RoundingMode.HALF_UP);
	}

	private static double getTotalForActiveUsers(Users users, TempValObj yearAndMonth, BigDecimal dailyCharge) {
		int yearValue = yearAndMonth.getYear();
		int monthValue = yearAndMonth.getMonth();
		LocalDate startDate = LocalDate.of(yearValue, monthValue, 1);
		LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

		return users.getUsers().stream()
			.filter(user -> isEligible(user, startDate, endDate))
			.collect(Collectors.summingDouble(user -> sumRevenuePerMonth(user, dailyCharge, startDate, endDate)));
	}

	private static double sumRevenuePerMonth(Users.User user, BigDecimal dailyCharge, LocalDate startDate, LocalDate endDate) {
		LocalDate start = user.getActivatedOn().isBefore(startDate) ? startDate : user.getActivatedOn();
		LocalDate end = user.getDeactivatedOn();
		long activeDays = ChronoUnit.DAYS.between(start, end) + 1;
		return dailyCharge.multiply(BigDecimal.valueOf(activeDays)).doubleValue();
	}

	private static boolean isEligible(Users.User user, LocalDate startDate, LocalDate endDate) {
		return !(user.getActivatedOn().isAfter(endDate) || user.getDeactivatedOn().isBefore(startDate));
	}

	private static class TempValObj {
		private int year;
		private int month;

		public TempValObj(int year, int month) {
			this.year = year; 
			this.month = month; 
		}

		public int getYear() { return this.year; }
		public int getMonth() { return this.month; }
	}

	public static void main(String[] args) {
		ArrayList<Users.User> usersArray = new ArrayList<>();
		usersArray.add(
				new Users.User(1,
					LocalDate.of(2022, 10, 05),
					LocalDate.of(2022, 10, 31))
			      );
		Users users = new Users(usersArray);
		BigDecimal revenue = CalculateRevenue
			.calculateRevenue(users)
			.amount();
		System.out.println(revenue);
	}
} 
