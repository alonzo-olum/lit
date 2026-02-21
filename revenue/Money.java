import java.math.BigDecimal;

 class Money {
     private BigDecimal amount;

     public Money(double amount) {
         this.amount = BigDecimal.valueOf(amount);
     }

     public BigDecimal amount() { return amount; }
 }
