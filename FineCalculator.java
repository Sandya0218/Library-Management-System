import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    public static double calculateFine(LocalDate dueDate) {

        LocalDate today = LocalDate.now();

        long daysLate = ChronoUnit.DAYS.between(dueDate, today);

        if (daysLate <= 0) {
            return 0;
        }

        return daysLate * 10;
    }
}
