package io.github.lsj8367.refactoring.lock;

import java.util.Objects;

public class Lock {

    private final String lockReason;
    private final boolean readAccess;

    private Lock(final String lockReason, final boolean readAccess) {
        this.lockReason = lockReason;
        this.readAccess = readAccess;
    }

    public static Lock writeLock(final boolean readAccess) {
        return new Lock(null, readAccess);
    }

    public static Lock readLockWithMessage(final String lockReason, final boolean readAccess) {
        return new Lock(lockReason, readAccess);
    }

    public String getLockReason() {
        return lockReason;
    }

    public boolean isReadAccess() {
        return readAccess;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Lock lock = (Lock) o;
        return readAccess == lock.readAccess && Objects.equals(lockReason, lock.lockReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lockReason, readAccess);
    }

}
