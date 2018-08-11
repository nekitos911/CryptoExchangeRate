package ru.crypto.enums;

public enum  Currency {
    USD {
        public String toString() {
            return "USD";
        }
    },
    RUB {
        public String toString() {
            return "RUB";
        }
    },
    EUR {
        public String toString() {
            return "EUR";
        }
    }
}
