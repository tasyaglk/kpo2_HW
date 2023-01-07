package runner;

import process.ReadDir;

import java.io.*;

public class Runner {
    public void beginProgramm() throws FileNotFoundException {
        ReadDir beg = new ReadDir();
        beg.readDir();
    }
}
