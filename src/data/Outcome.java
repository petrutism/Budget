package data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Outcome {

    public static int outcomeCount = 0;

    private final Integer number;
    private final BigDecimal sum;
    private final LocalDate date;
    private final OutcomeCategory category;
    private final OutcomeType type;
    private final Person person;
    private final TransferStatus status;

    public Outcome(int number, BigDecimal sum, OutcomeCategory category, OutcomeType type) {
        this.number = number;
        this.sum = sum;
        this.date = null;
        this.category = category;
        this.type = type;
        this.person = null;
        this.status = null;

    }

    public Outcome(int number, BigDecimal sum, LocalDate date, OutcomeCategory category, OutcomeType type, Person person, TransferStatus status) {
        this.number = number;
        this.sum = sum;
        this.date = date;
        this.category = category;
        this.type = type;
        this.person = person;
        this.status = status;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public OutcomeCategory getCategory() {
        return category;
    }

    public OutcomeType getType() {
        return type;
    }

    public Person getPerson() {
        return person;
    }

    public TransferStatus getStatus() {
        return status;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Outcome{" +
                "number=" + number +
                ", sum=" + sum +
                ", date=" + date +
                ", category=" + category +
                ", type=" + type +
                ", person=" + person +
                ", status=" + status +
                '}';
    }
}
