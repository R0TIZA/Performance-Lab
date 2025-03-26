package entity;

import java.util.List;

public class Tests implements Cloneable {
    List<Test> tests;

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tests [\n");
        for (Test test : tests) {
            sb.append(test.toString()).append(", \n");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
