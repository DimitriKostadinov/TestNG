package org.example;
import com.apachepoiexcelfiles.CreateWriteReadExcel;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CreateWriteReadExcel obj = new CreateWriteReadExcel();
        //obj.createExcel();
        obj.ReadExcel();
    }
}