import java.time.LocalDate;
import java.time.YearMonth;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

class CalculateRevenue {

    private static final String YEAR_MONTH = "2022-10";
    private static final int MONTHLY_CHARGE = 50;

    public static Money calculateRevenue(Users users) {
       TemporalValueObject yearAndMonth = getYearAndMonth(YEAR_MONTH);

       int daysOfMonth = YearMonth.of(yearAndMonth.getYear(), yearAndMonth.getMonth()).lengthOfMonth();
       int chargeRate = getDailyChargeRate(daysOfMonth, MONTHLY_CHARGE);

       int total = getTotalForActiveUsers(users,
		       yearAndMonth,
		       chargeRate); 
       return new Money(total);
    }
    
    private static TemporalValueObject getYearAndMonth(String yearMonth) { 
        String[] yearMonthArray = yearMonth.split("-");
	int year = Integer.valueOf(yearMonthArray[0]);
       	int month = Integer.valueOf(yearMonthArray[1]);
	return new TemporalValueObject(year, month);
    }

    private static int getDailyChargeRate(int daysInMonth, long monthCharge) {
        return Math.round(monthCharge / daysInMonth);
    }

    private static int getTotalForActiveUsers(Users users, TemporalValueObject yearAndMonth, int dailyCharge) {
	int yearValue = yearAndMonth.getYear();
	int monthValue = yearAndMonth.getMonth();
	
        return users.getUsers().stream()
		    .filter(user -> 
			// activatedOn same month
			user.getActivatedOn().getMonthValue() == monthValue
		        // activatedOn same year
			&& user.getActivatedOn().getYear() == yearValue
		    )
		        .collect(Collectors.summingInt(user -> sumRevenuePerDayForUser(user, dailyCharge)));
    }

    private static int sumRevenuePerDayForUser(Users.User user, int dailyCharge) {
	int total = 0;
        LocalDate startDate = firstDay(user.getActivatedOn());
        LocalDate lastDate = lastDay(user.getActivatedOn());

        for (LocalDate dayToday = startDate; isWithinPeriod(dayToday, lastDate); dayToday = nextDay(dayToday)) {
	    if (isWithinPeriod(user.getActivatedOn(), dayToday) && !isWithinPeriod(user.getDeactivatedOn(), dayToday)) {
	         total+=dailyCharge; 
	    }
	}
	return total;
    }

    private static LocalDate firstDay(LocalDate day) { return day.withDayOfMonth(1); }
    private static LocalDate nextDay(LocalDate day) { return day.plusDays(1); }
    private static LocalDate lastDay(LocalDate day) { return day.withDayOfMonth(day.getMonth().maxLength()); }

    private static boolean isWithinPeriod(LocalDate startDay, LocalDate dayToday) {
        return startDay.isBefore(dayToday) || startDay.isEqual(dayToday);
    }
    
    private static class TemporalValueObject {
        private int year;
        private int month;

	public TemporalValueObject(int year, int month) {
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
        long revenue = CalculateRevenue
		.calculateRevenue(users)
		.amount();
        System.out.println(revenue);
    }
} 
