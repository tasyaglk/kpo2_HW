package process;

import checkErrors.CheckErrors;
import output.Output;

public class Work {

    public void work() {
        CheckErrors ifErrors = new CheckErrors();
        ifErrors.allFilesCorrect();
        ifErrors.ifCycleCheck();
        ifErrors.ifEmpty();
        BuildingSequence build = new BuildingSequence();
        build.findSeq();
        Output newFile = new Output();
        newFile.genNewFile();
    }
}
