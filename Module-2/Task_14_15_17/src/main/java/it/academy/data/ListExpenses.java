package it.academy.data;

import java.io.Serializable;
import java.util.Objects;

public class ListExpenses implements Serializable {
    private int num;
    private String paydate;
    private String receiver;
    private double value;

    public ListExpenses() {
    }

    @Override
    public String toString() {
        return "ListExpenses{" +
                "num=" + num +
                ", paydate='" + paydate + '\'' +
                ", receiver='" + receiver + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListExpenses that = (ListExpenses) o;
        return num == that.num && Double.compare(that.value, value) == 0 && Objects.equals(paydate, that.paydate) && Objects.equals(receiver, that.receiver);
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
