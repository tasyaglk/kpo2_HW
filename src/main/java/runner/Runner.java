package runner;

import process.ReadDir;

import java.io.*;

public class Runner {
    /**
     * Функция начала работы.
     */
    public final void beginProgramm() throws FileNotFoundException {
        ReadDir beg = new ReadDir();
        beg.readDir();
    }
}
