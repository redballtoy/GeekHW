package com.example.redballtoy.calculator_hw_03;

public class CalcCore {
    private String result = "";
    private String keyName;
    private String inMemory;
    private String memoryLabel;
    private boolean hasDoublePrecission;
    private final StringBuilder stringBuilder = new StringBuilder();


    private void calculateResult() {
        switch (keyName) {
            case "C":
                inMemory = "";
                setMemoryLabel("");
                clearStringuilder();
                break;
            case "m+":
                inMemory = stringBuilder.toString();
                clearStringuilder();
                setMemoryLabel("M");
                break;
            case "m-":
                stringBuilder.append(inMemory);
                setMemoryLabel("M");
                break;
            case "mc":
                inMemory = "";
                setMemoryLabel("");
                break;
            case "=":
                stringBuilder.append("=");
                stringBuilder.append("результат расчета");
                break;
            case "<":
                clearStringuilder();
                int lenghtResult = result.length();
                if (lenghtResult > 0) {
                    stringBuilder.append(result.substring(0, lenghtResult - 1));
                }
                break;
            default:

                stringBuilder.append(keyName);
        }


    }

    private void clearStringuilder() {
        stringBuilder.delete(0, stringBuilder.length());
        //stringBuilder.append("0");
    }

    public String getResult() {
        calculateResult();
        return stringBuilder.toString();
    }

    public void setHasDoublePrecission(boolean hasDoublePrecission) {
        this.hasDoublePrecission = hasDoublePrecission;
    }

    public void setResult(String result) {
        this.result = result;

    }


    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getMemoryLabel() {
        return memoryLabel;
    }

    public void setMemoryLabel(String memoryLabel) {
        this.memoryLabel = memoryLabel;
    }
}
