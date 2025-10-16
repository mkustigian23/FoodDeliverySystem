public class CreditDebit {

        private double Name;
        private double cardNum;
        private double CVV;
        private double expDate;

        private String country;

        private double zipCode;


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

        public void setZipCode(double zipCode) {
            this.zipCode = zipCode;
        }

        public double getZipCode() {
            return zipCode;
    }
        public void setCountry(String country) {
            this.country = country;
    }

        public String getCountry() {
            return country;
    }
}
