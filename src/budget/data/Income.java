package budget.data;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Income {

   public static int incomeCount = 0;

   private final Integer number;
   private final BigDecimal sum;
    private final LocalDate date;
    private final IncomeCategory category;
    private final boolean isBankTransfer;
    private final Person person;
    private final TransferStatus transferStatus;

    public Income(int number, BigDecimal sum, LocalDate date, IncomeCategory category, boolean isBankTransfer, Person person, TransferStatus transferStatus) {
        this.number = number;
        this.sum = sum;
        this.date = date;
        this.category = category;
        this.isBankTransfer = isBankTransfer;
        this.person = person;
        this.transferStatus = transferStatus;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public LocalDate getDate() {
        return date;
    }

    public IncomeCategory getCategory() {
        return category;
    }

    public boolean isBankTransfer() {
        return isBankTransfer;
    }

    public Person getPerson() {
        return person;
    }

    public TransferStatus getTransferStatus() {
        return transferStatus;
    }

    public Integer getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Income {" +
                "number=" + number +
                ", sum=" + sum +
                ", date=" + date +
                ", category=" + category +
                ", isBankTransfer=" + isBankTransfer +
                ", person=" + person +
                ", transferStatus=" + transferStatus +
                '}';
    }
}
