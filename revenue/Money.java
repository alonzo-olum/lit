 class Money {
     private long amount;

     public Money(int amount) {
         this.amount = Math.round(amount);
     }

     public long amount() { return amount; }
 }
