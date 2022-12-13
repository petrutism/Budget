package data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Outcome {
    private final BigDecimal sum;
    private final LocalDateTime date;
    private final OutcomeCategory category;
    private final OutcomeType type;
    private final Person person;
    private final TransferStatus status;

    public Outcome(BigDecimal sum, OutcomeCategory category, OutcomeType type) {
        this.sum = sum;
        this.date = null;
        this.category = category;
        this.type = type;
        this.person = null;
        this.status = null;

    }

    public Outcome(BigDecimal sum, LocalDateTime date, OutcomeCategory category, OutcomeType type, Person person, TransferStatus status) {
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

    public LocalDateTime getDate() {
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
}
