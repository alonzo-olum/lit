import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CalcMonthRevenue {
	private static final BigDecimal MONTHLY_RATE = BigDecimal.valueOf(50);

	private static BigDecimal getDailyRate(int monthDays) {
		return MONTHLY_RATE.divide(BigDecimal.valueOf(monthDays));
	}

	public Map<LocalDate, BigDecimal> calculateMonthRevenue(List<Users.User> users, int year, int month) {
		LocalDate startDate = LocalDate.of(year, month, 1);
		int endDays = startDate.lengthOfMonth();
		BigDecimal dailyCharge = getDailyRate(endDays);
		return IntStream.range(1, endDays)
			.mapToObj(day -> LocalDate.of(year, month, day))
			.collect(Collectors.toMap(day -> day,
						day -> {
							return users.stream()
								.filter(u -> u.isActiveOn(day))
								.collect(Collectors.reducing(BigDecimal.ZERO, u -> dailyCharge, BigDecimal::add));
						}));
	}
}
