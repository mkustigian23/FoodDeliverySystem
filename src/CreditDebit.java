public class CreditDebit {

        private double Name;
        private double cardNum;
        private double CVV;
        private double expDate;


        public double getName() {
            return Name;
        }

        public double getCardNum() {
            return cardNum;
        }

        public double getCVV() {
            return CVV;
        }

        public double getExpDate() {
            return expDate;
        }

        public void setName(double name) {
            Name = name;
        }

        public void setCardNum(double cardNum) {
            this.cardNum = cardNum;
        }

        public void setCVV(double CVV) {
            this.CVV = CVV;
        }

        public void setExpDate(double expDate) {
            this.expDate = expDate;
        }
}
