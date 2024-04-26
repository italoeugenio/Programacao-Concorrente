import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conta {
    private Double saldo = 0.0;
    private PropertyChangeSupport propertyChangeSupport;
    private Lock lock = new ReentrantLock();

    public Conta() {
        this.propertyChangeSupport = new PropertyChangeSupport(this);
    }

    Double getSaldo() {
        return this.saldo;
    }

    void debitarSaldo(Double valor) {
        lock.lock();
        try {
            Double saldoAnterior = this.saldo;
            this.saldo -= valor;
            propertyChangeSupport.firePropertyChange("pago", saldoAnterior, this.saldo);
        } finally {
            lock.unlock();
        }
    }

    void creditarSaldo(Double valor) {
        lock.lock();
        try {
            Double saldoAnterior = this.saldo;
            this.saldo += valor;
            propertyChangeSupport.firePropertyChange("pago", saldoAnterior, this.saldo);
        } finally {
            lock.unlock();
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }
}
