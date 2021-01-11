package it.academy.data;

import java.io.Serializable;
import java.util.Objects;

public class Expense implements Serializable {

    private int num;
    private String paydate;
    private int receiver;
    private double value;

    public Expense() {
    }

    @Override
    public String toString() {
        return "Expense{" +
                "num=" + num +
                ", paydate='" + paydate + '\'' +
                ", receiver=" + receiver +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Expense expense = (Expense) o;
        return num == expense.num && receiver == expense.receiver && Double.compare(expense.value, value) == 0 && Objects.equals(paydate, expense.paydate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num, paydate, receiver, value);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
