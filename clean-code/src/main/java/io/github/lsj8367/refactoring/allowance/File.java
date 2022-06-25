package io.github.lsj8367.refactoring.allowance;

public class File {

    String prevFileVersionStatus;

    public String getPrevFileVersionStatus() {
        return prevFileVersionStatus;
    }

    public void setPrevFileVersionStatus(String prevFileVersionStatus) {
        this.prevFileVersionStatus = prevFileVersionStatus;
    }

    public boolean isFileClosed() {
        return Constants.LEVEL_D2.equals(prevFileVersionStatus);
    }

}
