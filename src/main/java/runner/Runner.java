package runner;

import process.Work;

import java.io.*;

public class Runner {
    public void beginProgramm() throws FileNotFoundException {
        process.Work beg = new Work();
        beg.readDir();
    }
}
