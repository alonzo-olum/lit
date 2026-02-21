
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import java.time.YearMonth;

class CalculateRevenueV2 {

    private static final double MONTHLY_CHARGE = 50;

    public static double calculateRevenue(Users users, String month) {
        String[] yearAndMonth = month.split("-");
	int yearValue = Integer.valueOf(yearAndMonth[0]);
	int monthValue = Integer.valueOf(yearAndMonth[1]);

	int daysOfMonth = YearMonth.of(yearValue, monthValue).lengthOfMonth();
	double chargeRate = getDailyCharge(daysOfMonth); 

	return computeRevenueForMonth(users, chargeRate, yearValue, monthValue);
    }

    private static double getDailyCharge(int daysOfMonth) { 
	return Math.round(MONTHLY_CHARGE / daysOfMonth);
    }

    private static double computeRevenueForMonth(Users users, double chargeRate, int year, int month) {
	LocalDate start = LocalDate.of(year, month, 1);
	LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
	int daysInMonth = start.until(end).getDays();
	return IntStream.range(1, daysInMonth)
		.mapToObj(day -> LocalDate.of(year, month, day))
		.collect(Collectors.summingDouble((date -> {
			int active = Math.toIntExact(getActiveUsers(users.getUsers(), date));
			return active * chargeRate;
		})));
    }

    private static long getActiveUsers(List<Users.User> users, LocalDate date) {
        return users.stream()
		    .filter(user -> (user.getActivatedOn().isBefore(date) || user.getActivatedOn().isEqual(date)) && (user.getDeactivatedOn().isAfter(date) || user.getDeactivatedOn().isEqual(date)))
		        .collect(Collectors.counting());
    }
}
